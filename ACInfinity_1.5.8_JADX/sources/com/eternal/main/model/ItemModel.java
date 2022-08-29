package com.eternal.main.model;

import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.LogService;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.eternal.main.C2343R;
import com.eternal.main.MainActivity;
import com.eternal.main.model.PortModel;
import com.eternal.res.C2663R;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Maybe;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class ItemModel {
    public ObservableBoolean allPortVisible = new ObservableBoolean();
    public ObservableInt bleIcon = new ObservableInt();
    public ObservableBoolean blueIconVisible = new ObservableBoolean();
    Observable.OnPropertyChangedCallback callback;
    public long connectTime;
    public byte connectType;
    private String devId;
    public ObservableField<String> device = new ObservableField<>();
    /* access modifiers changed from: private */
    public DeviceInfo deviceInfo;
    public ObservableInt distanceHum = new ObservableInt();
    public ObservableInt distanceTmp = new ObservableInt();
    public ObservableField<String> fanSize = new ObservableField<>();
    public ObservableInt fanState = new ObservableInt();
    public ObservableInt fanType = new ObservableInt();
    public ObservableField<String> fanTypeTitle = new ObservableField<>();
    public ObservableBoolean fanVisibility = new ObservableBoolean();
    private String firmwareVersion;
    private DecimalFormat format;
    private String hardwareVersion;
    public ObservableInt highHum = new ObservableInt();
    public ObservableInt highTmp = new ObservableInt();
    public ObservableInt hum = new ObservableInt();
    public ObservableBoolean humClose = new ObservableBoolean();
    public ObservableInt humColor = new ObservableInt();
    public ObservableBoolean humHighClose = new ObservableBoolean();
    public ObservableBoolean humLowClose = new ObservableBoolean();
    public ObservableField<String> humUnit = new ObservableField<>();
    public ObservableInt humUnitColor = new ObservableInt();
    public ObservableBoolean humVisibility = new ObservableBoolean();
    public ObservableBoolean isConnectWiFi = new ObservableBoolean();
    /* access modifiers changed from: private */
    public boolean isConnected;
    public ObservableBoolean isConnet = new ObservableBoolean();
    public boolean isRefreshNotify;
    public MutableLiveData<Boolean> isShare = new MutableLiveData<>();
    private boolean isUpdateComplete;
    Disposable keepAliveDisposable;
    ObservableEmitter<Boolean> keepAliveEmitter;
    private int lastNotifyType = -1;
    public ObservableInt lowHum = new ObservableInt();
    public ObservableInt lowTmp = new ObservableInt();
    /* access modifiers changed from: private */
    public String mac;
    public ObservableBoolean masterVisibility = new ObservableBoolean();
    public ObservableInt minHum = new ObservableInt();
    public ObservableInt minTmp = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ItemModel.this, "show delete dialog");
        }
    });
    public BindingCommand<Void> onDetail = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ItemModel itemModel = ItemModel.this;
            itemModel.pushToDetail(itemModel.deviceInfo.choosePort);
        }
    });
    public BindingCommand<Void> onExpand = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ItemModel.this.open.set(!ItemModel.this.open.get());
            ItemModel.this.updatePort();
        }
    });
    public ObservableBoolean open = new ObservableBoolean();
    public ObservableField<String> perSize = new ObservableField<>();
    public ObservableInt perState = new ObservableInt();
    public PortModel portModel0 = new PortModel();
    public PortModel portModel1 = new PortModel();
    public PortModel portModel2 = new PortModel();
    public PortModel portModel3 = new PortModel();
    public PortModel portModel4 = new PortModel();
    public PortModel portModel5 = new PortModel();
    public PortModel portModel6 = new PortModel();
    public PortModel portModel7 = new PortModel();
    public PortModel portModel8 = new PortModel();
    public PortModel portModel9 = new PortModel();
    public PortModel portModelAll = new PortModel();
    private List<PortModel> portModelList = new ArrayList();
    public ObservableBoolean powerOff = new ObservableBoolean();
    public ObservableBoolean powerVisibility = new ObservableBoolean();
    public ObservableBoolean rangeVisibility = new ObservableBoolean();
    public ObservableInt shareIcon = new ObservableInt();
    public ObservableBoolean showLoading = new ObservableBoolean();
    private String softwareVersion;
    public byte switchToPort;
    public ObservableField<String> time = new ObservableField<>();
    public ObservableInt timeIcon = new ObservableInt();
    public ObservableInt tmp = new ObservableInt();
    public ObservableBoolean tmpClose = new ObservableBoolean();
    public ObservableInt tmpColor = new ObservableInt();
    public ObservableField<String> tmpFlag = new ObservableField<>();
    public ObservableBoolean tmpHighClose = new ObservableBoolean();
    public ObservableBoolean tmpLowClose = new ObservableBoolean();
    public ObservableField<String> tmpSize = new ObservableField<>();
    public ObservableInt tmpState = new ObservableInt();
    public ObservableField<String> tmpUnit = new ObservableField<>();
    public ObservableInt tmpUnitColor = new ObservableInt();
    /* access modifiers changed from: private */
    public byte type;
    public MutableLiveData<Integer> typeIcon = new MutableLiveData<>();
    private byte version;
    private DecimalFormat vpdFormat;
    public ObservableField<String> vpdSize = new ObservableField<>();
    public ObservableInt vpdState = new ObservableInt();
    public ObservableBoolean vpdVisibility = new ObservableBoolean();
    public ObservableInt wifiIcon = new ObservableInt();
    public ObservableBoolean wifiIconVisible = new ObservableBoolean();

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0020 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0026 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte getAlarmType(boolean r3, boolean r4, boolean r5, int r6, int r7, int r8) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x0004
            return r0
        L_0x0004:
            r3 = 2
            r1 = 1
            if (r4 != 0) goto L_0x001c
            if (r5 != 0) goto L_0x001c
            if (r7 >= r8) goto L_0x0011
            if (r6 < r7) goto L_0x0027
            if (r6 > r8) goto L_0x0027
            goto L_0x0020
        L_0x0011:
            if (r7 <= r8) goto L_0x0019
            if (r6 < r7) goto L_0x0016
            goto L_0x0020
        L_0x0016:
            if (r6 > r8) goto L_0x0027
            goto L_0x0026
        L_0x0019:
            if (r6 != r7) goto L_0x0027
            goto L_0x0020
        L_0x001c:
            if (r4 != 0) goto L_0x0022
            if (r6 < r7) goto L_0x0027
        L_0x0020:
            r0 = 1
            goto L_0x0027
        L_0x0022:
            if (r5 != 0) goto L_0x0027
            if (r6 > r8) goto L_0x0027
        L_0x0026:
            r0 = 2
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.main.model.ItemModel.getAlarmType(boolean, boolean, boolean, int, int, int):byte");
    }

    ItemModel(DeviceInfo deviceInfo2) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
        this.vpdFormat = decimalFormat2;
        decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);
        this.deviceInfo = deviceInfo2;
        this.mac = deviceInfo2.mac;
        this.name.set(deviceInfo2.name);
        this.type = deviceInfo2.type;
        this.version = deviceInfo2.version;
        this.firmwareVersion = deviceInfo2.firmwareVersion;
        this.hardwareVersion = deviceInfo2.hardwareVersion;
        this.softwareVersion = deviceInfo2.softwareVersion;
        this.devId = deviceInfo2.deviceId;
        boolean z = false;
        this.isShare.setValue(Boolean.valueOf(deviceInfo2.isShare == 1));
        this.device.set(Utils.getString(C2343R.string.tip_name_name, ProtocolTransformer.getType(deviceInfo2.type), deviceInfo2.typeName));
        ObservableField<String> observableField = this.fanTypeTitle;
        byte b = this.type;
        observableField.set((b == 8 || b == 11 || b == 7) ? "LEVEL" : "SPEED");
        this.typeIcon.setValue(Integer.valueOf(getDeviceIcon(this.type)));
        this.isConnet.set(false);
        byte b2 = this.type;
        if (b2 == 11) {
            this.blueIconVisible.set(true);
            this.wifiIconVisible.set(true);
        } else if (b2 == 8) {
            this.blueIconVisible.set(false);
            this.wifiIconVisible.set(true);
        } else {
            this.blueIconVisible.set(true);
            this.wifiIconVisible.set(false);
        }
        this.time.set(ProtocolTransformer.getTime(deviceInfo2.connectTime));
        this.timeIcon.set(C2343R.mipmap.icon_disconect);
        this.wifiIcon.set(C2343R.mipmap.wifi_icon_grey);
        this.bleIcon.set(C2343R.mipmap.bluetooth_icon_grey);
        this.shareIcon.set(C2343R.mipmap.share_icon_grey);
        C23481 r8 = new Observable.OnPropertyChangedCallback() {
            public void onPropertyChanged(Observable observable, int i) {
            }
        };
        this.callback = r8;
        this.open.addOnPropertyChangedCallback(r8);
        this.portModelList.add(this.portModel0);
        this.portModelList.add(this.portModel1);
        this.portModelList.add(this.portModel2);
        this.portModelList.add(this.portModel3);
        this.portModelList.add(this.portModel4);
        this.portModelList.add(this.portModel5);
        this.portModelList.add(this.portModel6);
        this.portModelList.add(this.portModel7);
        this.portModelList.add(this.portModel8);
        this.portModelList.add(this.portModel9);
        for (PortModel portListener : this.portModelList) {
            setPortListener(portListener);
        }
        setPortListener(this.portModelAll);
        update();
        if (ProtocolTransformer.isDeviceMultiPort(this.type)) {
            setOpen(this.deviceInfo.choosePort == 0 ? true : z);
        } else {
            setOpen(true);
        }
        initDaemonObservable();
    }

    private void setPortListener(PortModel portModel) {
        portModel.isConnet.set(false);
        portModel.setListener(new PortModel.OnClickListener() {
            public void onClick(byte b) {
                ItemModel.this.onDetail.execute();
            }
        });
    }

    private void clickPort(byte b) {
        if (this.deviceInfo.portList != null) {
            for (PortInfo next : this.deviceInfo.portList) {
                if (next.f138id == b && next.isPlug) {
                    this.switchToPort = b;
                    byte b2 = this.type;
                    if (b2 == 8 || b2 == 11) {
                        Messenger.getDefault().send(this, "choose port net");
                    } else {
                        Messenger.getDefault().send(this, "choose port");
                    }
                    pushToDetail(b);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setOpen(boolean z) {
        this.open.set(z);
    }

    public void setTriggerNotifyTime(long j) {
        this.deviceInfo.triggerNotifyTime = j;
    }

    public long getTriggerNotifyTime() {
        return this.deviceInfo.triggerNotifyTime;
    }

    public int getLastNotifyType() {
        return this.lastNotifyType;
    }

    public void setLastNotifyType(int i) {
        this.lastNotifyType = i;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public String getDevId() {
        return this.devId;
    }

    /* access modifiers changed from: package-private */
    public void updateTFP(DeviceTFP deviceTFP) {
        this.deviceInfo.update(deviceTFP, this.type);
        update();
        this.isUpdateComplete = true;
    }

    /* access modifiers changed from: package-private */
    public void updateVersion(byte b, String str, String str2, String str3) {
        this.firmwareVersion = str;
        this.hardwareVersion = str2;
        this.softwareVersion = str3;
        this.version = b;
        this.deviceInfo.firmwareVersion = str;
        this.deviceInfo.hardwareVersion = str2;
        this.deviceInfo.softwareVersion = str3;
        this.deviceInfo.version = b;
    }

    /* access modifiers changed from: package-private */
    public void updateSoftwareVersion(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.softwareVersion = str;
            this.deviceInfo.softwareVersion = str;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLeafTemperatureC(byte b) {
        this.deviceInfo.leafTemperatureC = b;
    }

    /* access modifiers changed from: package-private */
    public void updateConnectType(byte b, String str) {
        this.deviceInfo.connectType = b;
        this.connectType = b;
        this.deviceInfo.deviceId = str;
        this.devId = str;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0431  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x044c  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x030e  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0341  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x035e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x03ce  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x03d1  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x03ee  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x03f1  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x040e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update() {
        /*
            r13 = this;
            com.eternal.base.concat.DeviceInfo r0 = r13.deviceInfo
            byte r0 = r0.connectType
            r13.connectType = r0
            com.eternal.base.concat.DeviceInfo r0 = r13.deviceInfo
            boolean r0 = r0.isDegree
            java.lang.String r1 = "℃"
            java.lang.String r2 = "℉"
            if (r0 == 0) goto L_0x0022
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpFlag
            java.lang.Object r0 = r0.get()
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0033
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpFlag
            r0.set(r1)
            goto L_0x0033
        L_0x0022:
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpFlag
            java.lang.Object r0 = r0.get()
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0033
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpFlag
            r0.set(r2)
        L_0x0033:
            byte r0 = r13.type
            r7 = 11
            r8 = 8
            r9 = 7
            r10 = 2
            r3 = 0
            r11 = 1
            if (r0 != r11) goto L_0x0072
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpSize
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            boolean r2 = r2.isDegree
            java.text.DecimalFormat r4 = r13.format
            java.lang.String r1 = com.eternal.base.protocol.ProtocolTransformer.getString4Degree(r1, r2, r4)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.masterVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.powerVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.rangeVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.humVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.vpdVisibility
            r0.set(r11)
            goto L_0x02ec
        L_0x0072:
            if (r0 != r10) goto L_0x00b5
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpSize
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            boolean r2 = r2.isDegree
            java.text.DecimalFormat r4 = r13.format
            java.lang.String r1 = com.eternal.base.protocol.ProtocolTransformer.getString4Degree(r1, r2, r4)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.masterVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.powerVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.powerOff
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.fan
            if (r1 != 0) goto L_0x00a0
            r1 = 1
            goto L_0x00a1
        L_0x00a0:
            r1 = 0
        L_0x00a1:
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.rangeVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.humVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.vpdVisibility
            r0.set(r11)
            goto L_0x02ec
        L_0x00b5:
            r4 = 6
            if (r0 != r4) goto L_0x00eb
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpSize
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            boolean r2 = r2.isDegree
            java.text.DecimalFormat r4 = r13.format
            java.lang.String r1 = com.eternal.base.protocol.ProtocolTransformer.getString4Degree(r1, r2, r4)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.masterVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.powerVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.rangeVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.humVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.vpdVisibility
            r0.set(r3)
            goto L_0x02ec
        L_0x00eb:
            if (r0 == r9) goto L_0x029b
            if (r0 == r8) goto L_0x029b
            if (r0 != r7) goto L_0x00f3
            goto L_0x029b
        L_0x00f3:
            r4 = 9
            if (r0 == r4) goto L_0x025b
            r4 = 12
            if (r0 != r4) goto L_0x00fd
            goto L_0x025b
        L_0x00fd:
            androidx.databinding.ObservableBoolean r0 = r13.rangeVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.humVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.vpdVisibility
            r0.set(r3)
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpSize
            com.eternal.base.concat.DeviceInfo r4 = r13.deviceInfo
            int r4 = r4.tmp
            com.eternal.base.concat.DeviceInfo r5 = r13.deviceInfo
            boolean r5 = r5.isDegree
            java.text.DecimalFormat r6 = r13.format
            java.lang.String r4 = com.eternal.base.protocol.ProtocolTransformer.getC_TmpString(r4, r5, r6)
            r0.set(r4)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.masterVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.powerVisibility
            r0.set(r3)
            com.eternal.base.concat.DeviceInfo r0 = r13.deviceInfo
            boolean r0 = r0.isDegree
            if (r0 == 0) goto L_0x0146
            androidx.databinding.ObservableInt r0 = r13.minTmp
            r0.set(r3)
            androidx.databinding.ObservableInt r0 = r13.distanceTmp
            r2 = 90
            r0.set(r2)
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpUnit
            r0.set(r1)
            goto L_0x0159
        L_0x0146:
            androidx.databinding.ObservableInt r0 = r13.minTmp
            r1 = 32
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.distanceTmp
            r1 = 162(0xa2, float:2.27E-43)
            r0.set(r1)
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpUnit
            r0.set(r2)
        L_0x0159:
            androidx.databinding.ObservableInt r0 = r13.tmp
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            int r1 = com.eternal.base.protocol.ProtocolTransformer.getC_Tmp(r1)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.tmpClose
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            r2 = -32768(0xffffffffffff8000, float:NaN)
            if (r1 != r2) goto L_0x0172
            r1 = 1
            goto L_0x0173
        L_0x0172:
            r1 = 0
        L_0x0173:
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.lowTmp
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.autoLowTmp
            int r1 = com.google.common.primitives.UnsignedBytes.toInt(r1)
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.highTmp
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.autoHighTmp
            int r1 = com.google.common.primitives.UnsignedBytes.toInt(r1)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.tmpLowClose
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            boolean r1 = r1.autoLowTmpSwitch
            r1 = r1 ^ r11
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.tmpHighClose
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            boolean r1 = r1.autoHighTmpSwitch
            r1 = r1 ^ r11
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.humClose
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.hum
            r2 = 10000(0x2710, float:1.4013E-41)
            if (r1 > r2) goto L_0x01b7
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.hum
            if (r1 >= 0) goto L_0x01b5
            goto L_0x01b7
        L_0x01b5:
            r1 = 0
            goto L_0x01b8
        L_0x01b7:
            r1 = 1
        L_0x01b8:
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.hum
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.hum
            int r1 = com.eternal.base.protocol.ProtocolTransformer.getHum(r1)
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.minHum
            r0.set(r3)
            androidx.databinding.ObservableInt r0 = r13.distanceHum
            r1 = 100
            r0.set(r1)
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.humUnit
            java.lang.String r1 = "%"
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.lowHum
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.autoLowHum
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.highHum
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.autoHighHum
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.humLowClose
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            boolean r1 = r1.autoLowHumSwitch
            r1 = r1 ^ r11
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.humHighClose
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            boolean r1 = r1.autoHighHumSwitch
            r1 = r1 ^ r11
            r0.set(r1)
            boolean r0 = r13.isConnected
            if (r0 == 0) goto L_0x02ec
            androidx.databinding.ObservableBoolean r0 = r13.tmpClose
            boolean r1 = r0.get()
            androidx.databinding.ObservableBoolean r0 = r13.tmpHighClose
            boolean r2 = r0.get()
            androidx.databinding.ObservableBoolean r0 = r13.tmpLowClose
            boolean r3 = r0.get()
            androidx.databinding.ObservableInt r0 = r13.tmp
            int r4 = r0.get()
            androidx.databinding.ObservableInt r0 = r13.highTmp
            int r5 = r0.get()
            androidx.databinding.ObservableInt r0 = r13.lowTmp
            int r6 = r0.get()
            r0 = r13
            byte r12 = r0.getAlarmType(r1, r2, r3, r4, r5, r6)
            androidx.databinding.ObservableBoolean r0 = r13.humClose
            boolean r1 = r0.get()
            androidx.databinding.ObservableBoolean r0 = r13.humHighClose
            boolean r2 = r0.get()
            androidx.databinding.ObservableBoolean r0 = r13.humLowClose
            boolean r3 = r0.get()
            androidx.databinding.ObservableInt r0 = r13.hum
            int r4 = r0.get()
            androidx.databinding.ObservableInt r0 = r13.highHum
            int r5 = r0.get()
            androidx.databinding.ObservableInt r0 = r13.lowHum
            int r6 = r0.get()
            r0 = r13
            byte r3 = r0.getAlarmType(r1, r2, r3, r4, r5, r6)
            r0 = r3
            r3 = r12
            goto L_0x02ed
        L_0x025b:
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpSize
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            boolean r2 = r2.isDegree
            java.text.DecimalFormat r4 = r13.format
            java.lang.String r1 = com.eternal.base.protocol.ProtocolTransformer.getString4Degree(r1, r2, r4)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.masterVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.powerVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.rangeVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.humVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.vpdVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.powerOff
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.fan
            if (r1 != 0) goto L_0x0296
            r1 = 1
            goto L_0x0297
        L_0x0296:
            r1 = 0
        L_0x0297:
            r0.set(r1)
            goto L_0x02ec
        L_0x029b:
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.tmpSize
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            boolean r2 = r2.isDegree
            java.text.DecimalFormat r4 = r13.format
            java.lang.String r1 = com.eternal.base.protocol.ProtocolTransformer.getString4Degree(r1, r2, r4)
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.masterVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.powerVisibility
            r0.set(r3)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r13.isShare
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.isShare
            if (r1 != r11) goto L_0x02c7
            r1 = 1
            goto L_0x02c8
        L_0x02c7:
            r1 = 0
        L_0x02c8:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r0.setValue(r1)
            androidx.databinding.ObservableBoolean r0 = r13.isConnectWiFi
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.connectType
            if (r1 != r11) goto L_0x02d9
            r1 = 1
            goto L_0x02da
        L_0x02d9:
            r1 = 0
        L_0x02da:
            r0.set(r1)
            androidx.databinding.ObservableBoolean r0 = r13.rangeVisibility
            r0.set(r3)
            androidx.databinding.ObservableBoolean r0 = r13.humVisibility
            r0.set(r11)
            androidx.databinding.ObservableBoolean r0 = r13.vpdVisibility
            r0.set(r11)
        L_0x02ec:
            r0 = 0
        L_0x02ed:
            byte r1 = r13.type
            if (r1 == r9) goto L_0x0305
            if (r1 == r8) goto L_0x0305
            if (r1 != r7) goto L_0x02f6
            goto L_0x0305
        L_0x02f6:
            androidx.databinding.ObservableInt r1 = r13.fanType
            boolean r2 = r13.isConnected
            if (r2 == 0) goto L_0x02ff
            int r2 = com.eternal.main.C2343R.mipmap.fan_icon_normal
            goto L_0x0301
        L_0x02ff:
            int r2 = com.eternal.main.C2343R.mipmap.fan_icon_grey
        L_0x0301:
            r1.set(r2)
            goto L_0x0313
        L_0x0305:
            androidx.databinding.ObservableInt r1 = r13.fanType
            boolean r2 = r13.isConnected
            if (r2 == 0) goto L_0x030e
            int r2 = com.eternal.main.C2343R.mipmap.level_icon_normal
            goto L_0x0310
        L_0x030e:
            int r2 = com.eternal.main.C2343R.mipmap.level_icon_grey
        L_0x0310:
            r1.set(r2)
        L_0x0313:
            androidx.databinding.ObservableField<java.lang.String> r1 = r13.perSize
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            int r2 = r2.hum
            java.text.DecimalFormat r4 = r13.format
            java.lang.String r2 = com.eternal.base.protocol.ProtocolTransformer.getPer(r2, r4)
            r1.set(r2)
            androidx.databinding.ObservableInt r1 = r13.tmpState
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            byte r2 = r2.tmpState
            int r2 = r13.getState(r2, r3)
            r1.set(r2)
            androidx.databinding.ObservableInt r1 = r13.perState
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            byte r2 = r2.humState
            int r2 = r13.getState(r2, r0)
            r1.set(r2)
            byte r1 = r13.version
            r2 = 3
            if (r1 < r2) goto L_0x035e
            androidx.databinding.ObservableField<java.lang.String> r1 = r13.vpdSize
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            int r2 = r2.vpd
            java.text.DecimalFormat r4 = r13.vpdFormat
            java.lang.String r2 = com.eternal.base.protocol.ProtocolTransformer.getVPDString(r2, r4)
            r1.set(r2)
            androidx.databinding.ObservableInt r1 = r13.vpdState
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            byte r2 = r2.vpdState
            int r2 = r13.getState(r2, r3)
            r1.set(r2)
            goto L_0x03c4
        L_0x035e:
            byte r1 = r13.type
            boolean r1 = com.eternal.base.protocol.ProtocolTransformer.isDeviceC(r1)
            if (r1 == 0) goto L_0x0396
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            int r2 = r2.hum
            com.eternal.base.concat.DeviceInfo r4 = r13.deviceInfo
            byte r4 = r4.leafTemperatureC
            com.eternal.base.concat.DeviceInfo r5 = r13.deviceInfo
            boolean r5 = r5.isDegree
            float r1 = com.eternal.base.protocol.ProtocolTransformer.getVPD(r1, r2, r4, r5)
            androidx.databinding.ObservableField<java.lang.String> r2 = r13.vpdSize
            com.eternal.base.concat.DeviceInfo r4 = r13.deviceInfo
            int r4 = r4.tmp
            com.eternal.base.concat.DeviceInfo r5 = r13.deviceInfo
            int r5 = r5.hum
            com.eternal.base.concat.DeviceInfo r6 = r13.deviceInfo
            byte r6 = r6.leafTemperatureC
            com.eternal.base.concat.DeviceInfo r7 = r13.deviceInfo
            boolean r7 = r7.isDegree
            java.text.DecimalFormat r8 = r13.vpdFormat
            java.lang.String r4 = com.eternal.base.protocol.ProtocolTransformer.getVPDString(r4, r5, r6, r7, r8)
            r2.set(r4)
            goto L_0x03bd
        L_0x0396:
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.tmp
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            int r2 = r2.hum
            com.eternal.base.concat.DeviceInfo r4 = r13.deviceInfo
            byte r4 = r4.leafTemperatureC
            float r1 = com.eternal.base.protocol.ProtocolTransformer.getVPD(r1, r2, r4, r11)
            androidx.databinding.ObservableField<java.lang.String> r2 = r13.vpdSize
            com.eternal.base.concat.DeviceInfo r4 = r13.deviceInfo
            int r4 = r4.tmp
            com.eternal.base.concat.DeviceInfo r5 = r13.deviceInfo
            int r5 = r5.hum
            com.eternal.base.concat.DeviceInfo r6 = r13.deviceInfo
            byte r6 = r6.leafTemperatureC
            java.text.DecimalFormat r7 = r13.vpdFormat
            java.lang.String r4 = com.eternal.base.protocol.ProtocolTransformer.getVPDString(r4, r5, r6, r11, r7)
            r2.set(r4)
        L_0x03bd:
            com.eternal.base.concat.DeviceInfo r2 = r13.deviceInfo
            byte r2 = r2.leafTemperatureC
            r13.setVpdState(r1, r2)
        L_0x03c4:
            androidx.databinding.ObservableInt r1 = r13.tmpColor
            android.content.Context r2 = com.eternal.framework.utils.Utils.getContext()
            boolean r4 = r13.isConnected
            if (r4 != 0) goto L_0x03d1
            int r4 = com.eternal.main.C2343R.C2344color.color_707070
            goto L_0x03dd
        L_0x03d1:
            if (r3 != r11) goto L_0x03d6
            int r4 = com.eternal.main.C2343R.C2344color.color_FF6A6A
            goto L_0x03dd
        L_0x03d6:
            if (r3 != r10) goto L_0x03db
            int r4 = com.eternal.main.C2343R.C2344color.color_15BAFF
            goto L_0x03dd
        L_0x03db:
            int r4 = com.eternal.main.C2343R.C2344color.color_FFFFFF
        L_0x03dd:
            int r2 = androidx.core.content.ContextCompat.getColor(r2, r4)
            r1.set(r2)
            androidx.databinding.ObservableInt r1 = r13.humColor
            android.content.Context r2 = com.eternal.framework.utils.Utils.getContext()
            boolean r4 = r13.isConnected
            if (r4 != 0) goto L_0x03f1
            int r4 = com.eternal.main.C2343R.C2344color.color_707070
            goto L_0x03fd
        L_0x03f1:
            if (r0 != r11) goto L_0x03f6
            int r4 = com.eternal.main.C2343R.C2344color.color_FF6A6A
            goto L_0x03fd
        L_0x03f6:
            if (r0 != r10) goto L_0x03fb
            int r4 = com.eternal.main.C2343R.C2344color.color_15BAFF
            goto L_0x03fd
        L_0x03fb:
            int r4 = com.eternal.main.C2343R.C2344color.color_FFFFFF
        L_0x03fd:
            int r2 = androidx.core.content.ContextCompat.getColor(r2, r4)
            r1.set(r2)
            androidx.databinding.ObservableInt r1 = r13.tmpUnitColor
            android.content.Context r2 = com.eternal.framework.utils.Utils.getContext()
            boolean r4 = r13.isConnected
            if (r4 != 0) goto L_0x0411
            int r4 = com.eternal.main.C2343R.C2344color.color_707070
            goto L_0x041d
        L_0x0411:
            if (r3 != r11) goto L_0x0416
            int r4 = com.eternal.main.C2343R.C2344color.color_FF6A6A
            goto L_0x041d
        L_0x0416:
            if (r3 != r10) goto L_0x041b
            int r4 = com.eternal.main.C2343R.C2344color.color_15BAFF
            goto L_0x041d
        L_0x041b:
            int r4 = com.eternal.main.C2343R.C2344color.color_BFBFBF
        L_0x041d:
            int r2 = androidx.core.content.ContextCompat.getColor(r2, r4)
            r1.set(r2)
            androidx.databinding.ObservableInt r1 = r13.humUnitColor
            android.content.Context r2 = com.eternal.framework.utils.Utils.getContext()
            boolean r4 = r13.isConnected
            if (r4 != 0) goto L_0x0431
            int r0 = com.eternal.main.C2343R.C2344color.color_707070
            goto L_0x043d
        L_0x0431:
            if (r0 != r11) goto L_0x0436
            int r0 = com.eternal.main.C2343R.C2344color.color_FF6A6A
            goto L_0x043d
        L_0x0436:
            if (r0 != r10) goto L_0x043b
            int r0 = com.eternal.main.C2343R.C2344color.color_15BAFF
            goto L_0x043d
        L_0x043b:
            int r0 = com.eternal.main.C2343R.C2344color.color_BFBFBF
        L_0x043d:
            int r0 = androidx.core.content.ContextCompat.getColor(r2, r0)
            r1.set(r0)
            androidx.databinding.ObservableBoolean r0 = r13.fanVisibility
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x0466
            androidx.databinding.ObservableField<java.lang.String> r0 = r13.fanSize
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            int r1 = r1.fan
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r0.set(r1)
            androidx.databinding.ObservableInt r0 = r13.fanState
            com.eternal.base.concat.DeviceInfo r1 = r13.deviceInfo
            byte r1 = r1.fanState
            int r1 = r13.getState(r1, r3)
            r0.set(r1)
        L_0x0466:
            r13.updatePort()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.main.model.ItemModel.update():void");
    }

    /* access modifiers changed from: private */
    public void updatePort() {
        if (!this.open.get() && this.deviceInfo.portList != null) {
            Collections.sort(this.deviceInfo.portList, new Comparator<PortInfo>() {
                public int compare(PortInfo portInfo, PortInfo portInfo2) {
                    return portInfo.f138id - portInfo2.f138id;
                }
            });
            if (this.deviceInfo.choosePort == 0) {
                boolean isOutletDevice = isOutletDevice(0);
                this.portModelAll.update(this.deviceInfo.toPortInfo(isOutletDevice ? "All Outlets" : "All Ports", true), this.type, isOutletDevice);
                this.allPortVisible.set(true);
                return;
            }
            this.allPortVisible.set(false);
            for (int i = 0; i < this.deviceInfo.portList.size(); i++) {
                this.portModelList.get(i).update(this.deviceInfo.portList.get(i), this.type, isOutletDevice(this.deviceInfo.portList.get(i).fanType));
            }
        }
    }

    private boolean isOutletDevice(int i) {
        DeviceInfo deviceInfo2 = this.deviceInfo;
        if (deviceInfo2 == null) {
            return ProtocolTransformer.isOutletDevice(this.type, i);
        }
        if (this.type == 11 && deviceInfo2.choosePort == 0 && this.deviceInfo.portList != null && this.deviceInfo.portList.size() > 0) {
            for (PortInfo next : this.deviceInfo.portList) {
                if (next.f138id != 0 && next.isPlug) {
                    return ProtocolTransformer.isOutletDevice(this.type, next.fanType);
                }
            }
        }
        return ProtocolTransformer.isOutletDevice(this.type, i);
    }

    /* access modifiers changed from: package-private */
    public void update(List<PortInfo> list) {
        DeviceInfo deviceInfo2 = this.deviceInfo;
        if (deviceInfo2 != null && list != null) {
            if (deviceInfo2.portList == null || this.deviceInfo.portList.size() != list.size()) {
                KLog.m65e(String.format("设备列表 %s", new Object[]{list}));
                this.deviceInfo.portList = list;
            } else {
                for (PortInfo next : this.deviceInfo.portList) {
                    for (PortInfo next2 : list) {
                        if (next.f138id == next2.f138id) {
                            next.name = next2.name;
                            next.portType = next2.portType;
                        }
                    }
                }
            }
            update();
        }
    }

    /* access modifiers changed from: package-private */
    public void update(String str) {
        this.name.set(str);
    }

    /* access modifiers changed from: package-private */
    public void disconnect() {
        disconnect(System.currentTimeMillis());
    }

    /* access modifiers changed from: package-private */
    public void disconnect(long j) {
        this.connectTime = j;
        this.time.set(ProtocolTransformer.getTime(j));
        this.timeIcon.set(C2343R.mipmap.icon_disconect);
        this.wifiIcon.set(C2343R.mipmap.wifi_icon_grey);
        this.bleIcon.set(C2343R.mipmap.bluetooth_icon_grey);
        this.shareIcon.set(C2343R.mipmap.share_icon_grey);
        this.isConnected = false;
        this.isConnet.set(false);
        for (PortModel portModel : this.portModelList) {
            portModel.isConnet.set(this.isConnected);
        }
        this.portModelAll.isConnet.set(this.isConnected);
    }

    /* access modifiers changed from: package-private */
    public void connect(long j) {
        this.tmpColor.set(ContextCompat.getColor(Utils.getContext(), C2343R.C2344color.color_FFFFFF));
        this.humColor.set(ContextCompat.getColor(Utils.getContext(), C2343R.C2344color.color_FFFFFF));
        this.tmpUnitColor.set(ContextCompat.getColor(Utils.getContext(), C2343R.C2344color.color_BFBFBF));
        this.humUnitColor.set(ContextCompat.getColor(Utils.getContext(), C2343R.C2344color.color_BFBFBF));
        this.time.set(ProtocolTransformer.getTime(j));
        this.timeIcon.set(C2343R.mipmap.icon_connected);
        this.wifiIcon.set(C2343R.mipmap.wifi_icon);
        this.bleIcon.set(C2343R.mipmap.bluetooth_icon);
        this.shareIcon.set(C2343R.mipmap.share_icon);
        this.isConnected = true;
        this.isConnet.set(true);
        for (PortModel portModel : this.portModelList) {
            portModel.isConnet.set(this.isConnected);
        }
        this.portModelAll.isConnet.set(this.isConnected);
    }

    /* access modifiers changed from: package-private */
    public boolean needRefresh() {
        byte b = this.type;
        if (!(b == 1 || b == 6 || b == 2 || b == 7 || b == 9 || b == 12) || this.version >= 3) {
            return false;
        }
        return true;
    }

    public byte getType() {
        return this.type;
    }

    public String getMac() {
        return this.mac;
    }

    private int getState(byte b, byte b2) {
        if (b != 0) {
            if (b != 1) {
                if (!this.isConnected) {
                    return C2343R.mipmap.increase_trend_grey;
                }
                if (b2 == 1) {
                    return C2343R.mipmap.increase_trend_red;
                }
                return b2 == 2 ? C2343R.mipmap.increase_trend_blue : C2343R.mipmap.increase_trend;
            } else if (!this.isConnected) {
                return C2343R.mipmap.decrease_trend_grey;
            } else {
                if (b2 == 1) {
                    return C2343R.mipmap.decrease_trend_red;
                }
                return b2 == 2 ? C2343R.mipmap.decrease_trend_blue : C2343R.mipmap.decrease_trend;
            }
        } else if (!this.isConnected) {
            return C2343R.mipmap.stead_trend_grey;
        } else {
            if (b2 == 1) {
                return C2343R.mipmap.stead_trend_red;
            }
            return b2 == 2 ? C2343R.mipmap.stead_trend_blue : C2343R.mipmap.stead_trend;
        }
    }

    private void setVpdState(final float f, final byte b) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        RepositoryInjection.providerHistoryRepository().getHistory(this.mac, currentTimeMillis - 60, currentTimeMillis).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TmpHum>>() {
            public void accept(List<TmpHum> list) throws Exception {
                int i;
                int i2 = ItemModel.this.isConnected ? C2343R.mipmap.stead_trend : C2343R.mipmap.stead_trend_grey;
                if (list != null && list.size() > 0) {
                    TmpHum tmpHum = list.get(0);
                    float round = (float) Math.round(ProtocolTransformer.getVPD(tmpHum.tmp, tmpHum.hum, b, !ProtocolTransformer.isDeviceC(ItemModel.this.type)) * 100.0f);
                    if (round != 0.0f) {
                        float f = f;
                        if (!(f == 0.0f || round == f)) {
                            i = round > f ? ItemModel.this.isConnected ? C2343R.mipmap.decrease_trend : C2343R.mipmap.decrease_trend_grey : ItemModel.this.isConnected ? C2343R.mipmap.increase_trend : C2343R.mipmap.increase_trend_grey;
                            i2 = i;
                        }
                    }
                    i = ItemModel.this.isConnected ? C2343R.mipmap.stead_trend : C2343R.mipmap.stead_trend_grey;
                    i2 = i;
                }
                ItemModel.this.vpdState.set(i2);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                ItemModel.this.vpdState.set(ItemModel.this.isConnected ? C2343R.mipmap.stead_trend : C2343R.mipmap.stead_trend_grey);
            }
        });
    }

    private int getDeviceIcon(byte b) {
        switch (b) {
            case 2:
                return C2343R.mipmap.controller_76_small;
            case 4:
                return C2343R.mipmap.ic_cloudcom_cb1;
            case 5:
                return C2343R.mipmap.ic_cloudcom_cb2;
            case 6:
                return C2343R.mipmap.airtap_small;
            case 7:
            case 8:
            case 11:
                return C2343R.mipmap.controller_69;
            case 9:
                return C2343R.mipmap.controller_independent_port;
            case 12:
                return C2663R.mipmap.controller_75_independ;
            case 14:
                return C2343R.mipmap.ic_cloudcom_ca1;
            case 15:
                return C2343R.mipmap.ic_cloudcom_ca2;
            default:
                return C2343R.mipmap.controller_67_small;
        }
    }

    /* access modifiers changed from: package-private */
    public void pushToDetail(byte b) {
        this.deviceInfo.choosePort = b;
        ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_TYPE, this.type).withByte(ActivityEvent.DEVICE_PORT, this.deviceInfo.choosePort).withByte(ActivityEvent.DEVICE_VERSION, this.version).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withString(ActivityEvent.DEVICE_SOFTWARE_VERSION, this.softwareVersion).withString(ActivityEvent.DEVICE_HARDWARE_VERSION, this.hardwareVersion).withString(ActivityEvent.DEVICE_FIRMWARE_VERSION, this.firmwareVersion).withTransition(C2343R.anim.right_in, C2343R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        Messenger.getDefault().send(this, MainActivity.ON_ITEM_CLICK);
    }

    public void setShowLoading() {
        boolean z = this.showLoading.get();
        Boolean bool = Boolean.TRUE;
        if (!z) {
            this.showLoading.set(true);
            Maybe.just(true).delay(3000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    ItemModel.this.showLoading.set(false);
                }
            });
        }
    }

    public void onFlushAliveTime() {
        ObservableEmitter<Boolean> observableEmitter;
        if (this.type == 11 && this.connectType == 0 && (observableEmitter = this.keepAliveEmitter) != null) {
            observableEmitter.onNext(true);
        }
    }

    private void initDaemonObservable() {
        this.keepAliveDisposable = p014io.reactivex.Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                ItemModel.this.keepAliveEmitter = observableEmitter;
            }
        }).debounce(6, TimeUnit.SECONDS).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                LogService.getInstance().refreshNotificationMessage(ItemModel.this.mac, ItemModel.this.deviceInfo.choosePort, false);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        Disposable disposable = this.keepAliveDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
