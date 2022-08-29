package com.eternal.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.alibaba.android.arouter.utils.Consts;
import com.eternal.base.BaseFragment;
import com.eternal.base.IConnectAction;
import com.eternal.base.ILevel;
import com.eternal.base.ITFPAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.GuQiangUtil;
import com.eternal.control.databinding.FragmentControlBinding;
import com.eternal.control.view.GuQiangCycleDialView;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.DoubleCloseAddLayoutListener;
import com.eternal.widget.guqiang.FanProgressBar;
import com.eternal.widget.guqiang.IEffectBar;
import com.eternal.widget.wheelview.common.WheelConstants;
import com.google.android.material.tabs.TabLayout;
import com.google.common.primitives.UnsignedBytes;
import com.jakewharton.rxbinding2.view.RxView;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class ControlFragment extends BaseFragment<FragmentControlBinding, ControlModel> implements IConnectAction, IToolBarAction, ILevel, ITFPAction {
    public static final long ANIMATE_DURATION = 1000;
    public static final String SCROLL_TO_TOP = "scroll to top";
    private Disposable automationSubs;
    byte connectType;
    String devId;
    /* access modifiers changed from: private */
    public DecimalFormat format;
    /* access modifiers changed from: private */
    public boolean isFirstControl = false;
    private int lastX;
    private int lastY;
    String mac;
    private View now;
    private IEffectBar.Factory schedFactory;
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

        public void onTabUnselected(TabLayout.Tab tab) {
        }

        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getTag() != null && ((FragmentControlBinding) ControlFragment.this.binding).tbPort.getTabCount() > 1) {
                ((ControlModel) ControlFragment.this.viewModel).choosePort(((Byte) tab.getTag()).byteValue());
            }
        }
    };
    private TranslateAnimation translateAniHide;
    private TranslateAnimation translateAniShow;
    /* access modifiers changed from: private */
    public byte type;
    /* access modifiers changed from: private */
    public byte version;
    /* access modifiers changed from: private */
    public DecimalFormat vpdFormat;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0020 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0026 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getAutoResidueFlag(boolean r3, boolean r4, boolean r5, int r6, int r7, int r8) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.ControlFragment.getAutoResidueFlag(boolean, boolean, boolean, int, int, int):int");
    }

    private void initShowListener() {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C1760R.layout.fragment_control;
    }

    public int initVariableId() {
        return C1669BR.model;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initListener();
        bindingEvent();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
        this.vpdFormat = decimalFormat2;
        decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);
        Intent intent = getActivity().getIntent();
        this.version = intent.getByteExtra(ActivityEvent.DEVICE_VERSION, (byte) 0);
        this.type = intent.getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 1);
        ((ControlModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository(), this.mac, this.devId, intent.getByteExtra(ActivityEvent.DEVICE_PORT, (byte) 0), this.type, this.version, this.connectType);
        initView();
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) SCROLL_TO_TOP, ControlModel.class, new BindingConsumer<ControlModel>() {
            public void call(ControlModel controlModel) {
                ((FragmentControlBinding) ControlFragment.this.binding).scContent.scrollTo(0, 0);
            }
        });
    }

    public void connected() {
        ((ControlModel) this.viewModel).connected.setValue(true);
        ((ControlModel) this.viewModel).start();
    }

    public void disconnected() {
        ((ControlModel) this.viewModel).connected.setValue(false);
        updatePlugType();
        ((ControlModel) this.viewModel).stop();
        ((ControlModel) this.viewModel).save();
    }

    public void setConnectType(String str, byte b) {
        this.devId = str;
        ((ControlModel) this.viewModel).devId = str;
        if (this.connectType != b) {
            this.connectType = b;
            ((ControlModel) this.viewModel).connectType = b;
            if (getActivity() == AppManager.getAppManager().currentActivity()) {
                updatePlugType();
                ((ControlModel) this.viewModel).stop();
                ((ControlModel) this.viewModel).start();
            }
        }
    }

    public void onDestroyView() {
        ((ControlModel) this.viewModel).onDestroy();
        Disposable disposable = this.automationSubs;
        if (disposable != null && !disposable.isDisposed()) {
            this.automationSubs.dispose();
            this.automationSubs = null;
        }
        Messenger.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            ((ControlModel) this.viewModel).onPause();
            return;
        }
        ((ControlModel) this.viewModel).onResume();
        initPortTab();
        updateTabSelect(((ControlModel) this.viewModel).port);
    }

    private void translateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
        this.translateAniShow = translateAnimation;
        translateAnimation.setRepeatMode(2);
        this.translateAniShow.setDuration(300);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
        this.translateAniHide = translateAnimation2;
        translateAnimation2.setRepeatMode(2);
        this.translateAniHide.setDuration(300);
    }

    private void initView() {
        initPortTabListener();
        translateAnimation();
        ((FragmentControlBinding) this.binding).vTouchOutside.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ControlFragment controlFragment = ControlFragment.this;
                    if (!controlFragment.isTouchPointInView(((FragmentControlBinding) controlFragment.binding).txtMode, (int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && ((ControlModel) ControlFragment.this.viewModel).expand.getValue().equals(Boolean.TRUE)) {
                        ((ControlModel) ControlFragment.this.viewModel).expand.postValue(false);
                    }
                }
                return false;
            }
        });
        RxView.clicks(((FragmentControlBinding) this.binding).vMask).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            public void accept(Object obj) throws Exception {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(ControlFragment.this.mac)) {
                    ControlFragment controlFragment = ControlFragment.this;
                    controlFragment.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(controlFragment.type, ((ControlModel) ControlFragment.this.viewModel).connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                }
            }
        });
        this.automationSubs = Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                ((FragmentControlBinding) ControlFragment.this.binding).vMaskAutomation.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ControlModel) ControlFragment.this.viewModel).automationVisible.setValue(true);
                        observableEmitter.onNext(view);
                    }
                });
            }
        }).debounce(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            public void accept(Object obj) {
                ((ControlModel) ControlFragment.this.viewModel).automationVisible.setValue(false);
            }
        });
        ((FragmentControlBinding) this.binding).layoutCs.setFillWhenEqual(this.version != 0);
        this.schedFactory = new IEffectBar.Factory() {
            public String getText(int i) {
                return GuQiangUtil.stringForTimeByAmPm(i);
            }
        };
        ((FragmentControlBinding) this.binding).sbFirst.setType(true);
        ((FragmentControlBinding) this.binding).sbFirst.setFactory(this.schedFactory);
        ((FragmentControlBinding) this.binding).sbLast.setType(false);
        ((FragmentControlBinding) this.binding).sbLast.setFactory(this.schedFactory);
        ((FragmentControlBinding) this.binding).sbHighTmp.setFactory(new IEffectBar.Factory() {
            public String getText(int i) {
                String str;
                if (((ControlModel) ControlFragment.this.viewModel).info == null || !((ControlModel) ControlFragment.this.viewModel).info.isDegree) {
                    str = Utils.getString(C1760R.string.tip_fah_num, Integer.valueOf(i));
                } else {
                    str = Utils.getString(C1760R.string.tip_degree_num, Integer.valueOf(i));
                }
                ControlFragment.this.setAutoDesc(str, C1760R.C1761color.color_FF6A6A, ((FragmentControlBinding) ControlFragment.this.binding).tvAutoTempHighDesc, true);
                return str;
            }
        });
        ((FragmentControlBinding) this.binding).sbLowTmp.setFactory(new IEffectBar.Factory() {
            public String getText(int i) {
                String str;
                if (((ControlModel) ControlFragment.this.viewModel).info == null || !((ControlModel) ControlFragment.this.viewModel).info.isDegree) {
                    str = Utils.getString(C1760R.string.tip_fah_num, Integer.valueOf(i));
                } else {
                    str = Utils.getString(C1760R.string.tip_degree_num, Integer.valueOf(i));
                }
                ControlFragment.this.setAutoDesc(str, C1760R.C1761color.color_15BAFF, ((FragmentControlBinding) ControlFragment.this.binding).tvAutoTempLowDesc, false);
                return str;
            }
        });
        ((FragmentControlBinding) this.binding).sbHighHum.setFactory(new IEffectBar.Factory() {
            public String getText(int i) {
                ControlFragment.this.setAutoDesc(Utils.getString(C1760R.string.tip_percent_num, Integer.valueOf(i)), C1760R.C1761color.color_FF6A6A, ((FragmentControlBinding) ControlFragment.this.binding).tvAutoHumHighDesc, true);
                return Utils.getString(C1760R.string.tip_percent_num, Integer.valueOf(i));
            }
        });
        ((FragmentControlBinding) this.binding).sbLowHum.setFactory(new IEffectBar.Factory() {
            public String getText(int i) {
                ControlFragment.this.setAutoDesc(Utils.getString(C1760R.string.tip_percent_num, Integer.valueOf(i)), C1760R.C1761color.color_15BAFF, ((FragmentControlBinding) ControlFragment.this.binding).tvAutoHumLowDesc, false);
                return Utils.getString(C1760R.string.tip_percent_num, Integer.valueOf(i));
            }
        });
        ((FragmentControlBinding) this.binding).sbHighVpd.setFactory(new IEffectBar.Factory() {
            public String getText(int i) {
                float f = ((float) i) / 10.0f;
                ControlFragment.this.setAutoDesc(Utils.getString(C1760R.string.tip_kpa, Float.valueOf(f)), C1760R.C1761color.color_FF6A6A, ((FragmentControlBinding) ControlFragment.this.binding).tvVpdHighDesc, true);
                return Utils.getString(C1760R.string.tip_kpa, Float.valueOf(f));
            }
        });
        ((FragmentControlBinding) this.binding).sbLowVpd.setFactory(new IEffectBar.Factory() {
            public String getText(int i) {
                float f = ((float) i) / 10.0f;
                ControlFragment.this.setAutoDesc(Utils.getString(C1760R.string.tip_kpa, Float.valueOf(f)), C1760R.C1761color.color_15BAFF, ((FragmentControlBinding) ControlFragment.this.binding).tvVpdLowDesc, false);
                return Utils.getString(C1760R.string.tip_kpa, Float.valueOf(f));
            }
        });
        C169613 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return GuQiangUtil.stringForTimeNoZero(i);
            }
        };
        ((FragmentControlBinding) this.binding).sbTime.setFactory(r0);
        ((FragmentControlBinding) this.binding).sbCycleOff.setFactory(r0);
        ((FragmentControlBinding) this.binding).sbCycleOn.setFactory(r0);
        byte b = this.type;
        int i = (b == 1 || b == 6) ? 1 : (b == 7 || b == 8 || b == 11) ? 2 : 0;
        ((FragmentControlBinding) this.binding).gqDial.showFan(i);
        ((FragmentControlBinding) this.binding).gqDial.showOffOrOn(isOutletDevice());
        ((FragmentControlBinding) this.binding).gqDial.setFillWhenEqual(this.version != 0);
        ((FragmentControlBinding) this.binding).ivFan.setImageResource(i == 2 ? C1760R.mipmap.level_icon : C1760R.mipmap.fan_icon);
        ((ControlModel) this.viewModel).fanType.set(i == 2 ? C1760R.mipmap.level_icon : C1760R.mipmap.fan_icon);
        if (this.type == 11) {
            ((ControlModel) this.viewModel).vpdModeVisible.setValue(true);
        }
        if (this.type == 6) {
            ((ControlModel) this.viewModel).humVisibility.set(false);
            ((ControlModel) this.viewModel).vpdVisibility.set(false);
            ((ControlModel) this.viewModel).schedModeVisible.setValue(false);
            ((ControlModel) this.viewModel).humModeVisible.setValue(false);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentControlBinding) this.binding).txtModeTimeToOn.getLayoutParams();
            layoutParams.setMarginEnd(getResources().getDimensionPixelSize(C1760R.dimen.dp_10));
            ((FragmentControlBinding) this.binding).txtModeTimeToOn.setLayoutParams(layoutParams);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(((FragmentControlBinding) this.binding).layoutModelType);
            constraintSet.setHorizontalChainStyle(C1760R.C1763id.txt_mode_time_to_on, 2);
            constraintSet.applyTo(((FragmentControlBinding) this.binding).layoutModelType);
            return;
        }
        ((ControlModel) this.viewModel).humVisibility.set(true);
        ((ControlModel) this.viewModel).vpdVisibility.set(true);
        ((ControlModel) this.viewModel).schedModeVisible.setValue(true);
        ((ControlModel) this.viewModel).humModeVisible.setValue(true);
    }

    /* access modifiers changed from: private */
    public void setAutoDesc(String str, int i, TextView textView, boolean z) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[1];
        String str2 = "above";
        objArr[0] = z ? str2 : "below";
        spannableStringBuilder.append(String.format(locale, "Turns ON at or %s ", objArr));
        spannableStringBuilder.append(str, new ForegroundColorSpan(getResources().getColor(i)), 33);
        Locale locale2 = Locale.ENGLISH;
        Object[] objArr2 = new Object[1];
        if (z) {
            str2 = "below";
        }
        objArr2[0] = str2;
        spannableStringBuilder.append(String.format(locale2, ". Turns OFF %s ", objArr2));
        spannableStringBuilder.append(str, new ForegroundColorSpan(getResources().getColor(i)), 33);
        spannableStringBuilder.append(Consts.DOT);
        textView.setText(spannableStringBuilder);
    }

    /* access modifiers changed from: private */
    public boolean isTouchPointInView(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        int measuredWidth = view.getMeasuredWidth() + i3;
        int measuredHeight = view.getMeasuredHeight() + i4;
        if (i2 < i4 || i2 > measuredHeight || i < i3 || i > measuredWidth) {
            return false;
        }
        return true;
    }

    private void initListener() {
        initFanListener();
        initTimeListener();
        initTmpListener();
        initHumListener();
        initVpdListener();
        initCycleListener();
        initSchedListener();
        initDialListener();
        initShowListener();
    }

    private void initFanListener() {
        ((FragmentControlBinding) this.binding).fpb.setStyle("", "0", "10", 0, 10);
        ((FragmentControlBinding) this.binding).fpb.setListener(new FanProgressBar.Listener() {
            public void onDown(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
                KLog.m65e("控制页 取消刷新");
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(true);
                } else {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
            }

            public void onChange(boolean z, int i) {
                if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue() != null) {
                    if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue() == 1) {
                        ((ControlModel) ControlFragment.this.viewModel).info.typeOff = (byte) i;
                    } else {
                        ((ControlModel) ControlFragment.this.viewModel).info.typeOn = (byte) i;
                    }
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i, i, WheelConstants.WHEEL_SCROLL_DELAY_DURATION, 0);
                }
            }

            public void onUp(boolean z, int i) {
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(false);
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
                }
                ((ControlModel) ControlFragment.this.viewModel).sendData();
                KLog.m65e("控制页 发送数据");
            }
        });
    }

    private void initTimeListener() {
        ((FragmentControlBinding) this.binding).sbTime.setListener(new IEffectBar.Listener() {
            public void onChecked(boolean z) {
            }

            public void onDown(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(true);
                } else {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
            }

            public void onChange(int i) {
                if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue() == 4) {
                    ((ControlModel) ControlFragment.this.viewModel).info.timeOn = (char) i;
                } else {
                    ((ControlModel) ControlFragment.this.viewModel).info.timeOff = (char) i;
                }
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i, i, WheelConstants.WHEEL_SCROLL_DELAY_DURATION, 0);
            }

            public void onUp(boolean z, int i) {
                if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue() == 4) {
                    ((ControlModel) ControlFragment.this.viewModel).info.timeOn = (char) i;
                } else {
                    ((ControlModel) ControlFragment.this.viewModel).info.timeOff = (char) i;
                }
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(false);
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
                }
                ((ControlModel) ControlFragment.this.viewModel).sendData();
            }
        });
    }

    private void initTmpListener() {
        ((FragmentControlBinding) this.binding).layoutTmp.setListener(new DoubleListenerAdapter() {
            public void onFirst(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoHighTmp = (byte) i;
                if (!((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                    int i2 = UnsignedBytes.toInt(((ControlModel) ControlFragment.this.viewModel).info.autoLowTmp);
                    if (!((ControlModel) ControlFragment.this.viewModel).info.isDegree) {
                        i -= 32;
                        i2 -= 32;
                    }
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i2, i2, i, i);
                }
            }

            public void onLast(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoLowTmp = (byte) i;
                if (!((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                    int i2 = UnsignedBytes.toInt(((ControlModel) ControlFragment.this.viewModel).info.autoHighTmp);
                    if (!((ControlModel) ControlFragment.this.viewModel).info.isDegree) {
                        i -= 32;
                        i2 -= 32;
                    }
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i, i, i2, i2);
                }
            }

            public void onEndFirst(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoHighTmp = (byte) i;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(false);
                }
                super.onEndFirst(z, i);
            }

            public void onEndLast(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoLowTmp = (byte) i;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(false);
                }
                super.onEndLast(z, i);
            }

            public void onFirstChecked(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoHighTmpSwitch = z;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(false);
                }
                if (((FragmentControlBinding) ControlFragment.this.binding).gqDial.getEnd() != z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEnd(z, true);
                }
                ControlFragment.this.autoAllClose();
                super.onFirstChecked(z);
            }

            public void onLastChecked(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoLowTmpSwitch = z;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(false);
                }
                if (((FragmentControlBinding) ControlFragment.this.binding).gqDial.getStart() != z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStart(z, true);
                }
                ControlFragment.this.autoAllClose();
                super.onLastChecked(z);
            }
        });
    }

    private void initHumListener() {
        ((FragmentControlBinding) this.binding).layoutHum.setListener(new DoubleListenerAdapter() {
            public void onFirst(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoHighHum = (byte) i;
                if (((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                    byte b = ((ControlModel) ControlFragment.this.viewModel).info.autoHighHum;
                    byte b2 = ((ControlModel) ControlFragment.this.viewModel).info.autoLowHum;
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(b2, b2, b, b);
                }
            }

            public void onLast(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoLowHum = (byte) i;
                if (((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                    byte b = ((ControlModel) ControlFragment.this.viewModel).info.autoHighHum;
                    byte b2 = ((ControlModel) ControlFragment.this.viewModel).info.autoLowHum;
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(b2, b2, b, b);
                }
            }

            public void onEndFirst(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoHighHum = (byte) i;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(true);
                }
                super.onEndFirst(z, i);
            }

            public void onEndLast(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoLowHum = (byte) i;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(true);
                }
                super.onEndLast(z, i);
            }

            public void onFirstChecked(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoHighHumSwitch = z;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(true);
                }
                if (((FragmentControlBinding) ControlFragment.this.binding).gqDial.getEnd() != z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEnd(z, true);
                }
                ControlFragment.this.autoAllClose();
                super.onFirstChecked(z);
            }

            public void onLastChecked(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).info.autoLowHumSwitch = z;
                if (((ControlModel) ControlFragment.this.viewModel).isRefreshComplete) {
                    ControlFragment.this.setControlType(true);
                }
                if (((FragmentControlBinding) ControlFragment.this.binding).gqDial.getStart() != z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStart(z, true);
                }
                ControlFragment.this.autoAllClose();
                super.onLastChecked(z);
            }
        });
    }

    private void initVpdListener() {
        ((FragmentControlBinding) this.binding).layoutVpd.setListener(new DoubleListenerAdapter() {
            public void onFirst(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.highVpd = i;
                int i2 = ((ControlModel) ControlFragment.this.viewModel).info.highVpd;
                int i3 = ((ControlModel) ControlFragment.this.viewModel).info.lowVpd;
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i3, i3, i2, i2);
            }

            public void onLast(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.lowVpd = i;
                int i2 = ((ControlModel) ControlFragment.this.viewModel).info.highVpd;
                int i3 = ((ControlModel) ControlFragment.this.viewModel).info.lowVpd;
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i3, i3, i2, i2);
            }

            public void onEndFirst(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.highVpd = i;
                super.onEndFirst(z, i);
            }

            public void onEndLast(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.lowVpd = i;
                super.onEndLast(z, i);
            }

            public void onFirstChecked(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).info.highVpdSwitch = z;
                if (((FragmentControlBinding) ControlFragment.this.binding).gqDial.getEnd() != z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEnd(z, true);
                }
                ControlFragment.this.autoAllClose();
                super.onFirstChecked(z);
            }

            public void onLastChecked(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).info.lowVpdSwitch = z;
                if (((FragmentControlBinding) ControlFragment.this.binding).gqDial.getStart() != z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStart(z, true);
                }
                ControlFragment.this.autoAllClose();
                super.onLastChecked(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void autoAllClose() {
        Object obj;
        Object obj2;
        Object obj3;
        if (((ControlModel) this.viewModel).cycleModel.getValue() != null) {
            Object obj4 = "--";
            boolean z = false;
            if (((ControlModel) this.viewModel).cycleModel.getValue().byteValue() == 3) {
                if (!((ControlModel) this.viewModel).info.autoHighTmpSwitch) {
                    obj = obj4;
                } else {
                    obj = UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoHighTmp) + "";
                }
                setAutoDesc(((ControlModel) this.viewModel).info.isDegree ? Utils.getString(C1760R.string.tip_degree_num, obj) : Utils.getString(C1760R.string.tip_fah_num, obj), C1760R.C1761color.color_FF6A6A, ((FragmentControlBinding) this.binding).tvAutoTempHighDesc, true);
                if (!((ControlModel) this.viewModel).info.autoLowTmpSwitch) {
                    obj2 = obj4;
                } else {
                    obj2 = UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoLowTmp) + "";
                }
                setAutoDesc(((ControlModel) this.viewModel).info.isDegree ? Utils.getString(C1760R.string.tip_degree_num, obj2) : Utils.getString(C1760R.string.tip_fah_num, obj2), C1760R.C1761color.color_15BAFF, ((FragmentControlBinding) this.binding).tvAutoTempLowDesc, false);
                if (!((ControlModel) this.viewModel).info.autoHighHumSwitch) {
                    obj3 = obj4;
                } else {
                    obj3 = ((ControlModel) this.viewModel).info.autoHighHum + "";
                }
                setAutoDesc(Utils.getString(C1760R.string.tip_percent_num, obj3), C1760R.C1761color.color_FF6A6A, ((FragmentControlBinding) this.binding).tvAutoHumHighDesc, true);
                if (((ControlModel) this.viewModel).info.autoLowHumSwitch) {
                    obj4 = ((ControlModel) this.viewModel).info.autoLowHum + "";
                }
                setAutoDesc(Utils.getString(C1760R.string.tip_percent_num, obj4), C1760R.C1761color.color_15BAFF, ((FragmentControlBinding) this.binding).tvAutoHumLowDesc, false);
                boolean isAutoCloseAll = isAutoCloseAll();
                if (((FragmentControlBinding) this.binding).gqDial.getAutoCloseAll() != isAutoCloseAll) {
                    ((FragmentControlBinding) this.binding).gqDial.setAutoCloseAll(isAutoCloseAll);
                }
            } else if (((ControlModel) this.viewModel).cycleModel.getValue().byteValue() == 8) {
                int i = C1760R.string.tip_kpa;
                Object[] objArr = new Object[1];
                objArr[0] = !((ControlModel) this.viewModel).info.highVpdSwitch ? obj4 : Float.valueOf(((float) ((ControlModel) this.viewModel).info.highVpd) / 10.0f);
                setAutoDesc(Utils.getString(i, objArr), C1760R.C1761color.color_FF6A6A, ((FragmentControlBinding) this.binding).tvVpdHighDesc, true);
                int i2 = C1760R.string.tip_kpa;
                Object[] objArr2 = new Object[1];
                if (((ControlModel) this.viewModel).info.lowVpdSwitch) {
                    obj4 = Float.valueOf(((float) ((ControlModel) this.viewModel).info.lowVpd) / 10.0f);
                }
                objArr2[0] = obj4;
                setAutoDesc(Utils.getString(i2, objArr2), C1760R.C1761color.color_15BAFF, ((FragmentControlBinding) this.binding).tvVpdLowDesc, false);
                if (!((ControlModel) this.viewModel).info.highVpdSwitch && !((ControlModel) this.viewModel).info.lowVpdSwitch) {
                    z = true;
                }
                if (((FragmentControlBinding) this.binding).gqDial.getAutoCloseAll() != z) {
                    ((FragmentControlBinding) this.binding).gqDial.setAutoCloseAll(z);
                }
            } else if (((ControlModel) this.viewModel).cycleModel.getValue().byteValue() == 7) {
                if (((ControlModel) this.viewModel).info.schedOff == 65535 && ((ControlModel) this.viewModel).info.schedOn == 65535) {
                    z = true;
                }
                if (((FragmentControlBinding) this.binding).gqDial.getAutoCloseAll() != z) {
                    ((FragmentControlBinding) this.binding).gqDial.setAutoCloseAll(z);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setControlType(boolean z) {
        boolean z2 = false;
        if (((ControlModel) this.viewModel).humModeVisible.getValue() != null && !((ControlModel) this.viewModel).humModeVisible.getValue().booleanValue()) {
            z = false;
        }
        boolean z3 = ((ControlModel) this.viewModel).info.autoHighTmpSwitch;
        boolean z4 = ((ControlModel) this.viewModel).info.autoLowTmpSwitch;
        boolean z5 = ((ControlModel) this.viewModel).info.autoHighHumSwitch;
        boolean z6 = ((ControlModel) this.viewModel).info.autoLowHumSwitch;
        if (!z ? !(z3 || z4 || (!z5 && !z6)) : !(!z5 && !z6 && (z3 || z4))) {
            z2 = true;
        }
        if (((ControlModel) this.viewModel).controlTypeByHumModel.getValue() == null || z2 != ((ControlModel) this.viewModel).controlTypeByHumModel.getValue().booleanValue()) {
            ((ControlModel) this.viewModel).controlTypeByHumModel.setValue(Boolean.valueOf(z2));
        }
    }

    private boolean isAutoCloseAll() {
        return !((ControlModel) this.viewModel).info.autoHighTmpSwitch && !((ControlModel) this.viewModel).info.autoLowTmpSwitch && !((ControlModel) this.viewModel).info.autoHighHumSwitch && !((ControlModel) this.viewModel).info.autoLowHumSwitch;
    }

    /* access modifiers changed from: private */
    public void switchControlType(boolean z, final boolean z2) {
        ((ControlModel) this.viewModel).info.isControlTypeByHum = z;
        ((ControlModel) this.viewModel).setControlTypeByHum(z);
        if (((ControlModel) this.viewModel).cycleModel.getValue() != null && ((ControlModel) this.viewModel).cycleModel.getValue().byteValue() == 3) {
            initAuto();
        }
        ((FragmentControlBinding) this.binding).gqDial.animate().setDuration(z2 ? 1000 : 1).rotationBy(360.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setRotation(0.0f);
                ControlFragment.this.updateDialAuto();
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
            }

            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
            }
        }).start();
        ((FragmentControlBinding) this.binding).gqDial.invalidate();
        if (((ControlModel) this.viewModel).cycleModel.getValue() != null && ((ControlModel) this.viewModel).cycleModel.getValue().byteValue() == 3) {
            ((FragmentControlBinding) this.binding).scContent.post(new Runnable() {
                public void run() {
                    if (((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                        if (z2) {
                            ((FragmentControlBinding) ControlFragment.this.binding).scContent.smoothScrollTo(0, ((FragmentControlBinding) ControlFragment.this.binding).tvHumTitle.getTop());
                        } else {
                            ((FragmentControlBinding) ControlFragment.this.binding).scContent.scrollTo(0, ((FragmentControlBinding) ControlFragment.this.binding).tvHumTitle.getTop());
                        }
                    } else if (z2) {
                        ((FragmentControlBinding) ControlFragment.this.binding).scContent.smoothScrollTo(0, ((FragmentControlBinding) ControlFragment.this.binding).tvTmpTitle.getTop());
                    } else {
                        ((FragmentControlBinding) ControlFragment.this.binding).scContent.scrollTo(0, ((FragmentControlBinding) ControlFragment.this.binding).tvTmpTitle.getTop());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateDialAuto() {
        String str;
        String str2;
        int i;
        int i2;
        int i3 = 0;
        if (((ControlModel) this.viewModel).info.isControlTypeByHum) {
            str2 = ProtocolTransformer.getPer(((ControlModel) this.viewModel).info.hum, this.format);
            str = "%";
        } else if (((ControlModel) this.viewModel).info.isDegree) {
            str2 = ProtocolTransformer.getString4Degree(((ControlModel) this.viewModel).info.tmp, true, this.format);
            str = ProtocolTransformer.DEGREE;
        } else {
            str2 = ProtocolTransformer.getString4Degree(((ControlModel) this.viewModel).info.tmp, false, this.format);
            str = ProtocolTransformer.FAH;
        }
        ((FragmentControlBinding) this.binding).gqDial.setTmp(str2, str);
        if (((ControlModel) this.viewModel).cycleModel.getValue().byteValue() == 3) {
            if (((ControlModel) this.viewModel).info.isControlTypeByHum) {
                ((FragmentControlBinding) this.binding).gqDial.setModeType(6);
                byte b = ((ControlModel) this.viewModel).info.autoLowHum;
                byte b2 = ((ControlModel) this.viewModel).info.autoHighHum;
                ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(b, b, b2, b2);
                if (((ControlModel) this.viewModel).info.hum != -32768) {
                    i3 = Math.round(((float) ((ControlModel) this.viewModel).info.hum) / 100.0f);
                }
            } else {
                if (((ControlModel) this.viewModel).info.isDegree) {
                    ((FragmentControlBinding) this.binding).gqDial.setModeType(4);
                    i2 = ((ControlModel) this.viewModel).info.autoHighTmp;
                    i = ((ControlModel) this.viewModel).info.autoLowTmp;
                } else {
                    ((FragmentControlBinding) this.binding).gqDial.setModeType(5);
                    i2 = UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoHighTmp) - 32;
                    i = UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoLowTmp) - 32;
                }
                ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(i, i, i2, i2);
                int tmp = ProtocolTransformer.getTmp(((ControlModel) this.viewModel).info.tmp, ((ControlModel) this.viewModel).info.isDegree);
                if (((ControlModel) this.viewModel).info.isDegree || tmp - 32 >= 0) {
                    i3 = tmp;
                }
            }
            ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(i3), "");
        }
    }

    private void initSchedListener() {
        ((FragmentControlBinding) this.binding).layoutCs.setListener(new DoubleListenerAdapter() {
            public void onFirstChecked(boolean z) {
            }

            public void onLastChecked(boolean z) {
            }

            public void onFirstDown(boolean z) {
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(true);
                } else {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
            }

            public void onLastDown(boolean z) {
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(true);
                } else {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
            }

            public void onFirst(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.schedOn = (char) i;
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i, i, ((ControlModel) ControlFragment.this.viewModel).info.schedOff, ((ControlModel) ControlFragment.this.viewModel).info.schedOff);
                ControlFragment.this.autoAllClose();
            }

            public void onLast(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.schedOff = (char) i;
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(((ControlModel) ControlFragment.this.viewModel).info.schedOn, ((ControlModel) ControlFragment.this.viewModel).info.schedOn, i, i);
                ControlFragment.this.autoAllClose();
            }

            public void onEndFirst(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.schedOn = (char) i;
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(false);
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
                }
                ((ControlModel) ControlFragment.this.viewModel).sendData();
                ControlFragment.this.scrollPosition(true);
                ControlFragment.this.autoAllClose();
            }

            public void onEndLast(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.schedOff = (char) i;
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(false);
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
                }
                ((ControlModel) ControlFragment.this.viewModel).sendData();
                ControlFragment.this.scrollPosition(false);
                ControlFragment.this.autoAllClose();
            }
        });
    }

    private void initCycleListener() {
        ((FragmentControlBinding) this.binding).sbCycleOn.setListener(new IEffectBar.Listener() {
            public void onChecked(boolean z) {
            }

            public void onDown(boolean z) {
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(true);
                } else {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
            }

            public void onChange(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.cycleOn = (char) i;
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(i, i, ((ControlModel) ControlFragment.this.viewModel).info.cycleOff, ((ControlModel) ControlFragment.this.viewModel).info.cycleOff);
            }

            public void onUp(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.cycleOn = (char) i;
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(false);
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
                }
                ((ControlModel) ControlFragment.this.viewModel).sendData();
                ControlFragment.this.scrollPosition(true);
            }
        });
        ((FragmentControlBinding) this.binding).sbCycleOff.setListener(new IEffectBar.Listener() {
            public void onChecked(boolean z) {
            }

            public void onDown(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(true);
                } else {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
            }

            public void onChange(int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.cycleOff = (char) i;
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.initStartOrEndAngle(((ControlModel) ControlFragment.this.viewModel).info.cycleOn, ((ControlModel) ControlFragment.this.viewModel).info.cycleOn, i, i);
            }

            public void onUp(boolean z, int i) {
                ((ControlModel) ControlFragment.this.viewModel).info.cycleOff = (char) i;
                if (z) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(false);
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
                }
                ((ControlModel) ControlFragment.this.viewModel).sendData();
                ControlFragment.this.scrollPosition(false);
            }
        });
    }

    private void initDialListener() {
        ((FragmentControlBinding) this.binding).gqDial.setOnChangeListeners(new GuQiangCycleDialView.OnChangeListeners() {
            public void onChangeTouchStart(boolean z) {
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
                if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue() != null) {
                    if (3 == ((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue()) {
                        if (((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                            if (z) {
                                if (!((FragmentControlBinding) ControlFragment.this.binding).sbLowHum.isChecked()) {
                                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStart(true);
                                    ((FragmentControlBinding) ControlFragment.this.binding).sbLowHum.setChecked(true);
                                }
                            } else if (!((FragmentControlBinding) ControlFragment.this.binding).sbHighHum.isChecked()) {
                                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEnd(true);
                                ((FragmentControlBinding) ControlFragment.this.binding).sbHighHum.setChecked(true);
                            }
                        } else if (z) {
                            if (!((FragmentControlBinding) ControlFragment.this.binding).sbLowTmp.isChecked()) {
                                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStart(true);
                                ((FragmentControlBinding) ControlFragment.this.binding).sbLowTmp.setChecked(true);
                            }
                        } else if (!((FragmentControlBinding) ControlFragment.this.binding).sbHighTmp.isChecked()) {
                            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEnd(true);
                            ((FragmentControlBinding) ControlFragment.this.binding).sbHighTmp.setChecked(true);
                        }
                    } else if (8 != ((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue()) {
                    } else {
                        if (z) {
                            if (!((FragmentControlBinding) ControlFragment.this.binding).sbLowVpd.isChecked()) {
                                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStart(true);
                                ((FragmentControlBinding) ControlFragment.this.binding).sbLowVpd.setChecked(true);
                            }
                        } else if (!((FragmentControlBinding) ControlFragment.this.binding).sbHighVpd.isChecked()) {
                            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEnd(true);
                            ((FragmentControlBinding) ControlFragment.this.binding).sbHighVpd.setChecked(true);
                        }
                    }
                }
            }

            public void onChangeStartAndEnd(int i, int i2) {
                if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue() != null) {
                    switch (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue()) {
                        case 1:
                        case 2:
                            ((FragmentControlBinding) ControlFragment.this.binding).fpb.setProgress(i);
                            return;
                        case 4:
                        case 5:
                            ((FragmentControlBinding) ControlFragment.this.binding).sbTime.setProgress(i);
                            return;
                        case 6:
                            ((FragmentControlBinding) ControlFragment.this.binding).sbCycleOn.setProgress(i);
                            ((FragmentControlBinding) ControlFragment.this.binding).sbCycleOff.setProgress(i2);
                            return;
                        case 7:
                            if (i >= 1440) {
                                i = 1439;
                            }
                            if (i2 >= 1440) {
                                i2 = 1439;
                            }
                            ((FragmentControlBinding) ControlFragment.this.binding).sbFirst.setProgress(i);
                            ((FragmentControlBinding) ControlFragment.this.binding).sbLast.setProgress(i2);
                            return;
                        case 8:
                            ((FragmentControlBinding) ControlFragment.this.binding).sbLowVpd.setProgress(i);
                            ((FragmentControlBinding) ControlFragment.this.binding).sbHighVpd.setProgress(i2);
                            return;
                        default:
                            if (((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                                ((FragmentControlBinding) ControlFragment.this.binding).sbLowHum.setProgress(i);
                                ((FragmentControlBinding) ControlFragment.this.binding).sbHighHum.setProgress(i2);
                                return;
                            } else if (((ControlModel) ControlFragment.this.viewModel).info.isDegree) {
                                ((FragmentControlBinding) ControlFragment.this.binding).sbLowTmp.setProgress(i);
                                ((FragmentControlBinding) ControlFragment.this.binding).sbHighTmp.setProgress(i2);
                                return;
                            } else {
                                ((FragmentControlBinding) ControlFragment.this.binding).sbLowTmp.setProgress(i + 32);
                                ((FragmentControlBinding) ControlFragment.this.binding).sbHighTmp.setProgress(i2 + 32);
                                return;
                            }
                    }
                }
            }

            public void onChangeAndEndByTouchEnd(boolean z, int i, int i2) {
                ((ControlModel) ControlFragment.this.viewModel).sendData();
                ControlFragment.this.scrollPosition(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void scrollPosition(boolean z) {
        if (z != ((ControlModel) this.viewModel).isStart) {
            ((ControlModel) this.viewModel).isStart = z;
            ((FragmentControlBinding) this.binding).scContent.post(new Runnable() {
                public void run() {
                    int i;
                    if (((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue() != null) {
                        if (7 == ((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue()) {
                            i = ((ControlModel) ControlFragment.this.viewModel).isStart ? ((FragmentControlBinding) ControlFragment.this.binding).layoutCs.getTop() : ((FragmentControlBinding) ControlFragment.this.binding).lineSched.getTop();
                        } else if (6 == ((ControlModel) ControlFragment.this.viewModel).cycleModel.getValue().byteValue()) {
                            i = ((ControlModel) ControlFragment.this.viewModel).isStart ? ((FragmentControlBinding) ControlFragment.this.binding).layoutCycle.getTop() : ((FragmentControlBinding) ControlFragment.this.binding).lineCycle.getTop();
                        } else {
                            i = -1;
                        }
                        if (i != -1) {
                            ((FragmentControlBinding) ControlFragment.this.binding).scContent.smoothScrollTo(0, i);
                        }
                    }
                }
            });
        }
    }

    private void bindingEvent() {
        ((ControlModel) this.viewModel).controlTypeByHumModel.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                ControlFragment.this.switchControlType(bool.booleanValue(), ((ControlModel) ControlFragment.this.viewModel).isRefreshComplete && ControlFragment.this.isFirstControl);
                if (!ControlFragment.this.isFirstControl) {
                    boolean unused = ControlFragment.this.isFirstControl = true;
                }
            }
        });
        ((ControlModel) this.viewModel).expand.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    ((FragmentControlBinding) ControlFragment.this.binding).vTouchOutside.setVisibility(0);
                    ControlFragment.this.showModeType();
                    return;
                }
                ((FragmentControlBinding) ControlFragment.this.binding).vTouchOutside.setVisibility(8);
                ControlFragment.this.hideModeType();
            }
        });
        ((ControlModel) this.viewModel).residueFlag.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                boolean z = false;
                ((ControlModel) ControlFragment.this.viewModel).maxFanType.set(ControlFragment.this.getFanIcon(num.intValue() == 1));
                ObservableInt observableInt = ((ControlModel) ControlFragment.this.viewModel).minFanType;
                ControlFragment controlFragment = ControlFragment.this;
                if (num.intValue() == 2) {
                    z = true;
                }
                observableInt.set(controlFragment.getFanIcon(z));
            }
        });
        ((ControlModel) this.viewModel).cycleModel.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                ControlFragment.this.initPortTab();
                ControlFragment controlFragment = ControlFragment.this;
                controlFragment.updateTabSelect(((ControlModel) controlFragment.viewModel).port);
                ((ControlModel) ControlFragment.this.viewModel).tmpSize.set(ProtocolTransformer.getString4Degree(((ControlModel) ControlFragment.this.viewModel).info.tmp, ((ControlModel) ControlFragment.this.viewModel).info.isDegree, ControlFragment.this.format));
                ((ControlModel) ControlFragment.this.viewModel).tmpFlag.set(((ControlModel) ControlFragment.this.viewModel).info.isDegree ? ProtocolTransformer.DEGREE : ProtocolTransformer.FAH);
                ((ControlModel) ControlFragment.this.viewModel).perSize.set(ProtocolTransformer.getPer(((ControlModel) ControlFragment.this.viewModel).info.hum, ControlFragment.this.format));
                int i = 1;
                if (ControlFragment.this.version < 3) {
                    ((ControlModel) ControlFragment.this.viewModel).vpdSize.set(ProtocolTransformer.getVPDString(((ControlModel) ControlFragment.this.viewModel).info.tmp, ((ControlModel) ControlFragment.this.viewModel).info.hum, ((ControlModel) ControlFragment.this.viewModel).info.leafTemperatureC, true, ControlFragment.this.vpdFormat));
                } else {
                    ((ControlModel) ControlFragment.this.viewModel).vpdSize.set(ProtocolTransformer.getVPDString(((ControlModel) ControlFragment.this.viewModel).info.vpd, ControlFragment.this.vpdFormat));
                }
                ((ControlModel) ControlFragment.this.viewModel).fanTypeTitle.set((ControlFragment.this.type == 8 || ControlFragment.this.type == 11 || ControlFragment.this.type == 7) ? "LEVEL" : "FAN");
                ObservableField<String> observableField = ((ControlModel) ControlFragment.this.viewModel).onFanSize;
                StringBuilder sb = new StringBuilder();
                sb.append("MAX:");
                String str = "  ";
                sb.append(((ControlModel) ControlFragment.this.viewModel).info.typeOn < 10 ? str : "");
                sb.append(((ControlModel) ControlFragment.this.viewModel).info.typeOn);
                observableField.set(sb.toString());
                ObservableField<String> observableField2 = ((ControlModel) ControlFragment.this.viewModel).offFanSize;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("MIN: ");
                if (((ControlModel) ControlFragment.this.viewModel).info.typeOff >= 10) {
                    str = "";
                }
                sb2.append(str);
                sb2.append(((ControlModel) ControlFragment.this.viewModel).info.typeOff);
                observableField2.set(sb2.toString());
                int i2 = 0;
                if (ControlFragment.this.isOutletDevice()) {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.showOffOrOn(true);
                    ((ControlModel) ControlFragment.this.viewModel).fanVisibility.set(false);
                    ((ControlModel) ControlFragment.this.viewModel).powerVisibility.set(true);
                    ((ControlModel) ControlFragment.this.viewModel).powerOff.set(((ControlModel) ControlFragment.this.viewModel).info.fan == 0);
                    ((ControlModel) ControlFragment.this.viewModel).currentResidueOnVisible.setValue(false);
                } else {
                    ((FragmentControlBinding) ControlFragment.this.binding).gqDial.showOffOrOn(false);
                    ((ControlModel) ControlFragment.this.viewModel).fanVisibility.set(true);
                    ((ControlModel) ControlFragment.this.viewModel).powerVisibility.set(false);
                    ((ControlModel) ControlFragment.this.viewModel).fanSize.set(String.valueOf(((ControlModel) ControlFragment.this.viewModel).info.fan));
                    ((ControlModel) ControlFragment.this.viewModel).currentResidueOnVisible.setValue(true);
                }
                switch (b.byteValue()) {
                    case 2:
                        ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(1);
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.power_on));
                        ControlFragment controlFragment2 = ControlFragment.this;
                        controlFragment2.setWidth(((FragmentControlBinding) controlFragment2.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_71));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(1);
                        if (ControlFragment.this.type == 7 || ControlFragment.this.type == 8 || ControlFragment.this.type == 11) {
                            ((ControlModel) ControlFragment.this.viewModel).hintText.set("Sets maximum level in other modes");
                        } else {
                            ((ControlModel) ControlFragment.this.viewModel).hintText.set("Sets maximum speed in other modes");
                        }
                        ControlFragment.this.initOn();
                        return;
                    case 3:
                        ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(Integer.valueOf(ControlFragment.this.getAutoResidueFlag()));
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.tip_control_auto));
                        ControlFragment controlFragment3 = ControlFragment.this;
                        controlFragment3.setWidth(((FragmentControlBinding) controlFragment3.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_71));
                        if (((ControlModel) ControlFragment.this.viewModel).info.isControlTypeByHum) {
                            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(6);
                        } else if (((ControlModel) ControlFragment.this.viewModel).info.isDegree) {
                            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(4);
                        } else {
                            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(5);
                        }
                        String string = ((ControlModel) ControlFragment.this.viewModel).info.isDegree ? Utils.getString(C1760R.string.tip_degree_num, Byte.valueOf(((ControlModel) ControlFragment.this.viewModel).info.transitionTemperatureC)) : Utils.getString(C1760R.string.tip_fah_num, Byte.valueOf(((ControlModel) ControlFragment.this.viewModel).info.transitionTemperature));
                        String string2 = Utils.getString(C1760R.string.tip_percent_num, Byte.valueOf(((ControlModel) ControlFragment.this.viewModel).info.transitionHumidity));
                        ObservableField<String> observableField3 = ((ControlModel) ControlFragment.this.viewModel).bufferTempSize;
                        Locale locale = Locale.ENGLISH;
                        Object[] objArr = new Object[2];
                        String str2 = "BUFF";
                        objArr[0] = ControlFragment.this.isOutletDevice() ? str2 : "TRAN";
                        objArr[1] = string;
                        observableField3.set(String.format(locale, "%s: %s", objArr));
                        ObservableField<String> observableField4 = ((ControlModel) ControlFragment.this.viewModel).bufferHumSize;
                        Locale locale2 = Locale.ENGLISH;
                        Object[] objArr2 = new Object[2];
                        if (!ControlFragment.this.isOutletDevice()) {
                            str2 = "TRAN";
                        }
                        objArr2[0] = str2;
                        objArr2[1] = string2;
                        observableField4.set(String.format(locale2, "%s: %s", objArr2));
                        ((ControlModel) ControlFragment.this.viewModel).hintText.set("All triggers can occur concurrently, turn off if not in use.");
                        ControlFragment.this.initAuto();
                        return;
                    case 4:
                        ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(Integer.valueOf(ControlFragment.this.getResidueFlat()));
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.tip_control_time_to_on));
                        ControlFragment controlFragment4 = ControlFragment.this;
                        controlFragment4.setWidth(((FragmentControlBinding) controlFragment4.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_108));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(8);
                        ControlFragment.this.initTimeOn();
                        return;
                    case 5:
                        ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(Integer.valueOf(ControlFragment.this.getResidueFlat()));
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.tip_control_time_to_off));
                        ControlFragment controlFragment5 = ControlFragment.this;
                        controlFragment5.setWidth(((FragmentControlBinding) controlFragment5.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_108));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(9);
                        ControlFragment.this.initTimeOff();
                        return;
                    case 6:
                        MutableLiveData<Integer> mutableLiveData = ((ControlModel) ControlFragment.this.viewModel).residueFlag;
                        if (((ControlModel) ControlFragment.this.viewModel).info.currentTypeResidueTime != 0) {
                            i2 = ControlFragment.this.getResidueFlat();
                        }
                        mutableLiveData.setValue(Integer.valueOf(i2));
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.tip_control_cycle));
                        ControlFragment controlFragment6 = ControlFragment.this;
                        controlFragment6.setWidth(((FragmentControlBinding) controlFragment6.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_71));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(3);
                        ControlFragment.this.initCycle();
                        return;
                    case 7:
                        if (((ControlModel) ControlFragment.this.viewModel).info.schedOff == ((ControlModel) ControlFragment.this.viewModel).info.schedOn) {
                            MutableLiveData<Integer> mutableLiveData2 = ((ControlModel) ControlFragment.this.viewModel).residueFlag;
                            if (ControlFragment.this.version <= 0) {
                                i = 2;
                            }
                            mutableLiveData2.setValue(Integer.valueOf(i));
                        } else {
                            ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(Integer.valueOf(ControlFragment.this.getResidueFlat()));
                        }
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.tip_control_sched));
                        ControlFragment controlFragment7 = ControlFragment.this;
                        controlFragment7.setWidth(((FragmentControlBinding) controlFragment7.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_108));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(7);
                        ControlFragment.this.initSched();
                        return;
                    case 8:
                        ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(Integer.valueOf(ControlFragment.this.getVpdResidueFlag()));
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.tip_control_vpd));
                        ControlFragment controlFragment8 = ControlFragment.this;
                        controlFragment8.setWidth(((FragmentControlBinding) controlFragment8.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_71));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(10);
                        ((ControlModel) ControlFragment.this.viewModel).hintText.set("All triggers can occur concurrently, turn off if not in use.");
                        ControlFragment.this.initVpd();
                        return;
                    default:
                        ((ControlModel) ControlFragment.this.viewModel).residueFlag.setValue(0);
                        ((ControlModel) ControlFragment.this.viewModel).model.setValue(Utils.getString(C1760R.string.power_off));
                        ControlFragment controlFragment9 = ControlFragment.this;
                        controlFragment9.setWidth(((FragmentControlBinding) controlFragment9.binding).txtMode, ControlFragment.this.getResources().getDimensionPixelSize(C1760R.dimen.dp_71));
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setModeType(2);
                        if (ControlFragment.this.type == 7 || ControlFragment.this.type == 8 || ControlFragment.this.type == 11) {
                            ((ControlModel) ControlFragment.this.viewModel).hintText.set("Sets minimum level in other modes");
                        } else {
                            ((ControlModel) ControlFragment.this.viewModel).hintText.set("Sets minimum speed in other modes");
                        }
                        ControlFragment.this.initOff();
                        return;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isOutletDevice() {
        if (((ControlModel) this.viewModel).info == null) {
            return ProtocolTransformer.isOutletDevice(this.type, 0);
        }
        if (this.type != 11 || ((ControlModel) this.viewModel).port != 0) {
            return ProtocolTransformer.isOutletDevice(this.type, ((ControlModel) this.viewModel).info.fanType);
        }
        if (((ControlModel) this.viewModel).info.portList != null && ((ControlModel) this.viewModel).info.portList.size() > 0) {
            for (PortInfo next : ((ControlModel) this.viewModel).info.portList) {
                if (next.f138id != 0 && next.isPlug) {
                    return ProtocolTransformer.isOutletDevice(this.type, next.fanType);
                }
            }
        }
        return ProtocolTransformer.isOutletDevice(this.type, 0);
    }

    /* access modifiers changed from: private */
    public void showModeType() {
        ((FragmentControlBinding) this.binding).svModelType.startAnimation(this.translateAniShow);
        ((FragmentControlBinding) this.binding).svModelType.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void hideModeType() {
        ((FragmentControlBinding) this.binding).svModelType.startAnimation(this.translateAniHide);
        this.translateAniHide.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ((FragmentControlBinding) ControlFragment.this.binding).svModelType.setVisibility(8);
            }
        });
    }

    public int getFanIcon(boolean z) {
        byte b = this.type;
        return (b == 7 || b == 8 || b == 11) ? z ? C1760R.mipmap.level_icon_small_white : C1760R.mipmap.level_icon_small_gray : z ? C1760R.mipmap.speed_icon_small_white : C1760R.mipmap.speed_icon_small_gray;
    }

    /* access modifiers changed from: private */
    public int getResidueFlat() {
        if (((ControlModel) this.viewModel).info.currentTypeResidueTime == 0) {
            if (((ControlModel) this.viewModel).info.currentTypeResidueOn) {
                return 1;
            }
            return 2;
        } else if (((ControlModel) this.viewModel).info.currentTypeResidueOn) {
            return 2;
        } else {
            return 1;
        }
    }

    /* access modifiers changed from: private */
    public int getAutoResidueFlag() {
        if (isAutoCloseAll()) {
            return 0;
        }
        int autoResidueFlag = getAutoResidueFlag(((ControlModel) this.viewModel).info.tmp == -32768, !((ControlModel) this.viewModel).info.autoHighTmpSwitch, !((ControlModel) this.viewModel).info.autoLowTmpSwitch, ProtocolTransformer.getTmp(((ControlModel) this.viewModel).info.tmp, ((ControlModel) this.viewModel).info.isDegree), UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoHighTmp), UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoLowTmp));
        int autoResidueFlag2 = getAutoResidueFlag(((ControlModel) this.viewModel).info.hum > 10000 || ((ControlModel) this.viewModel).info.hum < 0, !((ControlModel) this.viewModel).info.autoHighHumSwitch, !((ControlModel) this.viewModel).info.autoLowHumSwitch, ProtocolTransformer.getHum(((ControlModel) this.viewModel).info.hum), UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoHighHum), UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoLowHum));
        if (autoResidueFlag == 0 && autoResidueFlag2 == 0) {
            return 2;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public int getVpdResidueFlag() {
        if (!((ControlModel) this.viewModel).info.highVpdSwitch && !((ControlModel) this.viewModel).info.lowVpdSwitch) {
            return 0;
        }
        return getAutoResidueFlag(((ControlModel) this.viewModel).info.vpd > 1000 || ((ControlModel) this.viewModel).info.vpd < 0, ((ControlModel) this.viewModel).info.highVpdSwitch ^ true, ((ControlModel) this.viewModel).info.lowVpdSwitch ^ true, ((ControlModel) this.viewModel).info.vpd / 10, ((ControlModel) this.viewModel).info.highVpd, ((ControlModel) this.viewModel).info.lowVpd) != 0 ? 1 : 2;
    }

    /* access modifiers changed from: private */
    public void setWidth(View view, int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void initAuto() {
        if (this.now != ((FragmentControlBinding) this.binding).layoutTh) {
            View view = this.now;
            if (view != null) {
                view.setVisibility(8);
            }
            ((FragmentControlBinding) this.binding).layoutTh.setVisibility(0);
        }
        this.now = ((FragmentControlBinding) this.binding).layoutTh;
        if (((ControlModel) this.viewModel).info.isDegree) {
            Utils.getString(C1760R.string.tip_min_degree_tmp);
            Utils.getString(C1760R.string.tip_max_degree_tmp);
            ((FragmentControlBinding) this.binding).sbHighTmp.setValue(0, 90);
            ((FragmentControlBinding) this.binding).sbLowTmp.setValue(0, 90);
        } else {
            Utils.getString(C1760R.string.tip_min_fah_tmp);
            Utils.getString(C1760R.string.tip_max_fah_tmp);
            ((FragmentControlBinding) this.binding).sbHighTmp.setValue(32, 162);
            ((FragmentControlBinding) this.binding).sbLowTmp.setValue(32, 162);
        }
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            if (!(!((ControlModel) this.viewModel).isRefreshComplete || ((ControlModel) this.viewModel).lastNetMode == null || ((ControlModel) this.viewModel).netMode == null)) {
                if (((ControlModel) this.viewModel).lastNetMode.devHt == ((ControlModel) this.viewModel).netMode.devHt && ((ControlModel) this.viewModel).lastNetMode.devLt == ((ControlModel) this.viewModel).netMode.devLt && ((ControlModel) this.viewModel).lastNetMode.devHtf == ((ControlModel) this.viewModel).netMode.devHtf && ((ControlModel) this.viewModel).lastNetMode.devLtf == ((ControlModel) this.viewModel).netMode.devLtf && ((ControlModel) this.viewModel).lastNetMode.activeHt == ((ControlModel) this.viewModel).netMode.activeHt && ((ControlModel) this.viewModel).lastNetMode.activeLt == ((ControlModel) this.viewModel).netMode.activeLt) {
                    if (!(((ControlModel) this.viewModel).lastNetMode.devHh == ((ControlModel) this.viewModel).netMode.devHh && ((ControlModel) this.viewModel).lastNetMode.devLh == ((ControlModel) this.viewModel).netMode.devLh && ((ControlModel) this.viewModel).lastNetMode.activeHh == ((ControlModel) this.viewModel).netMode.activeHh && ((ControlModel) this.viewModel).lastNetMode.activeLh == ((ControlModel) this.viewModel).netMode.activeLh) && (((ControlModel) this.viewModel).controlTypeByHumModel.getValue() == null || !((ControlModel) this.viewModel).controlTypeByHumModel.getValue().booleanValue())) {
                        setControlType(true);
                    }
                } else if (((ControlModel) this.viewModel).controlTypeByHumModel.getValue() != null && ((ControlModel) this.viewModel).controlTypeByHumModel.getValue().booleanValue()) {
                    setControlType(false);
                }
            }
        } else if (((ControlModel) this.viewModel).isRefreshComplete && ((ControlModel) this.viewModel).lastInfo != null) {
            if (((ControlModel) this.viewModel).lastInfo.autoHighTmp == ((ControlModel) this.viewModel).info.autoHighTmp && ((ControlModel) this.viewModel).lastInfo.autoLowTmp == ((ControlModel) this.viewModel).info.autoLowTmp && ((ControlModel) this.viewModel).lastInfo.autoHighTmpSwitch == ((ControlModel) this.viewModel).info.autoHighTmpSwitch && ((ControlModel) this.viewModel).lastInfo.autoLowTmpSwitch == ((ControlModel) this.viewModel).info.autoLowTmpSwitch) {
                if (!(((ControlModel) this.viewModel).lastInfo.autoHighHum == ((ControlModel) this.viewModel).info.autoHighHum && ((ControlModel) this.viewModel).lastInfo.autoLowHum == ((ControlModel) this.viewModel).info.autoLowHum && ((ControlModel) this.viewModel).lastInfo.autoHighHumSwitch == ((ControlModel) this.viewModel).info.autoHighHumSwitch && ((ControlModel) this.viewModel).lastInfo.autoLowHumSwitch == ((ControlModel) this.viewModel).info.autoLowHumSwitch) && (((ControlModel) this.viewModel).controlTypeByHumModel.getValue() == null || !((ControlModel) this.viewModel).controlTypeByHumModel.getValue().booleanValue())) {
                    setControlType(true);
                }
            } else if (((ControlModel) this.viewModel).controlTypeByHumModel.getValue() != null && ((ControlModel) this.viewModel).controlTypeByHumModel.getValue().booleanValue() && ((ControlModel) this.viewModel).lastInfo.isDegree == ((ControlModel) this.viewModel).info.isDegree) {
                setControlType(false);
            }
        }
        ((FragmentControlBinding) this.binding).sbHighTmp.setProgress(UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoHighTmp));
        ((FragmentControlBinding) this.binding).sbLowTmp.setProgress(UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoLowTmp));
        ((FragmentControlBinding) this.binding).sbHighHum.setProgress(((ControlModel) this.viewModel).info.autoHighHum);
        ((FragmentControlBinding) this.binding).sbLowHum.setProgress(((ControlModel) this.viewModel).info.autoLowHum);
        ((FragmentControlBinding) this.binding).sbHighTmp.setCheckedSilently(((ControlModel) this.viewModel).info.autoHighTmpSwitch);
        ((FragmentControlBinding) this.binding).sbLowTmp.setCheckedSilently(((ControlModel) this.viewModel).info.autoLowTmpSwitch);
        ((FragmentControlBinding) this.binding).sbHighHum.setCheckedSilently(((ControlModel) this.viewModel).info.autoHighHumSwitch);
        ((FragmentControlBinding) this.binding).sbLowHum.setCheckedSilently(((ControlModel) this.viewModel).info.autoLowHumSwitch);
        if (((ControlModel) this.viewModel).info.isControlTypeByHum) {
            initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), ((ControlModel) this.viewModel).info.humState);
            byte b = ((ControlModel) this.viewModel).info.autoHighHum;
            byte b2 = ((ControlModel) this.viewModel).info.autoLowHum;
            ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(b2, b2, b, b);
            int round = ((ControlModel) this.viewModel).info.hum != -32768 ? Math.round(((float) ((ControlModel) this.viewModel).info.hum) / 100.0f) : 0;
            ((FragmentControlBinding) this.binding).gqDial.setStart(((ControlModel) this.viewModel).info.autoLowHumSwitch, true);
            ((FragmentControlBinding) this.binding).gqDial.setEnd(((ControlModel) this.viewModel).info.autoHighHumSwitch, true);
            ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(round), "");
            if (!((ControlModel) this.viewModel).isSetDragState) {
                ((ControlModel) this.viewModel).isSetDragState = true;
            }
        } else {
            initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), ((ControlModel) this.viewModel).info.tmpState);
            int i = UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoHighTmp);
            int i2 = UnsignedBytes.toInt(((ControlModel) this.viewModel).info.autoLowTmp);
            if (!((ControlModel) this.viewModel).info.isDegree) {
                i -= 32;
                i2 -= 32;
                if (i < 0) {
                    i = 0;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
            }
            ((FragmentControlBinding) this.binding).gqDial.setStart(((ControlModel) this.viewModel).info.autoLowTmpSwitch, true);
            ((FragmentControlBinding) this.binding).gqDial.setEnd(((ControlModel) this.viewModel).info.autoHighTmpSwitch, true);
            ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(i2, i2, i, i);
            int tmp = ProtocolTransformer.getTmp(((ControlModel) this.viewModel).info.tmp, ((ControlModel) this.viewModel).info.isDegree);
            if (!((ControlModel) this.viewModel).info.isDegree && tmp - 32 < 0) {
                tmp = 0;
            }
            ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(tmp), "");
            if (!((ControlModel) this.viewModel).isSetDragState) {
                ((ControlModel) this.viewModel).isSetDragState = true;
            }
        }
        if (!((ControlModel) this.viewModel).isRefreshComplete || !this.isFirstControl) {
            setControlType(false);
        }
        autoAllClose();
    }

    /* access modifiers changed from: private */
    public void initVpd() {
        if (this.now != ((FragmentControlBinding) this.binding).llVpd) {
            View view = this.now;
            if (view != null) {
                view.setVisibility(8);
            }
            ((FragmentControlBinding) this.binding).llVpd.setVisibility(0);
        }
        this.now = ((FragmentControlBinding) this.binding).llVpd;
        ((FragmentControlBinding) this.binding).sbHighVpd.setValue(0, 99);
        ((FragmentControlBinding) this.binding).sbLowVpd.setValue(0, 99);
        ((FragmentControlBinding) this.binding).sbHighVpd.setProgress(((ControlModel) this.viewModel).info.highVpd);
        ((FragmentControlBinding) this.binding).sbLowVpd.setProgress(((ControlModel) this.viewModel).info.lowVpd);
        ((FragmentControlBinding) this.binding).sbHighVpd.setCheckedSilently(((ControlModel) this.viewModel).info.highVpdSwitch);
        ((FragmentControlBinding) this.binding).sbLowVpd.setCheckedSilently(((ControlModel) this.viewModel).info.lowVpdSwitch);
        initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), ((ControlModel) this.viewModel).info.vpdState);
        int i = ((ControlModel) this.viewModel).info.highVpd;
        int i2 = ((ControlModel) this.viewModel).info.lowVpd;
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(i2, i2, i, i);
        ((FragmentControlBinding) this.binding).gqDial.setStart(((ControlModel) this.viewModel).info.lowVpdSwitch, true);
        ((FragmentControlBinding) this.binding).gqDial.setEnd(((ControlModel) this.viewModel).info.highVpdSwitch, true);
        ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(((ControlModel) this.viewModel).info.vpd != -32768 ? Math.round(((float) ((ControlModel) this.viewModel).info.vpd) / 10.0f) : 0), "");
        if (!((ControlModel) this.viewModel).isSetDragState) {
            ((ControlModel) this.viewModel).isSetDragState = true;
        }
        if (!((ControlModel) this.viewModel).isRefreshComplete || !this.isFirstControl) {
            setControlType(false);
        }
        autoAllClose();
    }

    /* access modifiers changed from: private */
    public void initOff() {
        View view;
        int i = 8;
        if (!(this.now == ((FragmentControlBinding) this.binding).llFan || (view = this.now) == null)) {
            view.setVisibility(8);
        }
        RelativeLayout relativeLayout = ((FragmentControlBinding) this.binding).llFan;
        if (!isOutletDevice()) {
            i = 0;
        }
        relativeLayout.setVisibility(i);
        this.now = ((FragmentControlBinding) this.binding).llFan;
        ((FragmentControlBinding) this.binding).fpb.setTitle(Utils.getString(C1760R.string.title_control_min_fan));
        byte b = ((ControlModel) this.viewModel).info.typeOff;
        ((FragmentControlBinding) this.binding).fpb.setProgress(b);
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(b, b, WheelConstants.WHEEL_SCROLL_DELAY_DURATION, 0);
        initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), ((ControlModel) this.viewModel).info.fanState);
    }

    private void initDial(String str, byte b) {
        String str2;
        String string4Degree;
        if (((ControlModel) this.viewModel).info.workType == 8) {
            string4Degree = ProtocolTransformer.getVPDString(((ControlModel) this.viewModel).info.vpd, this.vpdFormat);
            str2 = "kPa";
        } else if (((ControlModel) this.viewModel).info.isControlTypeByHum) {
            string4Degree = ProtocolTransformer.getPer(((ControlModel) this.viewModel).info.hum, this.format);
            str2 = "%";
        } else {
            str2 = ((ControlModel) this.viewModel).info.isDegree ? ProtocolTransformer.DEGREE : ProtocolTransformer.FAH;
            string4Degree = ProtocolTransformer.getString4Degree(((ControlModel) this.viewModel).info.tmp, ((ControlModel) this.viewModel).info.isDegree, this.format);
        }
        ((FragmentControlBinding) this.binding).gqDial.setInnerCircleText(string4Degree, str2, str, String.valueOf(((ControlModel) this.viewModel).info.fan), b);
    }

    /* access modifiers changed from: private */
    public void initOn() {
        View view;
        int i = 8;
        if (!(this.now == ((FragmentControlBinding) this.binding).llFan || (view = this.now) == null)) {
            view.setVisibility(8);
        }
        RelativeLayout relativeLayout = ((FragmentControlBinding) this.binding).llFan;
        if (!isOutletDevice()) {
            i = 0;
        }
        relativeLayout.setVisibility(i);
        this.now = ((FragmentControlBinding) this.binding).llFan;
        ((FragmentControlBinding) this.binding).fpb.setTitle(Utils.getString(C1760R.string.title_control_fan));
        byte b = ((ControlModel) this.viewModel).info.typeOn;
        ((FragmentControlBinding) this.binding).fpb.setProgress(b);
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(b, b, WheelConstants.WHEEL_SCROLL_DELAY_DURATION, 0);
        ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(((ControlModel) this.viewModel).info.fan), "");
        initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), ((ControlModel) this.viewModel).info.fanState);
    }

    /* access modifiers changed from: private */
    public void initCycle() {
        if (this.now != ((FragmentControlBinding) this.binding).layoutCycle) {
            View view = this.now;
            if (view != null) {
                view.setVisibility(8);
            }
            ((FragmentControlBinding) this.binding).layoutCycle.setVisibility(0);
        }
        this.now = ((FragmentControlBinding) this.binding).layoutCycle;
        char c = ((ControlModel) this.viewModel).info.cycleOn;
        char c2 = ((ControlModel) this.viewModel).info.cycleOff;
        ((FragmentControlBinding) this.binding).sbCycleOn.setProgress(c);
        ((FragmentControlBinding) this.binding).sbCycleOff.setProgress(c2);
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(c, c, c2, c2);
        initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), (byte) 0);
        int i = ((ControlModel) this.viewModel).info.currentTypeResidueTime;
        ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(i), ((ControlModel) this.viewModel).info.currentTypeResidueOn ? "1" : "0");
        if (i > 0) {
            ((FragmentControlBinding) this.binding).gqDial.startAnimation();
        }
    }

    /* access modifiers changed from: private */
    public void initSched() {
        if (this.now != ((FragmentControlBinding) this.binding).layoutCs) {
            View view = this.now;
            if (view != null) {
                view.setVisibility(8);
            }
            ((FragmentControlBinding) this.binding).layoutCs.setVisibility(0);
        }
        this.now = ((FragmentControlBinding) this.binding).layoutCs;
        char c = ((ControlModel) this.viewModel).info.schedOn;
        char c2 = ((ControlModel) this.viewModel).info.schedOff;
        ((FragmentControlBinding) this.binding).sbFirst.setProgress(c);
        ((FragmentControlBinding) this.binding).sbLast.setProgress(c2);
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(c, c, c2, c2);
        Calendar instance = Calendar.getInstance();
        initDial(formatResidueTime(((ControlModel) this.viewModel).info.currentTypeResidueTime), (byte) 0);
        ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf((long) ((instance.get(11) * 60 * 60) + (instance.get(12) * 60) + instance.get(13))), ((ControlModel) this.viewModel).info.currentTypeResidueOn ? "1" : "0");
        if (((ControlModel) this.viewModel).info.currentTypeResidueTime > 0) {
            ((FragmentControlBinding) this.binding).gqDial.startAnimation();
        }
        autoAllClose();
    }

    private String formatResidueTime(int i) {
        long j = (long) i;
        int i2 = (int) (j / 3600);
        long j2 = j % 3600;
        int i3 = (int) (j2 / 60);
        int i4 = (int) (j2 % 60);
        if (i4 > 0) {
            if (i3 >= 59) {
                i2++;
                i3 = 0;
            } else if (!(i2 == 0 && i3 == 0)) {
                i3++;
            }
        }
        if (i2 == 0 && i3 == 0 && i4 != 0) {
            return String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(i4)});
        }
        return String.format(Locale.ENGLISH, "%d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    /* access modifiers changed from: private */
    public void initTimeOff() {
        if (this.now != ((FragmentControlBinding) this.binding).layoutTime) {
            View view = this.now;
            if (view != null) {
                view.setVisibility(8);
            }
            ((FragmentControlBinding) this.binding).layoutTime.setVisibility(0);
        }
        this.now = ((FragmentControlBinding) this.binding).layoutTime;
        ((FragmentControlBinding) this.binding).sbTime.setType(false);
        char c = ((ControlModel) this.viewModel).info.timeOff;
        ((FragmentControlBinding) this.binding).sbTime.setProgress(c);
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(c, c, WheelConstants.WHEEL_SCROLL_DELAY_DURATION, 0);
        initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), (byte) 0);
        int i = ((ControlModel) this.viewModel).info.currentTypeResidueTime;
        ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(i), "0");
        if (i > 0) {
            ((FragmentControlBinding) this.binding).gqDial.startAnimation();
        }
    }

    /* access modifiers changed from: private */
    public void initTimeOn() {
        if (this.now != ((FragmentControlBinding) this.binding).layoutTime) {
            View view = this.now;
            if (view != null) {
                view.setVisibility(8);
            }
            ((FragmentControlBinding) this.binding).layoutTime.setVisibility(0);
        }
        this.now = ((FragmentControlBinding) this.binding).layoutTime;
        ((FragmentControlBinding) this.binding).layoutTime.setVisibility(0);
        ((FragmentControlBinding) this.binding).sbTime.setType(true);
        char c = ((ControlModel) this.viewModel).info.timeOn;
        ((FragmentControlBinding) this.binding).sbTime.setProgress(c);
        ((FragmentControlBinding) this.binding).gqDial.initStartOrEndAngle(c, c, WheelConstants.WHEEL_SCROLL_DELAY_DURATION, 0);
        int i = ((ControlModel) this.viewModel).info.currentTypeResidueTime;
        initDial(ProtocolTransformer.getTimeWithAMPM(System.currentTimeMillis()), (byte) 0);
        ((FragmentControlBinding) this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(i), "1");
        if (i > 0) {
            ((FragmentControlBinding) this.binding).gqDial.startAnimation();
        }
    }

    public void onSecond() {
        int i = 0;
        if (((ControlModel) this.viewModel).overlayVisible.getValue() != null && ((ControlModel) this.viewModel).overlayVisible.getValue() != Boolean.FALSE) {
            ((ControlModel) this.viewModel).overlayVisible.setValue(false);
        } else if (((ControlModel) this.viewModel).cycleModel.getValue() != null) {
            switch (((ControlModel) this.viewModel).cycleModel.getValue().byteValue()) {
                case 1:
                    if (!isOutletDevice()) {
                        i = C1760R.C1762drawable.overlay_off;
                        break;
                    } else {
                        i = C1760R.C1762drawable.overlay_off_b;
                        break;
                    }
                case 2:
                    if (!isOutletDevice()) {
                        i = C1760R.C1762drawable.overlay_on;
                        break;
                    } else {
                        i = C1760R.C1762drawable.overlay_on_b;
                        break;
                    }
                case 3:
                    if (!isOutletDevice()) {
                        if (this.type != 6) {
                            i = C1760R.C1762drawable.overlay_auto;
                            break;
                        } else {
                            i = C1760R.C1762drawable.overlay_auto_d;
                            break;
                        }
                    } else {
                        i = C1760R.C1762drawable.overlay_auto_b;
                        break;
                    }
                case 4:
                    if (!isOutletDevice()) {
                        i = C1760R.C1762drawable.overlay_time_to_on;
                        break;
                    } else {
                        i = C1760R.C1762drawable.overlay_time_to_on_b;
                        break;
                    }
                case 5:
                    if (!isOutletDevice()) {
                        i = C1760R.C1762drawable.overlay_time_to_off;
                        break;
                    } else {
                        i = C1760R.C1762drawable.overlay_time_to_off_b;
                        break;
                    }
                case 6:
                    if (!isOutletDevice()) {
                        i = C1760R.C1762drawable.overlay_cycle;
                        break;
                    } else {
                        i = C1760R.C1762drawable.overlay_cycle_b;
                        break;
                    }
                case 7:
                    if (!isOutletDevice()) {
                        i = C1760R.C1762drawable.overlay_schedule;
                        break;
                    } else {
                        i = C1760R.C1762drawable.overlay_schedule_b;
                        break;
                    }
                case 8:
                    i = C1760R.C1762drawable.overlay_vpd;
                    break;
            }
            ((ControlModel) this.viewModel).overlayRes.setValue(Integer.valueOf(i));
            ((ControlModel) this.viewModel).overlayVisible.setValue(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void initPortTabListener() {
        if (!ProtocolTransformer.isDeviceMultiPort(this.type)) {
            ((FragmentControlBinding) this.binding).tbPort.setVisibility(8);
            return;
        }
        ((FragmentControlBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabSelectedListener);
        ((FragmentControlBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
    }

    public void onNameUpdate(List<DeviceName> list) {
        if (list != null && list.size() > 1 && ((ControlModel) this.viewModel).info != null && ((ControlModel) this.viewModel).info.portList != null && ((ControlModel) this.viewModel).info.portList.size() != 0) {
            for (PortInfo next : ((ControlModel) this.viewModel).info.portList) {
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
            int tabCount = ((FragmentControlBinding) this.binding).tbPort.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = ((FragmentControlBinding) this.binding).tbPort.getTabAt(i);
                if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                    Iterator<PortInfo> it2 = ((ControlModel) this.viewModel).info.portList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        PortInfo next3 = it2.next();
                        if (((Byte) tabAt.getTag()).byteValue() == next3.f138id) {
                            TextView textView = (TextView) tabAt.getCustomView().findViewById(C2779R.C2782id.tv_title);
                            if (next3.f138id != 0) {
                                textView.setText(ProtocolTransformer.getDisplayPortName(next3.f138id, this.type, next3.name));
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
        if (((ControlModel) this.viewModel).info != null && ((ControlModel) this.viewModel).info.portList != null && ((ControlModel) this.viewModel).info.portList.size() != 0) {
            ArrayList<PortInfo> arrayList = new ArrayList<>();
            Boolean bool = null;
            int i = 0;
            for (PortInfo next : ((ControlModel) this.viewModel).info.portList) {
                if (next.isPlug && next.f138id != 0) {
                    arrayList.add(next);
                    boolean isOutletDevice = ProtocolTransformer.isOutletDevice(this.type, next.fanType);
                    if (bool == null) {
                        bool = Boolean.valueOf(isOutletDevice);
                    } else if (bool.booleanValue() != isOutletDevice) {
                        i = 1;
                    }
                }
            }
            if (((FragmentControlBinding) this.binding).tbPort.getTabCount() == arrayList.size() + (i ^ 1)) {
                int tabCount = ((FragmentControlBinding) this.binding).tbPort.getTabCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= tabCount) {
                        z = false;
                        break;
                    }
                    TabLayout.Tab tabAt = ((FragmentControlBinding) this.binding).tbPort.getTabAt(i2);
                    if (!(tabAt == null || tabAt.getTag() == null || tabAt.getCustomView() == null)) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z2 = false;
                                break;
                            }
                            PortInfo portInfo = (PortInfo) it.next();
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
            z = true;
            if (z) {
                ((FragmentControlBinding) this.binding).tbPort.removeAllTabs();
                if (i == 0) {
                    TabLayout.Tab newTab = ((FragmentControlBinding) this.binding).tbPort.newTab();
                    View inflate = LayoutInflater.from(getContext()).inflate(C2779R.layout.item_port_all, newTab.parent, false);
                    newTab.setTag((byte) 0);
                    newTab.setCustomView(inflate);
                    if (((ControlModel) this.viewModel).port == 0 && !newTab.isSelected()) {
                        setTabSelectSilently(newTab);
                    }
                    ((FragmentControlBinding) this.binding).tbPort.addTab(newTab);
                }
                for (PortInfo portInfo2 : arrayList) {
                    TabLayout.Tab newTab2 = ((FragmentControlBinding) this.binding).tbPort.newTab();
                    View inflate2 = LayoutInflater.from(getContext()).inflate(C2779R.layout.item_port, newTab2.parent, false);
                    ((TextView) inflate2.findViewById(C2779R.C2782id.tv_title)).setText(ProtocolTransformer.getDisplayPortName(portInfo2.f138id, this.type, portInfo2.name));
                    ((ImageView) inflate2.findViewById(C1760R.C1763id.iv_image)).setImageResource(ProtocolTransformer.getPlugIcon(portInfo2.portType, portInfo2.fanType, ((ControlModel) this.viewModel).connected.getValue() == Boolean.TRUE, this.type));
                    newTab2.setTag(Byte.valueOf(portInfo2.f138id));
                    newTab2.setCustomView(inflate2);
                    if (((ControlModel) this.viewModel).port == portInfo2.f138id && !newTab2.isSelected()) {
                        setTabSelectSilently(newTab2);
                    }
                    ((FragmentControlBinding) this.binding).tbPort.addTab(newTab2);
                }
                return;
            }
            updatePlugType();
        }
    }

    private void updatePlugType() {
        if (((ControlModel) this.viewModel).info != null && ((ControlModel) this.viewModel).info.portList != null && ((ControlModel) this.viewModel).info.portList.size() != 0) {
            int tabCount = ((FragmentControlBinding) this.binding).tbPort.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tabAt = ((FragmentControlBinding) this.binding).tbPort.getTabAt(i);
                if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                    Iterator<PortInfo> it = ((ControlModel) this.viewModel).info.portList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        PortInfo next = it.next();
                        if (((Byte) tabAt.getTag()).byteValue() == next.f138id) {
                            if (next.f138id != 0) {
                                ((ImageView) tabAt.getCustomView().findViewById(C1760R.C1763id.iv_image)).setImageResource(ProtocolTransformer.getPlugIcon(next.portType, next.fanType, ((ControlModel) this.viewModel).connected.getValue() != null ? ((ControlModel) this.viewModel).connected.getValue().booleanValue() : false, this.type));
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTabSelect(byte b) {
        int tabCount = ((FragmentControlBinding) this.binding).tbPort.getTabCount();
        int i = 0;
        while (i < tabCount) {
            TabLayout.Tab tabAt = ((FragmentControlBinding) this.binding).tbPort.getTabAt(i);
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
            ((FragmentControlBinding) this.binding).tbPort.removeOnTabSelectedListener(this.tabUserSelectedListener);
            tab.select();
            ((FragmentControlBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
        }
    }

    public byte[] getLevels() {
        if (((ControlModel) this.viewModel).info == null) {
            return new byte[2];
        }
        return new byte[]{((ControlModel) this.viewModel).info.typeOn, ((ControlModel) this.viewModel).info.typeOff};
    }

    public void refreshTFP(DeviceTFP deviceTFP) {
        if (deviceTFP != null && ((ControlModel) this.viewModel).info != null && ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            ((ControlModel) this.viewModel).info.vpd = deviceTFP.vpd;
            ((ControlModel) this.viewModel).info.vpdState = deviceTFP.vpdState;
            ((ControlModel) this.viewModel).info.choosePort = deviceTFP.choosePort;
            ((ControlModel) this.viewModel).setPortList(deviceTFP.portList);
        }
    }

    abstract class DoubleListenerAdapter implements DoubleCloseAddLayoutListener {
        DoubleListenerAdapter() {
        }

        public void onFirstDown(boolean z) {
            ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
            if (z) {
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(true);
            } else {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
            }
        }

        public void onLastDown(boolean z) {
            ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
            if (z) {
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(true);
            } else {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
            }
        }

        public void onFirstChecked(boolean z) {
            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(true);
            if (!((ControlModel) ControlFragment.this.viewModel).refreshStopped) {
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
                ((ControlModel) ControlFragment.this.viewModel).sendData(true, new Consumer<Boolean>() {
                    public void accept(Boolean bool) throws Exception {
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(false);
                    }
                });
            }
        }

        public void onLastChecked(boolean z) {
            ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(true);
            if (!((ControlModel) ControlFragment.this.viewModel).refreshStopped) {
                ((ControlModel) ControlFragment.this.viewModel).cancelUpdate();
                ((ControlModel) ControlFragment.this.viewModel).sendData(true, new Consumer<Boolean>() {
                    public void accept(Boolean bool) throws Exception {
                        ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(false);
                    }
                });
            }
        }

        public void onEndFirst(boolean z, int i) {
            if (z) {
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setEndCanDraging(false);
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
            }
            ((ControlModel) ControlFragment.this.viewModel).sendData();
        }

        public void onEndLast(boolean z, int i) {
            if (z) {
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.setStartCanDraging(false);
                ((FragmentControlBinding) ControlFragment.this.binding).gqDial.invalidate();
            }
            ((ControlModel) ControlFragment.this.viewModel).sendData();
        }
    }
}
