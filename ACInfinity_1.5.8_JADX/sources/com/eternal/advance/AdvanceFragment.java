package com.eternal.advance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.transition.TransitionManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.advance.databinding.FragmentAdvanceBinding;
import com.eternal.advance.model.AdvanceModel;
import com.eternal.advance.model.ItemModel;
import com.eternal.base.BaseFragment;
import com.eternal.base.IConnectAction;
import com.eternal.base.IDegreeAction;
import com.eternal.base.ITFPAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.TipDialog;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import com.eternal.widget.C2779R;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvanceFragment extends BaseFragment<FragmentAdvanceBinding, AdvanceModel> implements IConnectAction, IDegreeAction, IToolBarAction, ITFPAction {
    public static final String CLOSE_MENU = "close menu";
    public static final String ITEM_DELETE = "delete item";
    public static final String OPEN_NOTIFY = "openNotify";
    public static final String REFRESH_LOG = "refresh log";
    public static final String UPDATE_PORT_TAB = "update port tab";
    byte connectType;
    String devId;
    byte deviceType;
    byte deviceVersion;
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    String mac;
    byte port;
    private final TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        public void onTabReselected(TabLayout.Tab tab) {
        }

        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getCustomView() != null) {
                tab.getCustomView().setSelected(true);
            }
        }

        public void onTabUnselected(TabLayout.Tab tab) {
            if (tab.getCustomView() != null) {
                tab.getCustomView().setSelected(false);
            }
        }
    };
    private final TabLayout.OnTabSelectedListener tabUserSelectedListener = new TabLayout.OnTabSelectedListener() {
        public void onTabReselected(TabLayout.Tab tab) {
        }

        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getTag() != null && ((FragmentAdvanceBinding) AdvanceFragment.this.binding).tbPort.getTabCount() > 1) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceFragment.this.mac)) {
                    AdvanceFragment advanceFragment = AdvanceFragment.this;
                    advanceFragment.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceFragment.deviceType, AdvanceFragment.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                ((AdvanceModel) AdvanceFragment.this.viewModel).choosePort(((Byte) tab.getTag()).byteValue());
            }
        }

        public void onTabUnselected(TabLayout.Tab tab) {
            KLog.m65e("on Tab ");
        }
    };

    public void onSecond() {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C1202R.layout.fragment_advance;
    }

    public int initVariableId() {
        return C1200BR.model;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        initMessenger();
        ((AdvanceModel) this.viewModel).init(RepositoryInjection.providerNotificationRepository(), this.mac, this.devId, this.port, this.deviceType, this.deviceVersion, this.connectType);
    }

    public void connected() {
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            ((AdvanceModel) this.viewModel).start();
        }
    }

    public void disconnected() {
        ((AdvanceModel) this.viewModel).needSyncData = true;
        ((AdvanceModel) this.viewModel).stop();
        updatePlugType();
    }

    public void setConnectType(String str, byte b) {
        this.devId = str;
        ((AdvanceModel) this.viewModel).devId = str;
        if (this.connectType != b) {
            this.connectType = b;
            ((AdvanceModel) this.viewModel).connectType = b;
            if (getActivity() == AppManager.getAppManager().currentActivity()) {
                disconnected();
                connected();
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            ((AdvanceModel) this.viewModel).stop();
            return;
        }
        ((AdvanceModel) this.viewModel).updateAll();
        ((AdvanceModel) this.viewModel).start();
    }

    private void initView() {
        initPortTabListener();
        ((AdvanceModel) this.viewModel).selectTab.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(((FragmentAdvanceBinding) AdvanceFragment.this.binding).ctTab);
                if (num == null || num.intValue() == 0) {
                    ConstraintSet constraintSet2 = constraintSet;
                    constraintSet2.connect(C1202R.C1205id.v_slide, 6, C1202R.C1205id.tv_automations, 6, 0);
                    constraintSet2.connect(C1202R.C1205id.v_slide, 7, C1202R.C1205id.tv_automations, 7, 0);
                } else if (num.intValue() == 1) {
                    ConstraintSet constraintSet3 = constraintSet;
                    constraintSet3.connect(C1202R.C1205id.v_slide, 6, C1202R.C1205id.tv_alarms, 6, 0);
                    constraintSet3.connect(C1202R.C1205id.v_slide, 7, C1202R.C1205id.tv_alarms, 7, 0);
                }
                constraintSet.applyTo(((FragmentAdvanceBinding) AdvanceFragment.this.binding).ctTab);
                TransitionManager.beginDelayedTransition(((FragmentAdvanceBinding) AdvanceFragment.this.binding).ctTab);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void initPortTabListener() {
        if (!ProtocolTransformer.isDeviceMultiPort(this.deviceType)) {
            ((FragmentAdvanceBinding) this.binding).tbPort.setVisibility(8);
            return;
        }
        ((FragmentAdvanceBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabSelectedListener);
        ((FragmentAdvanceBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
    }

    public void onNameUpdate(List<DeviceName> list) {
        if (list != null && list.size() > 1 && ((AdvanceModel) this.viewModel).info != null && ((AdvanceModel) this.viewModel).info.portList != null && ((AdvanceModel) this.viewModel).info.portList.size() != 0) {
            for (PortInfo next : ((AdvanceModel) this.viewModel).info.portList) {
                Iterator<DeviceName> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceName next2 = it.next();
                    if (next.f138id == next2.port) {
                        next.name = next2.name;
                        next.portType = next2.portType;
                        break;
                    }
                }
            }
            int tabCount = ((FragmentAdvanceBinding) this.binding).tbPort.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = ((FragmentAdvanceBinding) this.binding).tbPort.getTabAt(i);
                if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                    Iterator<PortInfo> it2 = ((AdvanceModel) this.viewModel).info.portList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        PortInfo next3 = it2.next();
                        if (((Byte) tabAt.getTag()).byteValue() == next3.f138id) {
                            TextView textView = (TextView) tabAt.getCustomView().findViewById(C2779R.C2782id.tv_title);
                            if (next3.f138id != 0) {
                                textView.setText(ProtocolTransformer.getDisplayPortName(next3.f138id, this.deviceType, next3.name));
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void initPortTab() {
        boolean z;
        boolean z2;
        if (((AdvanceModel) this.viewModel).info != null && ((AdvanceModel) this.viewModel).info.portList != null && ((AdvanceModel) this.viewModel).info.portList.size() != 0) {
            ArrayList<PortInfo> arrayList = new ArrayList<>();
            Boolean bool = null;
            Iterator<PortInfo> it = ((AdvanceModel) this.viewModel).info.portList.iterator();
            int i = 0;
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                PortInfo next = it.next();
                if (next.isPlug && next.f138id != 0) {
                    arrayList.add(next);
                    boolean isOutletDevice = ProtocolTransformer.isOutletDevice(this.deviceType, next.fanType);
                    if (bool == null) {
                        bool = Boolean.valueOf(isOutletDevice);
                    } else if (bool.booleanValue() != isOutletDevice) {
                        i = 1;
                    }
                }
            }
            if (((FragmentAdvanceBinding) this.binding).tbPort.getTabCount() == arrayList.size() + (i ^ 1)) {
                int tabCount = ((FragmentAdvanceBinding) this.binding).tbPort.getTabCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= tabCount) {
                        z = false;
                        break;
                    }
                    TabLayout.Tab tabAt = ((FragmentAdvanceBinding) this.binding).tbPort.getTabAt(i2);
                    if (!(tabAt == null || tabAt.getTag() == null || tabAt.getCustomView() == null)) {
                        Iterator it2 = arrayList.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                z2 = false;
                                break;
                            }
                            PortInfo portInfo = (PortInfo) it2.next();
                            if (((Byte) tabAt.getTag()).byteValue() == 0) {
                                if (i == 0) {
                                    break;
                                }
                            } else if (((Byte) tabAt.getTag()).byteValue() == portInfo.f138id) {
                                break;
                            }
                        }
                        z2 = true;
                        if (!z2) {
                            break;
                        }
                    }
                    i2++;
                }
            }
            if (z) {
                ((FragmentAdvanceBinding) this.binding).tbPort.removeAllTabs();
                if (i == 0) {
                    TabLayout.Tab newTab = ((FragmentAdvanceBinding) this.binding).tbPort.newTab();
                    View inflate = LayoutInflater.from(getContext()).inflate(C2779R.layout.item_port_all, newTab.parent, false);
                    newTab.setTag((byte) 0);
                    newTab.setCustomView(inflate);
                    if (((AdvanceModel) this.viewModel).port == 0 && !newTab.isSelected()) {
                        setTabSelectSilently(newTab);
                    }
                    ((FragmentAdvanceBinding) this.binding).tbPort.addTab(newTab);
                }
                for (PortInfo portInfo2 : arrayList) {
                    TabLayout.Tab newTab2 = ((FragmentAdvanceBinding) this.binding).tbPort.newTab();
                    View inflate2 = LayoutInflater.from(getContext()).inflate(C2779R.layout.item_port, newTab2.parent, false);
                    ((TextView) inflate2.findViewById(C2779R.C2782id.tv_title)).setText(ProtocolTransformer.getDisplayPortName(portInfo2.f138id, this.deviceType, portInfo2.name));
                    ((ImageView) inflate2.findViewById(C1202R.C1205id.iv_image)).setImageResource(ProtocolTransformer.getPlugIcon(portInfo2.portType, portInfo2.fanType, RepositoryInjection.providerDeviceRepository().isConnect(this.mac), this.deviceType));
                    newTab2.setTag(Byte.valueOf(portInfo2.f138id));
                    newTab2.setCustomView(inflate2);
                    if (((AdvanceModel) this.viewModel).port == portInfo2.f138id && !newTab2.isSelected()) {
                        setTabSelectSilently(newTab2);
                    }
                    ((FragmentAdvanceBinding) this.binding).tbPort.addTab(newTab2);
                }
                return;
            }
            updatePlugType();
        }
    }

    private void updatePlugType() {
        if (((AdvanceModel) this.viewModel).info != null && ((AdvanceModel) this.viewModel).info.portList != null && ((AdvanceModel) this.viewModel).info.portList.size() != 0) {
            int tabCount = ((FragmentAdvanceBinding) this.binding).tbPort.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = ((FragmentAdvanceBinding) this.binding).tbPort.getTabAt(i);
                if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                    Iterator<PortInfo> it = ((AdvanceModel) this.viewModel).info.portList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        PortInfo next = it.next();
                        if (((Byte) tabAt.getTag()).byteValue() == next.f138id) {
                            if (next.f138id != 0) {
                                ((ImageView) tabAt.getCustomView().findViewById(C1202R.C1205id.iv_image)).setImageResource(ProtocolTransformer.getPlugIcon(next.portType, next.fanType, RepositoryInjection.providerDeviceRepository().isConnect(this.mac), this.deviceType));
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTabSelect(byte b) {
        int tabCount = ((FragmentAdvanceBinding) this.binding).tbPort.getTabCount();
        int i = 0;
        while (i < tabCount) {
            TabLayout.Tab tabAt = ((FragmentAdvanceBinding) this.binding).tbPort.getTabAt(i);
            if (tabAt == null || tabAt.getTag() == null || tabAt.getCustomView() == null || ((Byte) tabAt.getTag()).byteValue() != b || tabAt.isSelected()) {
                i++;
            } else {
                setTabSelectSilently(tabAt);
                return;
            }
        }
    }

    private void setTabSelectSilently(TabLayout.Tab tab) {
        if (tab != null) {
            ((FragmentAdvanceBinding) this.binding).tbPort.removeOnTabSelectedListener(this.tabUserSelectedListener);
            tab.select();
            ((FragmentAdvanceBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
        }
    }

    private void initMessenger() {
        Messenger.getDefault().register((Object) this, (Object) "delete item", ItemModel.class, new BindingConsumer<ItemModel>() {
            public void call(ItemModel itemModel) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceFragment.this.mac)) {
                    AdvanceFragment advanceFragment = AdvanceFragment.this;
                    advanceFragment.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceFragment.deviceType, AdvanceFragment.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                AdvanceFragment.this.showDelete(itemModel);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "close menu", (BindingAction) new BindingAction() {
            public void call() {
                AdvanceFragment.this.closeMenu();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "update port tab", (BindingAction) new BindingAction() {
            public void call() {
                AdvanceFragment.this.initPortTab();
                AdvanceFragment advanceFragment = AdvanceFragment.this;
                advanceFragment.updateTabSelect(((AdvanceModel) advanceFragment.viewModel).port);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showDelete(final ItemModel itemModel) {
        this.dialog = TipDialog.showTipDialog(getContext(), getString(C1202R.string.tip_remove_adv_title, itemModel.model.name), getString(C1202R.string.tip_remove_adv_content, itemModel.model.name), getResources().getString(C1202R.string.tip_cancel_lowercase), getResources().getString(C1202R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
                AdvanceFragment.this.closeMenu();
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                AdvanceFragment.this.closeMenu();
                ((AdvanceModel) AdvanceFragment.this.viewModel).remove(itemModel);
                AdvanceFragment.this.dialog.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void closeMenu() {
        ((FragmentAdvanceBinding) this.binding).listNotification.closeMenu();
        ((FragmentAdvanceBinding) this.binding).listAlarms.closeMenu();
        ((FragmentAdvanceBinding) this.binding).listAutomations.closeMenu();
    }

    public void onDestroyView() {
        Messenger.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void setDegree(boolean z) {
        ((AdvanceModel) this.viewModel).setDegree(z);
    }

    public void refreshTFP(DeviceTFP deviceTFP) {
        if (deviceTFP != null && ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType) && ((AdvanceModel) this.viewModel).port != deviceTFP.choosePort) {
            ((AdvanceModel) this.viewModel).needSyncData = true;
        }
    }
}
