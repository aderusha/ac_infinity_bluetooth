package com.eternal.advance.model;

import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.advance.AdvanceFragmentV4;
import com.eternal.advance.C1200BR;
import com.eternal.advance.C1202R;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class ItemModelV4 {
    public ObservableBoolean advActive = new ObservableBoolean();
    public Observable.OnPropertyChangedCallback callback;
    public ObservableList<ItemChildModelV4> childList = new ObservableArrayList();
    private byte connectType;
    public ObservableField<String> desc = new ObservableField<>();
    private DeviceModel deviceModel;
    private byte deviceType;
    private byte deviceVersion;
    public ObservableField<String> info = new ObservableField<>();
    public ItemBinding<ItemChildModelV4> itemBinding = ItemBinding.m1008of(C1200BR.childV4, C1202R.layout.item_child_notification_v4);
    public ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(3, 0);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            int layoutPosition = viewHolder.getLayoutPosition();
            int layoutPosition2 = viewHolder2.getLayoutPosition();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyItemMoved(layoutPosition, layoutPosition2);
            }
            Collections.swap(ItemModelV4.this.childList, layoutPosition, layoutPosition2);
            return true;
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            super.onSelectedChanged(viewHolder, i);
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            Messenger.getDefault().send(ItemModelV4.this, AdvanceFragmentV4.UPDATE_CHILD_SEQUENCES);
        }
    });
    private String mac;
    public Notification model;
    private String name;
    public BindingCommand<Void> onAdd = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ItemModelV4.this, "add child item");
        }
    });
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ItemModelV4.this, "delete item");
        }
    });
    public BindingCommand<Void> onEdit = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ItemModelV4.this, "edit item");
        }
    });
    public ObservableBoolean open = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableInt typeRes = new ObservableInt();

    ItemModelV4(List<Notification> list, DeviceModel deviceModel2, byte b, byte b2) {
        this.deviceModel = deviceModel2;
        this.mac = list.get(0).mac;
        this.name = list.get(0).name;
        this.deviceType = b;
        this.deviceVersion = b2;
        this.childList.clear();
        Collections.sort(list, new Comparator<Notification>() {
            public int compare(Notification notification, Notification notification2) {
                return notification.childId - notification2.childId;
            }
        });
        this.open.set(list.get(0).open);
        C13056 r5 = new Observable.OnPropertyChangedCallback() {
            public void onPropertyChanged(Observable observable, int i) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(ItemModelV4.this.model.mac)) {
                    Messenger.getDefault().send(ItemModelV4.this, "open item");
                    ItemModelV4 itemModelV4 = ItemModelV4.this;
                    itemModelV4.setOpen(itemModelV4.model.open);
                    return;
                }
                ItemModelV4.this.model.open = ItemModelV4.this.open.get();
                if (!ItemModelV4.this.model.open) {
                    ItemModelV4.this.advActive.set(false);
                }
                Messenger.getDefault().send(ItemModelV4.this, "open item");
            }
        };
        this.callback = r5;
        this.open.addOnPropertyChangedCallback(r5);
        update(list, false);
        this.title.set(list.get(0).name);
    }

    public void update(List<Notification> list, boolean z) {
        if (!z) {
            for (Notification next : list) {
                next.name = this.name;
                next.mac = this.mac;
            }
        }
        Notification notification = list.get(0);
        this.model = notification;
        this.title.set(notification.name);
        if (this.model.type != 1) {
            this.typeRes.set(C1202R.C1204drawable.advance_type_rc_alarm_selector);
        } else {
            this.typeRes.set(C1202R.C1204drawable.advance_type_rc_automations_selector);
        }
        if (this.model.open != this.open.get()) {
            setOpen(this.model.open);
        }
        updateChild(list, z);
    }

    private void updateChild(List<Notification> list, boolean z) {
        if (this.childList.size() == list.size()) {
            for (int i = 0; i < list.size(); i++) {
                ((ItemChildModelV4) this.childList.get(i)).update(list.get(i), z);
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Notification itemChildModelV4 : list) {
            arrayList.add(new ItemChildModelV4(itemChildModelV4, this.deviceModel, this.deviceType, this.deviceVersion));
        }
        this.childList.clear();
        this.childList.addAll(arrayList);
    }

    public void updateInfo(DeviceModel deviceModel2) {
        this.info.set(getInfo(this.model, deviceModel2));
    }

    /* access modifiers changed from: package-private */
    public void setOpen(boolean z) {
        this.open.removeOnPropertyChangedCallback(this.callback);
        this.open.set(z);
        this.model.open = z;
        this.open.addOnPropertyChangedCallback(this.callback);
    }

    private String getDesc(Notification notification) {
        if (notification == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (notification.type == 1) {
            sb.append("Everyday");
            byte b = this.deviceType;
            if (b == 2 || b == 9 || b == 12 || this.deviceVersion < 2) {
                return sb.toString();
            }
            byte b2 = notification.model;
            if (b2 == 1) {
                sb.append("\nMax: ");
                sb.append(notification.levelOn);
            } else if (b2 != 2) {
                sb.append(String.format("\nMin: %s / Max: %s", new Object[]{Integer.valueOf(notification.levelOff), Integer.valueOf(notification.levelOn)}));
            }
        } else if (notification.type == 2) {
            if (ProtocolTransformer.paramModel(notification.model) || ProtocolTransformer.vpdParam(notification.model)) {
                sb.append("Continuous Alarm");
            } else if (ProtocolTransformer.isModel(notification.model)) {
                sb.append("3 Beeps Alarm");
            }
        }
        return sb.toString();
    }

    private String getInfo(Notification notification, DeviceModel deviceModel2) {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        if (notification == null || deviceModel2 == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        byte b5 = notification.type;
        String str = ProtocolTransformer.FAH;
        if (b5 == 1) {
            if (this.deviceType != 6) {
                sb.append(stringForTimeBySched(notification.start));
                sb.append(" - ");
                sb.append(stringForTimeBySched(notification.end));
                sb.append(" ");
            }
            sb.append("• ");
            byte b6 = notification.model;
            if (b6 == 1) {
                sb.append("ON");
            } else if (b6 == 2) {
                sb.append("OFF");
            } else if (b6 == 3) {
                sb.append("CYCLE");
                sb.append(String.format("\n• %s TO ON / %s TO OFF", new Object[]{stringForTimeByCycleSpace(notification.cycleOn), stringForTimeByCycleSpace(notification.cycleOff)}));
            } else if (b6 != 5) {
                sb.append("AUTO");
                if (ProtocolTransformer.checkTmpHum(notification.tmpHum)) {
                    if (deviceModel2.isDegree) {
                        b4 = notification.hTmpC;
                        b3 = notification.lTmpC;
                        str = ProtocolTransformer.DEGREE;
                    } else {
                        b4 = UnsignedBytes.toInt(notification.hTmpF);
                        b3 = UnsignedBytes.toInt(notification.lTmpF);
                    }
                    initTmpHum(sb, str, b4, b3, notification.hHum, notification.lHum, notification.tmpHum);
                }
            } else {
                sb.append("AUTO");
                if (ProtocolTransformer.checkTmpHum(notification.tmpHum)) {
                    sb.append("\n• ");
                    initVpd(sb, notification.hVpd, notification.lVpd, (notification.tmpHum & 2) != 0, (notification.tmpHum & 1) != 0);
                }
            }
        } else {
            if (ProtocolTransformer.paramModel(notification.model) && ProtocolTransformer.checkTmpHum(notification.tmpHum)) {
                sb.append("\n");
                if (deviceModel2.isDegree) {
                    b2 = notification.hTmpC;
                    b = notification.lTmpC;
                    str = ProtocolTransformer.DEGREE;
                } else {
                    b2 = UnsignedBytes.toInt(notification.hTmpF);
                    b = UnsignedBytes.toInt(notification.lTmpF);
                }
                initAlarmTmpHum(sb, str, b2, b, notification.hHum, notification.lHum, notification.tmpHum);
            }
            if (ProtocolTransformer.vpdParam(notification.model) && ProtocolTransformer.checkVpd(notification.tmpHum)) {
                sb.append("\n");
                sb.append("VPD ");
                initVpd(sb, notification.hVpd, notification.lVpd, (notification.tmpHum & 32) != 0, (notification.tmpHum & Ascii.DLE) != 0);
            }
            if (ProtocolTransformer.isModel(notification.model)) {
                sb.append("\nMODES ");
                if (!ProtocolTransformer.allModel(notification.model)) {
                    if (ProtocolTransformer.autoModel(notification.model)) {
                        sb.append("AUTO / ");
                    }
                    if (ProtocolTransformer.onModel(notification.model)) {
                        sb.append("TIMER TO ON / ");
                    }
                    if (ProtocolTransformer.offModel(notification.model)) {
                        sb.append("TIMER TO OFF / ");
                    }
                    if (ProtocolTransformer.cycleModel(notification.model)) {
                        sb.append("CYCLE / ");
                    }
                    if (ProtocolTransformer.scheduleModel(notification.model)) {
                        sb.append("SCHEDULE / ");
                    }
                    if (ProtocolTransformer.vpdModel(notification.model)) {
                        sb.append("VPD / ");
                    }
                    sb.setLength(sb.length() - 3);
                }
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(0);
            }
        }
        return sb.toString();
    }

    public String stringForTimeBySched(int i) {
        boolean z;
        Object obj;
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = 12;
        if (i3 >= 12) {
            i3 -= 12;
            z = true;
        } else {
            z = false;
        }
        if (i3 > 0 && i3 <= 12) {
            i4 = i3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i4);
        sb.append(":");
        if (i2 > 9) {
            obj = Integer.valueOf(i2);
        } else {
            obj = "0" + i2;
        }
        sb.append(obj);
        sb.append(z ? "pm" : "am");
        return sb.toString();
    }

    public static String stringForTimeByCycleSpace(int i) {
        int i2 = i % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(i / 60);
        sb.append(":");
        sb.append(i2 < 10 ? "0" : "");
        sb.append(i2);
        return sb.toString();
    }

    private void initTmpHum(StringBuilder sb, String str, int i, int i2, byte b, byte b2, int i3) {
        boolean z = false;
        boolean z2 = (i3 & 8) != 0;
        boolean z3 = (i3 & 4) != 0;
        boolean z4 = (i3 & 2) != 0;
        if ((i3 & 1) != 0) {
            z = true;
        }
        sb.append("\n• ");
        if (z2) {
            sb.append("H ");
            sb.append(i);
            sb.append(str);
        }
        if (z3) {
            if (z2) {
                sb.append(" / ");
            }
            sb.append("L ");
            sb.append(i2);
            sb.append(str);
        }
        if ((z2 || z3) && (z4 || z)) {
            sb.append(" • ");
        }
        if (z4) {
            sb.append("H ");
            sb.append(b);
            sb.append("%");
        }
        if (z) {
            if (z4) {
                sb.append(" / ");
            }
            sb.append("L ");
            sb.append(b2);
            sb.append("%");
        }
    }

    private void initAlarmTmpHum(StringBuilder sb, String str, int i, int i2, byte b, byte b2, int i3) {
        boolean z = false;
        boolean z2 = (i3 & 8) != 0;
        boolean z3 = (i3 & 4) != 0;
        boolean z4 = (i3 & 2) != 0;
        if ((i3 & 1) != 0) {
            z = true;
        }
        if ((!z2 && !z3) || (!z4 && !z)) {
            if (z2 || z3) {
                sb.append("TEMP ");
            } else {
                sb.append("HUM ");
            }
        }
        if (z2) {
            sb.append("H ");
            sb.append(i);
            sb.append(str);
            sb.append(" / ");
        }
        if (z3) {
            sb.append("L ");
            sb.append(i2);
            sb.append(str);
            sb.append(" / ");
        }
        if (z4) {
            sb.append("H ");
            sb.append(b);
            sb.append("%");
            sb.append(" / ");
        }
        if (z) {
            sb.append("L ");
            sb.append(b2);
            sb.append("%");
            sb.append(" / ");
        }
        sb.setLength(sb.length() - 3);
    }

    private void initVpd(StringBuilder sb, byte b, byte b2, boolean z, boolean z2) {
        if (z) {
            sb.append("H ");
            sb.append(String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) b) / 10.0f)}));
        }
        if (z2) {
            if (z) {
                sb.append(" / ");
            }
            sb.append("L ");
            sb.append(String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) b2) / 10.0f)}));
        }
    }

    /* access modifiers changed from: package-private */
    public Notification getNotification() {
        return this.model;
    }
}
