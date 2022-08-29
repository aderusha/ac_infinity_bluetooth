package com.eternal.base.data.source;

import com.eternal.base.concat.DeviceModelInfo;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.database.BaseDatabase;
import com.eternal.base.database.dao.DeviceDao;
import com.eternal.base.database.dao.NotificationDao;
import com.eternal.base.database.entity.Notification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationSource implements INotificationSource {
    private static final String DEFAULT_ALARMS = "Alarms";
    private static final String DEFAULT_ALERTS = "Alerts";
    private static final String DEFAULT_AUTOMATIC_NAME = "Automations";
    private static final String DEFAULT_NOTIFICATIONS = "Notifications";
    private DeviceDao dDao = BaseDatabase.getInstance().deviceDao();
    private NotificationDao nDao = BaseDatabase.getInstance().notificationDao();

    public List<NotificationName> getNotificationNames(String str, byte b) {
        return null;
    }

    public void setNotification(Notification notification) {
        this.nDao.update(notification);
    }

    public void addNotification(Notification notification) {
        this.nDao.insertNotification(notification);
    }

    public List<Notification> getNotifications(String str, byte b) {
        return this.nDao.loadMacAll(str, b);
    }

    public List<Notification> getNotifications(String str) {
        return this.nDao.loadMacAll(str);
    }

    public void deleteNotification(String str, byte b, int i, byte b2) {
        this.nDao.deleteNotification(str, b, i, b2);
    }

    public void deleteNotification(String str, byte b, int i, byte b2, int i2) {
        this.nDao.deleteNotification(str, b, i, b2);
    }

    public void setNotificationOpen(String str, byte b, int i, byte b2, boolean z) {
        this.nDao.updateOpen(str, b, i, b2, z);
    }

    public Notification getNotification(String str, byte b, int i, byte b2) {
        return this.nDao.loadByMacAndId(str, b, i, b2);
    }

    public DeviceModelInfo getModelInfo(String str) {
        return this.dDao.loadModelInfo(str);
    }

    public Boolean nameExists(String str, byte b, String str2, byte b2) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (b2 == 1) {
            arrayList.add((byte) 1);
        } else {
            arrayList.addAll(Arrays.asList(new Byte[]{(byte) 3, (byte) 2}));
        }
        if (this.nDao.loadNameByMac(str, b, str2, arrayList) != null) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(com.eternal.base.database.entity.Notification r13, boolean r14, byte r15) {
        /*
            r12 = this;
            r0 = 0
            r1 = 1
            r2 = 5
            if (r15 < r2) goto L_0x014c
            com.eternal.base.database.dao.NotificationDao r15 = r12.nDao
            java.lang.String r2 = r13.mac
            java.util.List r15 = r15.loadMacAllName(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = 0
            byte r7 = r13.type
            if (r7 != r1) goto L_0x009a
            java.util.Iterator r7 = r15.iterator()
            r8 = 0
        L_0x002b:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x00c5
            java.lang.Object r9 = r7.next()
            com.eternal.base.concat.NotificationName r9 = (com.eternal.base.concat.NotificationName) r9
            byte r10 = r9.type
            if (r10 != r1) goto L_0x002b
            int r10 = r9.f137id
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            boolean r10 = r2.contains(r10)
            if (r10 != 0) goto L_0x0050
            int r10 = r9.f137id
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r2.add(r10)
        L_0x0050:
            byte r10 = r9.groupIndex
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            boolean r10 = r3.contains(r10)
            if (r10 != 0) goto L_0x0065
            byte r10 = r9.groupIndex
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            r3.add(r10)
        L_0x0065:
            int r10 = r13.f144id
            int r11 = r9.f137id
            if (r10 != r11) goto L_0x002b
            java.lang.String r6 = r9.name
            byte r8 = r9.groupIndex
            byte r10 = r9.childId
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            boolean r10 = r4.contains(r10)
            if (r10 != 0) goto L_0x0084
            byte r10 = r9.childId
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            r4.add(r10)
        L_0x0084:
            byte r10 = r9.childIndex
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            boolean r10 = r5.contains(r10)
            if (r10 != 0) goto L_0x002b
            byte r9 = r9.childIndex
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)
            r5.add(r9)
            goto L_0x002b
        L_0x009a:
            java.util.Iterator r7 = r15.iterator()
        L_0x009e:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00c4
            java.lang.Object r8 = r7.next()
            com.eternal.base.concat.NotificationName r8 = (com.eternal.base.concat.NotificationName) r8
            byte r9 = r8.type
            if (r9 == r1) goto L_0x009e
            int r9 = r8.f137id
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            boolean r9 = r2.contains(r9)
            if (r9 != 0) goto L_0x009e
            int r8 = r8.f137id
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r2.add(r8)
            goto L_0x009e
        L_0x00c4:
            r8 = 0
        L_0x00c5:
            int r1 = r13.f144id
            r7 = -1
            if (r1 != r7) goto L_0x0101
            java.util.Collections.sort(r2)
            r1 = 0
        L_0x00ce:
            int r7 = r2.size()
            if (r1 >= r7) goto L_0x00e4
            java.lang.Object r7 = r2.get(r1)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r7 == r1) goto L_0x00e1
            goto L_0x00e4
        L_0x00e1:
            int r1 = r1 + 1
            goto L_0x00ce
        L_0x00e4:
            r13.f144id = r1
            java.util.Collections.sort(r3)
            r8 = 0
        L_0x00ea:
            int r1 = r3.size()
            if (r8 >= r1) goto L_0x0101
            java.lang.Object r1 = r3.get(r8)
            java.lang.Byte r1 = (java.lang.Byte) r1
            byte r1 = r1.byteValue()
            if (r1 == r8) goto L_0x00fd
            goto L_0x0101
        L_0x00fd:
            int r8 = r8 + 1
            byte r8 = (byte) r8
            goto L_0x00ea
        L_0x0101:
            r13.groupIndex = r8
            java.util.Collections.sort(r4)
            r1 = 0
        L_0x0107:
            int r2 = r4.size()
            if (r1 >= r2) goto L_0x011e
            java.lang.Object r2 = r4.get(r1)
            java.lang.Byte r2 = (java.lang.Byte) r2
            byte r2 = r2.byteValue()
            if (r2 == r1) goto L_0x011a
            goto L_0x011e
        L_0x011a:
            int r1 = r1 + 1
            byte r1 = (byte) r1
            goto L_0x0107
        L_0x011e:
            r13.childId = r1
            java.util.Collections.sort(r5)
        L_0x0123:
            int r1 = r5.size()
            if (r0 >= r1) goto L_0x013a
            java.lang.Object r1 = r5.get(r0)
            java.lang.Byte r1 = (java.lang.Byte) r1
            byte r1 = r1.byteValue()
            if (r1 == r0) goto L_0x0136
            goto L_0x013a
        L_0x0136:
            int r0 = r0 + 1
            byte r0 = (byte) r0
            goto L_0x0123
        L_0x013a:
            r13.childIndex = r0
            if (r14 == 0) goto L_0x01dd
            java.lang.String r14 = r13.name
            if (r14 != 0) goto L_0x01dd
            if (r6 != 0) goto L_0x0148
            java.lang.String r6 = r12.getDefaultName(r13, r15)
        L_0x0148:
            r13.name = r6
            goto L_0x01dd
        L_0x014c:
            com.eternal.base.database.dao.NotificationDao r15 = r12.nDao
            java.lang.String r2 = r13.mac
            byte r3 = r13.port
            java.util.List r15 = r15.loadMacAllName(r2, r3)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            if (r14 == 0) goto L_0x0167
            java.lang.String r14 = r13.name
            if (r14 != 0) goto L_0x0167
            java.lang.String r14 = r12.getDefaultName(r13, r15)
            r13.name = r14
        L_0x0167:
            byte r14 = r13.type
            if (r14 != r1) goto L_0x0195
            java.util.Iterator r14 = r15.iterator()
        L_0x016f:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x01bf
            java.lang.Object r15 = r14.next()
            com.eternal.base.concat.NotificationName r15 = (com.eternal.base.concat.NotificationName) r15
            byte r3 = r15.type
            if (r3 != r1) goto L_0x016f
            int r3 = r15.f137id
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto L_0x016f
            int r15 = r15.f137id
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r2.add(r15)
            goto L_0x016f
        L_0x0195:
            java.util.Iterator r14 = r15.iterator()
        L_0x0199:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x01bf
            java.lang.Object r15 = r14.next()
            com.eternal.base.concat.NotificationName r15 = (com.eternal.base.concat.NotificationName) r15
            byte r3 = r15.type
            if (r3 == r1) goto L_0x0199
            int r3 = r15.f137id
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto L_0x0199
            int r15 = r15.f137id
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r2.add(r15)
            goto L_0x0199
        L_0x01bf:
            java.util.Collections.sort(r2)
        L_0x01c2:
            int r14 = r2.size()
            if (r0 >= r14) goto L_0x01d8
            java.lang.Object r14 = r2.get(r0)
            java.lang.Integer r14 = (java.lang.Integer) r14
            int r14 = r14.intValue()
            if (r14 == r0) goto L_0x01d5
            goto L_0x01d8
        L_0x01d5:
            int r0 = r0 + 1
            goto L_0x01c2
        L_0x01d8:
            r13.f144id = r0
            byte r14 = (byte) r0
            r13.groupIndex = r14
        L_0x01dd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.base.data.source.NotificationSource.create(com.eternal.base.database.entity.Notification, boolean, byte):void");
    }

    private String getDefaultName(Notification notification, List<NotificationName> list) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (notification.type == 1) {
            str = DEFAULT_AUTOMATIC_NAME;
            for (NotificationName next : list) {
                if (next.type == 1) {
                    MinNumberUtil.getNumber(arrayList, str, next.name);
                }
            }
        } else {
            str = DEFAULT_ALERTS;
            for (NotificationName next2 : list) {
                if (next2.type != 1) {
                    MinNumberUtil.getNumber(arrayList, str, next2.name);
                }
            }
        }
        int minNumberZero = MinNumberUtil.minNumberZero(arrayList);
        if (minNumberZero == 0) {
            return str;
        }
        return str + " " + minNumberZero;
    }
}
