package com.eternal.advance.model;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import com.eternal.advance.C1202R;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.ByteUtils;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Locale;

public class ItemChildModelV4 {
    public ObservableBoolean advActive = new ObservableBoolean();
    public Observable.OnPropertyChangedCallback callback;
    public ObservableField<String> desc = new ObservableField<>();
    private DeviceModel deviceModel;
    private byte deviceType;
    private byte deviceVersion;
    public ObservableField<String> info = new ObservableField<>();
    private String mac;
    public Notification model;
    private String name;
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ItemChildModelV4.this, "delete item");
        }
    });
    public BindingCommand<Void> onEdit = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ItemChildModelV4.this, "edit item");
        }
    });
    public ObservableBoolean open = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableInt typeRes = new ObservableInt();

    ItemChildModelV4(Notification notification, DeviceModel deviceModel2, byte b, byte b2) {
        this.deviceModel = deviceModel2;
        this.mac = notification.mac;
        this.name = notification.name;
        this.deviceType = b;
        this.deviceVersion = b2;
        this.open.set(notification.open);
        C12963 r4 = new Observable.OnPropertyChangedCallback() {
            public void onPropertyChanged(Observable observable, int i) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(ItemChildModelV4.this.model.mac)) {
                    Messenger.getDefault().send(ItemChildModelV4.this, "open item");
                    ItemChildModelV4 itemChildModelV4 = ItemChildModelV4.this;
                    itemChildModelV4.setOpen(itemChildModelV4.model.open);
                    return;
                }
                ItemChildModelV4.this.model.open = ItemChildModelV4.this.open.get();
                if (!ItemChildModelV4.this.model.open) {
                    ItemChildModelV4.this.advActive.set(false);
                }
                Messenger.getDefault().send(ItemChildModelV4.this, "open item");
            }
        };
        this.callback = r4;
        this.open.addOnPropertyChangedCallback(r4);
        update(notification, false);
        this.title.set(notification.name);
    }

    public void update(Notification notification, boolean z) {
        if (!z) {
            notification.name = this.name;
            notification.mac = this.mac;
        }
        this.model = notification;
        this.title.set(notification.name);
        if (notification.type != 1) {
            this.typeRes.set(C1202R.C1204drawable.advance_type_rc_alarm_selector);
        } else {
            this.typeRes.set(C1202R.C1204drawable.advance_type_rc_automations_selector);
        }
        this.info.set(getInfo(notification, this.deviceModel));
        this.desc.set(getDesc(notification));
        if (notification.open != this.open.get()) {
            setOpen(notification.open);
        }
        this.advActive.set(notification.advActive);
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
            if (this.deviceVersion < 4 || !ByteUtils.getBit(notification.day, 0)) {
                sb.append("Everyday");
            } else {
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("Su, ");
                }
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("S, ");
                }
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("F, ");
                }
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("T, ");
                }
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("W, ");
                }
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("Tu, ");
                }
                if (!ByteUtils.getBit(notification.day, 1)) {
                    sb.append("M, ");
                }
                if (sb.length() > 1) {
                    sb.setLength(sb.length() - 2);
                }
            }
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
        } else if (this.deviceVersion >= 0) {
            if (notification.type == 2) {
                if (notification.beeps == -1) {
                    sb.append("Continuous Alarm");
                } else if (notification.beeps != 0) {
                    sb.append(notification.beeps);
                    sb.append(" Beeps Alarm");
                }
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
        String str;
        String str2;
        byte b;
        byte b2;
        String str3;
        String str4;
        String str5;
        byte b3;
        byte b4;
        byte b5;
        byte b6;
        Notification notification2 = notification;
        DeviceModel deviceModel3 = deviceModel2;
        if (notification2 == null || deviceModel3 == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        byte b7 = notification2.type;
        String str6 = ProtocolTransformer.DEGREE;
        if (b7 == 1) {
            String str7 = " ";
            if (this.deviceType == 6 || (notification2.start == 65535 && notification2.end == 65535)) {
                sb.append("All Time ");
            } else {
                sb.append(stringForTimeBySched(notification2.start));
                sb.append(" - ");
                sb.append(stringForTimeBySched(notification2.end));
                sb.append(str7);
            }
            sb.append("• ");
            byte b8 = notification2.model;
            if (b8 == 1) {
                sb.append("ON");
            } else if (b8 == 2) {
                sb.append("OFF");
            } else if (b8 == 3) {
                sb.append("CYCLE");
                sb.append(String.format("\n• %s TO ON / %s TO OFF", new Object[]{stringForTimeByCycleSpace(notification2.cycleOn), stringForTimeByCycleSpace(notification2.cycleOff)}));
            } else if (b8 != 5) {
                sb.append("AUTO");
                if (ProtocolTransformer.checkTmpHum(notification2.tmpHum)) {
                    if (deviceModel3.isDegree) {
                        b6 = notification2.hTmpC;
                        b5 = notification2.lTmpC;
                    } else {
                        int i = UnsignedBytes.toInt(notification2.hTmpF);
                        b5 = UnsignedBytes.toInt(notification2.lTmpF);
                        str6 = ProtocolTransformer.FAH;
                        b6 = i;
                    }
                    initTmpHum(sb, str6, b6, b5, notification2.hHum, notification2.lHum, notification2.tmpHum);
                    str7 = str7;
                }
            } else {
                sb.append("AUTO");
                if (ProtocolTransformer.checkTmpHum(notification2.tmpHum)) {
                    sb.append("\n• ");
                    initVpd(sb, notification2.hVpd, notification2.lVpd, (notification2.tmpHum & 2) != 0, (notification2.tmpHum & 1) != 0);
                }
            }
            if (this.deviceVersion >= 4) {
                sb.append("\nPort: ");
                for (int i2 = 0; i2 < 8; i2++) {
                    if (!ByteUtils.getBit(notification2.port, i2)) {
                        sb.append(8 - i2);
                        sb.append(str7);
                    }
                }
            }
        } else {
            if (this.deviceVersion >= 4) {
                if (!ProtocolTransformer.tempParamModelV4(notification2.model) || !ProtocolTransformer.checkTmpHum(notification2.tmpHum)) {
                    str4 = "\n";
                    str5 = "\nMODES ";
                    str3 = "VPD ";
                } else {
                    sb.append("\n");
                    if (deviceModel3.isDegree) {
                        b4 = notification2.hTmpC;
                        b3 = notification2.lTmpC;
                    } else {
                        int i3 = UnsignedBytes.toInt(notification2.hTmpF);
                        b3 = UnsignedBytes.toInt(notification2.lTmpF);
                        str6 = ProtocolTransformer.FAH;
                        b4 = i3;
                    }
                    str4 = "\n";
                    str3 = "VPD ";
                    str5 = "\nMODES ";
                    initAlarmTmpHum(sb, str6, b4, b3, notification2.hHum, notification2.lHum, (notification2.tmpHum & 2) != 0, (notification2.tmpHum & 1) != 0, false, false);
                }
                if (ProtocolTransformer.humParamModelV4(notification2.model) && ProtocolTransformer.checkTmpHum(notification2.tmpHum)) {
                    sb.append(str4);
                    initAlarmTmpHum(sb, "", 0, 0, notification2.hHum, notification2.lHum, false, false, (notification2.tmpHum & 2) != 0, (notification2.tmpHum & 1) != 0);
                }
                if (ProtocolTransformer.vpdParamV4(notification2.model) && ProtocolTransformer.checkTmpHum(notification2.tmpHum)) {
                    sb.append(str4);
                    sb.append(str3);
                    initVpd(sb, notification2.hVpd, notification2.lVpd, (notification2.tmpHum & 2) != 0, (notification2.tmpHum & 1) != 0);
                }
                if (ProtocolTransformer.isModelV4(notification2.model)) {
                    sb.append(str5);
                }
            } else {
                String str8 = "\n";
                String str9 = "\nMODES ";
                String str10 = "VPD ";
                if (!ProtocolTransformer.paramModel(notification2.model) || !ProtocolTransformer.checkTmpHum(notification2.tmpHum)) {
                    str2 = str9;
                    str = str10;
                } else {
                    sb.append(str8);
                    if (deviceModel3.isDegree) {
                        b2 = notification2.hTmpC;
                        b = notification2.lTmpC;
                    } else {
                        int i4 = UnsignedBytes.toInt(notification2.hTmpF);
                        b = UnsignedBytes.toInt(notification2.lTmpF);
                        str6 = ProtocolTransformer.FAH;
                        b2 = i4;
                    }
                    str2 = str9;
                    str = str10;
                    initAlarmTmpHum(sb, str6, b2, b, notification2.hHum, notification2.lHum, (notification2.tmpHum & 8) != 0, (notification2.tmpHum & 4) != 0, (notification2.tmpHum & 2) != 0, (notification2.tmpHum & 1) != 0);
                }
                if (ProtocolTransformer.vpdParam(notification2.model) && ProtocolTransformer.checkVpd(notification2.tmpHum)) {
                    sb.append(str8);
                    sb.append(str);
                    initVpd(sb, notification2.hVpd, notification2.lVpd, (notification2.tmpHum & 32) != 0, (notification2.tmpHum & Ascii.DLE) != 0);
                }
                if (ProtocolTransformer.isModel(notification2.model)) {
                    sb.append(str2);
                    if (!ProtocolTransformer.allModel(notification2.model)) {
                        if (ProtocolTransformer.autoModel(notification2.model)) {
                            sb.append("AUTO / ");
                        }
                        if (ProtocolTransformer.onModel(notification2.model)) {
                            sb.append("TIMER TO ON / ");
                        }
                        if (ProtocolTransformer.offModel(notification2.model)) {
                            sb.append("TIMER TO OFF / ");
                        }
                        if (ProtocolTransformer.cycleModel(notification2.model)) {
                            sb.append("CYCLE / ");
                        }
                        if (ProtocolTransformer.scheduleModel(notification2.model)) {
                            sb.append("SCHEDULE / ");
                        }
                        if (ProtocolTransformer.vpdModel(notification2.model)) {
                            sb.append("VPD / ");
                        }
                        sb.setLength(sb.length() - 3);
                    }
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

    private void initAlarmTmpHum(StringBuilder sb, String str, int i, int i2, byte b, byte b2, boolean z, boolean z2, boolean z3, boolean z4) {
        if ((!z && !z2) || (!z3 && !z4)) {
            if (z || z2) {
                sb.append("TEMP ");
            } else {
                sb.append("HUM ");
            }
        }
        if (z) {
            sb.append("H ");
            sb.append(i);
            sb.append(str);
            sb.append(" / ");
        }
        if (z2) {
            sb.append("L ");
            sb.append(i2);
            sb.append(str);
            sb.append(" / ");
        }
        if (z3) {
            sb.append("H ");
            sb.append(b);
            sb.append("%");
            sb.append(" / ");
        }
        if (z4) {
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
