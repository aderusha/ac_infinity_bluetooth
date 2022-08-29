package com.eternal.framework.bus;

import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Messenger {
    private static Messenger defaultInstance;
    private HashMap<Type, List<WeakActionAndToken>> recipientsOfSubclassesAction;
    private HashMap<Type, List<WeakActionAndToken>> recipientsStrictAction;

    public static class NotMsgType {
    }

    public static Messenger getDefault() {
        if (defaultInstance == null) {
            defaultInstance = new Messenger();
        }
        return defaultInstance;
    }

    public static void overrideDefault(Messenger messenger) {
        defaultInstance = messenger;
    }

    public static void reset() {
        defaultInstance = null;
    }

    public void register(Object obj, BindingAction bindingAction) {
        register(obj, (Object) null, false, bindingAction);
    }

    public void register(Object obj, boolean z, BindingAction bindingAction) {
        register(obj, (Object) null, z, bindingAction);
    }

    public void register(Object obj, Object obj2, BindingAction bindingAction) {
        register(obj, obj2, false, bindingAction);
    }

    public void register(Object obj, Object obj2, boolean z, BindingAction bindingAction) {
        HashMap<Type, List<WeakActionAndToken>> hashMap;
        List list;
        Class<NotMsgType> cls = NotMsgType.class;
        if (z) {
            if (this.recipientsOfSubclassesAction == null) {
                this.recipientsOfSubclassesAction = new HashMap<>();
            }
            hashMap = this.recipientsOfSubclassesAction;
        } else {
            if (this.recipientsStrictAction == null) {
                this.recipientsStrictAction = new HashMap<>();
            }
            hashMap = this.recipientsStrictAction;
        }
        if (!hashMap.containsKey(cls)) {
            list = new ArrayList();
            hashMap.put(cls, list);
        } else {
            list = hashMap.get(cls);
        }
        list.add(new WeakActionAndToken(new WeakAction(obj, bindingAction), obj2));
        cleanup();
    }

    public <T> void register(Object obj, Class<T> cls, BindingConsumer<T> bindingConsumer) {
        register(obj, (Object) null, false, bindingConsumer, cls);
    }

    public <T> void register(Object obj, boolean z, Class<T> cls, BindingConsumer<T> bindingConsumer) {
        register(obj, (Object) null, z, bindingConsumer, cls);
    }

    public <T> void register(Object obj, Object obj2, Class<T> cls, BindingConsumer<T> bindingConsumer) {
        register(obj, obj2, false, bindingConsumer, cls);
    }

    public <T> void register(Object obj, Object obj2, boolean z, BindingConsumer<T> bindingConsumer, Class<T> cls) {
        HashMap<Type, List<WeakActionAndToken>> hashMap;
        List list;
        if (z) {
            if (this.recipientsOfSubclassesAction == null) {
                this.recipientsOfSubclassesAction = new HashMap<>();
            }
            hashMap = this.recipientsOfSubclassesAction;
        } else {
            if (this.recipientsStrictAction == null) {
                this.recipientsStrictAction = new HashMap<>();
            }
            hashMap = this.recipientsStrictAction;
        }
        if (!hashMap.containsKey(cls)) {
            list = new ArrayList();
            hashMap.put(cls, list);
        } else {
            list = hashMap.get(cls);
        }
        list.add(new WeakActionAndToken(new WeakAction(obj, bindingConsumer), obj2));
        cleanup();
    }

    private void cleanup() {
        cleanupList(this.recipientsOfSubclassesAction);
        cleanupList(this.recipientsStrictAction);
    }

    public void sendNoMsg(Object obj) {
        sendToTargetOrType((Type) null, obj);
    }

    public void sendNoMsgToTarget(Object obj) {
        sendToTargetOrType(obj.getClass(), (Object) null);
    }

    public void sendNoMsgToTargetWithToken(Object obj, Object obj2) {
        sendToTargetOrType(obj2.getClass(), obj);
    }

    public <T> void send(T t) {
        sendToTargetOrType(t, (Type) null, (Object) null);
    }

    public <T> void send(T t, Object obj) {
        sendToTargetOrType(t, (Type) null, obj);
    }

    public <T, R> void sendToTarget(T t, R r) {
        sendToTargetOrType(t, r.getClass(), (Object) null);
    }

    public void unregister(Object obj) {
        unregisterFromLists(obj, this.recipientsOfSubclassesAction);
        unregisterFromLists(obj, this.recipientsStrictAction);
        cleanup();
    }

    public <T> void unregister(Object obj, Object obj2) {
        unregisterFromLists(obj, obj2, (BindingAction) null, this.recipientsStrictAction);
        unregisterFromLists(obj, obj2, (BindingAction) null, this.recipientsOfSubclassesAction);
        cleanup();
    }

    private static <T> void sendToList(T t, Collection<WeakActionAndToken> collection, Type type, Object obj) {
        if (collection != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(collection);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                WeakActionAndToken weakActionAndToken = (WeakActionAndToken) it.next();
                WeakAction action = weakActionAndToken.getAction();
                if (!(action == null || !weakActionAndToken.getAction().isLive() || weakActionAndToken.getAction().getTarget() == null)) {
                    if ((type == null || weakActionAndToken.getAction().getTarget().getClass() == type || classImplements(weakActionAndToken.getAction().getTarget().getClass(), type)) && ((weakActionAndToken.getToken() == null && obj == null) || (weakActionAndToken.getToken() != null && weakActionAndToken.getToken().equals(obj)))) {
                        action.execute(t);
                    }
                }
            }
        }
    }

    private static void unregisterFromLists(Object obj, HashMap<Type, List<WeakActionAndToken>> hashMap) {
        if (obj != null && hashMap != null && hashMap.size() != 0) {
            synchronized (hashMap) {
                for (Type type : hashMap.keySet()) {
                    for (WeakActionAndToken action : hashMap.get(type)) {
                        WeakAction action2 = action.getAction();
                        if (action2 != null && obj == action2.getTarget()) {
                            action2.markForDeletion();
                        }
                    }
                }
            }
            cleanupList(hashMap);
        }
    }

    private static <T> void unregisterFromLists(Object obj, BindingConsumer<T> bindingConsumer, HashMap<Type, List<WeakActionAndToken>> hashMap, Class<T> cls) {
        if (obj != null && hashMap != null && hashMap.size() != 0 && hashMap.containsKey(cls)) {
            synchronized (hashMap) {
                for (WeakActionAndToken weakActionAndToken : hashMap.get(cls)) {
                    WeakAction action = weakActionAndToken.getAction();
                    if (action != null && obj == action.getTarget()) {
                        if (bindingConsumer == null || bindingConsumer == action.getBindingConsumer()) {
                            weakActionAndToken.getAction().markForDeletion();
                        }
                    }
                }
            }
        }
    }

    private static void unregisterFromLists(Object obj, BindingAction bindingAction, HashMap<Type, List<WeakActionAndToken>> hashMap) {
        Class<NotMsgType> cls = NotMsgType.class;
        if (obj != null && hashMap != null && hashMap.size() != 0 && hashMap.containsKey(cls)) {
            synchronized (hashMap) {
                for (WeakActionAndToken weakActionAndToken : hashMap.get(cls)) {
                    WeakAction action = weakActionAndToken.getAction();
                    if (action != null && obj == action.getTarget()) {
                        if (bindingAction == null || bindingAction == action.getBindingAction()) {
                            weakActionAndToken.getAction().markForDeletion();
                        }
                    }
                }
            }
        }
    }

    private static <T> void unregisterFromLists(Object obj, Object obj2, BindingConsumer<T> bindingConsumer, HashMap<Type, List<WeakActionAndToken>> hashMap, Class<T> cls) {
        if (obj != null && hashMap != null && hashMap.size() != 0 && hashMap.containsKey(cls)) {
            synchronized (hashMap) {
                for (WeakActionAndToken weakActionAndToken : hashMap.get(cls)) {
                    WeakAction action = weakActionAndToken.getAction();
                    if (action != null && obj == action.getTarget()) {
                        if ((bindingConsumer == null || bindingConsumer == action.getBindingConsumer()) && (obj2 == null || obj2.equals(weakActionAndToken.getToken()))) {
                            weakActionAndToken.getAction().markForDeletion();
                        }
                    }
                }
            }
        }
    }

    private static void unregisterFromLists(Object obj, Object obj2, BindingAction bindingAction, HashMap<Type, List<WeakActionAndToken>> hashMap) {
        Class<NotMsgType> cls = NotMsgType.class;
        if (obj != null && hashMap != null && hashMap.size() != 0 && hashMap.containsKey(cls)) {
            synchronized (hashMap) {
                for (WeakActionAndToken weakActionAndToken : hashMap.get(cls)) {
                    WeakAction action = weakActionAndToken.getAction();
                    if (action != null && obj == action.getTarget()) {
                        if ((bindingAction == null || bindingAction == action.getBindingAction()) && (obj2 == null || obj2.equals(weakActionAndToken.getToken()))) {
                            weakActionAndToken.getAction().markForDeletion();
                        }
                    }
                }
            }
        }
    }

    private static boolean classImplements(Type type, Type type2) {
        if (!(type2 == null || type == null)) {
            for (Class cls : ((Class) type).getInterfaces()) {
                if (cls == type2) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.util.HashMap<java.lang.reflect.Type, java.util.List<com.eternal.framework.bus.Messenger$WeakActionAndToken>>, java.util.HashMap] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void cleanupList(java.util.HashMap<java.lang.reflect.Type, java.util.List<com.eternal.framework.bus.Messenger.WeakActionAndToken>> r6) {
        /*
            if (r6 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.Set r0 = r6.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = r0.next()
            java.lang.Object r2 = r6.get(r1)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x000b
            java.util.Iterator r3 = r2.iterator()
        L_0x0021:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0041
            java.lang.Object r4 = r3.next()
            com.eternal.framework.bus.Messenger$WeakActionAndToken r4 = (com.eternal.framework.bus.Messenger.WeakActionAndToken) r4
            com.eternal.framework.bus.WeakAction r5 = r4.getAction()
            if (r5 == 0) goto L_0x003d
            com.eternal.framework.bus.WeakAction r5 = r4.getAction()
            boolean r5 = r5.isLive()
            if (r5 != 0) goto L_0x0021
        L_0x003d:
            r2.remove(r4)
            goto L_0x0021
        L_0x0041:
            int r2 = r2.size()
            if (r2 != 0) goto L_0x000b
            r6.remove(r1)
            goto L_0x000b
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.bus.Messenger.cleanupList(java.util.HashMap):void");
    }

    private void sendToTargetOrType(Type type, Object obj) {
        Class<NotMsgType> cls = NotMsgType.class;
        if (this.recipientsOfSubclassesAction != null) {
            ArrayList<Type> arrayList = new ArrayList<>();
            arrayList.addAll(this.recipientsOfSubclassesAction.keySet());
            for (Type type2 : arrayList) {
                List list = null;
                if (cls == type2 || ((Class) type2).isAssignableFrom(cls) || classImplements(cls, type2)) {
                    list = this.recipientsOfSubclassesAction.get(type2);
                }
                sendToList(list, type, obj);
            }
        }
        HashMap<Type, List<WeakActionAndToken>> hashMap = this.recipientsStrictAction;
        if (hashMap != null && hashMap.containsKey(cls)) {
            sendToList(this.recipientsStrictAction.get(cls), type, obj);
        }
        cleanup();
    }

    private static void sendToList(Collection<WeakActionAndToken> collection, Type type, Object obj) {
        if (collection != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(collection);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                WeakActionAndToken weakActionAndToken = (WeakActionAndToken) it.next();
                WeakAction action = weakActionAndToken.getAction();
                if (!(action == null || !weakActionAndToken.getAction().isLive() || weakActionAndToken.getAction().getTarget() == null)) {
                    if ((type == null || weakActionAndToken.getAction().getTarget().getClass() == type || classImplements(weakActionAndToken.getAction().getTarget().getClass(), type)) && ((weakActionAndToken.getToken() == null && obj == null) || (weakActionAndToken.getToken() != null && weakActionAndToken.getToken().equals(obj)))) {
                        action.execute();
                    }
                }
            }
        }
    }

    private <T> void sendToTargetOrType(T t, Type type, Object obj) {
        Class<?> cls = t.getClass();
        if (this.recipientsOfSubclassesAction != null) {
            ArrayList<Type> arrayList = new ArrayList<>();
            arrayList.addAll(this.recipientsOfSubclassesAction.keySet());
            for (Type type2 : arrayList) {
                List list = null;
                if (cls == type2 || ((Class) type2).isAssignableFrom(cls) || classImplements(cls, type2)) {
                    list = this.recipientsOfSubclassesAction.get(type2);
                }
                sendToList(t, list, type, obj);
            }
        }
        HashMap<Type, List<WeakActionAndToken>> hashMap = this.recipientsStrictAction;
        if (hashMap != null && hashMap.containsKey(cls)) {
            sendToList(t, this.recipientsStrictAction.get(cls), type, obj);
        }
        cleanup();
    }

    private class WeakActionAndToken {
        private WeakAction action;
        private Object token;

        public WeakActionAndToken(WeakAction weakAction, Object obj) {
            this.action = weakAction;
            this.token = obj;
        }

        public WeakAction getAction() {
            return this.action;
        }

        public void setAction(WeakAction weakAction) {
            this.action = weakAction;
        }

        public Object getToken() {
            return this.token;
        }

        public void setToken(Object obj) {
            this.token = obj;
        }
    }
}
