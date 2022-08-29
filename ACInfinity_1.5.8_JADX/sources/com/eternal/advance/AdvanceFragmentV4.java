package com.eternal.advance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.advance.databinding.FragmentAdvanceV4Binding;
import com.eternal.advance.model.AdvanceModelV4;
import com.eternal.advance.model.ItemChildModelV4;
import com.eternal.advance.model.ItemModelV4;
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
import com.eternal.base.database.entity.Notification;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AdvanceFragmentV4 extends BaseFragment<FragmentAdvanceV4Binding, AdvanceModelV4> implements IConnectAction, IDegreeAction, IToolBarAction, ITFPAction {
    public static final String CLOSE_MENU = "close menu";
    public static final String ITEM_DELETE = "delete item";
    public static final String OPEN_NOTIFY = "openNotify";
    public static final String REFRESH_LOG = "refresh log";
    private static final String SAVED_STATE_EXPANDABLE_ITEM_MANAGER = "RecyclerViewExpandableItemManager";
    public static final String UPDATE_CHILD_SEQUENCES = "update child sequences";
    public static final String UPDATE_LIST = "update list";
    public static final String UPDATE_PORT_TAB = "update port tab";
    ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            ((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).stop();
            return makeMovementFlags(3, 0);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            int layoutPosition = viewHolder.getLayoutPosition();
            int layoutPosition2 = viewHolder2.getLayoutPosition();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyItemMoved(layoutPosition, layoutPosition2);
            }
            Collections.swap(((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).automations, layoutPosition, layoutPosition2);
            return true;
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            super.onSelectedChanged(viewHolder, i);
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (ItemModelV4 itemModelV4 : ((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).automations) {
                itemModelV4.model.groupIndex = (byte) i;
                arrayList.add(Byte.valueOf((byte) (itemModelV4.model.groupIndex | (itemModelV4.model.f144id << 4))));
                i++;
            }
            ((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).updateSequences(arrayList, (byte) 15, false);
        }
    };
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
            if (tab.getTag() != null && ((FragmentAdvanceV4Binding) AdvanceFragmentV4.this.binding).tbPort.getTabCount() > 1) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceFragmentV4.this.mac)) {
                    AdvanceFragmentV4 advanceFragmentV4 = AdvanceFragmentV4.this;
                    advanceFragmentV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceFragmentV4.deviceType, AdvanceFragmentV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                ((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).choosePort(((Byte) tab.getTag()).byteValue());
            }
        }

        public void onTabUnselected(TabLayout.Tab tab) {
            KLog.m65e("on Tab ");
        }
    };

    public void onSecond() {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C1202R.layout.fragment_advance_v4;
    }

    public int initVariableId() {
        return C1200BR.modelV4;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        initMessenger();
        ((AdvanceModelV4) this.viewModel).init(RepositoryInjection.providerNotificationRepository(), this.mac, this.devId, this.port, this.deviceType, this.deviceVersion, this.connectType);
    }

    public void connected() {
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            ((AdvanceModelV4) this.viewModel).start();
        }
    }

    public void disconnected() {
        ((AdvanceModelV4) this.viewModel).needSyncData = true;
        ((AdvanceModelV4) this.viewModel).stop();
        updatePlugType();
    }

    public void setConnectType(String str, byte b) {
        this.devId = str;
        ((AdvanceModelV4) this.viewModel).devId = str;
        if (this.connectType != b) {
            this.connectType = b;
            ((AdvanceModelV4) this.viewModel).connectType = b;
            if (getActivity() == AppManager.getAppManager().currentActivity()) {
                disconnected();
                connected();
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            ((AdvanceModelV4) this.viewModel).stop();
            return;
        }
        ((AdvanceModelV4) this.viewModel).updateAll();
        ((AdvanceModelV4) this.viewModel).start();
    }

    private void initView() {
        initPortTabListener();
        new ItemTouchHelper(this.callback).attachToRecyclerView(((FragmentAdvanceV4Binding) this.binding).listAutomations);
        ((AdvanceModelV4) this.viewModel).selectTab.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(((FragmentAdvanceV4Binding) AdvanceFragmentV4.this.binding).ctTab);
                if (num == null || num.intValue() == 0) {
                    ConstraintSet constraintSet2 = constraintSet;
                    constraintSet2.connect(C1202R.C1205id.v_slide, 6, C1202R.C1205id.tv_automations, 6, 0);
                    constraintSet2.connect(C1202R.C1205id.v_slide, 7, C1202R.C1205id.tv_automations, 7, 0);
                } else if (num.intValue() == 1) {
                    ConstraintSet constraintSet3 = constraintSet;
                    constraintSet3.connect(C1202R.C1205id.v_slide, 6, C1202R.C1205id.tv_alarms, 6, 0);
                    constraintSet3.connect(C1202R.C1205id.v_slide, 7, C1202R.C1205id.tv_alarms, 7, 0);
                }
                constraintSet.applyTo(((FragmentAdvanceV4Binding) AdvanceFragmentV4.this.binding).ctTab);
                TransitionManager.beginDelayedTransition(((FragmentAdvanceV4Binding) AdvanceFragmentV4.this.binding).ctTab);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void initPortTabListener() {
        if (!ProtocolTransformer.isDeviceMultiPort(this.deviceType)) {
            ((FragmentAdvanceV4Binding) this.binding).tbPort.setVisibility(8);
            return;
        }
        ((FragmentAdvanceV4Binding) this.binding).tbPort.addOnTabSelectedListener(this.tabSelectedListener);
        ((FragmentAdvanceV4Binding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
    }

    public void onNameUpdate(List<DeviceName> list) {
        if (list != null && list.size() > 1 && ((AdvanceModelV4) this.viewModel).info != null && ((AdvanceModelV4) this.viewModel).info.portList != null && ((AdvanceModelV4) this.viewModel).info.portList.size() != 0) {
            for (PortInfo next : ((AdvanceModelV4) this.viewModel).info.portList) {
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
            int tabCount = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabAt(i);
                if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                    Iterator<PortInfo> it2 = ((AdvanceModelV4) this.viewModel).info.portList.iterator();
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
        if (((AdvanceModelV4) this.viewModel).info != null && ((AdvanceModelV4) this.viewModel).info.portList != null && ((AdvanceModelV4) this.viewModel).info.portList.size() != 0) {
            ArrayList<PortInfo> arrayList = new ArrayList<>();
            for (PortInfo next : ((AdvanceModelV4) this.viewModel).info.portList) {
                if (next.isPlug && next.f138id != 0) {
                    arrayList.add(next);
                }
            }
            boolean z2 = true;
            if (((FragmentAdvanceV4Binding) this.binding).tbPort.getTabCount() == arrayList.size() + 1) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    PortInfo portInfo = (PortInfo) it.next();
                    int tabCount = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabCount();
                    int i = 0;
                    while (true) {
                        if (i < tabCount) {
                            TabLayout.Tab tabAt = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabAt(i);
                            if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null && ((Byte) tabAt.getTag()).byteValue() == portInfo.f138id) {
                                z = true;
                                continue;
                                break;
                            }
                            i++;
                        } else {
                            z = false;
                            continue;
                            break;
                        }
                    }
                    if (!z) {
                        break;
                    }
                }
            }
            if (z2) {
                ((FragmentAdvanceV4Binding) this.binding).tbPort.removeAllTabs();
                TabLayout.Tab newTab = ((FragmentAdvanceV4Binding) this.binding).tbPort.newTab();
                View inflate = LayoutInflater.from(getContext()).inflate(C2779R.layout.item_port_all, newTab.parent, false);
                newTab.setTag((byte) 0);
                newTab.setCustomView(inflate);
                if (((AdvanceModelV4) this.viewModel).port == 0 && !newTab.isSelected()) {
                    setTabSelectSilently(newTab);
                }
                ((FragmentAdvanceV4Binding) this.binding).tbPort.addTab(newTab);
                for (PortInfo portInfo2 : arrayList) {
                    TabLayout.Tab newTab2 = ((FragmentAdvanceV4Binding) this.binding).tbPort.newTab();
                    View inflate2 = LayoutInflater.from(getContext()).inflate(C2779R.layout.item_port, newTab2.parent, false);
                    ((TextView) inflate2.findViewById(C2779R.C2782id.tv_title)).setText(ProtocolTransformer.getDisplayPortName(portInfo2.f138id, this.deviceType, portInfo2.name));
                    ((ImageView) inflate2.findViewById(C1202R.C1205id.iv_image)).setImageResource(ProtocolTransformer.getPlugIcon(portInfo2.portType, portInfo2.fanType, RepositoryInjection.providerDeviceRepository().isConnect(this.mac), this.deviceType));
                    newTab2.setTag(Byte.valueOf(portInfo2.f138id));
                    newTab2.setCustomView(inflate2);
                    if (((AdvanceModelV4) this.viewModel).port == portInfo2.f138id && !newTab2.isSelected()) {
                        setTabSelectSilently(newTab2);
                    }
                    ((FragmentAdvanceV4Binding) this.binding).tbPort.addTab(newTab2);
                }
                return;
            }
            updatePlugType();
        }
    }

    private void updatePlugType() {
        if (((AdvanceModelV4) this.viewModel).info != null && ((AdvanceModelV4) this.viewModel).info.portList != null && ((AdvanceModelV4) this.viewModel).info.portList.size() != 0) {
            int tabCount = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabAt(i);
                if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                    Iterator<PortInfo> it = ((AdvanceModelV4) this.viewModel).info.portList.iterator();
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
        int tabCount = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabCount();
        int i = 0;
        while (i < tabCount) {
            TabLayout.Tab tabAt = ((FragmentAdvanceV4Binding) this.binding).tbPort.getTabAt(i);
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
            ((FragmentAdvanceV4Binding) this.binding).tbPort.removeOnTabSelectedListener(this.tabUserSelectedListener);
            tab.select();
            ((FragmentAdvanceV4Binding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
        }
    }

    private void initMessenger() {
        Messenger.getDefault().register((Object) this, (Object) "delete item", ItemModelV4.class, new BindingConsumer<ItemModelV4>() {
            public void call(ItemModelV4 itemModelV4) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceFragmentV4.this.mac)) {
                    AdvanceFragmentV4 advanceFragmentV4 = AdvanceFragmentV4.this;
                    advanceFragmentV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceFragmentV4.deviceType, AdvanceFragmentV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                AdvanceFragmentV4.this.showDelete(itemModelV4, itemModelV4.model);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "delete item", ItemChildModelV4.class, new BindingConsumer<ItemChildModelV4>() {
            public void call(ItemChildModelV4 itemChildModelV4) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceFragmentV4.this.mac)) {
                    AdvanceFragmentV4 advanceFragmentV4 = AdvanceFragmentV4.this;
                    advanceFragmentV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceFragmentV4.deviceType, AdvanceFragmentV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                AdvanceFragmentV4.this.showDelete((ItemModelV4) null, itemChildModelV4.model);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_CHILD_SEQUENCES, ItemModelV4.class, new BindingConsumer<ItemModelV4>() {
            public void call(ItemModelV4 itemModelV4) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceFragmentV4.this.mac)) {
                    AdvanceFragmentV4 advanceFragmentV4 = AdvanceFragmentV4.this;
                    advanceFragmentV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceFragmentV4.deviceType, AdvanceFragmentV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                int i = 0;
                for (ItemChildModelV4 itemChildModelV4 : itemModelV4.childList) {
                    itemChildModelV4.model.childIndex = (byte) i;
                    arrayList.add(Byte.valueOf((byte) (itemChildModelV4.model.childIndex | (itemChildModelV4.model.childId << 4))));
                    i++;
                }
                ((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).updateSequences(arrayList, (byte) itemModelV4.model.f144id, true);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "close menu", (BindingAction) new BindingAction() {
            public void call() {
                AdvanceFragmentV4.this.closeMenu();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "update port tab", (BindingAction) new BindingAction() {
            public void call() {
                AdvanceFragmentV4.this.initPortTab();
                AdvanceFragmentV4 advanceFragmentV4 = AdvanceFragmentV4.this;
                advanceFragmentV4.updateTabSelect(((AdvanceModelV4) advanceFragmentV4.viewModel).port);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showDelete(final ItemModelV4 itemModelV4, final Notification notification) {
        this.dialog = TipDialog.showTipDialog(getContext(), getString(C1202R.string.tip_remove_adv_title, itemModelV4.model.name), getString(C1202R.string.tip_remove_adv_content, itemModelV4.model.name), getResources().getString(C1202R.string.tip_cancel_lowercase), getResources().getString(C1202R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
                AdvanceFragmentV4.this.closeMenu();
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                AdvanceFragmentV4.this.closeMenu();
                ((AdvanceModelV4) AdvanceFragmentV4.this.viewModel).remove(itemModelV4, notification);
                AdvanceFragmentV4.this.dialog.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void closeMenu() {
        ((FragmentAdvanceV4Binding) this.binding).listNotification.closeMenu();
        ((FragmentAdvanceV4Binding) this.binding).listAlarms.closeMenu();
        ((FragmentAdvanceV4Binding) this.binding).listAutomations.closeMenu();
    }

    public void onDestroyView() {
        Messenger.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void setDegree(boolean z) {
        ((AdvanceModelV4) this.viewModel).setDegree(z);
    }

    public void refreshTFP(DeviceTFP deviceTFP) {
        if (deviceTFP != null && ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType) && ((AdvanceModelV4) this.viewModel).port != deviceTFP.choosePort) {
            ((AdvanceModelV4) this.viewModel).needSyncData = true;
        }
    }
}
