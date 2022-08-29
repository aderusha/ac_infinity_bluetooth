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
import com.eternal.notification.databinding.FragmentOtherBinding;
import com.eternal.notification.model.OtherModel;
import com.eternal.widget.guqiang.DoubleCloseAddLayoutListener;
import com.eternal.widget.guqiang.IEffectBar;
import com.eternal.widget.guqiang.RangeSeekBar;
import java.util.Locale;

/* renamed from: com.eternal.notification.ui.OtherFragment */
public class OtherFragment extends BaseFragment<FragmentOtherBinding, OtherModel> implements ISoftKeyBoardAction, IConnectAction {
    public static String UPDATE_PARAM = "update param";
    MaterialDialog dialog;
    private boolean isUserSlide;
    /* access modifiers changed from: private */
    public MutableLiveData<Boolean> now;
    private byte type;

    public void connected() {
    }

    public void disconnected() {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C2420R.layout.fragment_other;
    }

    public int initVariableId() {
        return C2419BR.otherModel;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        boolean z = arguments.getBoolean(ActivityEvent.DEVICE_DEGREE);
        initListener(z);
        String string = arguments.getString(ActivityEvent.NOTIFICATION);
        byte b = arguments.getByte(ActivityEvent.NOTIFICATION_TYPE);
        this.type = b;
        initView(z, string != null, b);
        registerMessage();
        bindEvent();
        ((OtherModel) this.viewModel).init(RepositoryInjection.providerNotificationRepository(), arguments.getString(ActivityEvent.DEVICE_MAC), arguments.getString(ActivityEvent.DEVICE_ID), arguments.getByte(ActivityEvent.DEVICE_PORT), arguments.getByte(ActivityEvent.DEVICE_TYPE), (Notification) GsonUtils.fromJson(string, Notification.class), z, this.type, arguments.getByte(ActivityEvent.DEVICE_VERSION), arguments.getByte(ActivityEvent.DEVICE_CONNECT_TYPE));
    }

