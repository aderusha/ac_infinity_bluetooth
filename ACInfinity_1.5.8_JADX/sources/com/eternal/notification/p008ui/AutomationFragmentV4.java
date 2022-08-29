package com.eternal.notification.p008ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseFragment;
import com.eternal.base.IConnectAction;
import com.eternal.base.ISoftKeyBoardAction;
import com.eternal.base.TipDialog;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.base.utils.TimeUtil;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.utils.GsonUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.notification.C2419BR;
import com.eternal.notification.C2420R;
import com.eternal.notification.databinding.FragmentAutomationV4Binding;
import com.eternal.notification.model.AutomationModelV4;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.DoubleCloseAddLayoutListener;
import com.eternal.widget.guqiang.FanProgressBar;
import com.eternal.widget.guqiang.IEffectBar;
import com.eternal.widget.guqiang.RangeSeekBar;
import com.eternal.widget.wheelview.widget.WheelView;
import com.google.common.collect.Lists;
import com.zyyoona7.wheel.WheelView;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.text.Typography;

/* renamed from: com.eternal.notification.ui.AutomationFragmentV4 */
public class AutomationFragmentV4 extends BaseFragment<FragmentAutomationV4Binding, AutomationModelV4> implements ISoftKeyBoardAction, IConnectAction {
    public static String UPDATE_PARAM = "update param";
    /* access modifiers changed from: private */
    public ArrayList<String> cycleHours;
    /* access modifiers changed from: private */
    public ArrayList<String> cycleMinus;
    /* access modifiers changed from: private */
    public ArrayList<String> cycleMinus24;
    byte deviceType;
    byte deviceVersion;
    MaterialDialog dialog;
    int groupId;
    boolean isDegree;
    String mac;
    /* access modifiers changed from: private */
    public MutableLiveData<Boolean> now;
    byte offLevel;
    byte onLevel;
    byte port;

    public void connected() {
    }

    public void disconnected() {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C2420R.layout.fragment_automation_v4;
    }

    public int initVariableId() {
        return C2419BR.autoModelV4;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        String string = arguments.getString(ActivityEvent.NOTIFICATION);
        initListener();
        initView(this.isDegree, string != null);
        registerMessage();
        bindEvent();
        ((AutomationModelV4) this.viewModel).init(RepositoryInjection.providerNotificationRepository(), this.mac, arguments.getString(ActivityEvent.DEVICE_ID), this.port, this.deviceType, this.deviceVersion, (Notification) GsonUtils.fromJson(string, Notification.class), this.isDegree, this.onLevel, this.offLevel, this.groupId, arguments.getByte(ActivityEvent.DEVICE_CONNECT_TYPE));
    }

    public void initListener() {
        initTmpListener();
        initHumListener();
        initVpdListener();
    }

