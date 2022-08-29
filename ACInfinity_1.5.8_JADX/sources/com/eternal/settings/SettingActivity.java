package com.eternal.settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.transition.TransitionManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.Utils;
import com.eternal.settings.databinding.ActivitySettingsBinding;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.AddSubView;
import com.eternal.widget.guqiang.SingleProgressBar;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class SettingActivity extends BaseActivity<ActivitySettingsBinding, SettingModel> implements SingleProgressBar.OnChangeListener, AddSubView.OnChangeListener {
    static final String SET_BRIGHTNESS = "set brightness";
    static final String SET_CALI_HUM = "set cali hum";
    static final String SET_CALI_TMP = "set cali tmp";
    static final String SET_LEAF_TMP = "set leaf tmp";
    static final String SET_LEVEL_OFF = "set level off";
    static final String SET_LEVEL_ON = "set level on";
    static final String SET_PORT_TYPE = "set port type";
    static final String SET_TRANS_HUM = "set trans hum";
    static final String SET_TRANS_TMP = "set trans tmp";
    static final String SET_TRANS_VPD = "set trans vpd";
    static final String UPDATE = "update";
    static final String UPDATE_PORT_TYPE = "update port type";
    static final String UPDATE_TAB_NAME = "update tab name";
    private MaterialDialog dialog;
    String firmwareVersion;
    String hardwareVersion;
    private final View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            TabLayout.Tab tabAt = ((ActivitySettingsBinding) SettingActivity.this.binding).tbPort.getTabAt(((ActivitySettingsBinding) SettingActivity.this.binding).tbPort.getSelectedTabPosition());
            if (tabAt == null || tabAt.getTag() == null || ((Byte) view.getTag()).byteValue() != ((Byte) tabAt.getTag()).byteValue()) {
                SettingActivity.this.updateTabSelect(((Byte) view.getTag()).byteValue());
            }
        }
    };
    String mac;
    RadioGroup.OnCheckedChangeListener portTypeListener1 = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i != -1) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) null);
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.clearCheck();
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.setOnCheckedChangeListener(SettingActivity.this.portTypeListener2);
            }
        }
    };
    RadioGroup.OnCheckedChangeListener portTypeListener2 = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i != -1) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) null);
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.clearCheck();
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.setOnCheckedChangeListener(SettingActivity.this.portTypeListener1);
            }
        }
    };
    private Disposable progressEvent;
    /* access modifiers changed from: private */
    public SoftKeyBroadManager softKeyBroadManager;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivitySettingsBinding) SettingActivity.this.binding).btnConfirm.requestLayout();
            ((ActivitySettingsBinding) SettingActivity.this.binding).editName.setCursorVisible(true);
            InputMethodManager inputMethodManager = (InputMethodManager) SettingActivity.this.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(((ActivitySettingsBinding) SettingActivity.this.binding).editName, 0);
                ((ActivitySettingsBinding) SettingActivity.this.binding).editName.setSelection(((ActivitySettingsBinding) SettingActivity.this.binding).editName.getText().length());
            }
        }

        public void onSoftKeyboardClosed() {
            ((ActivitySettingsBinding) SettingActivity.this.binding).btnConfirm.requestLayout();
            ((ActivitySettingsBinding) SettingActivity.this.binding).editName.setCursorVisible(false);
            ((ActivitySettingsBinding) SettingActivity.this.binding).editName.clearFocus();
            if (((SettingModel) SettingActivity.this.viewModel).selectTabDevice.getValue() == null || ((SettingModel) SettingActivity.this.viewModel).selectTabDevice.getValue().intValue() == 0) {
                if (((SettingModel) SettingActivity.this.viewModel).deviceName.getValue() == null || TextUtils.isEmpty(((SettingModel) SettingActivity.this.viewModel).deviceName.getValue().trim())) {
                    ((SettingModel) SettingActivity.this.viewModel).showEmptyDialog();
                }
            } else if (((SettingModel) SettingActivity.this.viewModel).port == 0) {
            } else {
                if (((SettingModel) SettingActivity.this.viewModel).portName.getValue() == null || TextUtils.isEmpty(((SettingModel) SettingActivity.this.viewModel).portName.getValue().trim())) {
                    ((SettingModel) SettingActivity.this.viewModel).showEmptyDialog();
                }
            }
        }
    };
    String softwareVersion;
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
            tab.getTag();
        }
    };
    /* access modifiers changed from: private */
    public byte type;
    byte typeOff;
    byte typeOn;
    /* access modifiers changed from: private */
    public byte version;

    public void onDown() {
    }

    public void onEnd() {
    }

    public void showModifyDialog() {
    }

    public int initContentView(Bundle bundle) {
        return C2668R.layout.activity_settings;
    }

    public int initVariableId() {
        return C2667BR.model;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bindingEvent();
        registerEvent();
        register();
        Intent intent = getIntent();
        this.type = intent.getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 1);
        byte byteExtra = intent.getByteExtra(ActivityEvent.DEVICE_PORT, (byte) 0);
        this.version = intent.getByteExtra(ActivityEvent.DEVICE_VERSION, (byte) 0);
        byte byteExtra2 = intent.getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, (byte) 0);
        ((SettingModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository(), this.mac, intent.getStringExtra(ActivityEvent.DEVICE_ID), byteExtra, this.type, this.version, this.softwareVersion, this.hardwareVersion, this.firmwareVersion, byteExtra2);
        initView(this.type, byteExtra);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (((SettingModel) this.viewModel).dialog != null && ((SettingModel) this.viewModel).dialog.isShowing()) {
            ((SettingModel) this.viewModel).dialog.dismiss();
        }
        unregisterEvent();
        unregister();
        super.onDestroy();
    }

    private void registerEvent() {
        Messenger messenger = Messenger.getDefault();
        messenger.register((Object) this, (Object) UPDATE, DeviceSetting.class, new BindingConsumer<DeviceSetting>() {
            public void call(DeviceSetting deviceSetting) {
                SettingActivity.this.initPortTab();
                SettingActivity.this.updateTabSelect(deviceSetting.choosePort);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_TAB_NAME, (BindingAction) new BindingAction() {
            public void call() {
                if (((SettingModel) SettingActivity.this.viewModel).setting != null && ((SettingModel) SettingActivity.this.viewModel).setting.portList != null) {
                    int tabCount = ((ActivitySettingsBinding) SettingActivity.this.binding).tbPort.getTabCount();
                    for (int i = 0; i < tabCount; i++) {
                        TabLayout.Tab tabAt = ((ActivitySettingsBinding) SettingActivity.this.binding).tbPort.getTabAt(i);
                        if (tabAt != null && tabAt.getTag() != null && tabAt.getCustomView() != null) {
                            Iterator<PortSetting> it = ((SettingModel) SettingActivity.this.viewModel).setting.portList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PortSetting next = it.next();
                                if (((Byte) tabAt.getTag()).byteValue() == next.f138id) {
                                    TextView textView = (TextView) tabAt.getCustomView().findViewById(C2779R.C2782id.tv_title);
                                    if (next.f138id != 0) {
                                        textView.setText(ProtocolTransformer.getDisplayPortName(next.f138id, SettingActivity.this.type, next.name));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        messenger.register((Object) this, (Object) SET_BRIGHTNESS, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                int i;
                byte byteValue = b.byteValue();
                if (byteValue == -94) {
                    i = C2668R.C2671id.rb_a2;
                } else if (byteValue == 1) {
                    i = C2668R.C2671id.rb_low;
                } else if (byteValue == 2) {
                    i = C2668R.C2671id.rb_medium;
                } else if (byteValue != 3) {
                    i = C2668R.C2671id.rb_a3;
                } else {
                    i = C2668R.C2671id.rb_height;
                }
                ((ActivitySettingsBinding) SettingActivity.this.binding).brightRadioGroup.check(i);
            }
        });
        messenger.register((Object) this, (Object) SET_PORT_TYPE, PortSetting.class, new BindingConsumer<PortSetting>() {
            public void call(PortSetting portSetting) {
                int i;
                ((SettingModel) SettingActivity.this.viewModel).resistance = portSetting.fanType;
                ((SettingModel) SettingActivity.this.viewModel).portType = portSetting.portType;
                int portType = ProtocolTransformer.getPortType(portSetting.portType, portSetting.fanType);
                ((SettingModel) SettingActivity.this.viewModel).isPlugPortType.set(((SettingModel) SettingActivity.this.viewModel).isOutletDevice(portSetting.fanType));
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.clearCheck();
                ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.clearCheck();
                if (portType == 2) {
                    i = C2668R.C2671id.rb_fan;
                } else if (portType != 3) {
                    i = portType != 5 ? -1 : C2668R.C2671id.rb_ac;
                } else {
                    i = C2668R.C2671id.rb_light;
                }
                String str = null;
                if (i != -1) {
                    ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.check(i);
                    RadioButton radioButton = (RadioButton) ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.findViewById(i);
                    if (radioButton != null) {
                        str = radioButton.getText().toString();
                    }
                } else {
                    if (portType == 6) {
                        i = C2668R.C2671id.rb_hum;
                    } else if (portType == 7) {
                        i = C2668R.C2671id.rb_dehum;
                    } else if (portType == 8) {
                        i = C2668R.C2671id.rb_heater;
                    }
                    if (i != -1) {
                        ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.check(i);
                        RadioButton radioButton2 = (RadioButton) ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.findViewById(i);
                        if (radioButton2 != null) {
                            str = radioButton2.getText().toString();
                        }
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    str = ((SettingModel) SettingActivity.this.viewModel).isPlugPortType.get() ? "Outlet" : "Port";
                }
                ((SettingModel) SettingActivity.this.viewModel).portTypeText.setValue(str);
                ((SettingModel) SettingActivity.this.viewModel).portTypeRes.setValue(Integer.valueOf(ProtocolTransformer.getPlugIcon(portSetting.portType, portSetting.fanType, true, SettingActivity.this.type)));
            }
        });
        messenger.register((Object) this, (Object) UPDATE_PORT_TYPE, (BindingAction) new BindingAction() {
            public void call() {
                int checkedRadioButtonId = ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.getCheckedRadioButtonId();
                int checkedRadioButtonId2 = ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.getCheckedRadioButtonId();
                String str = null;
                if (checkedRadioButtonId == -1 && checkedRadioButtonId2 == -1) {
                    ((SettingModel) SettingActivity.this.viewModel).portType = 0;
                } else if (checkedRadioButtonId != -1) {
                    if (checkedRadioButtonId == C2668R.C2671id.rb_fan) {
                        ((SettingModel) SettingActivity.this.viewModel).portType = 2;
                    } else if (checkedRadioButtonId == C2668R.C2671id.rb_light) {
                        ((SettingModel) SettingActivity.this.viewModel).portType = 3;
                    } else if (checkedRadioButtonId == C2668R.C2671id.rb_ac) {
                        ((SettingModel) SettingActivity.this.viewModel).portType = 5;
                    }
                    RadioButton radioButton = (RadioButton) ((ActivitySettingsBinding) SettingActivity.this.binding).rgType1.findViewById(checkedRadioButtonId);
                    if (radioButton != null) {
                        str = radioButton.getText().toString();
                    }
                } else {
                    if (checkedRadioButtonId2 == C2668R.C2671id.rb_hum) {
                        ((SettingModel) SettingActivity.this.viewModel).portType = 6;
                    } else if (checkedRadioButtonId2 == C2668R.C2671id.rb_dehum) {
                        ((SettingModel) SettingActivity.this.viewModel).portType = 7;
                    } else if (checkedRadioButtonId2 == C2668R.C2671id.rb_heater) {
                        ((SettingModel) SettingActivity.this.viewModel).portType = 8;
                    }
                    RadioButton radioButton2 = (RadioButton) ((ActivitySettingsBinding) SettingActivity.this.binding).rgType2.findViewById(checkedRadioButtonId2);
                    if (radioButton2 != null) {
                        str = radioButton2.getText().toString();
                    }
                }
                ((SettingModel) SettingActivity.this.viewModel).portTypeRes.setValue(Integer.valueOf(ProtocolTransformer.getPlugIcon(((SettingModel) SettingActivity.this.viewModel).portType, ((SettingModel) SettingActivity.this.viewModel).resistance, true, SettingActivity.this.type)));
                if (TextUtils.isEmpty(str)) {
                    str = ((SettingModel) SettingActivity.this.viewModel).isPlugPortType.get() ? "Outlet" : "Port";
                }
                ((SettingModel) SettingActivity.this.viewModel).portTypeText.setValue(str);
            }
        });
        messenger.register((Object) this, (Object) SET_TRANS_TMP, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbTransitionTemp.setShowText(b.byteValue());
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbBufferTemp.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_TRANS_HUM, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbTransitionHum.setShowText(b.byteValue());
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbBufferHum.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_TRANS_VPD, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbTransitionVpd.setShowText(b.byteValue());
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbBufferVpd.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_LEVEL_OFF, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbLevelMin.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_LEVEL_ON, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbLevelMax.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_CALI_TMP, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbCalibrationTemperature.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_LEAF_TMP, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbVpdLeafTemperature.setShowText(b.byteValue());
            }
        });
        messenger.register((Object) this, (Object) SET_CALI_HUM, Byte.class, new BindingConsumer<Byte>() {
            public void call(Byte b) {
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbCalibrationHum.setShowText(b.byteValue());
            }
        });
    }

    private void unregisterEvent() {
        Messenger.getDefault().unregister(this);
    }

    private void register() {
        this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
            public void accept(ProgressEvent progressEvent) throws Exception {
                if (progressEvent.Statue == 0) {
                    ((ActivitySettingsBinding) SettingActivity.this.binding).toolBar.startProgress();
                } else if (progressEvent.Statue == 1) {
                    ((ActivitySettingsBinding) SettingActivity.this.binding).toolBar.endProgress(progressEvent.callback);
                }
            }
        });
    }

    private void unregister() {
        Disposable disposable = this.progressEvent;
        if (disposable != null) {
            disposable.dispose();
            this.progressEvent = null;
        }
    }

    private void bindingEvent() {
        ((ActivitySettingsBinding) this.binding).brightRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == C2668R.C2671id.rb_low) {
                    ((SettingModel) SettingActivity.this.viewModel).brightness = 1;
                } else if (i == C2668R.C2671id.rb_medium) {
                    ((SettingModel) SettingActivity.this.viewModel).brightness = 2;
                } else if (i == C2668R.C2671id.rb_height) {
                    ((SettingModel) SettingActivity.this.viewModel).brightness = 3;
                } else if (i == C2668R.C2671id.rb_a2) {
                    ((SettingModel) SettingActivity.this.viewModel).brightness = -94;
                } else {
                    ((SettingModel) SettingActivity.this.viewModel).brightness = -93;
                }
            }
        });
        ((ActivitySettingsBinding) this.binding).rgType1.clearCheck();
        ((ActivitySettingsBinding) this.binding).rgType2.clearCheck();
        ((ActivitySettingsBinding) this.binding).rgType1.setOnCheckedChangeListener(this.portTypeListener1);
        ((ActivitySettingsBinding) this.binding).rgType2.setOnCheckedChangeListener(this.portTypeListener2);
        ((ActivitySettingsBinding) this.binding).spbTransitionTemp.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbBufferTemp.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbTransitionHum.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbBufferHum.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbTransitionVpd.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbBufferVpd.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbCalibrationTemperature.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbCalibrationHum.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbVpdLeafTemperature.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbLevelMin.setOnChangeListener(this);
        ((ActivitySettingsBinding) this.binding).spbLevelMax.setOnChangeListener(this);
        ((SettingModel) this.viewModel).isDegree.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                boolean access$5500 = SettingActivity.this.isABDevice();
                if (bool.booleanValue()) {
                    ((ActivitySettingsBinding) SettingActivity.this.binding).spbCalibrationTemperature.setModeType((byte) 4, SettingActivity.this.version, access$5500);
                    ((ActivitySettingsBinding) SettingActivity.this.binding).spbVpdLeafTemperature.setModeType((byte) 10, SettingActivity.this.version, access$5500);
                } else {
                    ((ActivitySettingsBinding) SettingActivity.this.binding).spbCalibrationTemperature.setModeType((byte) 3, SettingActivity.this.version, access$5500);
                    ((ActivitySettingsBinding) SettingActivity.this.binding).spbVpdLeafTemperature.setModeType((byte) 9, SettingActivity.this.version, access$5500);
                }
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(((ActivitySettingsBinding) SettingActivity.this.binding).ctTmp);
                if (bool.booleanValue()) {
                    ConstraintSet constraintSet2 = constraintSet;
                    constraintSet2.connect(C2668R.C2671id.v_slide, 6, C2668R.C2671id.tv_unit_c, 6, 0);
                    constraintSet2.connect(C2668R.C2671id.v_slide, 7, C2668R.C2671id.tv_unit_c, 7, 0);
                } else {
                    ConstraintSet constraintSet3 = constraintSet;
                    constraintSet3.connect(C2668R.C2671id.v_slide, 6, C2668R.C2671id.tv_unit_f, 6, 0);
                    constraintSet3.connect(C2668R.C2671id.v_slide, 7, C2668R.C2671id.tv_unit_f, 7, 0);
                }
                constraintSet.applyTo(((ActivitySettingsBinding) SettingActivity.this.binding).ctTmp);
                TransitionManager.beginDelayedTransition(((ActivitySettingsBinding) SettingActivity.this.binding).ctTmp);
            }
        });
        ((SettingModel) this.viewModel).realDegree.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                boolean access$5500 = SettingActivity.this.isABDevice();
                if (bool.booleanValue()) {
                    ((ActivitySettingsBinding) SettingActivity.this.binding).spbBufferTemp.setModeType((byte) 1, SettingActivity.this.version, access$5500);
                    ((ActivitySettingsBinding) SettingActivity.this.binding).spbTransitionTemp.setModeType((byte) 1, SettingActivity.this.version, access$5500);
                    return;
                }
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbBufferTemp.setModeType((byte) 0, SettingActivity.this.version, access$5500);
                ((ActivitySettingsBinding) SettingActivity.this.binding).spbTransitionTemp.setModeType((byte) 0, SettingActivity.this.version, access$5500);
            }
        });
        ((SettingModel) this.viewModel).selectTabDevice.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                if (num != null) {
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(((ActivitySettingsBinding) SettingActivity.this.binding).ctTab);
                    int intValue = num.intValue();
                    Objects.requireNonNull((SettingModel) SettingActivity.this.viewModel);
                    if (intValue == 0) {
                        ConstraintSet constraintSet2 = constraintSet;
                        constraintSet2.connect(C2668R.C2671id.v_tab_slide, 6, C2668R.C2671id.tv_tab_controller, 6, 0);
                        constraintSet2.connect(C2668R.C2671id.v_tab_slide, 7, C2668R.C2671id.tv_tab_controller, 7, 0);
                    } else {
                        int intValue2 = num.intValue();
                        Objects.requireNonNull((SettingModel) SettingActivity.this.viewModel);
                        if (intValue2 == 1) {
                            ConstraintSet constraintSet3 = constraintSet;
                            constraintSet3.connect(C2668R.C2671id.v_tab_slide, 6, C2668R.C2671id.tv_tab_port, 6, 0);
                            constraintSet3.connect(C2668R.C2671id.v_tab_slide, 7, C2668R.C2671id.tv_tab_port, 7, 0);
                        } else {
                            ConstraintSet constraintSet4 = constraintSet;
                            constraintSet4.connect(C2668R.C2671id.v_tab_slide, 6, C2668R.C2671id.tv_tab_system, 6, 0);
                            constraintSet4.connect(C2668R.C2671id.v_tab_slide, 7, C2668R.C2671id.tv_tab_system, 7, 0);
                        }
                    }
                    constraintSet.applyTo(((ActivitySettingsBinding) SettingActivity.this.binding).ctTab);
                    TransitionManager.beginDelayedTransition(((ActivitySettingsBinding) SettingActivity.this.binding).ctTab);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isABDevice() {
        byte b = this.type;
        return b == 1 || b == 2;
    }

    private void initView(byte b, byte b2) {
        initPortTabListener();
        SoftKeyBroadManager softKeyBroadManager2 = new SoftKeyBroadManager(((ActivitySettingsBinding) this.binding).root);
        this.softKeyBroadManager = softKeyBroadManager2;
        softKeyBroadManager2.addSoftKeyboardStateListener(this.softKeyboardStateListener);
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(f2) <= Math.abs(f) || f2 == 0.0f || !SettingActivity.this.softKeyBroadManager.isSoftKeyboardOpened()) {
                    return false;
                }
                SettingActivity settingActivity = SettingActivity.this;
                SoftKeyBroadManager.closeKeyboard(settingActivity, ((ActivitySettingsBinding) settingActivity.binding).editName);
                return true;
            }
        });
        ((ActivitySettingsBinding) this.binding).scroll.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        boolean z = false;
        if (ProtocolTransformer.isDeviceC(b)) {
            ((ActivitySettingsBinding) this.binding).tvTabPort.setVisibility(8);
            ((ActivitySettingsBinding) this.binding).vClickPort.setVisibility(8);
            ((ActivitySettingsBinding) this.binding).rlDeviceBrightness.setVisibility(8);
            ((SettingModel) this.viewModel).levelVisible.setValue(false);
        } else if (b == 2 || b == 9 || b == 12) {
            ((SettingModel) this.viewModel).bufferVisible.setValue(true);
            ((SettingModel) this.viewModel).levelVisible.setValue(false);
        } else if (b == 7 || b == 8 || b == 11) {
            ((SettingModel) this.viewModel).transitionVisible.setValue(true);
            ((ActivitySettingsBinding) this.binding).tvTransitionDes.setText(Utils.getString(C2668R.string.setting_transition_des, "levels", "level"));
            ((ActivitySettingsBinding) this.binding).tvMaxLevel.setText(Utils.getString(C2668R.string.setting_max_level, "LEVEL"));
            ((ActivitySettingsBinding) this.binding).tvMinLevel.setText(Utils.getString(C2668R.string.setting_min_level, "LEVEL"));
            ((ActivitySettingsBinding) this.binding).tvMaxLevelDes.setText(Utils.getString(C2668R.string.setting_max_level_des, "level"));
            ((ActivitySettingsBinding) this.binding).tvMinLevelDes.setText(Utils.getString(C2668R.string.setting_min_level_des, "level"));
            ((SettingModel) this.viewModel).levelVisible.setValue(true);
        } else {
            ((ActivitySettingsBinding) this.binding).tvTransitionDes.setText(Utils.getString(C2668R.string.setting_transition_des, "speeds", "speed"));
            ((SettingModel) this.viewModel).transitionVisible.setValue(true);
            ((ActivitySettingsBinding) this.binding).tvMaxLevel.setText(Utils.getString(C2668R.string.setting_max_level, "SPEED"));
            ((ActivitySettingsBinding) this.binding).tvMinLevel.setText(Utils.getString(C2668R.string.setting_min_level, "SPEED"));
            ((ActivitySettingsBinding) this.binding).tvMaxLevelDes.setText(Utils.getString(C2668R.string.setting_max_level_des, "speed"));
            ((ActivitySettingsBinding) this.binding).tvMinLevelDes.setText(Utils.getString(C2668R.string.setting_min_level_des, "speed"));
            ((SettingModel) this.viewModel).levelVisible.setValue(true);
        }
        ((SettingModel) this.viewModel).humVisible.setValue(Boolean.valueOf(b != 6));
        MutableLiveData<Boolean> mutableLiveData = ((SettingModel) this.viewModel).vpdLeafVisible;
        if (b != 6) {
            z = true;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
        ((ActivitySettingsBinding) this.binding).spbCalibrationHum.setModeType((byte) 5, this.version, isABDevice());
        ((ActivitySettingsBinding) this.binding).spbTransitionHum.setModeType((byte) 2, this.version, isABDevice());
        ((ActivitySettingsBinding) this.binding).spbBufferHum.setModeType((byte) 2, this.version, isABDevice());
        ((ActivitySettingsBinding) this.binding).spbLevelMax.disEditable();
        ((ActivitySettingsBinding) this.binding).spbLevelMin.disEditable();
    }

    /* access modifiers changed from: package-private */
    public void initPortTabListener() {
        if (!ProtocolTransformer.isDeviceMultiPort(this.type)) {
            ((ActivitySettingsBinding) this.binding).tbPort.setVisibility(8);
            return;
        }
        ((ActivitySettingsBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabSelectedListener);
        ((ActivitySettingsBinding) this.binding).tbPort.addOnTabSelectedListener(this.tabUserSelectedListener);
    }

    /* access modifiers changed from: package-private */
    public void initPortTab() {
        if (((SettingModel) this.viewModel).setting != null && ((SettingModel) this.viewModel).setting.portList != null && ((SettingModel) this.viewModel).setting.portList.size() != 0) {
            ArrayList<PortSetting> arrayList = new ArrayList<>();
            for (PortSetting next : ((SettingModel) this.viewModel).setting.portList) {
                if (next.isPlug && next.f138id != 0) {
                    arrayList.add(next);
                }
            }
            ((ActivitySettingsBinding) this.binding).tbPort.removeAllTabs();
            for (PortSetting portSetting : arrayList) {
                TabLayout.Tab newTab = ((ActivitySettingsBinding) this.binding).tbPort.newTab();
                View inflate = LayoutInflater.from(this).inflate(C2779R.layout.item_port, newTab.parent, false);
                ((TextView) inflate.findViewById(C2779R.C2782id.tv_title)).setText(ProtocolTransformer.getDisplayPortName(portSetting.f138id, this.type, portSetting.name));
                ((ImageView) inflate.findViewById(C2668R.C2671id.iv_image)).setImageResource(ProtocolTransformer.getPlugIcon(portSetting.portType, portSetting.fanType, RepositoryInjection.providerDeviceRepository().isConnect(this.mac), this.type));
                newTab.setTag(Byte.valueOf(portSetting.f138id));
                inflate.setOnClickListener(this.mTabOnClickListener);
                inflate.setTag(Byte.valueOf(portSetting.f138id));
                newTab.setCustomView(inflate);
                if (((SettingModel) this.viewModel).port == portSetting.f138id) {
                    newTab.select();
                    ((SettingModel) this.viewModel).choosePort(portSetting.f138id);
                }
                ((ActivitySettingsBinding) this.binding).tbPort.addTab(newTab);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTabSelect(byte b) {
        if (b == 0) {
            ((SettingModel) this.viewModel).choosePort(b);
            return;
        }
        int tabCount = ((ActivitySettingsBinding) this.binding).tbPort.getTabCount();
        int i = 0;
        while (i < tabCount) {
            TabLayout.Tab tabAt = ((ActivitySettingsBinding) this.binding).tbPort.getTabAt(i);
            if (tabAt == null || tabAt.getTag() == null || tabAt.getCustomView() == null || ((Byte) tabAt.getTag()).byteValue() != b) {
                i++;
            } else {
                tabAt.select();
                ((SettingModel) this.viewModel).choosePort(b);
                return;
            }
        }
    }

    public void onBackPressed() {
        ((SettingModel) this.viewModel).onBack.execute();
    }

    public void onChange(View view, int i) {
        int id = view.getId();
        if (id == C2668R.C2671id.spb_transition_temp || id == C2668R.C2671id.spb_buffer_temp) {
            if (((SettingModel) this.viewModel).realDegree.getValue() == Boolean.TRUE) {
                ((SettingModel) this.viewModel).transTmpF = (byte) ((ActivitySettingsBinding) this.binding).spbTransitionTemp.convertValue((byte) 0, i);
                ((SettingModel) this.viewModel).transTmpC = (byte) i;
                return;
            }
            ((SettingModel) this.viewModel).transTmpF = (byte) i;
            ((SettingModel) this.viewModel).transTmpC = (byte) ((ActivitySettingsBinding) this.binding).spbTransitionTemp.convertValue((byte) 1, i);
        } else if (id == C2668R.C2671id.spb_transition_hum || id == C2668R.C2671id.spb_buffer_hum) {
            ((SettingModel) this.viewModel).transHum = (byte) i;
        } else if (id == C2668R.C2671id.spb_transition_vpd || id == C2668R.C2671id.spb_buffer_vpd) {
            ((SettingModel) this.viewModel).transVpd = (byte) i;
        } else if (id == C2668R.C2671id.spb_level_min) {
            ((SettingModel) this.viewModel).typeOff = (byte) i;
        } else if (id == C2668R.C2671id.spb_level_max) {
            ((SettingModel) this.viewModel).typeOn = (byte) i;
        } else if (id == C2668R.C2671id.spb_calibration_temperature) {
            ((SettingModel) this.viewModel).caliTmp = (byte) i;
        } else if (id == C2668R.C2671id.spb_calibration_hum) {
            ((SettingModel) this.viewModel).caliHum = (byte) i;
        } else if (id != C2668R.C2671id.spb_vpd_leaf_temperature) {
        } else {
            if (((SettingModel) this.viewModel).isDegree.getValue() == null || !((SettingModel) this.viewModel).isDegree.getValue().booleanValue()) {
                ((SettingModel) this.viewModel).leafTmpF = (byte) i;
                ((SettingModel) this.viewModel).leafTmpC = (byte) ((ActivitySettingsBinding) this.binding).spbVpdLeafTemperature.convertValue((byte) 10, i);
                return;
            }
            ((SettingModel) this.viewModel).leafTmpF = (byte) ((ActivitySettingsBinding) this.binding).spbVpdLeafTemperature.convertValue((byte) 9, i);
            ((SettingModel) this.viewModel).leafTmpC = (byte) i;
        }
    }

    public void onExceeded(View view, boolean z) {
        String str;
        int id = view.getId();
        String str2 = "Buffer";
        String str3 = "maximum";
        if (id == C2668R.C2671id.spb_transition_temp || id == C2668R.C2671id.spb_buffer_temp) {
            StringBuilder sb = new StringBuilder();
            if (!ProtocolTransformer.isOutletDevice(this.type)) {
                str2 = "Transition";
            }
            sb.append(str2);
            sb.append(" temperature ");
            if (!z) {
                str3 = "minimum";
            }
            sb.append(str3);
            sb.append(" exceeded.");
            str = sb.toString();
        } else if (id == C2668R.C2671id.spb_transition_hum || id == C2668R.C2671id.spb_buffer_hum) {
            StringBuilder sb2 = new StringBuilder();
            if (!ProtocolTransformer.isOutletDevice(this.type)) {
                str2 = "Transition";
            }
            sb2.append(str2);
            sb2.append(" humidity ");
            if (!z) {
                str3 = "minimum";
            }
            sb2.append(str3);
            sb2.append(" exceeded.");
            str = sb2.toString();
        } else if (id == C2668R.C2671id.spb_transition_vpd || id == C2668R.C2671id.spb_buffer_vpd) {
            StringBuilder sb3 = new StringBuilder();
            if (!ProtocolTransformer.isOutletDevice(this.type)) {
                str2 = "Transition";
            }
            sb3.append(str2);
            sb3.append(" vpd ");
            if (!z) {
                str3 = "minimum";
            }
            sb3.append(str3);
            sb3.append(" exceeded.");
            str = sb3.toString();
        } else if (id == C2668R.C2671id.spb_calibration_temperature) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Calibration temperature ");
            if (!z) {
                str3 = "minimum";
            }
            sb4.append(str3);
            sb4.append(" exceeded.");
            str = sb4.toString();
        } else if (id == C2668R.C2671id.spb_calibration_hum) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Calibration humidity ");
            if (!z) {
                str3 = "minimum";
            }
            sb5.append(str3);
            sb5.append(" exceeded.");
            str = sb5.toString();
        } else if (id == C2668R.C2671id.spb_level_min || id == C2668R.C2671id.spb_level_max) {
            byte b = this.type;
            String str4 = (b == 7 || b == 8 || b == 11) ? "Level" : "Speed";
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[2];
            objArr[0] = str4;
            if (!z) {
                str3 = "minimum";
            }
            objArr[1] = str3;
            str = String.format(locale, "%s %s exceeded.", objArr);
        } else if (id == C2668R.C2671id.spb_vpd_leaf_temperature) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Vpd leaf temperature offset ");
            if (!z) {
                str3 = "minimum";
            }
            sb6.append(str3);
            sb6.append(" exceeded.");
            str = sb6.toString();
        } else {
            str = "";
        }
        CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), str);
    }
}
