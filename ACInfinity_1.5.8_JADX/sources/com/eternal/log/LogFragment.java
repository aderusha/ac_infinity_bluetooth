package com.eternal.log;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.base.BaseFragment;
import com.eternal.base.IDegreeAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.TipDialog;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.LogConfig;
import com.eternal.base.concat.LogExtra;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.database.entity.Log;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.TimeUtil;
import com.eternal.data.DataFragment;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.KLog;
import com.eternal.log.adapter.LogAdapter;
import com.eternal.log.databinding.FragmentHistoryBinding;
import com.eternal.log.model.LogModel;
import com.eternal.res.C2663R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class LogFragment extends BaseFragment<FragmentHistoryBinding, LogModel> implements IToolBarAction, IDegreeAction {
    public static final String REFRESH_FILTER_VIEW = "refresh filter view";
    public static final String REFRESH_LOG = "refresh log";
    public static final String SHOW_DELETE_DIALOG = "show delete log dialog";
    /* access modifiers changed from: private */
    public LogAdapter adapter;
    private Disposable initDis;
    private Disposable initDis2;
    /* access modifiers changed from: private */
    public String mac;
    /* access modifiers changed from: private */
    public LogObservable observable;
    private byte port;
    /* access modifiers changed from: private */
    public int position;
    BottomSheetDialog sheetDialog;
    /* access modifiers changed from: private */
    public byte type;

    public void onNameUpdate(List<DeviceName> list) {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C2303R.layout.fragment_history;
    }

    public int initVariableId() {
        return C2267BR.model;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        bindEvent();
        registerMassage();
    }

    public void onDestroyView() {
        Disposable disposable = this.initDis;
        if (disposable != null && !disposable.isDisposed()) {
            this.initDis.dispose();
        }
        Disposable disposable2 = this.initDis2;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.initDis2.dispose();
        }
        unregisterMassage();
        super.onDestroyView();
    }

    private void initView() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(f2) <= Math.abs(f) || f2 >= 0.0f || LogFragment.this.adapter == null || LogFragment.this.adapter.isClear()) {
                    return false;
                }
                ((LogModel) LogFragment.this.viewModel).resetTime(new Consumer<Long>() {
                    public void accept(Long l) throws Exception {
                        LogFragment.this.adapter.clear(l);
                        RxBus.getDefault().post(new BluetoothEvent((byte) 1, LogFragment.this.mac, 0));
                    }
                });
                return true;
            }
        });
        ((FragmentHistoryBinding) this.binding).rcContent.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0 && ((LogModel) LogFragment.this.viewModel).show.getValue() == Boolean.TRUE) {
                    ((LogModel) LogFragment.this.viewModel).show.setValue(false);
                }
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        ((FragmentHistoryBinding) this.binding).rcContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                if (recyclerView.getChildCount() > 0) {
                    try {
                        int unused = LogFragment.this.position = ((RecyclerView.LayoutParams) recyclerView.getChildAt(0).getLayoutParams()).getViewAdapterPosition();
                    } catch (Exception e) {
                        KLog.m65e(e);
                    }
                }
            }
        });
        ((FragmentHistoryBinding) this.binding).rcContent.setLayoutManager(new LinearLayoutManager(getContext()));
        ((FragmentHistoryBinding) this.binding).rcContent.setFocusable(false);
        ((FragmentHistoryBinding) this.binding).rcContent.setFocusableInTouchMode(false);
        this.observable = new LogObservable();
        Bundle arguments = getArguments();
        this.mac = arguments.getString(ActivityEvent.DEVICE_MAC);
        this.port = arguments.getByte(ActivityEvent.DEVICE_PORT, (byte) 0).byteValue();
        this.type = arguments.getByte(ActivityEvent.DEVICE_TYPE, (byte) 0).byteValue();
        ((LogModel) this.viewModel).init(RepositoryInjection.providerLogRepository(), this.mac, arguments.getString(ActivityEvent.DEVICE_ID), this.port, this.type);
        this.initDis2 = ((LogModel) this.viewModel).initAdapter().compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LogExtra>() {
            public void accept(LogExtra logExtra) throws Exception {
                if (logExtra != null) {
                    ((LogModel) LogFragment.this.viewModel).isDegree = logExtra.isDegree;
                    logExtra.hideAutoTime = ((LogModel) LogFragment.this.viewModel).deviceType == 6;
                    LogAdapter unused = LogFragment.this.adapter = new LogAdapter(logExtra);
                    ((FragmentHistoryBinding) LogFragment.this.binding).rcContent.setAdapter(LogFragment.this.adapter);
                    LiveData<PagedList<Log>> liveData = ((LogModel) LogFragment.this.viewModel).logs;
                    LogFragment logFragment = LogFragment.this;
                    liveData.observe(logFragment, logFragment.observable);
                }
            }
        });
    }

    public void onHiddenChanged(boolean z) {
        if (!z) {
            this.initDis = ((LogModel) this.viewModel).getExtra().compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LogExtra>() {
                public void accept(LogExtra logExtra) throws Exception {
                    logExtra.isDegree = ((LogModel) LogFragment.this.viewModel).isDegree;
                    LogFragment.this.adapter.setExtra(logExtra);
                    ((FragmentHistoryBinding) LogFragment.this.binding).rcContent.scrollToPosition(0);
                }
            });
        }
    }

    public void onResume() {
        if (this.adapter != null) {
            ((LogModel) this.viewModel).getExtra().compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).subscribe(new Consumer<LogExtra>() {
                public void accept(LogExtra logExtra) throws Exception {
                    logExtra.isDegree = ((LogModel) LogFragment.this.viewModel).isDegree;
                    LogFragment.this.adapter.setExtra(logExtra);
                }
            });
        }
        super.onResume();
    }

    private void bindEvent() {
        ((LogModel) this.viewModel).show.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    ((FragmentHistoryBinding) LogFragment.this.binding).umeFilter.expand();
                } else {
                    ((FragmentHistoryBinding) LogFragment.this.binding).umeFilter.collapse();
                }
            }
        });
    }

    private void registerMassage() {
        Messenger.getDefault().register((Object) this, (Object) "refresh log", (BindingAction) new BindingAction() {
            public void call() {
                KLog.m65e("refresh log");
                ((LogModel) LogFragment.this.viewModel).logs.removeObservers(LogFragment.this);
                ((LogModel) LogFragment.this.viewModel).refresh();
                LiveData<PagedList<Log>> liveData = ((LogModel) LogFragment.this.viewModel).logs;
                LogFragment logFragment = LogFragment.this;
                liveData.observe(logFragment, logFragment.observable);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) REFRESH_FILTER_VIEW, LogConfig.class, new BindingConsumer<LogConfig>() {
            public void call(LogConfig logConfig) {
                LogFragment.this.addItem("ALERTS", DataFragment.TAG_FILTER_VPD, true, logConfig.types.contains((byte) 2) || logConfig.types.contains((byte) 3));
                LogFragment.this.addItem("AUTOMATIONS", DataFragment.TAG_FILTER_HUMIDITY, true, logConfig.types.contains((byte) 1));
                LogFragment.this.addItem("MODES", (byte) 0, false, logConfig.portSelects.contains((byte) 0));
                for (byte b = 1; b < logConfig.portCount; b = (byte) (b + 1)) {
                    LogFragment logFragment = LogFragment.this;
                    Locale locale = Locale.ENGLISH;
                    Object[] objArr = new Object[2];
                    objArr[0] = ProtocolTransformer.isOutletDevice(LogFragment.this.type) ? "OUTLET" : "PORT";
                    objArr[1] = Byte.valueOf(b);
                    logFragment.addItem(String.format(locale, "%s %d MODES", objArr), b, b % 2 == 0, logConfig.portSelects.contains(Byte.valueOf(b)));
                }
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "show delete log dialog", (BindingAction) new BindingAction() {
            public void call() {
                LogFragment.this.showBottomSheep();
            }
        });
    }

    /* access modifiers changed from: private */
    public void addItem(String str, byte b, boolean z, boolean z2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(C2303R.dimen.dp_30));
        View inflate = LayoutInflater.from(getContext()).inflate(C2303R.layout.log_filter_item, (ViewGroup) null);
        inflate.setLayoutParams(layoutParams);
        inflate.setTag(Byte.valueOf(b));
        ((TextView) inflate.findViewById(C2303R.C2306id.tv_title)).setText(str);
        inflate.setOnClickListener(((LogModel) this.viewModel).onFilter);
        inflate.setSelected(z2);
        if (z) {
            ((FragmentHistoryBinding) this.binding).llRow1.addView(inflate);
        } else {
            ((FragmentHistoryBinding) this.binding).llRow2.addView(inflate);
        }
    }

    /* access modifiers changed from: private */
    public void showDeleteDialog(final int i) {
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            this.sheetDialog.dismiss();
        }
        TipDialog.showTipDialog(getContext(), "Delete Log", i == 0 ? "Delete all history logs except for the past \"1 Hour\"." : i == 1 ? "Delete all history logs except for the past \"24 Hours\"." : i == 2 ? "Delete all history logs except for the past \"7 Days\"." : i == 3 ? "Delete all history logs except for the past \"4 Weeks\"." : "Delete all history logs.", getResources().getString(C2303R.string.tip_cancel_lowercase), getResources().getString(C2303R.string.tip_confirm_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                int i = i;
                if (i == 4) {
                    ((LogModel) LogFragment.this.viewModel).deleteAll();
                    return;
                }
                long j = 0;
                if (i == 0) {
                    j = TimeUtil.getTimestamp(10, -1);
                } else if (i == 1) {
                    j = TimeUtil.getTimestamp(5, -1);
                } else if (i == 2) {
                    j = TimeUtil.getTimestamp(5, -7);
                } else if (i == 3) {
                    j = TimeUtil.getTimestamp(5, -28);
                }
                ((LogModel) LogFragment.this.viewModel).deleteOldData(j);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showBottomSheep() {
        View inflate = LayoutInflater.from(getContext()).inflate(C2663R.layout.bottom_sheet, (ViewGroup) null, false);
        inflate.findViewById(C2303R.C2306id.tv_1_hours).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogFragment.this.showDeleteDialog(0);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_24_hours).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogFragment.this.showDeleteDialog(1);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_7_days).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogFragment.this.showDeleteDialog(2);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_4_weeks).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogFragment.this.showDeleteDialog(3);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_delete_all).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogFragment.this.showDeleteDialog(4);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LogFragment.this.sheetDialog != null && LogFragment.this.sheetDialog.isShowing()) {
                    LogFragment.this.sheetDialog.dismiss();
                }
            }
        });
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C2663R.style.dialog);
        this.sheetDialog = bottomSheetDialog;
        bottomSheetDialog.setContentView(inflate);
        this.sheetDialog.setCancelable(true);
        BottomSheetBehavior.from((View) inflate.getParent()).setState(3);
        this.sheetDialog.show();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    public void onSecond() {
        ((LogModel) this.viewModel).onName.execute();
    }

    public void setDegree(boolean z) {
        if (this.adapter != null && ((LogModel) this.viewModel).isDegree != z) {
            ((LogModel) this.viewModel).isDegree = z;
            this.adapter.setDegree(z);
        }
    }

    private class LogObservable implements Observer<PagedList<Log>> {
        private LogObservable() {
        }

        public void onChanged(PagedList<Log> pagedList) {
            int i = 0;
            if (pagedList != null) {
                Iterator it = pagedList.iterator();
                while (it.hasNext() && ((Log) it.next()).time > LogFragment.this.adapter.getStart()) {
                    i++;
                }
            }
            RxBus.getDefault().post(new BluetoothEvent((byte) 1, LogFragment.this.mac, i));
            KLog.m65e("日志 改变了");
            LogFragment.this.adapter.submitList(pagedList);
            ((FragmentHistoryBinding) LogFragment.this.binding).rcContent.scrollToPosition(LogFragment.this.position);
        }
    }
}
