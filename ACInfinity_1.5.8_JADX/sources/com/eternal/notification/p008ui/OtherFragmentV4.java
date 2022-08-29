package com.eternal.notification.p008ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.utils.GsonUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.notification.C2419BR;
import com.eternal.notification.C2420R;
import com.eternal.notification.databinding.FragmentOtherV4Binding;
import com.eternal.notification.model.OtherModelV4;
import com.eternal.widget.guqiang.DoubleCloseAddLayoutListener;
import com.eternal.widget.guqiang.IEffectBar;
import com.eternal.widget.guqiang.RangeSeekBar;
import java.util.Locale;

/* renamed from: com.eternal.notification.ui.OtherFragmentV4 */
public class OtherFragmentV4 extends BaseFragment<FragmentOtherV4Binding, OtherModelV4> implements ISoftKeyBoardAction, IConnectAction {
    public static String UPDATE_PARAM = "update param";
    MaterialDialog dialog;
    int groupId;
    private boolean isUserSlide;
    /* access modifiers changed from: private */
    public MutableLiveData<Boolean> now;
    private byte type;
    /* access modifiers changed from: private */
    public byte version;

    public void connected() {
    }

    public void disconnected() {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C2420R.layout.fragment_other_v4;
    }

    public int initVariableId() {
        return C2419BR.otherModelV4;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        boolean z = arguments.getBoolean(ActivityEvent.DEVICE_DEGREE);
        initListener(z);
        String string = arguments.getString(ActivityEvent.NOTIFICATION);
        this.type = arguments.getByte(ActivityEvent.NOTIFICATION_TYPE);
        this.version = arguments.getByte(ActivityEvent.DEVICE_VERSION);
        initView(z, string != null, this.type);
        registerMessage();
        bindEvent();
        ((OtherModelV4) this.viewModel).init(RepositoryInjection.providerNotificationRepository(), arguments.getString(ActivityEvent.DEVICE_MAC), arguments.getString(ActivityEvent.DEVICE_ID), arguments.getByte(ActivityEvent.DEVICE_PORT), arguments.getByte(ActivityEvent.DEVICE_TYPE), (Notification) GsonUtils.fromJson(string, Notification.class), z, this.type, this.version, arguments.getByte(ActivityEvent.NOTIFICATION_TYPE));
    }

    public void onDestroyView() {
        unregisterMessage();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (((OtherModelV4) this.viewModel).saveDis != null && !((OtherModelV4) this.viewModel).saveDis.isDisposed()) {
            ((OtherModelV4) this.viewModel).saveDis.dispose();
        }
        super.onDestroyView();
    }

    public void initListener(boolean z) {
        initTmpListener(z);
        initHumListener();
        initVpdListener();
    }

