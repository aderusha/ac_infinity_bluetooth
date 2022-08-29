package com.eternal.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import com.eternal.base.BaseFragment;
import com.eternal.base.IConnectAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.control.databinding.FragmentControlCBindingImpl;
import com.eternal.control.view.GuQiangCycleDialView;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import com.eternal.widget.guqiang.DoubleCloseAddLayoutListener;
import com.eternal.widget.guqiang.IEffectBar;
import com.google.common.primitives.UnsignedBytes;
import com.jakewharton.rxbinding2.view.RxView;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.functions.Consumer;

public class ControlCFragment extends BaseFragment<FragmentControlCBindingImpl, ControlCModel> implements IConnectAction, IToolBarAction {
    public static final String REFRESH = "refresh";
    /* access modifiers changed from: private */
    public DecimalFormat format;
    /* access modifiers changed from: private */
    public boolean isFirstControl = false;
    /* access modifiers changed from: private */
    public String mac;
    /* access modifiers changed from: private */
    public DecimalFormat vpdFormat;

    private void initShowListener() {
    }

    public void onNameUpdate(List<DeviceName> list) {
    }

    public void setConnectType(String str, byte b) {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C1760R.layout.fragment_control_c;
    }

    public int initVariableId() {
        return C1669BR.CModel;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initListener();
        registerMessage();
        bindingEvent();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
        this.vpdFormat = decimalFormat2;
        decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);
        Intent intent = getActivity().getIntent();
        this.mac = intent.getStringExtra(ActivityEvent.DEVICE_MAC);
        String stringExtra = intent.getStringExtra(ActivityEvent.DEVICE_ID);
        byte byteExtra = intent.getByteExtra(ActivityEvent.DEVICE_PORT, (byte) 0);
        byte byteExtra2 = intent.getByteExtra(ActivityEvent.DEVICE_VERSION, (byte) 0);
        ((ControlCModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository(), this.mac, stringExtra, byteExtra);
        initView(byteExtra2);
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            ((ControlCModel) this.viewModel).stop();
        } else {
            ((ControlCModel) this.viewModel).start();
        }
    }

    public void connected() {
        ((ControlCModel) this.viewModel).connected.setValue(true);
        ((ControlCModel) this.viewModel).start();
    }

    public void disconnected() {
        ((ControlCModel) this.viewModel).connected.setValue(false);
        ((ControlCModel) this.viewModel).stop();
    }

    public void onDestroyView() {
        unregisterMessage();
        ((ControlCModel) this.viewModel).save();
        super.onDestroyView();
    }

    private void initListener() {
        initTmpListener();
        initHumListener();
        initDialListener();
        initShowListener();
    }

    private void initView(byte b) {
        C16701 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                if (((ControlCModel) ControlCFragment.this.viewModel).info == null || !((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                    return Utils.getString(C1760R.string.tip_fah_num, Integer.valueOf(i));
                }
                return Utils.getString(C1760R.string.tip_degree_num, Integer.valueOf(i));
            }
        };
        ((FragmentControlCBindingImpl) this.binding).sbHighTmp.setFactory(r0);
        ((FragmentControlCBindingImpl) this.binding).sbLowTmp.setFactory(r0);
        C16732 r02 = new IEffectBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C1760R.string.tip_percent_num, Integer.valueOf(i));
            }
        };
        ((FragmentControlCBindingImpl) this.binding).sbHighHum.setFactory(r02);
        ((FragmentControlCBindingImpl) this.binding).sbLowHum.setFactory(r02);
        boolean z = true;
        ((FragmentControlCBindingImpl) this.binding).gqDial.setDeviceC(true);
        GuQiangCycleDialView guQiangCycleDialView = ((FragmentControlCBindingImpl) this.binding).gqDial;
        if (b == 0) {
            z = false;
        }
        guQiangCycleDialView.setFillWhenEqual(z);
    }

    private void initDialListener() {
        ((FragmentControlCBindingImpl) this.binding).gqDial.setOnChangeListeners(new GuQiangCycleDialView.OnChangeListeners() {
            public void onChangeTouchStart(boolean z) {
                if (((ControlCModel) ControlCFragment.this.viewModel).info != null) {
                    ((ControlCModel) ControlCFragment.this.viewModel).cancelUpdate();
                    if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                        if (z) {
                            if (!((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowHum.isChecked()) {
                                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStart(true);
                                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowHum.setChecked(true);
                            }
                        } else if (!((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighHum.isChecked()) {
                            ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEnd(true);
                            ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighHum.setChecked(true);
                        }
                    } else if (z) {
                        if (!((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowTmp.isChecked()) {
                            ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStart(true, true);
                            ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowTmp.setChecked(true);
                        }
                    } else if (!((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighTmp.isChecked()) {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEnd(true, true);
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighTmp.setChecked(true);
                    }
                }
            }

            public void onChangeStartAndEnd(int i, int i2) {
                if (((ControlCModel) ControlCFragment.this.viewModel).info != null) {
                    if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowHum.setProgress(i);
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighHum.setProgress(i2);
                    } else if (((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowTmp.setProgress(i);
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighTmp.setProgress(i2);
                    } else {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbLowTmp.setProgress(i + 32);
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).sbHighTmp.setProgress(i2 + 32);
                    }
                }
            }

            public void onChangeAndEndByTouchEnd(boolean z, int i, int i2) {
                ((ControlCModel) ControlCFragment.this.viewModel).sendData();
            }
        });
    }

    private void initTmpListener() {
        ((FragmentControlCBindingImpl) this.binding).layoutTmp.setListener(new DoubleListenerAdapter() {
            public void onFirst(int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighTmp = (byte) i;
                if (!((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    int i2 = UnsignedBytes.toInt(((ControlCModel) ControlCFragment.this.viewModel).info.autoLowTmp);
                    if (!((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                        i -= 32;
                        i2 -= 32;
                    }
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.initStartOrEndAngle(i2, i2, i, i);
                }
            }

            public void onLast(int i) {
                if (((ControlCModel) ControlCFragment.this.viewModel).info != null) {
                    ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowTmp = (byte) i;
                    if (!((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                        int i2 = UnsignedBytes.toInt(((ControlCModel) ControlCFragment.this.viewModel).info.autoHighTmp);
                        if (!((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                            i -= 32;
                            i2 -= 32;
                        }
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.initStartOrEndAngle(i, i, i2, i2);
                    }
                }
            }

            public void onEndFirst(boolean z, int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighTmp = (byte) i;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(false);
                }
                super.onEndFirst(z, i);
            }

            public void onEndLast(boolean z, int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowTmp = (byte) i;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(false);
                }
                super.onEndLast(z, i);
            }

            public void onFirstChecked(boolean z) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighTmpSwitch = z;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(false);
                }
                if (((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.getEnd() != z) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEnd(z, true);
                }
                ControlCFragment.this.autoAllClose();
                super.onFirstChecked(z);
            }

            public void onLastChecked(boolean z) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowTmpSwitch = z;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(false);
                }
                if (((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.getStart() != z) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStart(z, true);
                }
                ControlCFragment.this.autoAllClose();
                super.onLastChecked(z);
            }
        });
    }

    private void initHumListener() {
        ((FragmentControlCBindingImpl) this.binding).layoutHum.setListener(new DoubleListenerAdapter() {
            public void onFirst(int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighHum = (byte) i;
                if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    byte b = ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighHum;
                    byte b2 = ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowHum;
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.initStartOrEndAngle(b2, b2, b, b);
                }
            }

            public void onLast(int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowHum = (byte) i;
                if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    byte b = ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighHum;
                    byte b2 = ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowHum;
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.initStartOrEndAngle(b2, b2, b, b);
                }
            }

            public void onEndFirst(boolean z, int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighHum = (byte) i;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(true);
                }
                super.onEndFirst(z, i);
            }

            public void onEndLast(boolean z, int i) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowHum = (byte) i;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(true);
                }
                super.onEndLast(z, i);
            }

            public void onFirstChecked(boolean z) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighHumSwitch = z;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(true);
                }
                if (((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.getEnd() != z) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEnd(z, true);
                }
                super.onFirstChecked(z);
                ControlCFragment.this.autoAllClose();
            }

            public void onLastChecked(boolean z) {
                ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowHumSwitch = z;
                if (((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete) {
                    ControlCFragment.this.setControlType(true);
                }
                if (((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.getStart() != z) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStart(z, true);
                }
                ControlCFragment.this.autoAllClose();
                super.onLastChecked(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void autoAllClose() {
        boolean z = !((ControlCModel) this.viewModel).info.autoHighTmpSwitch && !((ControlCModel) this.viewModel).info.autoLowTmpSwitch && !((ControlCModel) this.viewModel).info.autoHighHumSwitch && !((ControlCModel) this.viewModel).info.autoLowHumSwitch;
        if (((FragmentControlCBindingImpl) this.binding).gqDial.getAutoCloseAll() != z) {
            ((FragmentControlCBindingImpl) this.binding).gqDial.setAutoCloseAll(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setControlType(boolean z) {
        boolean z2 = ((ControlCModel) this.viewModel).info.autoHighTmpSwitch;
        boolean z3 = ((ControlCModel) this.viewModel).info.autoLowTmpSwitch;
        boolean z4 = ((ControlCModel) this.viewModel).info.autoHighHumSwitch;
        boolean z5 = ((ControlCModel) this.viewModel).info.autoLowHumSwitch;
        boolean z6 = true;
        if (!z ? z2 || z3 || (!z4 && !z5) : !z4 && !z5 && (z2 || z3)) {
            z6 = false;
        }
        if (((ControlCModel) this.viewModel).controlTypeByHumModel.getValue() == null || z6 != ((ControlCModel) this.viewModel).controlTypeByHumModel.getValue().booleanValue()) {
            ((ControlCModel) this.viewModel).controlTypeByHumModel.setValue(Boolean.valueOf(z6));
        }
    }

    private void bindingEvent() {
        RxView.clicks(((FragmentControlCBindingImpl) this.binding).vMask).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            public void accept(Object obj) throws Exception {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(ControlCFragment.this.mac)) {
                    ControlCFragment.this.showFailDialog(Utils.getString(C2663R.string.tip_ble_disconnect));
                }
            }
        });
        ((ControlCModel) this.viewModel).show.observe(this, new Observer<String>() {
            public void onChanged(String str) {
            }
        });
        ((ControlCModel) this.viewModel).controlTypeByHumModel.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                ControlCFragment.this.switchControlType(bool.booleanValue(), ((ControlCModel) ControlCFragment.this.viewModel).isRefreshComplete && ControlCFragment.this.isFirstControl);
                if (!ControlCFragment.this.isFirstControl) {
                    boolean unused = ControlCFragment.this.isFirstControl = true;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void switchControlType(boolean z, final boolean z2) {
        ((ControlCModel) this.viewModel).info.isControlTypeByHum = z;
        ((ControlCModel) this.viewModel).setControlTypeByHum(z);
        initAuto();
        if (!((ControlCModel) this.viewModel).info.isControlTypeByHum) {
            ((ControlCModel) this.viewModel).show.setValue(Utils.getString(C1760R.string.tip_percent_num, ProtocolTransformer.getPer(((ControlCModel) this.viewModel).info.hum, this.format)));
        } else if (((ControlCModel) this.viewModel).info.isDegree) {
            ((ControlCModel) this.viewModel).show.setValue(Utils.getString(C1760R.string.tip_degree_num, ProtocolTransformer.getC_TmpString(((ControlCModel) this.viewModel).info.tmp, true, this.format)));
        } else {
            ((ControlCModel) this.viewModel).show.setValue(Utils.getString(C1760R.string.tip_fah_num, ProtocolTransformer.getC_TmpString(((ControlCModel) this.viewModel).info.tmp, false, this.format)));
        }
        ((FragmentControlCBindingImpl) this.binding).gqDial.animate().setDuration(z2 ? 1000 : 1).rotationBy(360.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                String str;
                String str2;
                int i;
                int i2;
                super.onAnimationEnd(animator);
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setRotation(0.0f);
                if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    str = ProtocolTransformer.getPer(((ControlCModel) ControlCFragment.this.viewModel).info.hum, ControlCFragment.this.format);
                    str2 = "%";
                } else if (((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                    str = ProtocolTransformer.getC_TmpString(((ControlCModel) ControlCFragment.this.viewModel).info.tmp, ((ControlCModel) ControlCFragment.this.viewModel).info.isDegree, ControlCFragment.this.format);
                    str2 = ProtocolTransformer.DEGREE;
                } else {
                    str = ProtocolTransformer.getC_TmpString(((ControlCModel) ControlCFragment.this.viewModel).info.tmp, ((ControlCModel) ControlCFragment.this.viewModel).info.isDegree, ControlCFragment.this.format);
                    str2 = ProtocolTransformer.FAH;
                }
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setTmp(str, str2);
                int i3 = 0;
                if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setModeType(6);
                    byte b = ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowHum;
                    byte b2 = ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighHum;
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.initStartOrEndAngle(b, b, b2, b2);
                    if (((ControlCModel) ControlCFragment.this.viewModel).info.hum != -32768) {
                        i3 = Math.round(((float) ((ControlCModel) ControlCFragment.this.viewModel).info.hum) / 100.0f);
                    }
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(i3), "");
                } else {
                    if (((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setModeType(4);
                        i = ((ControlCModel) ControlCFragment.this.viewModel).info.autoHighTmp;
                        i2 = ((ControlCModel) ControlCFragment.this.viewModel).info.autoLowTmp;
                    } else {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setModeType(5);
                        i = UnsignedBytes.toInt(((ControlCModel) ControlCFragment.this.viewModel).info.autoHighTmp) - 32;
                        i2 = UnsignedBytes.toInt(((ControlCModel) ControlCFragment.this.viewModel).info.autoLowTmp) - 32;
                    }
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.initStartOrEndAngle(i2, i2, i, i);
                    int c_Tmp = ProtocolTransformer.getC_Tmp(((ControlCModel) ControlCFragment.this.viewModel).info.tmp);
                    if (((ControlCModel) ControlCFragment.this.viewModel).info.isDegree || c_Tmp - 32 >= 0) {
                        i3 = c_Tmp;
                    }
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStatusLineAndCenterText(String.valueOf(i3), "");
                }
                if (z2) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.startAnimation();
                }
            }

            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
            }
        }).start();
        if (z2) {
            ((FragmentControlCBindingImpl) this.binding).gqDial.startAnimation();
        }
        ((FragmentControlCBindingImpl) this.binding).scContent.post(new Runnable() {
            public void run() {
                if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    if (z2) {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).scContent.smoothScrollTo(0, ((FragmentControlCBindingImpl) ControlCFragment.this.binding).tvHumTitle.getTop());
                    } else {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).scContent.scrollTo(0, ((FragmentControlCBindingImpl) ControlCFragment.this.binding).tvHumTitle.getTop());
                    }
                } else if (z2) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).scContent.smoothScrollTo(0, ((FragmentControlCBindingImpl) ControlCFragment.this.binding).tvTmpTitle.getTop());
                } else {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).scContent.scrollTo(0, ((FragmentControlCBindingImpl) ControlCFragment.this.binding).tvTmpTitle.getTop());
                }
            }
        });
    }

    private void registerMessage() {
        Messenger.getDefault().register((Object) this, (Object) REFRESH, (BindingAction) new BindingAction() {
            public void call() {
                ((ControlCModel) ControlCFragment.this.viewModel).tmpSize.set(ProtocolTransformer.getC_TmpString(((ControlCModel) ControlCFragment.this.viewModel).info.tmp, ((ControlCModel) ControlCFragment.this.viewModel).info.isDegree, ControlCFragment.this.format));
                ((ControlCModel) ControlCFragment.this.viewModel).tmpFlag.set(((ControlCModel) ControlCFragment.this.viewModel).info.isDegree ? ProtocolTransformer.DEGREE : ProtocolTransformer.FAH);
                ((ControlCModel) ControlCFragment.this.viewModel).perSize.set(ProtocolTransformer.getPer(((ControlCModel) ControlCFragment.this.viewModel).info.hum, ControlCFragment.this.format));
                ((ControlCModel) ControlCFragment.this.viewModel).vpdSize.set(ProtocolTransformer.getVPDString(((ControlCModel) ControlCFragment.this.viewModel).info.tmp, ((ControlCModel) ControlCFragment.this.viewModel).info.hum, ((ControlCModel) ControlCFragment.this.viewModel).info.leafTemperatureC, ((ControlCModel) ControlCFragment.this.viewModel).info.isDegree, ControlCFragment.this.vpdFormat));
                if (((ControlCModel) ControlCFragment.this.viewModel).info.isControlTypeByHum) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setModeType(6);
                } else if (((ControlCModel) ControlCFragment.this.viewModel).info.isDegree) {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setModeType(4);
                } else {
                    ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setModeType(5);
                }
                ((ControlCModel) ControlCFragment.this.viewModel).hintText.set("All triggers can occur concurrently, turn off if not in use.");
                ControlCFragment.this.initAuto();
            }
        });
    }

    private void unregisterMessage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x02d0, code lost:
        if (r0 < 0) goto L_0x02d2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initAuto() {
        /*
            r8 = this;
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            boolean r0 = r0.isDegree
            r1 = 0
            if (r0 == 0) goto L_0x0020
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbHighTmp
            r2 = 90
            r0.setValue(r1, r2)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbLowTmp
            r0.setValue(r1, r2)
            goto L_0x0036
        L_0x0020:
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbHighTmp
            r2 = 32
            r3 = 162(0xa2, float:2.27E-43)
            r0.setValue(r2, r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbLowTmp
            r0.setValue(r2, r3)
        L_0x0036:
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            r2 = 1
            if (r0 == 0) goto L_0x012d
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            boolean r0 = r0.isRefreshComplete
            if (r0 == 0) goto L_0x012d
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            byte r0 = r0.autoHighTmp
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoHighTmp
            if (r0 != r3) goto L_0x00fa
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            byte r0 = r0.autoLowTmp
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoLowTmp
            if (r0 != r3) goto L_0x00fa
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            boolean r0 = r0.autoHighTmpSwitch
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoHighTmpSwitch
            if (r0 != r3) goto L_0x00fa
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            boolean r0 = r0.autoLowTmpSwitch
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoLowTmpSwitch
            if (r0 == r3) goto L_0x0090
            goto L_0x00fa
        L_0x0090:
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            byte r0 = r0.autoHighHum
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoHighHum
            if (r0 != r3) goto L_0x00d8
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            byte r0 = r0.autoLowHum
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoLowHum
            if (r0 != r3) goto L_0x00d8
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            boolean r0 = r0.autoHighHumSwitch
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoHighHumSwitch
            if (r0 != r3) goto L_0x00d8
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            boolean r0 = r0.autoLowHumSwitch
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoLowHumSwitch
            if (r0 == r3) goto L_0x012d
        L_0x00d8:
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.controlTypeByHumModel
            java.lang.Object r0 = r0.getValue()
            if (r0 == 0) goto L_0x00f6
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.controlTypeByHumModel
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x012d
        L_0x00f6:
            r8.setControlType(r2)
            goto L_0x012d
        L_0x00fa:
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.controlTypeByHumModel
            java.lang.Object r0 = r0.getValue()
            if (r0 == 0) goto L_0x012d
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.controlTypeByHumModel
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x012d
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.lastInfo
            boolean r0 = r0.isDegree
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.isDegree
            if (r0 != r3) goto L_0x012d
            r8.setControlType(r1)
        L_0x012d:
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbHighTmp
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoHighTmp
            int r3 = com.google.common.primitives.UnsignedBytes.toInt(r3)
            r0.setProgress(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbLowTmp
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoLowTmp
            int r3 = com.google.common.primitives.UnsignedBytes.toInt(r3)
            r0.setProgress(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbHighHum
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoHighHum
            r0.setProgress(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbLowHum
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            byte r3 = r3.autoLowHum
            r0.setProgress(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbHighTmp
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoHighTmpSwitch
            r0.setChecked(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbLowTmp
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoLowTmpSwitch
            r0.setChecked(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbHighHum
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoHighHumSwitch
            r0.setChecked(r3)
            androidx.databinding.ViewDataBinding r0 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r0 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r0
            com.eternal.widget.guqiang.SingleAddView r0 = r0.sbLowHum
            com.eternal.framework.component.BaseViewModel r3 = r8.viewModel
            com.eternal.control.ControlCModel r3 = (com.eternal.control.ControlCModel) r3
            com.eternal.base.concat.DeviceModel r3 = r3.info
            boolean r3 = r3.autoLowHumSwitch
            r0.setChecked(r3)
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            boolean r0 = r0.isControlTypeByHum
            java.lang.String r3 = ""
            r4 = -32768(0xffffffffffff8000, float:NaN)
            if (r0 == 0) goto L_0x0244
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.String r0 = com.eternal.base.protocol.ProtocolTransformer.getTime(r5)
            com.eternal.framework.component.BaseViewModel r5 = r8.viewModel
            com.eternal.control.ControlCModel r5 = (com.eternal.control.ControlCModel) r5
            com.eternal.base.concat.DeviceModel r5 = r5.info
            byte r5 = r5.humState
            r8.initDial(r0, r5)
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            byte r0 = r0.autoHighHum
            com.eternal.framework.component.BaseViewModel r5 = r8.viewModel
            com.eternal.control.ControlCModel r5 = (com.eternal.control.ControlCModel) r5
            com.eternal.base.concat.DeviceModel r5 = r5.info
            byte r5 = r5.autoLowHum
            androidx.databinding.ViewDataBinding r6 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r6 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r6
            com.eternal.control.view.GuQiangCycleDialView r6 = r6.gqDial
            r6.initStartOrEndAngle(r5, r5, r0, r0)
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            int r0 = r0.hum
            if (r0 == r4) goto L_0x0212
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            int r0 = r0.hum
            float r0 = (float) r0
            r4 = 1120403456(0x42c80000, float:100.0)
            float r0 = r0 / r4
            int r0 = java.lang.Math.round(r0)
            goto L_0x0213
        L_0x0212:
            r0 = 0
        L_0x0213:
            androidx.databinding.ViewDataBinding r4 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r4 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r4
            com.eternal.control.view.GuQiangCycleDialView r4 = r4.gqDial
            com.eternal.framework.component.BaseViewModel r5 = r8.viewModel
            com.eternal.control.ControlCModel r5 = (com.eternal.control.ControlCModel) r5
            com.eternal.base.concat.DeviceModel r5 = r5.info
            boolean r5 = r5.autoLowHumSwitch
            r4.setStart(r5, r2)
            androidx.databinding.ViewDataBinding r4 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r4 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r4
            com.eternal.control.view.GuQiangCycleDialView r4 = r4.gqDial
            com.eternal.framework.component.BaseViewModel r5 = r8.viewModel
            com.eternal.control.ControlCModel r5 = (com.eternal.control.ControlCModel) r5
            com.eternal.base.concat.DeviceModel r5 = r5.info
            boolean r5 = r5.autoHighHumSwitch
            r4.setEnd(r5, r2)
            androidx.databinding.ViewDataBinding r2 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r2 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r2
            com.eternal.control.view.GuQiangCycleDialView r2 = r2.gqDial
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.setStatusLineAndCenterText(r0, r3)
            goto L_0x02e0
        L_0x0244:
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.String r0 = com.eternal.base.protocol.ProtocolTransformer.getTime(r5)
            com.eternal.framework.component.BaseViewModel r5 = r8.viewModel
            com.eternal.control.ControlCModel r5 = (com.eternal.control.ControlCModel) r5
            com.eternal.base.concat.DeviceModel r5 = r5.info
            byte r5 = r5.tmpState
            r8.initDial(r0, r5)
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            byte r0 = r0.autoHighTmp
            int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
            com.eternal.framework.component.BaseViewModel r5 = r8.viewModel
            com.eternal.control.ControlCModel r5 = (com.eternal.control.ControlCModel) r5
            com.eternal.base.concat.DeviceModel r5 = r5.info
            byte r5 = r5.autoLowTmp
            int r5 = com.google.common.primitives.UnsignedBytes.toInt(r5)
            com.eternal.framework.component.BaseViewModel r6 = r8.viewModel
            com.eternal.control.ControlCModel r6 = (com.eternal.control.ControlCModel) r6
            com.eternal.base.concat.DeviceModel r6 = r6.info
            boolean r6 = r6.isDegree
            if (r6 != 0) goto L_0x0283
            int r0 = r0 + -32
            int r5 = r5 + -32
            if (r0 >= 0) goto L_0x0280
            r0 = 0
        L_0x0280:
            if (r5 >= 0) goto L_0x0283
            r5 = 0
        L_0x0283:
            androidx.databinding.ViewDataBinding r6 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r6 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r6
            com.eternal.control.view.GuQiangCycleDialView r6 = r6.gqDial
            com.eternal.framework.component.BaseViewModel r7 = r8.viewModel
            com.eternal.control.ControlCModel r7 = (com.eternal.control.ControlCModel) r7
            com.eternal.base.concat.DeviceModel r7 = r7.info
            boolean r7 = r7.autoLowTmpSwitch
            r6.setStart(r7, r2)
            androidx.databinding.ViewDataBinding r6 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r6 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r6
            com.eternal.control.view.GuQiangCycleDialView r6 = r6.gqDial
            com.eternal.framework.component.BaseViewModel r7 = r8.viewModel
            com.eternal.control.ControlCModel r7 = (com.eternal.control.ControlCModel) r7
            com.eternal.base.concat.DeviceModel r7 = r7.info
            boolean r7 = r7.autoHighTmpSwitch
            r6.setEnd(r7, r2)
            androidx.databinding.ViewDataBinding r2 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r2 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r2
            com.eternal.control.view.GuQiangCycleDialView r2 = r2.gqDial
            r2.initStartOrEndAngle(r5, r5, r0, r0)
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            int r0 = r0.tmp
            if (r0 == r4) goto L_0x02d2
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            com.eternal.base.concat.DeviceModel r0 = r0.info
            int r0 = r0.tmp
            int r0 = com.eternal.base.protocol.ProtocolTransformer.getC_Tmp(r0)
            com.eternal.framework.component.BaseViewModel r2 = r8.viewModel
            com.eternal.control.ControlCModel r2 = (com.eternal.control.ControlCModel) r2
            com.eternal.base.concat.DeviceModel r2 = r2.info
            boolean r2 = r2.isDegree
            if (r2 != 0) goto L_0x02d0
            int r0 = r0 + -32
        L_0x02d0:
            if (r0 >= 0) goto L_0x02d3
        L_0x02d2:
            r0 = 0
        L_0x02d3:
            androidx.databinding.ViewDataBinding r2 = r8.binding
            com.eternal.control.databinding.FragmentControlCBindingImpl r2 = (com.eternal.control.databinding.FragmentControlCBindingImpl) r2
            com.eternal.control.view.GuQiangCycleDialView r2 = r2.gqDial
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.setStatusLineAndCenterText(r0, r3)
        L_0x02e0:
            com.eternal.framework.component.BaseViewModel r0 = r8.viewModel
            com.eternal.control.ControlCModel r0 = (com.eternal.control.ControlCModel) r0
            boolean r0 = r0.isRefreshComplete
            if (r0 == 0) goto L_0x02ec
            boolean r0 = r8.isFirstControl
            if (r0 != 0) goto L_0x02ef
        L_0x02ec:
            r8.setControlType(r1)
        L_0x02ef:
            r8.autoAllClose()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.ControlCFragment.initAuto():void");
    }

    private void initDial(String str, byte b) {
        String str2;
        String str3;
        if (((ControlCModel) this.viewModel).info.isControlTypeByHum) {
            String per = ProtocolTransformer.getPer(((ControlCModel) this.viewModel).info.hum, this.format);
            if (((ControlCModel) this.viewModel).info.isDegree) {
                ((ControlCModel) this.viewModel).show.setValue(Utils.getString(C1760R.string.tip_degree_num, ProtocolTransformer.getC_TmpString(((ControlCModel) this.viewModel).info.tmp, true, this.format)));
            } else {
                ((ControlCModel) this.viewModel).show.setValue(Utils.getString(C1760R.string.tip_fah_num, ProtocolTransformer.getC_TmpString(((ControlCModel) this.viewModel).info.tmp, false, this.format)));
            }
            str3 = per;
            str2 = "%";
        } else {
            String str4 = ((ControlCModel) this.viewModel).info.isDegree ? ProtocolTransformer.DEGREE : ProtocolTransformer.FAH;
            String c_TmpString = ProtocolTransformer.getC_TmpString(((ControlCModel) this.viewModel).info.tmp, ((ControlCModel) this.viewModel).info.isDegree, this.format);
            ((ControlCModel) this.viewModel).show.setValue(Utils.getString(C1760R.string.tip_percent_num, ProtocolTransformer.getPer(((ControlCModel) this.viewModel).info.hum, this.format)));
            str2 = str4;
            str3 = c_TmpString;
        }
        ((FragmentControlCBindingImpl) this.binding).gqDial.setInnerCircleText(str3, str2, str, String.valueOf(((ControlCModel) this.viewModel).info.fan), b);
    }

    public void onSecond() {
        if (((ControlCModel) this.viewModel).overlayVisible.getValue() == null || ((ControlCModel) this.viewModel).overlayVisible.getValue() == Boolean.FALSE) {
            ((ControlCModel) this.viewModel).overlayRes.setValue(Integer.valueOf(C1760R.C1762drawable.overlay_auto_c));
            ((ControlCModel) this.viewModel).overlayVisible.setValue(true);
            return;
        }
        ((ControlCModel) this.viewModel).overlayVisible.setValue(false);
    }

    abstract class DoubleListenerAdapter implements DoubleCloseAddLayoutListener {
        DoubleListenerAdapter() {
        }

        public void onFirstDown(boolean z) {
            ((ControlCModel) ControlCFragment.this.viewModel).cancelUpdate();
            if (z) {
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEndCanDraging(true);
            } else {
                RxBus.getDefault().post(new ProgressEvent((byte) 3, 50.0f));
            }
        }

        public void onLastDown(boolean z) {
            ((ControlCModel) ControlCFragment.this.viewModel).cancelUpdate();
            if (z) {
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStartCanDraging(true);
            } else {
                RxBus.getDefault().post(new ProgressEvent((byte) 3, 50.0f));
            }
        }

        public void onFirstChecked(boolean z) {
            if (!((ControlCModel) ControlCFragment.this.viewModel).refreshStopped) {
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEndCanDraging(true);
                ((ControlCModel) ControlCFragment.this.viewModel).cancelUpdate();
                ((ControlCModel) ControlCFragment.this.viewModel).sendData(new Consumer<Boolean>() {
                    public void accept(Boolean bool) throws Exception {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEndCanDraging(false);
                    }
                });
            }
        }

        public void onLastChecked(boolean z) {
            if (!((ControlCModel) ControlCFragment.this.viewModel).refreshStopped) {
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStartCanDraging(true);
                ((ControlCModel) ControlCFragment.this.viewModel).cancelUpdate();
                ((ControlCModel) ControlCFragment.this.viewModel).sendData(new Consumer<Boolean>() {
                    public void accept(Boolean bool) throws Exception {
                        ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStartCanDraging(false);
                    }
                });
            }
        }

        public void onEndFirst(boolean z, int i) {
            ((ControlCModel) ControlCFragment.this.viewModel).sendData();
            if (z) {
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setEndCanDraging(false);
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.invalidate();
            }
        }

        public void onEndLast(boolean z, int i) {
            ((ControlCModel) ControlCFragment.this.viewModel).sendData();
            if (z) {
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.setStartCanDraging(false);
                ((FragmentControlCBindingImpl) ControlCFragment.this.binding).gqDial.invalidate();
            }
        }
    }
}
