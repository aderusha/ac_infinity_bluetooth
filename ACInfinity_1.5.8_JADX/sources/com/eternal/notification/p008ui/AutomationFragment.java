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
import com.eternal.notification.databinding.FragmentAutomationBinding;
import com.eternal.notification.model.AutomationModel;
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

/* renamed from: com.eternal.notification.ui.AutomationFragment */
public class AutomationFragment extends BaseFragment<FragmentAutomationBinding, AutomationModel> implements ISoftKeyBoardAction {
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
    boolean isDegree;
    String mac;
    /* access modifiers changed from: private */
    public MutableLiveData<Boolean> now;
    byte offLevel;
    byte onLevel;
    byte port;

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C2420R.layout.fragment_automation;
    }

    public int initVariableId() {
        return C2419BR.autoModel;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        String string = arguments.getString(ActivityEvent.NOTIFICATION);
        initListener();
        initView(this.isDegree, string != null);
        registerMessage();
        bindEvent();
        ((AutomationModel) this.viewModel).init(RepositoryInjection.providerNotificationRepository(), this.mac, arguments.getString(ActivityEvent.DEVICE_ID), this.port, this.deviceType, this.deviceVersion, (Notification) GsonUtils.fromJson(string, Notification.class), this.isDegree, this.onLevel, this.offLevel, arguments.getByte(ActivityEvent.DEVICE_CONNECT_TYPE));
    }

    public void initListener() {
        initTmpListener();
        initHumListener();
        initVpdListener();
    }

    private void initTmpListener() {
        if (this.isDegree) {
            ((FragmentAutomationBinding) this.binding).tvTempMin.setText("0℃");
            ((FragmentAutomationBinding) this.binding).tvTempMax.setText("90℃");
            ((AutomationModel) this.viewModel).tmpUnit.setValue(Utils.getString(C2420R.string.tip_degree_num));
            ((FragmentAutomationBinding) this.binding).rsbTemp.setMin(0);
            ((FragmentAutomationBinding) this.binding).rsbTemp.setDistance(90);
            ((FragmentAutomationBinding) this.binding).sbHighTmp.setValue(0, 90);
            ((FragmentAutomationBinding) this.binding).sbLowTmp.setValue(0, 90);
        } else {
            ((FragmentAutomationBinding) this.binding).tvTempMin.setText("32℉");
            ((FragmentAutomationBinding) this.binding).tvTempMax.setText("194℉");
            ((FragmentAutomationBinding) this.binding).rsbTemp.setMin(32);
            ((FragmentAutomationBinding) this.binding).rsbTemp.setDistance(162);
            ((FragmentAutomationBinding) this.binding).sbHighTmp.setValue(32, 162);
            ((FragmentAutomationBinding) this.binding).sbLowTmp.setValue(32, 162);
        }
        ((FragmentAutomationBinding) this.binding).rsbTemp.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                if (((AutomationModel) AutomationFragment.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        });
        ((FragmentAutomationBinding) this.binding).rsbTemp.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((AutomationModel) AutomationFragment.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((AutomationModel) AutomationFragment.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowTmp.setChecked(z);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighTmp.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    char c = (char) i2;
                    ((AutomationModel) AutomationFragment.this.viewModel).hTmp.setValue(Character.valueOf(c));
                    char c2 = (char) i;
                    ((AutomationModel) AutomationFragment.this.viewModel).lTmp.setValue(Character.valueOf(c2));
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighTmp.setProgress(c);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowTmp.setProgress(c2);
                }
            }
        });
        C25413 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                if (((AutomationModel) AutomationFragment.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        };
        ((FragmentAutomationBinding) this.binding).sbHighTmp.setFactory(r0);
        ((FragmentAutomationBinding) this.binding).sbLowTmp.setFactory(r0);
        ((FragmentAutomationBinding) this.binding).layoutTmp.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setHigh(i);
            }

            public void onLast(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((AutomationModel) AutomationFragment.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((AutomationModel) AutomationFragment.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setLowSwitch(z);
            }
        });
    }

    private void initHumListener() {
        ((FragmentAutomationBinding) this.binding).rsbHum.setMin(0);
        ((FragmentAutomationBinding) this.binding).rsbHum.setDistance(100);
        ((FragmentAutomationBinding) this.binding).sbHighHum.setValue(0, 100);
        ((FragmentAutomationBinding) this.binding).sbLowHum.setValue(0, 100);
        ((FragmentAutomationBinding) this.binding).rsbHum.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        });
        ((FragmentAutomationBinding) this.binding).rsbHum.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((AutomationModel) AutomationFragment.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((AutomationModel) AutomationFragment.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowHum.setChecked(z);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighHum.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((AutomationModel) AutomationFragment.this.viewModel).hHum.setValue(Byte.valueOf((byte) i2));
                    ((AutomationModel) AutomationFragment.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighHum.setProgress(i2);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowHum.setProgress(i);
                }
            }
        });
        C25597 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        };
        ((FragmentAutomationBinding) this.binding).sbHighHum.setFactory(r0);
        ((FragmentAutomationBinding) this.binding).sbLowHum.setFactory(r0);
        ((FragmentAutomationBinding) this.binding).layoutHum.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setHigh(i);
            }

            public void onLast(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((AutomationModel) AutomationFragment.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((AutomationModel) AutomationFragment.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setLowSwitch(z);
            }
        });
    }

    private void initVpdListener() {
        ((FragmentAutomationBinding) this.binding).rsbVpd.setMin(0);
        ((FragmentAutomationBinding) this.binding).rsbVpd.setDistance(99);
        ((FragmentAutomationBinding) this.binding).sbHighVpd.setValue(0, 99);
        ((FragmentAutomationBinding) this.binding).sbLowVpd.setValue(0, 99);
        ((FragmentAutomationBinding) this.binding).rsbVpd.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        });
        ((FragmentAutomationBinding) this.binding).rsbVpd.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((AutomationModel) AutomationFragment.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((AutomationModel) AutomationFragment.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowVpd.setChecked(z);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighVpd.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((AutomationModel) AutomationFragment.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i2));
                    ((AutomationModel) AutomationFragment.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighVpd.setProgress(i2);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowVpd.setProgress(i);
                }
            }
        });
        C252111 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        };
        ((FragmentAutomationBinding) this.binding).sbHighVpd.setFactory(r0);
        ((FragmentAutomationBinding) this.binding).sbLowVpd.setFactory(r0);
        ((FragmentAutomationBinding) this.binding).layoutVpd.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setHigh(i);
            }

            public void onLast(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((AutomationModel) AutomationFragment.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((AutomationModel) AutomationFragment.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setLowSwitch(z);
            }
        });
    }

    public void onDestroyView() {
        unregisterMessage();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (((AutomationModel) this.viewModel).saveDis != null && !((AutomationModel) this.viewModel).saveDis.isDisposed()) {
            ((AutomationModel) this.viewModel).saveDis.dispose();
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
            ((FragmentAutomationBinding) this.binding).btnDelete.setVisibility(0);
        }
        initLevelListener();
    }

    private void initLevelListener() {
        ((FragmentAutomationBinding) this.binding).fpbOff.setStyle("", "0", "10", 0, 10);
        ((FragmentAutomationBinding) this.binding).fpbOff.setListener(new FanProgressBar.Listener() {
            public void onDown(boolean z) {
            }

            public void onUp(boolean z, int i) {
            }

            public void onChange(boolean z, int i) {
                if (z) {
                    ((AutomationModel) AutomationFragment.this.viewModel).levelOff.setValue(Integer.valueOf(i));
                }
            }
        });
        ((FragmentAutomationBinding) this.binding).fpbOn.setStyle("", "0", "10", 0, 10);
        ((FragmentAutomationBinding) this.binding).fpbOn.setListener(new FanProgressBar.Listener() {
            public void onDown(boolean z) {
            }

            public void onUp(boolean z, int i) {
            }

            public void onChange(boolean z, int i) {
                if (z) {
                    ((AutomationModel) AutomationFragment.this.viewModel).levelOn.setValue(Integer.valueOf(i));
                }
            }
        });
    }

    public void onConfirm() {
        ((AutomationModel) this.viewModel).onConfirm.execute();
    }

    public void onCancel() {
        ((AutomationModel) this.viewModel).onCancel.execute();
    }

    private void initStartTime(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        ((FragmentAutomationBinding) this.binding).wvStartHour.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvStartHour.setData(arrayList);
        ((FragmentAutomationBinding) this.binding).wvStartHour.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvStartHour.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvStartHour.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvStartHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvStartHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvStartHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationBinding) this.binding).wvStartHour.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvStartHour.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvStartHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).startHour.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationBinding) this.binding).wvStartMin.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvStartMin.setData(arrayList2);
        ((FragmentAutomationBinding) this.binding).wvStartMin.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvStartMin.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvStartMin.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvStartMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvStartMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvStartMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationBinding) this.binding).wvStartMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvStartMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvStartMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).startMin.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setCyclic(false);
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setData(arrayList3);
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvStartAmOrPm.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).startAp.setValue(Byte.valueOf((byte) i));
            }
        });
    }

    private void initEndTime(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        ((FragmentAutomationBinding) this.binding).wvEndHour.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvEndHour.setData(arrayList);
        ((FragmentAutomationBinding) this.binding).wvEndHour.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvEndHour.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvEndHour.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvEndHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvEndHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvEndHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationBinding) this.binding).wvEndHour.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvEndHour.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvEndHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).endHour.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationBinding) this.binding).wvEndMin.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvEndMin.setData(arrayList2);
        ((FragmentAutomationBinding) this.binding).wvEndMin.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvEndMin.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvEndMin.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvEndMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvEndMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvEndMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationBinding) this.binding).wvEndMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvEndMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvEndMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).endMin.setValue(Byte.valueOf((byte) i));
            }
        });
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setCyclic(false);
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setData(arrayList3);
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvEndAmOrPm.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).endAp.setValue(Byte.valueOf((byte) i));
            }
        });
    }

    private void initCycleOnTime(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setData(arrayList);
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvCycleOnHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                if (i == AutomationFragment.this.cycleHours.size() - 1) {
                    if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.getData().size() != 1) {
                        ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.setCyclic(false);
                        ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.setData(AutomationFragment.this.cycleMinus24);
                        ((AutomationModel) AutomationFragment.this.viewModel).cycleOn.setValue(Character.valueOf((char) (i * 60)));
                        return;
                    }
                } else if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.getData().size() == 1) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.setCyclic(true);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.setData(AutomationFragment.this.cycleMinus);
                    ((AutomationModel) AutomationFragment.this.viewModel).cycleOn.setValue(Character.valueOf((char) (i * 60)));
                    return;
                }
                ((AutomationModel) AutomationFragment.this.viewModel).cycleOn.setValue(Character.valueOf((char) ((i * 60) + ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.getSelectedItemPosition())));
            }
        });
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setData(arrayList2);
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_15BAFF));
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvCycleOnMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).cycleOn.setValue(Character.valueOf((char) ((((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnHour.getSelectedItemPosition() * 60) + i)));
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
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setData(arrayList);
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvCycleOffHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                if (i == AutomationFragment.this.cycleHours.size() - 1) {
                    if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.getData().size() != 1) {
                        ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.setCyclic(false);
                        ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.setData(AutomationFragment.this.cycleMinus24);
                        ((AutomationModel) AutomationFragment.this.viewModel).cycleOff.setValue(Character.valueOf((char) (i * 60)));
                        return;
                    }
                } else if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.getData().size() == 1) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.setCyclic(true);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.setData(AutomationFragment.this.cycleMinus);
                    ((AutomationModel) AutomationFragment.this.viewModel).cycleOff.setValue(Character.valueOf((char) (i * 60)));
                    return;
                }
                ((AutomationModel) AutomationFragment.this.viewModel).cycleOff.setValue(Character.valueOf((char) ((i * 60) + ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.getSelectedItemPosition())));
            }
        });
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setCyclic(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setData(arrayList2);
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setVisibleItems(3);
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setResetSelectedPosition(true);
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setCurved(false);
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setLineSpacing(getResources().getDimension(C2420R.dimen.dp_23));
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2420R.font.avenir_medium));
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setSelectedItemTextColor(getResources().getColor(C2420R.C2421color.color_FF6A6A));
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setNormalItemTextColor(getResources().getColor(C2420R.C2421color.color_707070));
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setTextSize(getResources().getDimension(C2420R.dimen.dp_18));
        ((FragmentAutomationBinding) this.binding).wvCycleOffMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelItemChanged(int i, int i2) {
            }

            public void onWheelScroll(int i) {
            }

            public void onWheelScrollStateChanged(int i) {
            }

            public void onWheelSelected(int i) {
                ((AutomationModel) AutomationFragment.this.viewModel).cycleOff.setValue(Character.valueOf((char) ((((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffHour.getSelectedItemPosition() * 60) + i)));
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
        this.now = ((AutomationModel) this.viewModel).f215on;
        ((AutomationModel) this.viewModel).f215on.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragment.this.now != ((AutomationModel) AutomationFragment.this.viewModel).f215on) {
                        AutomationFragment.this.now.setValue(false);
                        AutomationFragment automationFragment = AutomationFragment.this;
                        MutableLiveData unused = automationFragment.now = ((AutomationModel) automationFragment.viewModel).f215on;
                    }
                    AutomationFragment automationFragment2 = AutomationFragment.this;
                    automationFragment2.addLevelView(((FragmentAutomationBinding) automationFragment2.binding).llOnContainer);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).elOn.expand();
                    return;
                }
                ((FragmentAutomationBinding) AutomationFragment.this.binding).elOn.collapse();
            }
        });
        ((AutomationModel) this.viewModel).off.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE && AutomationFragment.this.now != ((AutomationModel) AutomationFragment.this.viewModel).off) {
                    AutomationFragment.this.now.setValue(false);
                    AutomationFragment automationFragment = AutomationFragment.this;
                    MutableLiveData unused = automationFragment.now = ((AutomationModel) automationFragment.viewModel).off;
                    AutomationFragment.this.removeLevelView();
                }
            }
        });
        ((AutomationModel) this.viewModel).cycle.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragment.this.now != ((AutomationModel) AutomationFragment.this.viewModel).cycle) {
                        AutomationFragment.this.now.setValue(false);
                        AutomationFragment automationFragment = AutomationFragment.this;
                        MutableLiveData unused = automationFragment.now = ((AutomationModel) automationFragment.viewModel).cycle;
                    }
                    AutomationFragment automationFragment2 = AutomationFragment.this;
                    automationFragment2.addLevelView(((FragmentAutomationBinding) automationFragment2.binding).llCycleContainer);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).elCycle.expand();
                    return;
                }
                ((FragmentAutomationBinding) AutomationFragment.this.binding).elCycle.collapse();
            }
        });
        ((AutomationModel) this.viewModel).tmphum.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragment.this.now != ((AutomationModel) AutomationFragment.this.viewModel).tmphum) {
                        AutomationFragment.this.now.setValue(false);
                        AutomationFragment automationFragment = AutomationFragment.this;
                        MutableLiveData unused = automationFragment.now = ((AutomationModel) automationFragment.viewModel).tmphum;
                    }
                    AutomationFragment automationFragment2 = AutomationFragment.this;
                    automationFragment2.addLevelView(((FragmentAutomationBinding) automationFragment2.binding).llTmphumContainer);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).elTmpHum.expand();
                    return;
                }
                ((FragmentAutomationBinding) AutomationFragment.this.binding).elTmpHum.collapse();
            }
        });
        ((AutomationModel) this.viewModel).vpd.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (AutomationFragment.this.now != ((AutomationModel) AutomationFragment.this.viewModel).vpd) {
                        AutomationFragment.this.now.setValue(false);
                        AutomationFragment automationFragment = AutomationFragment.this;
                        MutableLiveData unused = automationFragment.now = ((AutomationModel) automationFragment.viewModel).vpd;
                    }
                    AutomationFragment automationFragment2 = AutomationFragment.this;
                    automationFragment2.addLevelView(((FragmentAutomationBinding) automationFragment2.binding).llVpdContainer);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).elVpd.expand();
                    return;
                }
                ((FragmentAutomationBinding) AutomationFragment.this.binding).elVpd.collapse();
            }
        });
    }

    /* access modifiers changed from: private */
    public void addLevelView(LinearLayout linearLayout) {
        removeLevelView();
        linearLayout.addView(((FragmentAutomationBinding) this.binding).llLevel);
        ((AutomationModel) this.viewModel).levelOffVisible.setValue(Boolean.valueOf(linearLayout != ((FragmentAutomationBinding) this.binding).llOnContainer));
    }

    /* access modifiers changed from: private */
    public void removeLevelView() {
        ViewGroup viewGroup = (ViewGroup) ((FragmentAutomationBinding) this.binding).llLevel.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(((FragmentAutomationBinding) this.binding).llLevel);
        }
    }

    private void bindTime() {
        ((AutomationModel) this.viewModel).startHour.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvStartHour.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvStartHour.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModel) this.viewModel).startMin.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvStartMin.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvStartMin.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModel) this.viewModel).startAp.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvStartAmOrPm.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvStartAmOrPm.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModel) this.viewModel).endHour.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvEndHour.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvEndHour.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModel) this.viewModel).endMin.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvEndMin.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvEndMin.setSelectedItemPosition(b.byteValue());
                }
            }
        });
        ((AutomationModel) this.viewModel).endAp.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                if (((FragmentAutomationBinding) AutomationFragment.this.binding).wvEndAmOrPm.getSelectedItemPosition() != b.byteValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvEndAmOrPm.setSelectedItemPosition(b.byteValue());
                }
            }
        });
    }

    private void bindCycle() {
        ((AutomationModel) this.viewModel).cycleOn.observe(this, new Observer<Character>() {
            public void onChanged(Character ch) {
                if ((((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnHour.getSelectedItemPosition() * 60) + ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.getSelectedItemPosition() != ch.charValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnHour.setSelectedItemPosition(ch.charValue() / Typography.less);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOnMin.setSelectedItemPosition(ch.charValue() % Typography.less);
                }
            }
        });
        ((AutomationModel) this.viewModel).cycleOff.observe(this, new Observer<Character>() {
            public void onChanged(Character ch) {
                if ((((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffHour.getSelectedItemPosition() * 60) + ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.getSelectedItemPosition() != ch.charValue()) {
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffHour.setSelectedItemPosition(ch.charValue() / Typography.less);
                    ((FragmentAutomationBinding) AutomationFragment.this.binding).wvCycleOffMin.setSelectedItemPosition(ch.charValue() % Typography.less);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void bindLevel() {
        ((AutomationModel) this.viewModel).levelOff.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ((FragmentAutomationBinding) AutomationFragment.this.binding).fpbOff.setProgress(num.intValue());
            }
        });
        ((AutomationModel) this.viewModel).levelOn.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ((FragmentAutomationBinding) AutomationFragment.this.binding).fpbOn.setProgress(num.intValue());
            }
        });
    }

    private void registerMessage() {
        Messenger.getDefault().register((Object) this, (Object) NotificationActivity.SHOW_DELETE_DIALOG, (BindingAction) new BindingAction() {
            public void call() {
                AutomationFragment.this.showDelete();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_PARAM, (BindingAction) new BindingAction() {
            public void call() {
                boolean z = true;
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowTmp.setChecked(((AutomationModel) AutomationFragment.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighTmp.setChecked(((AutomationModel) AutomationFragment.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowTmp.setProgress(((AutomationModel) AutomationFragment.this.viewModel).lTmp.getValue().charValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighTmp.setProgress(((AutomationModel) AutomationFragment.this.viewModel).hTmp.getValue().charValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setHighSwitch(((AutomationModel) AutomationFragment.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setLowSwitch(((AutomationModel) AutomationFragment.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbTemp.setProgress(((AutomationModel) AutomationFragment.this.viewModel).hTmp.getValue().charValue(), ((AutomationModel) AutomationFragment.this.viewModel).lTmp.getValue().charValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowHum.setChecked(((AutomationModel) AutomationFragment.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighHum.setChecked(((AutomationModel) AutomationFragment.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowHum.setProgress(((AutomationModel) AutomationFragment.this.viewModel).lHum.getValue().byteValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighHum.setProgress(((AutomationModel) AutomationFragment.this.viewModel).hHum.getValue().byteValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setHighSwitch(((AutomationModel) AutomationFragment.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setLowSwitch(((AutomationModel) AutomationFragment.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbHum.setProgress(((AutomationModel) AutomationFragment.this.viewModel).hHum.getValue().byteValue(), ((AutomationModel) AutomationFragment.this.viewModel).lHum.getValue().byteValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowVpd.setChecked(((AutomationModel) AutomationFragment.this.viewModel).lVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighVpd.setChecked(((AutomationModel) AutomationFragment.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbLowVpd.setProgress(((AutomationModel) AutomationFragment.this.viewModel).lVpd.getValue().byteValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).sbHighVpd.setProgress(((AutomationModel) AutomationFragment.this.viewModel).hVpd.getValue().byteValue());
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setHighSwitch(((AutomationModel) AutomationFragment.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                RangeSeekBar rangeSeekBar = ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd;
                if (((AutomationModel) AutomationFragment.this.viewModel).lVpdSwitch.getValue() != Boolean.TRUE) {
                    z = false;
                }
                rangeSeekBar.setLowSwitch(z);
                ((FragmentAutomationBinding) AutomationFragment.this.binding).rsbVpd.setProgress(((AutomationModel) AutomationFragment.this.viewModel).hVpd.getValue().byteValue(), ((AutomationModel) AutomationFragment.this.viewModel).lVpd.getValue().byteValue());
            }
        });
    }

    private void unregisterMessage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    public void showDelete() {
        this.dialog = TipDialog.showTipDialog(getContext(), getString(C2420R.string.tip_remove_adv_title, ((AutomationModel) this.viewModel).getNotifyName()), getString(C2420R.string.tip_remove_adv_content, ((AutomationModel) this.viewModel).getNotifyName()), getResources().getString(C2420R.string.tip_cancel_lowercase), getResources().getString(C2420R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ((AutomationModel) AutomationFragment.this.viewModel).delete();
            }
        });
    }

    public void onSoftKeyboardOpened(int i) {
        ((FragmentAutomationBinding) this.binding).etEditName.setCursorVisible(true);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(((FragmentAutomationBinding) this.binding).etEditName, 0);
            ((FragmentAutomationBinding) this.binding).etEditName.setSelection(((FragmentAutomationBinding) this.binding).etEditName.getText().length());
        }
    }

    public void onSoftKeyboardClosed() {
        ((FragmentAutomationBinding) this.binding).etEditName.setCursorVisible(false);
        ((FragmentAutomationBinding) this.binding).etEditName.clearFocus();
        if (((AutomationModel) this.viewModel).name.getValue() == null || TextUtils.isEmpty(((AutomationModel) this.viewModel).name.getValue().trim())) {
            showFailDialog(Utils.getString(C2420R.string.tip_empty_name));
            ((AutomationModel) this.viewModel).name.setValue(((AutomationModel) this.viewModel).originName);
        }
    }

    public void closeKeyboard() {
        SoftKeyBroadManager.closeKeyboard(getContext(), ((FragmentAutomationBinding) this.binding).etEditName);
    }
}
