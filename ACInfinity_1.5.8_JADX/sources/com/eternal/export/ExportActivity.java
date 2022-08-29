package com.eternal.export;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.net.MailTo;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.export.databinding.ActivityExportBinding;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.PathUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.guqiang.Toolbar;
import com.google.common.collect.Lists;
import com.zyyoona7.wheel.WheelView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class ExportActivity extends BaseActivity<ActivityExportBinding, ExportModel> {
    public static final String CHECK_TIME = "check time";
    public static final String EXPORT = "EXPORT";
    private static final String[] MIUI_PHONE = {"com.android.email", "com.kingsoft.mail.compose.ComposeActivity"};
    private static final String[] NARMAL_PHONE = {"com.android.email", "com.android.email.activity.MessageCompose"};
    private static final String[] SAMSUNG_PHONE = {"com.samsung.android.email.provider", "com.samsung.android.email.composer.activity.MessageCompose"};
    /* access modifiers changed from: private */
    public ValueAnimator animator;
    private Calendar calendar;
    private byte connectType;
    /* access modifiers changed from: private */
    public List<TmpHum> data;
    /* access modifiers changed from: private */
    public Disposable dataDispose;
    private String devId;
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    /* access modifiers changed from: private */
    public Disposable exportDispose;
    /* access modifiers changed from: private */
    public SimpleDateFormat format;
    /* access modifiers changed from: private */
    public boolean isDegree;
    volatile boolean isDeviceC;
    private String mac;
    /* access modifiers changed from: private */
    public String name;
    private Disposable nameRefresh;
    private byte port;
    /* access modifiers changed from: private */
    public int position;
    volatile boolean showHumAndVpd;
    private Toolbar toolbar;
    /* access modifiers changed from: private */
    public CSVUtil util;
    private byte version;

    /* access modifiers changed from: private */
    public String getUnit(boolean z) {
        return z ? "C" : "F";
    }

    public int initContentView(Bundle bundle) {
        return C2164R.layout.activity_export;
    }

    public int initVariableId() {
        return C2122BR.model;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
    }

    private void init() {
        this.calendar = Calendar.getInstance();
        this.format = new SimpleDateFormat("M/d/yyyy HH:mm:ss", Locale.ENGLISH);
        Intent intent = getIntent();
        this.mac = intent.getStringExtra(ActivityEvent.DEVICE_MAC);
        this.devId = intent.getStringExtra(ActivityEvent.DEVICE_ID);
        boolean z = false;
        this.port = intent.getByteExtra(ActivityEvent.DEVICE_PORT, (byte) 0);
        byte byteExtra = intent.getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 0);
        this.version = intent.getByteExtra(ActivityEvent.DEVICE_VERSION, (byte) 0);
        this.connectType = intent.getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, (byte) 0);
        this.isDegree = intent.getBooleanExtra(ActivityEvent.DEVICE_DEGREE, false);
        this.isDeviceC = byteExtra == 14 || byteExtra == 15 || byteExtra == 4 || byteExtra == 5;
        if (byteExtra != 6) {
            z = true;
        }
        this.showHumAndVpd = z;
        ((ExportModel) this.viewModel).init(RepositoryInjection.providerHistoryRepository(), this.mac, this.devId, this.port).compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList<String>>() {
            public void accept(ArrayList<String> arrayList) throws Exception {
                ((ActivityExportBinding) ExportActivity.this.binding).wvStartYear.setData(arrayList);
                ((ActivityExportBinding) ExportActivity.this.binding).wvEndYear.setData(arrayList);
                ((ActivityExportBinding) ExportActivity.this.binding).wvStartDay.setData(ExportActivity.this.createDay(Integer.parseInt(arrayList.get(arrayList.size() - 1)), ((ActivityExportBinding) ExportActivity.this.binding).wvStartMonth.getSelectedItemPosition()));
                ((ActivityExportBinding) ExportActivity.this.binding).wvEndDay.setData(ExportActivity.this.createDay(Integer.parseInt(arrayList.get(0)), ((ActivityExportBinding) ExportActivity.this.binding).wvEndMonth.getSelectedItemPosition()));
                ExportActivity.this.initWheel();
                ExportActivity.this.bindYearAndMonthEvent();
            }
        });
        initView();
        bindEvent();
        registerMessage();
        refreshName();
    }

    /* access modifiers changed from: private */
    public void initWheel() {
        ((ActivityExportBinding) this.binding).wvStartYear.setSelectedItemPosition(((ExportModel) this.viewModel).startYearNum.getValue().intValue() - 1970);
        ((ActivityExportBinding) this.binding).wvEndYear.setSelectedItemPosition(((ExportModel) this.viewModel).endYearNum.getValue().intValue() - 1970);
        ((ActivityExportBinding) this.binding).wvStartDay.setSelectedItemPosition(((ExportModel) this.viewModel).startDayNum - 1);
        ((ActivityExportBinding) this.binding).wvEndDay.setSelectedItemPosition(((ExportModel) this.viewModel).endDayNum - 1);
        ((ActivityExportBinding) this.binding).wvStartMonth.setSelectedItemPosition(((ExportModel) this.viewModel).startMonthNum.getValue().intValue());
        ((ActivityExportBinding) this.binding).wvEndMonth.setSelectedItemPosition(((ExportModel) this.viewModel).endMonthNum.getValue().intValue());
        ((ActivityExportBinding) this.binding).wvStartHour.setSelectedItemPosition(((ExportModel) this.viewModel).startHourNum - 1);
        ((ActivityExportBinding) this.binding).wvEndHour.setSelectedItemPosition(((ExportModel) this.viewModel).endHourNum - 1);
        ((ActivityExportBinding) this.binding).wvStartMin.setSelectedItemPosition(((ExportModel) this.viewModel).startMinuteNum);
        ((ActivityExportBinding) this.binding).wvEndMin.setSelectedItemPosition(((ExportModel) this.viewModel).endMinuteNum);
        ((ActivityExportBinding) this.binding).wvStartAmPm.setSelectedItemPosition(((ExportModel) this.viewModel).startIsAm ^ true ? 1 : 0);
        ((ActivityExportBinding) this.binding).wvEndAmPm.setSelectedItemPosition(((ExportModel) this.viewModel).endIsAm ^ true ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterMessage();
        Disposable disposable = this.nameRefresh;
        if (disposable != null) {
            disposable.dispose();
            this.nameRefresh = null;
        }
        super.onDestroy();
    }

    private void initView() {
        ArrayList newArrayList = Lists.newArrayList((E[]) new String[]{"AM", "PM"});
        ArrayList<String> createNumber = ((ExportModel) this.viewModel).createNumber(1, 12);
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(60);
        for (int i = 0; i < 60; i++) {
            newArrayListWithCapacity.add(String.format(Locale.ENGLISH, "%02d", new Object[]{Integer.valueOf(i)}));
        }
        ((ActivityExportBinding) this.binding).wvStartHour.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvStartHour.setData(createNumber);
        ((ActivityExportBinding) this.binding).wvStartHour.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvStartHour.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvStartHour.setCurved(false);
        ((ActivityExportBinding) this.binding).wvStartHour.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvStartHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvStartHour.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_15BAFF));
        ((ActivityExportBinding) this.binding).wvStartHour.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvStartHour.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvStartHour.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).startHourNum = i + 1;
            }
        });
        ((ActivityExportBinding) this.binding).wvEndHour.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvEndHour.setData(createNumber);
        ((ActivityExportBinding) this.binding).wvEndHour.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvEndHour.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvEndHour.setCurved(false);
        ((ActivityExportBinding) this.binding).wvEndHour.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvEndHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvEndHour.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_FF6A6A));
        ((ActivityExportBinding) this.binding).wvEndHour.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvEndHour.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvEndHour.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).endHourNum = i + 1;
            }
        });
        ((ActivityExportBinding) this.binding).wvStartAmPm.setCyclic(false);
        ((ActivityExportBinding) this.binding).wvStartAmPm.setData(newArrayList);
        ((ActivityExportBinding) this.binding).wvStartAmPm.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvStartAmPm.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvStartAmPm.setCurved(false);
        ((ActivityExportBinding) this.binding).wvStartAmPm.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvStartAmPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvStartAmPm.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_15BAFF));
        ((ActivityExportBinding) this.binding).wvStartAmPm.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvStartAmPm.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvStartAmPm.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).startIsAm = i == 0;
            }
        });
        ((ActivityExportBinding) this.binding).wvEndAmPm.setCyclic(false);
        ((ActivityExportBinding) this.binding).wvEndAmPm.setData(newArrayList);
        ((ActivityExportBinding) this.binding).wvEndAmPm.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvEndAmPm.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvEndAmPm.setCurved(false);
        ((ActivityExportBinding) this.binding).wvEndAmPm.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvEndAmPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvEndAmPm.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_FF6A6A));
        ((ActivityExportBinding) this.binding).wvEndAmPm.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvEndAmPm.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvEndAmPm.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).endIsAm = i == 0;
            }
        });
        ((ActivityExportBinding) this.binding).wvStartMin.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvStartMin.setData(newArrayListWithCapacity);
        ((ActivityExportBinding) this.binding).wvStartMin.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvStartMin.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvStartMin.setCurved(false);
        ((ActivityExportBinding) this.binding).wvStartMin.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvStartMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvStartMin.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_15BAFF));
        ((ActivityExportBinding) this.binding).wvStartMin.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvStartMin.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvStartMin.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).startMinuteNum = i;
            }
        });
        ((ActivityExportBinding) this.binding).wvEndMin.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvEndMin.setData(newArrayListWithCapacity);
        ((ActivityExportBinding) this.binding).wvEndMin.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvEndMin.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvEndMin.setCurved(false);
        ((ActivityExportBinding) this.binding).wvEndMin.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvEndMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvEndMin.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_FF6A6A));
        ((ActivityExportBinding) this.binding).wvEndMin.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvEndMin.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvEndMin.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).endMinuteNum = i;
            }
        });
        ((ActivityExportBinding) this.binding).wvStartMonth.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvStartMonth.setData(((ExportModel) this.viewModel).months);
        ((ActivityExportBinding) this.binding).wvStartMonth.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvStartMonth.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvStartMonth.setCurved(false);
        ((ActivityExportBinding) this.binding).wvStartMonth.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvStartMonth.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvStartMonth.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_15BAFF));
        ((ActivityExportBinding) this.binding).wvStartMonth.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvStartMonth.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvStartMonth.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).startMonthNum.setValue(Integer.valueOf(i));
            }
        });
        ((ActivityExportBinding) this.binding).wvEndMonth.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvEndMonth.setData(((ExportModel) this.viewModel).months);
        ((ActivityExportBinding) this.binding).wvEndMonth.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvEndMonth.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvEndMonth.setCurved(false);
        ((ActivityExportBinding) this.binding).wvEndMonth.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvEndMonth.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvEndMonth.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_FF6A6A));
        ((ActivityExportBinding) this.binding).wvEndMonth.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvEndMonth.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvEndMonth.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).endMonthNum.setValue(Integer.valueOf(i));
            }
        });
        ((ActivityExportBinding) this.binding).wvStartYear.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvStartYear.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvStartYear.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvStartYear.setCurved(false);
        ((ActivityExportBinding) this.binding).wvStartYear.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvStartYear.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvStartYear.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_15BAFF));
        ((ActivityExportBinding) this.binding).wvStartYear.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvStartYear.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvStartYear.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).startYearNum.setValue(Integer.valueOf(Integer.parseInt((String) obj)));
            }
        });
        ((ActivityExportBinding) this.binding).wvEndYear.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvEndYear.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvEndYear.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvEndYear.setCurved(false);
        ((ActivityExportBinding) this.binding).wvEndYear.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvEndYear.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvEndYear.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_FF6A6A));
        ((ActivityExportBinding) this.binding).wvEndYear.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvEndYear.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvEndYear.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).endYearNum.setValue(Integer.valueOf(Integer.parseInt((String) obj)));
            }
        });
        ((ActivityExportBinding) this.binding).wvStartDay.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvStartDay.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvStartDay.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvStartDay.setCurved(false);
        ((ActivityExportBinding) this.binding).wvStartDay.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvStartDay.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvStartDay.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_15BAFF));
        ((ActivityExportBinding) this.binding).wvStartDay.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvStartDay.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvStartDay.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).startDayNum = i + 1;
            }
        });
        ((ActivityExportBinding) this.binding).wvEndDay.setCyclic(true);
        ((ActivityExportBinding) this.binding).wvEndDay.setVisibleItems(3);
        ((ActivityExportBinding) this.binding).wvEndDay.setResetSelectedPosition(true);
        ((ActivityExportBinding) this.binding).wvEndDay.setCurved(false);
        ((ActivityExportBinding) this.binding).wvEndDay.setLineSpacing(getResources().getDimension(C2164R.dimen.dp_23));
        ((ActivityExportBinding) this.binding).wvEndDay.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2164R.font.avenir_medium));
        ((ActivityExportBinding) this.binding).wvEndDay.setSelectedItemTextColor(getResources().getColor(C2164R.C2165color.color_FF6A6A));
        ((ActivityExportBinding) this.binding).wvEndDay.setNormalItemTextColor(getResources().getColor(C2164R.C2165color.color_707070));
        ((ActivityExportBinding) this.binding).wvEndDay.setTextSize(getResources().getDimension(C2164R.dimen.dp_18));
        ((ActivityExportBinding) this.binding).wvEndDay.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            public void onItemSelected(WheelView wheelView, Object obj, int i) {
                ((ExportModel) ExportActivity.this.viewModel).endDayNum = i + 1;
            }
        });
        Toolbar toolbar2 = (Toolbar) findViewById(C2164R.C2167id.toolbar);
        this.toolbar = toolbar2;
        toolbar2.getBack().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ExportModel) ExportActivity.this.viewModel).onBack.execute();
            }
        });
    }

    private void registerMessage() {
        Messenger.getDefault().register((Object) this, (Object) EXPORT, (BindingAction) new BindingAction() {
            public void call() {
                ExportActivity.this.requestPermissionAndExport();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) CHECK_TIME, Boolean.class, new BindingConsumer<Boolean>() {
            public void call(Boolean bool) {
                ExportActivity.this.checkTime(bool.booleanValue());
            }
        });
    }

    private void unregisterMessage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    public void requestPermissionAndExport() {
        this.exportDispose = ((ExportModel) this.viewModel).getDeviceInfo().subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<DeviceInfo, ObservableSource<long[]>>() {
            public ObservableSource<long[]> apply(DeviceInfo deviceInfo) {
                return ExportActivity.this.export(deviceInfo.name, ExportActivity.this.isDegree, deviceInfo.leafTemperatureC, deviceInfo.leafTemperatureF);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<long[]>() {
            public void accept(long[] jArr) {
                final boolean z = jArr[2] == 1;
                final int i = (int) jArr[3];
                ExportActivity exportActivity = ExportActivity.this;
                Disposable unused = exportActivity.dataDispose = ((ExportModel) exportActivity.viewModel).export(jArr[0], jArr[1]).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TmpHum>>() {
                    public void accept(List<TmpHum> list) throws Exception {
                        if (list == null || list.isEmpty()) {
                            ExportActivity.this.finishSave();
                            return;
                        }
                        List unused = ExportActivity.this.data = list;
                        try {
                            ExportActivity.this.save(z, i);
                        } catch (IOException unused2) {
                            KLog.m65e("write file err");
                        }
                    }
                });
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                KLog.m65e(th);
            }
        });
    }

    private void bindEvent() {
        ((ExportModel) this.viewModel).startEdit.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
            }
        });
        ((ExportModel) this.viewModel).endEdit.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void bindYearAndMonthEvent() {
        ((ExportModel) this.viewModel).startYearNum.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ArrayList access$300 = ExportActivity.this.createDay(num.intValue(), ((ExportModel) ExportActivity.this.viewModel).startMonthNum.getValue().intValue());
                ((ActivityExportBinding) ExportActivity.this.binding).wvStartDay.setData(access$300);
                int i = ((ExportModel) ExportActivity.this.viewModel).startDayNum - 1;
                if (i >= access$300.size()) {
                    i = 0;
                }
                ((ActivityExportBinding) ExportActivity.this.binding).wvStartDay.setSelectedItemPosition(i);
                ((ExportModel) ExportActivity.this.viewModel).startDayNum = Integer.parseInt((String) access$300.get(i));
                ((ExportModel) ExportActivity.this.viewModel).updateStartTime();
            }
        });
        ((ExportModel) this.viewModel).endYearNum.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ArrayList access$300 = ExportActivity.this.createDay(num.intValue(), ((ExportModel) ExportActivity.this.viewModel).endMonthNum.getValue().intValue());
                ((ActivityExportBinding) ExportActivity.this.binding).wvEndDay.setData(access$300);
                int i = ((ExportModel) ExportActivity.this.viewModel).endDayNum - 1;
                if (i >= access$300.size()) {
                    i = 0;
                }
                ((ActivityExportBinding) ExportActivity.this.binding).wvEndDay.setSelectedItemPosition(i);
                ((ExportModel) ExportActivity.this.viewModel).endDayNum = Integer.parseInt((String) access$300.get(i));
                ((ExportModel) ExportActivity.this.viewModel).updateEndTime();
            }
        });
        ((ExportModel) this.viewModel).startMonthNum.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ExportActivity exportActivity = ExportActivity.this;
                ArrayList access$300 = exportActivity.createDay(((ExportModel) exportActivity.viewModel).startYearNum.getValue().intValue(), num.intValue());
                ((ActivityExportBinding) ExportActivity.this.binding).wvStartDay.setData(access$300);
                int i = ((ExportModel) ExportActivity.this.viewModel).startDayNum - 1;
                if (i >= access$300.size()) {
                    i = 0;
                }
                ((ActivityExportBinding) ExportActivity.this.binding).wvStartDay.setSelectedItemPosition(i);
                ((ExportModel) ExportActivity.this.viewModel).startDayNum = Integer.parseInt((String) access$300.get(i));
                ((ExportModel) ExportActivity.this.viewModel).updateStartTime();
            }
        });
        ((ExportModel) this.viewModel).endMonthNum.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                ExportActivity exportActivity = ExportActivity.this;
                ArrayList access$300 = exportActivity.createDay(((ExportModel) exportActivity.viewModel).endYearNum.getValue().intValue(), num.intValue());
                ((ActivityExportBinding) ExportActivity.this.binding).wvEndDay.setData(access$300);
                int i = ((ExportModel) ExportActivity.this.viewModel).endDayNum - 1;
                if (i >= access$300.size()) {
                    i = 0;
                }
                ((ActivityExportBinding) ExportActivity.this.binding).wvEndDay.setSelectedItemPosition(i);
                ((ExportModel) ExportActivity.this.viewModel).endDayNum = Integer.parseInt((String) access$300.get(i));
                ((ExportModel) ExportActivity.this.viewModel).updateEndTime();
            }
        });
    }

    private void refreshName() {
        Disposable disposable = this.nameRefresh;
        if (disposable == null || disposable.isDisposed()) {
            this.nameRefresh = RepositoryInjection.providerDeviceRepository().getDeviceName(this.mac, (byte) 0).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) {
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    KLog.m65e(th);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public Observable<long[]> export(String str, boolean z, byte b, byte b2) {
        final MaterialDialog.Builder builder;
        final View view;
        String str2 = str;
        this.name = str2;
        final long startTime = getStartTime();
        final long endTime = getEndTime();
        long currentTimeMillis = (long) ((int) (System.currentTimeMillis() / 60000));
        if (startTime / 60 > currentTimeMillis) {
            view = LayoutInflater.from(this).inflate(C2164R.layout.dialog_tip_ok, (ViewGroup) null, false);
            ((TextView) view.findViewById(C2164R.C2167id.tv_content)).setText("Start date or time cannot exceed current date or time");
            builder = new MaterialDialog.Builder(this).customView(view, false);
        } else if (endTime / 60 > currentTimeMillis) {
            view = LayoutInflater.from(this).inflate(C2164R.layout.dialog_tip_ok, (ViewGroup) null, false);
            ((TextView) view.findViewById(C2164R.C2167id.tv_content)).setText("End date or time cannot exceed current date or time");
            builder = new MaterialDialog.Builder(this).customView(view, false);
        } else if (startTime > endTime) {
            view = LayoutInflater.from(this).inflate(C2164R.layout.dialog_tip_ok, (ViewGroup) null, false);
            ((TextView) view.findViewById(C2164R.C2167id.tv_content)).setText("Start date or time cannot exceed end date or time");
            builder = new MaterialDialog.Builder(this).customView(view, false);
        } else {
            final View inflate = LayoutInflater.from(this).inflate(C2164R.layout.dialog_progress, (ViewGroup) null, false);
            ((TextView) inflate.findViewById(C2164R.C2167id.tv_title)).setText("Exporting data for Device ID " + str2 + "\nThis process can sometimes take\nseveral minutes.");
            final MaterialDialog.Builder customView = new MaterialDialog.Builder(this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false);
            final boolean z2 = z;
            final byte b3 = b;
            final byte b4 = b2;
            return Completable.create(new CompletableOnSubscribe() {
                public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                    MaterialDialog unused = ExportActivity.this.dialog = customView.build();
                    ExportActivity.this.dialog.show();
                    ExportActivity.this.startAnimator(inflate.findViewById(C2164R.C2167id.iv_progress));
                    ExportActivity.this.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (ExportActivity.this.dataDispose != null && ExportActivity.this.dataDispose.isDisposed()) {
                                ExportActivity.this.dataDispose.dispose();
                            }
                            List unused = ExportActivity.this.data = null;
                            ExportActivity.this.exportDispose.dispose();
                            ExportActivity.this.animator.end();
                        }
                    });
                    completableEmitter.onComplete();
                }
            }).compose(bindToLifecycle()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m983io()).andThen(Observable.create(new ObservableOnSubscribe<long[]>() {
                public void subscribe(ObservableEmitter<long[]> observableEmitter) throws Exception {
                    ExportActivity exportActivity = ExportActivity.this;
                    CSVUtil unused = exportActivity.util = CSVUtil.build(PathUtils.getInternalAppFilesPath() + "/AC INFINITY Data.csv");
                    ExportActivity.this.util.writeElements("Device ID", ExportActivity.this.name);
                    CSVUtil access$5500 = ExportActivity.this.util;
                    access$5500.writeElements("Export Time", "\"\"\"" + ExportActivity.this.format.format(new Date()) + "\"\"\"");
                    CSVUtil access$55002 = ExportActivity.this.util;
                    ExportActivity exportActivity2 = ExportActivity.this;
                    access$55002.writeElements("Sample Frequency", exportActivity2.formatFrequency(((ExportModel) exportActivity2.viewModel).frequency));
                    CSVUtil access$55003 = ExportActivity.this.util;
                    access$55003.writeElements("Start Time", "\"\"\"" + ExportActivity.this.format.format(new Date(startTime * 1000)) + "\"\"\"");
                    CSVUtil access$55004 = ExportActivity.this.util;
                    access$55004.writeElements("End Time", "\"\"\"" + ExportActivity.this.format.format(new Date(endTime * 1000)) + "\"\"\"");
                    ExportActivity.this.util.writeElements("Temperature Units", ExportActivity.this.getUnit(z2));
                    if (ExportActivity.this.showHumAndVpd) {
                        CSVUtil access$55005 = ExportActivity.this.util;
                        String[] strArr = new String[2];
                        strArr[0] = "Leaf Temperature Offset";
                        StringBuilder sb = new StringBuilder();
                        sb.append(z2 ? b3 : b4);
                        sb.append("");
                        strArr[1] = sb.toString();
                        access$55005.writeElements(strArr);
                    }
                    ExportActivity.this.util.writeLine("");
                    if (ExportActivity.this.showHumAndVpd) {
                        CSVUtil access$55006 = ExportActivity.this.util;
                        access$55006.writeElements("Time", "Temperature " + ExportActivity.this.getUnit(z2), "Relative Humidity", "VPD");
                    } else {
                        CSVUtil access$55007 = ExportActivity.this.util;
                        access$55007.writeElements("Time", "Temperature " + ExportActivity.this.getUnit(z2));
                    }
                    int unused2 = ExportActivity.this.position = 0;
                    long[] jArr = new long[4];
                    jArr[0] = startTime;
                    jArr[1] = endTime;
                    jArr[2] = z2 ? 1 : 0;
                    jArr[3] = (long) b3;
                    observableEmitter.onNext(jArr);
                    observableEmitter.onComplete();
                }
            }));
        }
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) {
                builder.backgroundColor(ViewCompat.MEASURED_SIZE_MASK);
                MaterialDialog unused = ExportActivity.this.dialog = builder.build();
                view.findViewById(C2164R.C2167id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ExportActivity.this.dialog.dismiss();
                    }
                });
                ExportActivity.this.dialog.show();
            }
        }).compose(bindToLifecycle()).subscribeOn(AndroidSchedulers.mainThread()).andThen(Observable.empty());
    }

    private long getStartTime() {
        this.calendar.set(1, ((ExportModel) this.viewModel).startYearNum.getValue().intValue());
        this.calendar.set(2, ((ExportModel) this.viewModel).startMonthNum.getValue().intValue());
        this.calendar.set(5, ((ExportModel) this.viewModel).startDayNum);
        if (((ExportModel) this.viewModel).startIsAm) {
            if (((ExportModel) this.viewModel).startHourNum == 12) {
                this.calendar.set(11, 0);
            } else {
                this.calendar.set(11, ((ExportModel) this.viewModel).startHourNum);
            }
        } else if (((ExportModel) this.viewModel).startHourNum == 12) {
            this.calendar.set(11, 12);
        } else {
            this.calendar.set(11, ((ExportModel) this.viewModel).startHourNum + 12);
        }
        this.calendar.set(12, ((ExportModel) this.viewModel).startMinuteNum);
        return this.calendar.getTimeInMillis() / 1000;
    }

    /* access modifiers changed from: private */
    public void startAnimator(final View view) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 11});
        this.animator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setRotation((float) (((Integer) valueAnimator.getAnimatedValue()).intValue() * 30));
            }
        });
        this.animator.setDuration(1000);
        this.animator.setRepeatCount(-1);
        this.animator.start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkTime(boolean r15) {
        /*
            r14 = this;
            long r0 = r14.getStartTime()
            long r2 = r14.getEndTime()
            r14.initWheel()
            long r4 = java.lang.System.currentTimeMillis()
            r6 = 60000(0xea60, double:2.9644E-319)
            long r4 = r4 / r6
            int r5 = (int) r4
            r6 = 60
            long r8 = r0 / r6
            long r4 = (long) r5
            r10 = 0
            r11 = 0
            int r12 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r12 <= 0) goto L_0x0043
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r14)
            int r1 = com.eternal.export.C2164R.layout.dialog_tip_ok
            android.view.View r10 = r0.inflate(r1, r10, r11)
            int r0 = com.eternal.export.C2164R.C2167id.tv_content
            android.view.View r0 = r10.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "Start date or time cannot exceed current date or time"
            r0.setText(r1)
            com.afollestad.materialdialogs.MaterialDialog$Builder r0 = new com.afollestad.materialdialogs.MaterialDialog$Builder
            r0.<init>(r14)
            com.afollestad.materialdialogs.MaterialDialog$Builder r0 = r0.customView((android.view.View) r10, (boolean) r11)
        L_0x003f:
            r13 = r10
            r10 = r0
            r0 = r13
            goto L_0x0090
        L_0x0043:
            long r6 = r2 / r6
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x006a
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r14)
            int r1 = com.eternal.export.C2164R.layout.dialog_tip_ok
            android.view.View r10 = r0.inflate(r1, r10, r11)
            int r0 = com.eternal.export.C2164R.C2167id.tv_content
            android.view.View r0 = r10.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "End date or time cannot exceed current date or time"
            r0.setText(r1)
            com.afollestad.materialdialogs.MaterialDialog$Builder r0 = new com.afollestad.materialdialogs.MaterialDialog$Builder
            r0.<init>(r14)
            com.afollestad.materialdialogs.MaterialDialog$Builder r0 = r0.customView((android.view.View) r10, (boolean) r11)
            goto L_0x003f
        L_0x006a:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x008f
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r14)
            int r1 = com.eternal.export.C2164R.layout.dialog_tip_ok
            android.view.View r10 = r0.inflate(r1, r10, r11)
            int r0 = com.eternal.export.C2164R.C2167id.tv_content
            android.view.View r0 = r10.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "Start date or time cannot exceed end date or time"
            r0.setText(r1)
            com.afollestad.materialdialogs.MaterialDialog$Builder r0 = new com.afollestad.materialdialogs.MaterialDialog$Builder
            r0.<init>(r14)
            com.afollestad.materialdialogs.MaterialDialog$Builder r0 = r0.customView((android.view.View) r10, (boolean) r11)
            goto L_0x003f
        L_0x008f:
            r0 = r10
        L_0x0090:
            if (r10 == 0) goto L_0x00c5
            if (r15 == 0) goto L_0x009c
            com.eternal.framework.component.BaseViewModel r15 = r14.viewModel
            com.eternal.export.ExportModel r15 = (com.eternal.export.ExportModel) r15
            r15.loadStart()
            goto L_0x00a3
        L_0x009c:
            com.eternal.framework.component.BaseViewModel r15 = r14.viewModel
            com.eternal.export.ExportModel r15 = (com.eternal.export.ExportModel) r15
            r15.loadEnd()
        L_0x00a3:
            r14.initWheel()
            r15 = 16777215(0xffffff, float:2.3509886E-38)
            r10.backgroundColor(r15)
            com.afollestad.materialdialogs.MaterialDialog r15 = r10.build()
            r14.dialog = r15
            int r15 = com.eternal.export.C2164R.C2167id.tv_confirm
            android.view.View r15 = r0.findViewById(r15)
            com.eternal.export.ExportActivity$32 r0 = new com.eternal.export.ExportActivity$32
            r0.<init>()
            r15.setOnClickListener(r0)
            com.afollestad.materialdialogs.MaterialDialog r15 = r14.dialog
            r15.show()
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.export.ExportActivity.checkTime(boolean):void");
    }

    private long getEndTime() {
        this.calendar.set(1, ((ExportModel) this.viewModel).endYearNum.getValue().intValue());
        this.calendar.set(2, ((ExportModel) this.viewModel).endMonthNum.getValue().intValue());
        this.calendar.set(5, ((ExportModel) this.viewModel).endDayNum);
        if (((ExportModel) this.viewModel).endIsAm) {
            if (((ExportModel) this.viewModel).endHourNum == 12) {
                this.calendar.set(11, 0);
            } else {
                this.calendar.set(11, ((ExportModel) this.viewModel).endHourNum);
            }
        } else if (((ExportModel) this.viewModel).endHourNum == 12) {
            this.calendar.set(11, 12);
        } else {
            this.calendar.set(11, ((ExportModel) this.viewModel).endHourNum + 12);
        }
        this.calendar.set(12, ((ExportModel) this.viewModel).endMinuteNum);
        return this.calendar.getTimeInMillis() / 1000;
    }

    /* access modifiers changed from: private */
    public void finishSave() {
        try {
            this.util.sync();
            share();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.dialog.dismiss();
            throw th;
        }
        this.dialog.dismiss();
    }

    private void share() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
        intent.putExtra("android.intent.extra.EMAIL", "");
        intent.putExtra("android.intent.extra.SUBJECT", "AC Infinity data");
        intent.setType("text/plain");
        Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", new File(PathUtils.getInternalAppFilesPath() + "/AC INFINITY Data.csv"));
        intent.setFlags(1);
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        startActivity(Intent.createChooser(intent, "Share"));
    }

    private TmpHum getItem(int i) {
        return this.data.get(i);
    }

    /* access modifiers changed from: private */
    public void save(boolean z, int i) throws IOException {
        TmpHum item;
        int i2;
        String str;
        while (this.position < this.data.size() && (item = getItem(this.position)) != null) {
            this.util.writeLine("");
            String formatValue = formatValue(item.tmp, z, this.isDeviceC);
            if (this.showHumAndVpd) {
                String formatValue2 = formatValue(item.hum);
                if (this.version >= 3) {
                    str = formatValue(item.vpd);
                    int i3 = i;
                } else {
                    str = formatValue(Math.round(ProtocolTransformer.getVPD(item.tmp, item.hum, i, !this.isDeviceC, false) * 100.0f));
                }
                this.util.writeElements(this.format.format(new Date(item.time * 1000)), formatValue, formatValue2, str);
                i2 = 1;
            } else {
                int i4 = i;
                i2 = 1;
                this.util.writeElements(this.format.format(new Date(item.time * 1000)), formatValue);
            }
            this.position += i2;
        }
        if (this.position == this.data.size()) {
            finishSave();
        }
    }

    public static String formatValue(int i, boolean z, boolean z2) {
        float f = ((float) i) / 100.0f;
        if (z2) {
            if (z) {
                f = ((f - 32.0f) * 5.0f) / 9.0f;
            }
        } else if (!z) {
            f = ((f * 9.0f) / 5.0f) + 32.0f;
        }
        return String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(f)});
    }

    private String formatValue(int i) {
        return String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(((float) i) / 100.0f)});
    }

    /* access modifiers changed from: private */
    public String formatFrequency(int i) {
        if (i < 60) {
            return i + " MIN";
        }
        return (i / 60) + " HRS";
    }

    /* access modifiers changed from: private */
    public ArrayList<String> createDay(int i, int i2) {
        this.calendar.set(1, i);
        this.calendar.set(2, i2);
        return ((ExportModel) this.viewModel).createNumber(1, this.calendar.getActualMaximum(5));
    }

    public void onBackPressed() {
        ((ExportModel) this.viewModel).onBack.execute();
    }

    public static String getDeviceBrand() {
        Log.e("--获取手机厂商--:", Build.BRAND);
        return Build.BRAND;
    }
}