    private void initTmpListener(boolean z) {
        if (z) {
            ((FragmentOtherV4Binding) this.binding).tvTempMin.setText("0℃");
            ((FragmentOtherV4Binding) this.binding).tvTempMax.setText("90℃");
            ((OtherModelV4) this.viewModel).tmpUnit.setValue(Utils.getString(C2420R.string.tip_degree_num));
            ((FragmentOtherV4Binding) this.binding).rsbTemp.setMin(0);
            ((FragmentOtherV4Binding) this.binding).rsbTemp.setDistance(90);
            ((FragmentOtherV4Binding) this.binding).sbHighTmp.setValue(0, 90);
            ((FragmentOtherV4Binding) this.binding).sbLowTmp.setValue(0, 90);
        } else {
            ((FragmentOtherV4Binding) this.binding).tvTempMin.setText("32℉");
            ((FragmentOtherV4Binding) this.binding).tvTempMax.setText("194℉");
            ((FragmentOtherV4Binding) this.binding).rsbTemp.setMin(32);
            ((FragmentOtherV4Binding) this.binding).rsbTemp.setDistance(162);
            ((FragmentOtherV4Binding) this.binding).sbHighTmp.setValue(32, 162);
            ((FragmentOtherV4Binding) this.binding).sbLowTmp.setValue(32, 162);
        }
        ((FragmentOtherV4Binding) this.binding).rsbTemp.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                if (((OtherModelV4) OtherFragmentV4.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        });
        ((FragmentOtherV4Binding) this.binding).rsbTemp.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowTmp.setChecked(z);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighTmp.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    char c = (char) i2;
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).hTmp.setValue(Character.valueOf(c));
                    char c2 = (char) i;
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).lTmp.setValue(Character.valueOf(c2));
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighTmp.setProgress(c);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowTmp.setProgress(c2);
                }
            }
        });
        C26543 r3 = new IEffectBar.Factory() {
            public String getText(int i) {
                if (((OtherModelV4) OtherFragmentV4.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        };
        ((FragmentOtherV4Binding) this.binding).sbHighTmp.setFactory(r3);
        ((FragmentOtherV4Binding) this.binding).sbLowTmp.setFactory(r3);
        ((FragmentOtherV4Binding) this.binding).layoutTmp.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setHigh(i);
            }

            public void onLast(int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setLowSwitch(z);
            }
        });
    }

    private void initHumListener() {
        ((FragmentOtherV4Binding) this.binding).rsbHum.setMin(0);
        ((FragmentOtherV4Binding) this.binding).rsbHum.setDistance(100);
        ((FragmentOtherV4Binding) this.binding).sbHighHum.setValue(0, 100);
        ((FragmentOtherV4Binding) this.binding).sbLowHum.setValue(0, 100);
        ((FragmentOtherV4Binding) this.binding).rsbHum.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        });
        ((FragmentOtherV4Binding) this.binding).rsbHum.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowHum.setChecked(z);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighHum.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).hHum.setValue(Byte.valueOf((byte) i2));
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighHum.setProgress(i2);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowHum.setProgress(i);
                }
            }
        });
        C26587 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        };
        ((FragmentOtherV4Binding) this.binding).sbHighHum.setFactory(r0);
        ((FragmentOtherV4Binding) this.binding).sbLowHum.setFactory(r0);
        ((FragmentOtherV4Binding) this.binding).layoutHum.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setHigh(i);
            }

            public void onLast(int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setLowSwitch(z);
            }
        });
    }

    private void initVpdListener() {
        ((FragmentOtherV4Binding) this.binding).rsbVpd.setMin(0);
        ((FragmentOtherV4Binding) this.binding).rsbVpd.setDistance(99);
        ((FragmentOtherV4Binding) this.binding).sbHighVpd.setValue(0, 99);
        ((FragmentOtherV4Binding) this.binding).sbLowVpd.setValue(0, 99);
        ((FragmentOtherV4Binding) this.binding).rsbVpd.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        });
        ((FragmentOtherV4Binding) this.binding).rsbVpd.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowVpd.setChecked(z);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighVpd.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i2));
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighVpd.setProgress(i2);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowVpd.setProgress(i);
                }
            }
        });
        C264111 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        };
        ((FragmentOtherV4Binding) this.binding).sbHighVpd.setFactory(r0);
        ((FragmentOtherV4Binding) this.binding).sbLowVpd.setFactory(r0);
        ((FragmentOtherV4Binding) this.binding).layoutVpd.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setHigh(i);
            }

            public void onLast(int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setLowSwitch(z);
            }
        });
    }

    private void initView(boolean z, boolean z2, byte b) {
        initInfo(b, z2);
        ((FragmentOtherV4Binding) this.binding).rgBeep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (OtherFragmentV4.this.version >= 4) {
                    if (i == C2420R.C2423id.rb_none) {
                        ((OtherModelV4) OtherFragmentV4.this.viewModel).alarmType.setValue((byte) 0);
                    } else if (i == C2420R.C2423id.rb_1) {
                        ((OtherModelV4) OtherFragmentV4.this.viewModel).alarmType.setValue((byte) 1);
                    } else if (i == C2420R.C2423id.rb_3) {
                        ((OtherModelV4) OtherFragmentV4.this.viewModel).alarmType.setValue((byte) 3);
                    } else if (i == C2420R.C2423id.rb_continuous) {
                        ((OtherModelV4) OtherFragmentV4.this.viewModel).alarmType.setValue((byte) -1);
                    }
                } else if (i == C2420R.C2423id.rb_none) {
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).alarmType.setValue((byte) 3);
                } else {
                    ((OtherModelV4) OtherFragmentV4.this.viewModel).alarmType.setValue((byte) 2);
                }
            }
        });
    }

    private void initInfo(byte b, boolean z) {
        if (!z) {
            ((OtherModelV4) this.viewModel).name.setValue(Utils.getString(C2420R.string.notify_alerts_name));
        }
    }

    private void bindEvent() {
        this.now = ((OtherModelV4) this.viewModel).tmp;
        ((OtherModelV4) this.viewModel).tmp.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragmentV4.this.now != ((OtherModelV4) OtherFragmentV4.this.viewModel).tmp) {
                        OtherFragmentV4.this.now.setValue(false);
                        OtherFragmentV4 otherFragmentV4 = OtherFragmentV4.this;
                        MutableLiveData unused = otherFragmentV4.now = ((OtherModelV4) otherFragmentV4.viewModel).tmp;
                    }
                    OtherFragmentV4 otherFragmentV42 = OtherFragmentV4.this;
                    otherFragmentV42.addLevelView(((FragmentOtherV4Binding) otherFragmentV42.binding).llTmpContainer);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elTmp.expand();
                    return;
                }
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elTmp.collapse();
            }
        });
        ((OtherModelV4) this.viewModel).hum.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragmentV4.this.now != ((OtherModelV4) OtherFragmentV4.this.viewModel).hum) {
                        OtherFragmentV4.this.now.setValue(false);
                        OtherFragmentV4 otherFragmentV4 = OtherFragmentV4.this;
                        MutableLiveData unused = otherFragmentV4.now = ((OtherModelV4) otherFragmentV4.viewModel).hum;
                    }
                    OtherFragmentV4 otherFragmentV42 = OtherFragmentV4.this;
                    otherFragmentV42.addLevelView(((FragmentOtherV4Binding) otherFragmentV42.binding).llHumContainer);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elHum.expand();
                    return;
                }
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elHum.collapse();
            }
        });
        ((OtherModelV4) this.viewModel).vpd.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragmentV4.this.now != ((OtherModelV4) OtherFragmentV4.this.viewModel).vpd) {
                        OtherFragmentV4.this.now.setValue(false);
                        OtherFragmentV4 otherFragmentV4 = OtherFragmentV4.this;
                        MutableLiveData unused = otherFragmentV4.now = ((OtherModelV4) otherFragmentV4.viewModel).vpd;
                    }
                    OtherFragmentV4 otherFragmentV42 = OtherFragmentV4.this;
                    otherFragmentV42.addLevelView(((FragmentOtherV4Binding) otherFragmentV42.binding).llVpdContainer);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elVpd.expand();
                    return;
                }
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elVpd.collapse();
            }
        });
        ((OtherModelV4) this.viewModel).modes.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragmentV4.this.now != ((OtherModelV4) OtherFragmentV4.this.viewModel).modes) {
                        OtherFragmentV4.this.now.setValue(false);
                        OtherFragmentV4 otherFragmentV4 = OtherFragmentV4.this;
                        MutableLiveData unused = otherFragmentV4.now = ((OtherModelV4) otherFragmentV4.viewModel).modes;
                    }
                    OtherFragmentV4 otherFragmentV42 = OtherFragmentV4.this;
                    otherFragmentV42.addLevelView(((FragmentOtherV4Binding) otherFragmentV42.binding).llModesContainer);
                    ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elModes.expand();
                    return;
                }
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).elModes.collapse();
            }
        });
        ((OtherModelV4) this.viewModel).alarmType.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                int i;
                if (b != null) {
                    if (OtherFragmentV4.this.version >= 4) {
                        i = b.byteValue() == 1 ? C2420R.C2423id.rb_1 : b.byteValue() == 3 ? C2420R.C2423id.rb_3 : b.byteValue() == -1 ? C2420R.C2423id.rb_continuous : C2420R.C2423id.rb_none;
                    } else {
                        i = b.byteValue() == 2 ? C2420R.C2423id.rb_continuous : C2420R.C2423id.rb_none;
                    }
                    if (((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rgBeep.getCheckedRadioButtonId() != i) {
                        ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rgBeep.check(i);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void addLevelView(LinearLayout linearLayout) {
        removeLevelView();
        linearLayout.addView(((FragmentOtherV4Binding) this.binding).llBeep);
        if (linearLayout == ((FragmentOtherV4Binding) this.binding).llModesContainer) {
            ((OtherModelV4) this.viewModel).beepText.setValue("3 beeps");
        } else {
            ((OtherModelV4) this.viewModel).beepText.setValue("CONTINUOUS");
        }
    }

    private void removeLevelView() {
        ViewGroup viewGroup = (ViewGroup) ((FragmentOtherV4Binding) this.binding).llBeep.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(((FragmentOtherV4Binding) this.binding).llBeep);
        }
    }

    public void onConfirm() {
        ((OtherModelV4) this.viewModel).onConfirm.execute();
    }

    public void onCancel() {
        ((OtherModelV4) this.viewModel).onCancel.execute();
    }

    private void registerMessage() {
        Messenger.getDefault().register((Object) this, (Object) NotificationActivity.SHOW_DELETE_DIALOG, (BindingAction) new BindingAction() {
            public void call() {
                OtherFragmentV4.this.showDelete();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_PARAM, (BindingAction) new BindingAction() {
            public void call() {
                boolean z = true;
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowTmp.setChecked(((OtherModelV4) OtherFragmentV4.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighTmp.setChecked(((OtherModelV4) OtherFragmentV4.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowTmp.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).lTmp.getValue().charValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighTmp.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).hTmp.getValue().charValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setHighSwitch(((OtherModelV4) OtherFragmentV4.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setLowSwitch(((OtherModelV4) OtherFragmentV4.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbTemp.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).hTmp.getValue().charValue(), ((OtherModelV4) OtherFragmentV4.this.viewModel).lTmp.getValue().charValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowHum.setChecked(((OtherModelV4) OtherFragmentV4.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighHum.setChecked(((OtherModelV4) OtherFragmentV4.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowHum.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).lHum.getValue().byteValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighHum.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).hHum.getValue().byteValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setHighSwitch(((OtherModelV4) OtherFragmentV4.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setLowSwitch(((OtherModelV4) OtherFragmentV4.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbHum.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).hHum.getValue().byteValue(), ((OtherModelV4) OtherFragmentV4.this.viewModel).lHum.getValue().byteValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowVpd.setChecked(((OtherModelV4) OtherFragmentV4.this.viewModel).lVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighVpd.setChecked(((OtherModelV4) OtherFragmentV4.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbLowVpd.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).lVpd.getValue().byteValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).sbHighVpd.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).hVpd.getValue().byteValue());
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setHighSwitch(((OtherModelV4) OtherFragmentV4.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                RangeSeekBar rangeSeekBar = ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd;
                if (((OtherModelV4) OtherFragmentV4.this.viewModel).lVpdSwitch.getValue() != Boolean.TRUE) {
                    z = false;
                }
                rangeSeekBar.setLowSwitch(z);
                ((FragmentOtherV4Binding) OtherFragmentV4.this.binding).rsbVpd.setProgress(((OtherModelV4) OtherFragmentV4.this.viewModel).hVpd.getValue().byteValue(), ((OtherModelV4) OtherFragmentV4.this.viewModel).lVpd.getValue().byteValue());
            }
        });
    }

    private void unregisterMessage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    public void showDelete() {
        this.dialog = TipDialog.showTipDialog(getContext(), getString(C2420R.string.tip_remove_adv_title, ((OtherModelV4) this.viewModel).getNotifyName()), getString(C2420R.string.tip_remove_adv_content, ((OtherModelV4) this.viewModel).getNotifyName()), getResources().getString(C2420R.string.tip_cancel_lowercase), getResources().getString(C2420R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ((OtherModelV4) OtherFragmentV4.this.viewModel).delete();
            }
        });
    }

    public void onSoftKeyboardOpened(int i) {
        ((FragmentOtherV4Binding) this.binding).etEditName.setCursorVisible(true);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(((FragmentOtherV4Binding) this.binding).etEditName, 0);
            ((FragmentOtherV4Binding) this.binding).etEditName.setSelection(((FragmentOtherV4Binding) this.binding).etEditName.getText().length());
        }
    }

    public void onSoftKeyboardClosed() {
        ((FragmentOtherV4Binding) this.binding).etEditName.setCursorVisible(false);
        ((FragmentOtherV4Binding) this.binding).etEditName.clearFocus();
        if (((OtherModelV4) this.viewModel).name.getValue() == null || TextUtils.isEmpty(((OtherModelV4) this.viewModel).name.getValue().trim())) {
            showFailDialog(Utils.getString(C2420R.string.tip_empty_name));
            ((OtherModelV4) this.viewModel).name.setValue(((OtherModelV4) this.viewModel).originName);
        }
    }

    public void closeKeyboard() {
        SoftKeyBroadManager.closeKeyboard(getContext(), ((FragmentOtherV4Binding) this.binding).etEditName);
    }

    public void setConnectType(String str, byte b) {
        ((OtherModelV4) this.viewModel).connectType = b;
        ((OtherModelV4) this.viewModel).devId = str;
    }
}