    public void onDestroyView() {
        unregisterMessage();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (((OtherModel) this.viewModel).saveDis != null && !((OtherModel) this.viewModel).saveDis.isDisposed()) {
            ((OtherModel) this.viewModel).saveDis.dispose();
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
            ((FragmentOtherBinding) this.binding).tvTempMin.setText("0℃");
            ((FragmentOtherBinding) this.binding).tvTempMax.setText("90℃");
            ((OtherModel) this.viewModel).tmpUnit.setValue(Utils.getString(C2420R.string.tip_degree_num));
            ((FragmentOtherBinding) this.binding).rsbTemp.setMin(0);
            ((FragmentOtherBinding) this.binding).rsbTemp.setDistance(90);
            ((FragmentOtherBinding) this.binding).sbHighTmp.setValue(0, 90);
            ((FragmentOtherBinding) this.binding).sbLowTmp.setValue(0, 90);
        } else {
            ((FragmentOtherBinding) this.binding).tvTempMin.setText("32℉");
            ((FragmentOtherBinding) this.binding).tvTempMax.setText("194℉");
            ((FragmentOtherBinding) this.binding).rsbTemp.setMin(32);
            ((FragmentOtherBinding) this.binding).rsbTemp.setDistance(162);
            ((FragmentOtherBinding) this.binding).sbHighTmp.setValue(32, 162);
            ((FragmentOtherBinding) this.binding).sbLowTmp.setValue(32, 162);
        }
        ((FragmentOtherBinding) this.binding).rsbTemp.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                if (((OtherModel) OtherFragment.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        });
        ((FragmentOtherBinding) this.binding).rsbTemp.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((OtherModel) OtherFragment.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((OtherModel) OtherFragment.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowTmp.setChecked(z);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighTmp.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    char c = (char) i2;
                    ((OtherModel) OtherFragment.this.viewModel).hTmp.setValue(Character.valueOf(c));
                    char c2 = (char) i;
                    ((OtherModel) OtherFragment.this.viewModel).lTmp.setValue(Character.valueOf(c2));
                    ((FragmentOtherBinding) OtherFragment.this.binding).sbHighTmp.setProgress(c);
                    ((FragmentOtherBinding) OtherFragment.this.binding).sbLowTmp.setProgress(c2);
                }
            }
        });
        C26323 r3 = new IEffectBar.Factory() {
            public String getText(int i) {
                if (((OtherModel) OtherFragment.this.viewModel).isDegree) {
                    return Utils.getString(C2420R.string.tip_degree_num, Integer.valueOf(i));
                }
                return Utils.getString(C2420R.string.tip_fah_num, Integer.valueOf(i));
            }
        };
        ((FragmentOtherBinding) this.binding).sbHighTmp.setFactory(r3);
        ((FragmentOtherBinding) this.binding).sbLowTmp.setFactory(r3);
        ((FragmentOtherBinding) this.binding).layoutTmp.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((OtherModel) OtherFragment.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setHigh(i);
            }

            public void onLast(int i) {
                ((OtherModel) OtherFragment.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((OtherModel) OtherFragment.this.viewModel).hTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((OtherModel) OtherFragment.this.viewModel).lTmp.setValue(Character.valueOf((char) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((OtherModel) OtherFragment.this.viewModel).hTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((OtherModel) OtherFragment.this.viewModel).lTmpSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setLowSwitch(z);
            }
        });
    }

    private void initHumListener() {
        ((FragmentOtherBinding) this.binding).rsbHum.setMin(0);
        ((FragmentOtherBinding) this.binding).rsbHum.setDistance(100);
        ((FragmentOtherBinding) this.binding).sbHighHum.setValue(0, 100);
        ((FragmentOtherBinding) this.binding).sbLowHum.setValue(0, 100);
        ((FragmentOtherBinding) this.binding).rsbHum.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        });
        ((FragmentOtherBinding) this.binding).rsbHum.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((OtherModel) OtherFragment.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((OtherModel) OtherFragment.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowHum.setChecked(z);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighHum.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((OtherModel) OtherFragment.this.viewModel).hHum.setValue(Byte.valueOf((byte) i2));
                    ((OtherModel) OtherFragment.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                    ((FragmentOtherBinding) OtherFragment.this.binding).sbHighHum.setProgress(i2);
                    ((FragmentOtherBinding) OtherFragment.this.binding).sbLowHum.setProgress(i);
                }
            }
        });
        C26367 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return Utils.getString(C2420R.string.tip_percent_num, Integer.valueOf(i));
            }
        };
        ((FragmentOtherBinding) this.binding).sbHighHum.setFactory(r0);
        ((FragmentOtherBinding) this.binding).sbLowHum.setFactory(r0);
        ((FragmentOtherBinding) this.binding).layoutHum.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((OtherModel) OtherFragment.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setHigh(i);
            }

            public void onLast(int i) {
                ((OtherModel) OtherFragment.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((OtherModel) OtherFragment.this.viewModel).hHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((OtherModel) OtherFragment.this.viewModel).lHum.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((OtherModel) OtherFragment.this.viewModel).hHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((OtherModel) OtherFragment.this.viewModel).lHumSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setLowSwitch(z);
            }
        });
    }

    private void initVpdListener() {
        ((FragmentOtherBinding) this.binding).rsbVpd.setMin(0);
        ((FragmentOtherBinding) this.binding).rsbVpd.setDistance(99);
        ((FragmentOtherBinding) this.binding).sbHighVpd.setValue(0, 99);
        ((FragmentOtherBinding) this.binding).sbLowVpd.setValue(0, 99);
        ((FragmentOtherBinding) this.binding).rsbVpd.setFactory(new RangeSeekBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        });
        ((FragmentOtherBinding) this.binding).rsbVpd.setListener(new RangeSeekBar.Listener() {
            public void onSwitchChange(boolean z, boolean z2) {
                ((OtherModel) OtherFragment.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((OtherModel) OtherFragment.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z2));
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowVpd.setChecked(z);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighVpd.setChecked(z2);
            }

            public void onChange(boolean z, boolean z2, int i, int i2) {
                if (z) {
                    ((OtherModel) OtherFragment.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i2));
                    ((OtherModel) OtherFragment.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                    ((FragmentOtherBinding) OtherFragment.this.binding).sbHighVpd.setProgress(i2);
                    ((FragmentOtherBinding) OtherFragment.this.binding).sbLowVpd.setProgress(i);
                }
            }
        });
        C261911 r0 = new IEffectBar.Factory() {
            public String getText(int i) {
                return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf(((float) i) / 10.0f)});
            }
        };
        ((FragmentOtherBinding) this.binding).sbHighVpd.setFactory(r0);
        ((FragmentOtherBinding) this.binding).sbLowVpd.setFactory(r0);
        ((FragmentOtherBinding) this.binding).layoutVpd.setListener(new DoubleCloseAddLayoutListener() {
            public void onFirstDown(boolean z) {
            }

            public void onLastDown(boolean z) {
            }

            public void onFirst(int i) {
                ((OtherModel) OtherFragment.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setHigh(i);
            }

            public void onLast(int i) {
                ((OtherModel) OtherFragment.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setLow(i);
            }

            public void onEndFirst(boolean z, int i) {
                ((OtherModel) OtherFragment.this.viewModel).hVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setHigh(i);
            }

            public void onEndLast(boolean z, int i) {
                ((OtherModel) OtherFragment.this.viewModel).lVpd.setValue(Byte.valueOf((byte) i));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setLow(i);
            }

            public void onFirstChecked(boolean z) {
                ((OtherModel) OtherFragment.this.viewModel).hVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setHighSwitch(z);
            }

            public void onLastChecked(boolean z) {
                ((OtherModel) OtherFragment.this.viewModel).lVpdSwitch.setValue(Boolean.valueOf(z));
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setLowSwitch(z);
            }
        });
    }

    private void initView(boolean z, boolean z2, byte b) {
        initInfo(b, z2);
        ((FragmentOtherBinding) this.binding).rgBeep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == C2420R.C2423id.rb_none) {
                    ((OtherModel) OtherFragment.this.viewModel).alarmType.setValue((byte) 3);
                } else {
                    ((OtherModel) OtherFragment.this.viewModel).alarmType.setValue((byte) 2);
                }
            }
        });
    }

    private void initInfo(byte b, boolean z) {
        if (!z) {
            ((OtherModel) this.viewModel).name.setValue(Utils.getString(C2420R.string.notify_alerts_name));
        }
    }

    private void bindEvent() {
        this.now = ((OtherModel) this.viewModel).tmp;
        ((OtherModel) this.viewModel).tmp.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragment.this.now != ((OtherModel) OtherFragment.this.viewModel).tmp) {
                        OtherFragment.this.now.setValue(false);
                        OtherFragment otherFragment = OtherFragment.this;
                        MutableLiveData unused = otherFragment.now = ((OtherModel) otherFragment.viewModel).tmp;
                    }
                    OtherFragment otherFragment2 = OtherFragment.this;
                    otherFragment2.addLevelView(((FragmentOtherBinding) otherFragment2.binding).llTmpContainer);
                    ((FragmentOtherBinding) OtherFragment.this.binding).elTmp.expand();
                    return;
                }
                ((FragmentOtherBinding) OtherFragment.this.binding).elTmp.collapse();
            }
        });
        ((OtherModel) this.viewModel).hum.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragment.this.now != ((OtherModel) OtherFragment.this.viewModel).hum) {
                        OtherFragment.this.now.setValue(false);
                        OtherFragment otherFragment = OtherFragment.this;
                        MutableLiveData unused = otherFragment.now = ((OtherModel) otherFragment.viewModel).hum;
                    }
                    OtherFragment otherFragment2 = OtherFragment.this;
                    otherFragment2.addLevelView(((FragmentOtherBinding) otherFragment2.binding).llHumContainer);
                    ((FragmentOtherBinding) OtherFragment.this.binding).elHum.expand();
                    return;
                }
                ((FragmentOtherBinding) OtherFragment.this.binding).elHum.collapse();
            }
        });
        ((OtherModel) this.viewModel).vpd.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragment.this.now != ((OtherModel) OtherFragment.this.viewModel).vpd) {
                        OtherFragment.this.now.setValue(false);
                        OtherFragment otherFragment = OtherFragment.this;
                        MutableLiveData unused = otherFragment.now = ((OtherModel) otherFragment.viewModel).vpd;
                    }
                    OtherFragment otherFragment2 = OtherFragment.this;
                    otherFragment2.addLevelView(((FragmentOtherBinding) otherFragment2.binding).llVpdContainer);
                    ((FragmentOtherBinding) OtherFragment.this.binding).elVpd.expand();
                    return;
                }
                ((FragmentOtherBinding) OtherFragment.this.binding).elVpd.collapse();
            }
        });
        ((OtherModel) this.viewModel).modes.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool == Boolean.TRUE) {
                    if (OtherFragment.this.now != ((OtherModel) OtherFragment.this.viewModel).modes) {
                        OtherFragment.this.now.setValue(false);
                        OtherFragment otherFragment = OtherFragment.this;
                        MutableLiveData unused = otherFragment.now = ((OtherModel) otherFragment.viewModel).modes;
                    }
                    OtherFragment otherFragment2 = OtherFragment.this;
                    otherFragment2.addLevelView(((FragmentOtherBinding) otherFragment2.binding).llModesContainer);
                    ((FragmentOtherBinding) OtherFragment.this.binding).elModes.expand();
                    return;
                }
                ((FragmentOtherBinding) OtherFragment.this.binding).elModes.collapse();
            }
        });
        ((OtherModel) this.viewModel).alarmType.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                int i = b.byteValue() == 2 ? C2420R.C2423id.rb_beep : C2420R.C2423id.rb_none;
                if (((FragmentOtherBinding) OtherFragment.this.binding).rgBeep.getCheckedRadioButtonId() != i) {
                    ((FragmentOtherBinding) OtherFragment.this.binding).rgBeep.check(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void addLevelView(LinearLayout linearLayout) {
        removeLevelView();
        linearLayout.addView(((FragmentOtherBinding) this.binding).llBeep);
        if (linearLayout == ((FragmentOtherBinding) this.binding).llModesContainer) {
            ((OtherModel) this.viewModel).beepText.setValue("3 beeps");
        } else {
            ((OtherModel) this.viewModel).beepText.setValue("CONTINUOUS");
        }
    }

    private void removeLevelView() {
        ViewGroup viewGroup = (ViewGroup) ((FragmentOtherBinding) this.binding).llBeep.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(((FragmentOtherBinding) this.binding).llBeep);
        }
    }

    public void onConfirm() {
        ((OtherModel) this.viewModel).onConfirm.execute();
    }

    public void onCancel() {
        ((OtherModel) this.viewModel).onCancel.execute();
    }

    private void registerMessage() {
        Messenger.getDefault().register((Object) this, (Object) NotificationActivity.SHOW_DELETE_DIALOG, (BindingAction) new BindingAction() {
            public void call() {
                OtherFragment.this.showDelete();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_PARAM, (BindingAction) new BindingAction() {
            public void call() {
                boolean z = true;
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowTmp.setChecked(((OtherModel) OtherFragment.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighTmp.setChecked(((OtherModel) OtherFragment.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowTmp.setProgress(((OtherModel) OtherFragment.this.viewModel).lTmp.getValue().charValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighTmp.setProgress(((OtherModel) OtherFragment.this.viewModel).hTmp.getValue().charValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setHighSwitch(((OtherModel) OtherFragment.this.viewModel).hTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setLowSwitch(((OtherModel) OtherFragment.this.viewModel).lTmpSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbTemp.setProgress(((OtherModel) OtherFragment.this.viewModel).hTmp.getValue().charValue(), ((OtherModel) OtherFragment.this.viewModel).lTmp.getValue().charValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowHum.setChecked(((OtherModel) OtherFragment.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighHum.setChecked(((OtherModel) OtherFragment.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowHum.setProgress(((OtherModel) OtherFragment.this.viewModel).lHum.getValue().byteValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighHum.setProgress(((OtherModel) OtherFragment.this.viewModel).hHum.getValue().byteValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setHighSwitch(((OtherModel) OtherFragment.this.viewModel).hHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setLowSwitch(((OtherModel) OtherFragment.this.viewModel).lHumSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbHum.setProgress(((OtherModel) OtherFragment.this.viewModel).hHum.getValue().byteValue(), ((OtherModel) OtherFragment.this.viewModel).lHum.getValue().byteValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowVpd.setChecked(((OtherModel) OtherFragment.this.viewModel).lVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighVpd.setChecked(((OtherModel) OtherFragment.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                ((FragmentOtherBinding) OtherFragment.this.binding).sbLowVpd.setProgress(((OtherModel) OtherFragment.this.viewModel).lVpd.getValue().byteValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).sbHighVpd.setProgress(((OtherModel) OtherFragment.this.viewModel).hVpd.getValue().byteValue());
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setHighSwitch(((OtherModel) OtherFragment.this.viewModel).hVpdSwitch.getValue() == Boolean.TRUE);
                RangeSeekBar rangeSeekBar = ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd;
                if (((OtherModel) OtherFragment.this.viewModel).lVpdSwitch.getValue() != Boolean.TRUE) {
                    z = false;
                }
                rangeSeekBar.setLowSwitch(z);
                ((FragmentOtherBinding) OtherFragment.this.binding).rsbVpd.setProgress(((OtherModel) OtherFragment.this.viewModel).hVpd.getValue().byteValue(), ((OtherModel) OtherFragment.this.viewModel).lVpd.getValue().byteValue());
            }
        });
    }

    private void unregisterMessage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    public void showDelete() {
        this.dialog = TipDialog.showTipDialog(getContext(), getString(C2420R.string.tip_remove_adv_title, ((OtherModel) this.viewModel).getNotifyName()), getString(C2420R.string.tip_remove_adv_content, ((OtherModel) this.viewModel).getNotifyName()), getResources().getString(C2420R.string.tip_cancel_lowercase), getResources().getString(C2420R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ((OtherModel) OtherFragment.this.viewModel).delete();
            }
        });
    }

    public void onSoftKeyboardOpened(int i) {
        ((FragmentOtherBinding) this.binding).etEditName.setCursorVisible(true);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(((FragmentOtherBinding) this.binding).etEditName, 0);
            ((FragmentOtherBinding) this.binding).etEditName.setSelection(((FragmentOtherBinding) this.binding).etEditName.getText().length());
        }
    }

    public void onSoftKeyboardClosed() {
        ((FragmentOtherBinding) this.binding).etEditName.setCursorVisible(false);
        ((FragmentOtherBinding) this.binding).etEditName.clearFocus();
        if (((OtherModel) this.viewModel).name.getValue() == null || TextUtils.isEmpty(((OtherModel) this.viewModel).name.getValue().trim())) {
            showFailDialog(Utils.getString(C2420R.string.tip_empty_name));
            ((OtherModel) this.viewModel).name.setValue(((OtherModel) this.viewModel).originName);
        }
    }

    public void closeKeyboard() {
        SoftKeyBroadManager.closeKeyboard(getContext(), ((FragmentOtherBinding) this.binding).etEditName);
    }

    public void setConnectType(String str, byte b) {
        ((OtherModel) this.viewModel).devId = str;
        ((OtherModel) this.viewModel).connectType = b;
    }
}