    private void initTmpListener() {
        if (this.isDegree) {
            ((FragmentAutomationV4Binding) this.binding).tvTempMin.setText("0℃");
            ((FragmentAutomationV4Binding) this.binding).tvTempMax.setText("90℃");
            ((AutomationModelV4) this.viewModel).tmpUnit.setValue(Utils.getString(C2420R.string.tip_degree_num));
            ((FragmentAutomationV4Binding) this.binding).rsbTemp.setMin(0);
            ((FragmentAutomationV4Binding) this.binding).rsbTemp.setDistance(90);
            ((FragmentAutomationV4Binding) this.binding).sbHighTmp.setValue(0, 90);
            ((FragmentAutomationV4Binding) this.binding).sbLowTmp.setValue(0, 90);
        } else {
            ((FragmentAutomationV4Binding) this.binding).tvTempMin.setText("32℉");
            ((FragmentAutomationV4Binding) this.binding).tvTempMax.setText("194℉");
            ((FragmentAutomationV4Binding) this.binding).rsbTemp.setMin(32);
            ((FragmentAutomationV4Binding) this.binding).rsbTemp.setDistance(162);
            ((FragmentAutomationV4Binding) this.binding).sbHighTmp.setValue(32, 162);
            ((FragmentAutomationV4Binding) this.binding).sbLowTmp.setValue(32, 162);
        }
        ((FragmentAutomationV4Binding) this.binding).rsbTemp.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                if (((AutomationModelV4) AutomationFragmentV4.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).rsbTemp.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowTmp.setChecked(z);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighTmp.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    char c = (char) i2;
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmp.setValue(Character.valueOf(c));
                    char c2 = (char) i;
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmp.setValue(Character.valueOf(c2));
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighTmp.setProgress(c);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowTmp.setProgress(c2);
                }
            }
        });
        C25843 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                if (((AutomationModelV4) AutomationFragmentV4.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        };
        ((FragmentAutomationV4Binding) this.binding).sbHighTmp.setFactory(r0);
        ((FragmentAutomationV4Binding) this.binding).sbLowTmp.setFactory(r0);
        ((FragmentAutomationV4Binding) this.binding).layoutTmp.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setHigh(i);
            }

            public void onLast(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setLowSwitch(z);
            }
        });
    }

    private void initHumListener() {
        ((FragmentAutomationV4Binding) this.binding).rsbHum.setMin(0);
        ((FragmentAutomationV4Binding) this.binding).rsbHum.setDistance(100);
        ((FragmentAutomationV4Binding) this.binding).sbHighHum.setValue(0, 100);
        ((FragmentAutomationV4Binding) this.binding).sbLowHum.setValue(0, 100);
        ((FragmentAutomationV4Binding) this.binding).rsbHum.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).rsbHum.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowHum.setChecked(z);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighHum.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHum.setValue(Byte.valueOf((byte) i2));
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighHum.setProgress(i2);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowHum.setProgress(i);
                }
            }
        });
        C26027 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        };
        ((FragmentAutomationV4Binding) this.binding).sbHighHum.setFactory(r0);
        ((FragmentAutomationV4Binding) this.binding).sbLowHum.setFactory(r0);
        ((FragmentAutomationV4Binding) this.binding).layoutHum.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setHigh(i);
            }

            public void onLast(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setLowSwitch(z);
            }
        });
    }

    private void initVpdListener() {
        ((FragmentAutomationV4Binding) this.binding).rsbVpd.setMin(0);
        ((FragmentAutomationV4Binding) this.binding).rsbVpd.setDistance(99);
        ((FragmentAutomationV4Binding) this.binding).sbHighVpd.setValue(0, 99);
        ((FragmentAutomationV4Binding) this.binding).sbLowVpd.setValue(0, 99);
        ((FragmentAutomationV4Binding) this.binding).rsbVpd.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        });
        ((FragmentAutomationV4Binding) this.binding).rsbVpd.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowVpd.setChecked(z);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighVpd.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i2));
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighVpd.setProgress(i2);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowVpd.setProgress(i);
                }
            }
        });
        C256411 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        };
        ((FragmentAutomationV4Binding) this.binding).sbHighVpd.setFactory(r0);
        ((FragmentAutomationV4Binding) this.binding).sbLowVpd.setFactory(r0);
        ((FragmentAutomationV4Binding) this.binding).layoutVpd.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setHigh(i);
            }

            public void onLast(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setLowSwitch(z);
            }
        });
    }

    public void onDestroyView() {
        unregisterMessage();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (((AutomationModelV4) this.viewModel).saveDis != null && !((AutomationModelV4) this.viewModel).saveDis.isDisposed()) {
            ((AutomationModelV4) this.viewModel).saveDis.dispose();
        }
        super.onDestroyView();
    }

    private void initView(boolean z, boolean z2) {
        ArrayList<String> timeList = TimeUtil.getTimeList(1, 12, true);
        ArrayList<String> timeList2 = TimeUtil.getTimeList(0, 59, true);
        ArrayList newArrayList = Lists.newArrayList((E[]) new String[]{"AM", "PM"});
        initStartTime(timeList, timeList2, newArrayList);
        initEndTime(timeList, timeList2, newArrayList);
        this.cycleHours = TimeUtil.getTimeList(0, 24, true);
        this.cycleMinus = TimeUtil.getTimeList(0, 59, true);
        ArrayList<String> arrayList = new ArrayList<>();
        this.cycleMinus24 = arrayList;
        arrayList.add("00");
        initCycleOnTime(this.cycleHours, this.cycleMinus);
        initCycleOffTime(this.cycleHours, this.cycleMinus);
        if (z2) {
            ((FragmentAutomationV4Binding) this.binding).btnDelete.setVisibility(0);
        }
        initLevelListener();
    }

    private void initLevelListener() {
        ((FragmentAutomationV4Binding) this.binding).fpbOff.setStyle("", "0", "10", 0, 10);
        ((FragmentAutomationV4Binding) this.binding).fpbOff.setListener(new FanProgressBar.Listener() {
            public void onDown(boolean z) {
            }

            public void onUp(boolean z, int i) {
            }

            public void onChange(boolean z, int i) {
                if (z) {
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).levelOff.setValue(Integer.valueOf(i));
                }
            }
        });
        ((FragmentAutomationV4Binding) this.binding).fpbOn.setStyle("", "0", "10", 0, 10);
        ((FragmentAutomationV4Binding) this.binding).fpbOn.setListener(new FanProgressBar.Listener() {
            public void onDown(boolean z) {
            }

            public void onUp(boolean z, int i) {
            }

            public void onChange(boolean z, int i) {
                if (z) {
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).levelOn.setValue(Integer.valueOf(i));
                }
            }
        });
    }

    public void onConfirm() {
        ((AutomationModelV4) this.viewModel).onConfirm.execute();
    }

    public void onCancel() {
        ((AutomationModelV4) this.viewModel).onCancel.execute();
    }

    private void initStartTime(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setData(arrayList);
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvStartHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).startHour.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setData(arrayList2);
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvStartMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).startMin.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setCyclic(false);
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setData(arrayList3);
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvStartAmOrPm.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).startAp.setValue(Byte.valueOf((byte) i));
            }
        });
    }

    private void initEndTime(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setData(arrayList);
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvEndHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).endHour.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setData(arrayList2);
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvEndMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).endMin.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setCyclic(false);
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setData(arrayList3);
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvEndAmOrPm.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).endAp.setValue(Byte.valueOf((byte) i));
            }
        });
    }

    private void initCycleOnTime(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setData(arrayList);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                if (i == AutomationFragmentV4.this.cycleHours.size() - 1) {
                    if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.getData().size() != 1) {
                        ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.setCyclic(false);
                        ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.setData(AutomationFragmentV4.this.cycleMinus24);
                        ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOn.setValue(Character.valueOf((char) (i * 60)));
                        return;
                    }
                } else if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.getData().size() == 1) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.setCyclic(true);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.setData(AutomationFragmentV4.this.cycleMinus);
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOn.setValue(Character.valueOf((char) (i * 60)));
                    return;
                }
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOn.setValue(Character.valueOf((char) ((i * 60) + ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.getSelectedItemPosition())));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setData(arrayList2);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOnMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOn.setValue(Character.valueOf((char) ((((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnHour.getSelectedItemPosition() * 60) + i)));
            }
        });
    }

    private WheelView.WheelViewStyle createWheelViewStyle(int i) {
        WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
        wheelViewStyle.textColor = getContext().getResources().getColor(C2420R.C2421color.color_707070);
        wheelViewStyle.selectedTextColor = getContext().getResources().getColor(i);
        wheelViewStyle.backgroundColor = getContext().getResources().getColor(C2420R.C2421color.color_transparency);
        wheelViewStyle.textSize = getResources().getDimensionPixelSize(C2420R.dimen.dp_18);
        wheelViewStyle.selectedTextSize = getResources().getDimensionPixelSize(C2420R.dimen.dp_18);
        return wheelViewStyle;
    }

    private void initCycleOffTime(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setData(arrayList);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                if (i == AutomationFragmentV4.this.cycleHours.size() - 1) {
                    if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.getData().size() != 1) {
                        ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.setCyclic(false);
                        ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.setData(AutomationFragmentV4.this.cycleMinus24);
                        ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOff.setValue(Character.valueOf((char) (i * 60)));
                        return;
                    }
                } else if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.getData().size() == 1) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.setCyclic(true);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.setData(AutomationFragmentV4.this.cycleMinus);
                    ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOff.setValue(Character.valueOf((char) (i * 60)));
                    return;
                }
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOff.setValue(Character.valueOf((char) ((i * 60) + ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.getSelectedItemPosition())));
            }
        });
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setCyclic(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setData(arrayList2);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setVisibleItems(3);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setResetSelectedPosition(true);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setCurved(false);
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationV4Binding) this.binding).wvCycleOffMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycleOff.setValue(Character.valueOf((char) ((((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffHour.getSelectedItemPosition() * 60) + i)));
            }
        });
    }

    private void bindEvent() {
        bindProgress();
        bindTime();
        bindCycle();
        bindLevel();
    }

    private void bindProgress() {
        this.now = ((AutomationModelV4) this.viewModel).f216on;
        ((AutomationModelV4) this.viewModel).f216on.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragmentV4.this.now != ((AutomationModelV4) AutomationFragmentV4.this.viewModel).f216on) {
                        AutomationFragmentV4.this.now.setValue(false);
                        AutomationFragmentV4 automationFragmentV4 = AutomationFragmentV4.this;
                        MutableLiveData unused = automationFragmentV4.now = ((AutomationModelV4) automationFragmentV4.viewModel).f216on;
                    }
                    AutomationFragmentV4 automationFragmentV42 = AutomationFragmentV4.this;
                    automationFragmentV42.addLevelView(((FragmentAutomationV4Binding) automationFragmentV42.binding).llOnContainer);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elOn.expand();
                    return;
                }
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elOn.collapse();
            }
        });
        ((AutomationModelV4) this.viewModel).off.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE && AutomationFragmentV4.this.now != ((AutomationModelV4) AutomationFragmentV4.this.viewModel).off) {
                    AutomationFragmentV4.this.now.setValue(false);
                    AutomationFragmentV4 automationFragmentV4 = AutomationFragmentV4.this;
                    MutableLiveData unused = automationFragmentV4.now = ((AutomationModelV4) automationFragmentV4.viewModel).off;
                    AutomationFragmentV4.this.removeLevelView();
                }
            }
        });
        ((AutomationModelV4) this.viewModel).cycle.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragmentV4.this.now != ((AutomationModelV4) AutomationFragmentV4.this.viewModel).cycle) {
                        AutomationFragmentV4.this.now.setValue(false);
                        AutomationFragmentV4 automationFragmentV4 = AutomationFragmentV4.this;
                        MutableLiveData unused = automationFragmentV4.now = ((AutomationModelV4) automationFragmentV4.viewModel).cycle;
                    }
                    AutomationFragmentV4 automationFragmentV42 = AutomationFragmentV4.this;
                    automationFragmentV42.addLevelView(((FragmentAutomationV4Binding) automationFragmentV42.binding).llCycleContainer);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elCycle.expand();
                    return;
                }
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elCycle.collapse();
            }
        });
        ((AutomationModelV4) this.viewModel).tmphum.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragmentV4.this.now != ((AutomationModelV4) AutomationFragmentV4.this.viewModel).tmphum) {
                        AutomationFragmentV4.this.now.setValue(false);
                        AutomationFragmentV4 automationFragmentV4 = AutomationFragmentV4.this;
                        MutableLiveData unused = automationFragmentV4.now = ((AutomationModelV4) automationFragmentV4.viewModel).tmphum;
                    }
                    AutomationFragmentV4 automationFragmentV42 = AutomationFragmentV4.this;
                    automationFragmentV42.addLevelView(((FragmentAutomationV4Binding) automationFragmentV42.binding).llTmphumContainer);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elTmpHum.expand();
                    return;
                }
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elTmpHum.collapse();
            }
        });
        ((AutomationModelV4) this.viewModel).vpd.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragmentV4.this.now != ((AutomationModelV4) AutomationFragmentV4.this.viewModel).vpd) {
                        AutomationFragmentV4.this.now.setValue(false);
                        AutomationFragmentV4 automationFragmentV4 = AutomationFragmentV4.this;
                        MutableLiveData unused = automationFragmentV4.now = ((AutomationModelV4) automationFragmentV4.viewModel).vpd;
                    }
                    AutomationFragmentV4 automationFragmentV42 = AutomationFragmentV4.this;
                    automationFragmentV42.addLevelView(((FragmentAutomationV4Binding) automationFragmentV42.binding).llVpdContainer);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elVpd.expand();
                    return;
                }
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).elVpd.collapse();
            }
        });
    }

    /* access modifiers changed from: private */
    public void addLevelView(LinearLayout linearLayout) {
        removeLevelView();
        linearLayout.addView(((FragmentAutomationV4Binding) this.binding).llLevel);
        ((AutomationModelV4) this.viewModel).levelOffVisible.setValue(Boolean.valueOf(linearLayout != ((FragmentAutomationV4Binding) this.binding).llOnContainer));
    }

    /* access modifiers changed from: private */
    public void removeLevelView() {
        ViewGroup viewGroup = (ViewGroup) ((FragmentAutomationV4Binding) this.binding).llLevel.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(((FragmentAutomationV4Binding) this.binding).llLevel);
        }
    }

    private void bindTime() {
        ((AutomationModelV4) this.viewModel).startHour.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvStartHour.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvStartHour.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModelV4) this.viewModel).startMin.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvStartMin.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvStartMin.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModelV4) this.viewModel).startAp.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvStartAmOrPm.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvStartAmOrPm.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModelV4) this.viewModel).endHour.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvEndHour.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvEndHour.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModelV4) this.viewModel).endMin.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvEndMin.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvEndMin.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModelV4) this.viewModel).endAp.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvEndAmOrPm.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvEndAmOrPm.setSelectedItemPosition(b.byteValue());
                }
            }
        });
    }

    private void bindCycle() {
        ((AutomationModelV4) this.viewModel).cycleOn.observe(this, new Observer<Character>() {
            public void onChanged(Character ch) {
                if ((((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnHour.getSelectedItemPosition() * 60) + ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.getSelectedItemPosition() != ch.charValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnHour.setSelectedItemPosition(ch.charValue() / Typography.less);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOnMin.setSelectedItemPosition(ch.charValue() % Typography.less);
                }
            }
        });
        ((AutomationModelV4) this.viewModel).cycleOff.observe(this, new Observer<Character>() {
            public void onChanged(Character ch) {
                if ((((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffHour.getSelectedItemPosition() * 60) + ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.getSelectedItemPosition() != ch.charValue()) {
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffHour.setSelectedItemPosition(ch.charValue() / Typography.less);
                    ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).wvCycleOffMin.setSelectedItemPosition(ch.charValue() % Typography.less);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void bindLevel() {
        ((AutomationModelV4) this.viewModel).levelOff.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).fpbOff.setProgress(num.intValue());
            }
        });
        ((AutomationModelV4) this.viewModel).levelOn.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).fpbOn.setProgress(num.intValue());
            }
        });
    }

    private void registerMessage() {
        Messenger.getDefault().register((Object) this, (Object) NotificationActivity.SHOW_DELETE_DIALOG, (BindingAction) new BindingAction() {
            public void call() {
                AutomationFragmentV4.this.showDelete();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_PARAM, (BindingAction) new BindingAction() {
            public void call() {
                boolean z = true;
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowTmp.setChecked(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighTmp.setChecked(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowTmp.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmp.getValue().charValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighTmp.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmp.getValue().charValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setHighSwitch(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setLowSwitch(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbTemp.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hTmp.getValue().charValue(), ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lTmp.getValue().charValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowHum.setChecked(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighHum.setChecked(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowHum.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHum.getValue().byteValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighHum.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHum.getValue().byteValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setHighSwitch(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setLowSwitch(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbHum.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hHum.getValue().byteValue(), ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lHum.getValue().byteValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowVpd.setChecked(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighVpd.setChecked(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbLowVpd.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpd.getValue().byteValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).sbHighVpd.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpd.getValue().byteValue());
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setHighSwitch(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                RangeSeekBar rangeSeekBar = ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd;
                if (((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpdSwitch.getValue() != Boolean.TRUE) {
                    z = false;
                }
                rangeSeekBar.setLowSwitch(z);
                ((FragmentAutomationV4Binding) AutomationFragmentV4.this.binding).rsbVpd.setProgress(((AutomationModelV4) AutomationFragmentV4.this.viewModel).hVpd.getValue().byteValue(), ((AutomationModelV4) AutomationFragmentV4.this.viewModel).lVpd.getValue().byteValue());
            }
        });
    }

    private void unregisterMessage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    public void showDelete() {
        this.dialog = TipDialog.showTipDialog(getContext(), getString(C2420R.string.tip_remove_adv_title, ((AutomationModelV4) this.viewModel).getNotifyName()), getString(C2420R.string.tip_remove_adv_content, ((AutomationModelV4) this.viewModel).getNotifyName()), getResources().getString(C2420R.string.tip_cancel_lowercase), getResources().getString(C2420R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ((AutomationModelV4) AutomationFragmentV4.this.viewModel).delete();
            }
        });
    }

    public void onSoftKeyboardOpened(int i) {
        ((FragmentAutomationV4Binding) this.binding).etEditName.setCursorVisible(true);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(((FragmentAutomationV4Binding) this.binding).etEditName, 0);
            ((FragmentAutomationV4Binding) this.binding).etEditName.setSelection(((FragmentAutomationV4Binding) this.binding).etEditName.getText().length());
        }
    }

    public void onSoftKeyboardClosed() {
        ((FragmentAutomationV4Binding) this.binding).etEditName.setCursorVisible(false);
        ((FragmentAutomationV4Binding) this.binding).etEditName.clearFocus();
        if (((AutomationModelV4) this.viewModel).name.getValue() == null || TextUtils.isEmpty(((AutomationModelV4) this.viewModel).name.getValue().trim())) {
            showFailDialog(Utils.getString(C2420R.string.tip_empty_name));
            ((AutomationModelV4) this.viewModel).name.setValue(((AutomationModelV4) this.viewModel).originName);
        }
    }

    public void closeKeyboard() {
        SoftKeyBroadManager.closeKeyboard(getContext(), ((FragmentAutomationV4Binding) this.binding).etEditName);
    }

    public void setConnectType(String str, byte b) {
        ((AutomationModelV4) this.viewModel).devId = str;
        ((AutomationModelV4) this.viewModel).connectType = b;
    }
}
