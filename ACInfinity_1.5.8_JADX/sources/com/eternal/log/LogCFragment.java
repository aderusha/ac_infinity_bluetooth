package com.eternal.log;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.eternal.base.BaseFragment;
import com.eternal.base.IDegreeAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.TipDialog;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.LogExtra;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.database.entity.Log;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.utils.TimeUtil;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.KLog;
import com.eternal.log.adapter.LogCAdapter;
import com.eternal.log.databinding.FragmentLogCBinding;
import com.eternal.log.model.LogCModel;
import com.eternal.res.C2663R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Iterator;
import java.util.List;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class LogCFragment extends BaseFragment<FragmentLogCBinding, LogCModel> implements IToolBarAction, IDegreeAction {
    public static final String REFRESH_LOG = "refresh log";
    public static final String SHOW_DELETE_DIALOG = "show delete log dialog";
    /* access modifiers changed from: private */
    public LogCAdapter adapter;
    /* access modifiers changed from: private */
    public String mac;
    /* access modifiers changed from: private */
    public LogObservable observable;
    BottomSheetDialog sheetDialog;
    private byte type;

    public void onNameUpdate(List<DeviceName> list) {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C2303R.layout.fragment_log_c;
    }

    public void onHiddenChanged(boolean z) {
        if (!z) {
            ((FragmentLogCBinding) this.binding).rcContent.smoothScrollToPosition(0);
        }
    }

    public int initVariableId() {
        return C2267BR.modelc;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        bindEvent();
        registerMassage();
    }

    public void onResume() {
        if (this.adapter != null) {
            ((LogCModel) this.viewModel).getExtra().compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).subscribe(new Consumer<LogExtra>() {
                public void accept(LogExtra logExtra) throws Exception {
                    LogCFragment.this.adapter.setExtra(logExtra);
                }
            });
        }
        super.onResume();
    }

    public void onDestroyView() {
        unregisterMassage();
        super.onDestroyView();
    }

    private void initView() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(f2) <= Math.abs(f) || f2 >= 0.0f || LogCFragment.this.adapter == null || LogCFragment.this.adapter.isClear()) {
                    return false;
                }
                ((LogCModel) LogCFragment.this.viewModel).resetTime(new Consumer<Long>() {
                    public void accept(Long l) throws Exception {
                        LogCFragment.this.adapter.clear(l);
                        RxBus.getDefault().post(new BluetoothEvent((byte) 1, ((LogCModel) LogCFragment.this.viewModel).mac));
                    }
                });
                return true;
            }
        });
        ((FragmentLogCBinding) this.binding).rcContent.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0 && ((LogCModel) LogCFragment.this.viewModel).show.getValue() == Boolean.TRUE) {
                    ((LogCModel) LogCFragment.this.viewModel).show.setValue(false);
                }
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        ((FragmentLogCBinding) this.binding).rcContent.setLayoutManager(new LinearLayoutManager(getContext()));
        ((FragmentLogCBinding) this.binding).rcContent.setNestedScrollingEnabled(false);
        this.observable = new LogObservable();
        Bundle arguments = getArguments();
        this.mac = arguments.getString(ActivityEvent.DEVICE_MAC);
        this.type = arguments.getByte(ActivityEvent.DEVICE_TYPE, (byte) 0).byteValue();
        ((LogCModel) this.viewModel).init(RepositoryInjection.providerLogRepository(), this.mac, this.type).compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LogExtra>() {
            public void accept(LogExtra logExtra) throws Exception {
                LogCAdapter unused = LogCFragment.this.adapter = new LogCAdapter(logExtra);
                ((FragmentLogCBinding) LogCFragment.this.binding).rcContent.setAdapter(LogCFragment.this.adapter);
                ((FragmentLogCBinding) LogCFragment.this.binding).rcContent.setFocusable(false);
                ((FragmentLogCBinding) LogCFragment.this.binding).rcContent.setFocusableInTouchMode(false);
                LiveData<PagedList<Log>> liveData = ((LogCModel) LogCFragment.this.viewModel).logs;
                LogCFragment logCFragment = LogCFragment.this;
                liveData.observe(logCFragment, logCFragment.observable);
            }
        });
    }

    private void bindEvent() {
        ((LogCModel) this.viewModel).show.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    ((FragmentLogCBinding) LogCFragment.this.binding).umeFilter.expand();
                } else {
                    ((FragmentLogCBinding) LogCFragment.this.binding).umeFilter.collapse();
                }
            }
        });
    }

    private void registerMassage() {
        Messenger.getDefault().register((Object) this, (Object) "refresh log", (BindingAction) new BindingAction() {
            public void call() {
                KLog.m65e("refresh log");
                ((LogCModel) LogCFragment.this.viewModel).logs.removeObservers(LogCFragment.this);
                ((LogCModel) LogCFragment.this.viewModel).refresh();
                LiveData<PagedList<Log>> liveData = ((LogCModel) LogCFragment.this.viewModel).logs;
                LogCFragment logCFragment = LogCFragment.this;
                liveData.observe(logCFragment, logCFragment.observable);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "show delete log dialog", (BindingAction) new BindingAction() {
            public void call() {
                LogCFragment.this.showBottomSheep();
            }
        });
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
                    ((LogCModel) LogCFragment.this.viewModel).deleteAll();
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
                ((LogCModel) LogCFragment.this.viewModel).deleteOldData(j);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showBottomSheep() {
        View inflate = LayoutInflater.from(getContext()).inflate(C2663R.layout.bottom_sheet, (ViewGroup) null, false);
        inflate.findViewById(C2303R.C2306id.tv_1_hours).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCFragment.this.showDeleteDialog(0);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_24_hours).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCFragment.this.showDeleteDialog(1);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_7_days).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCFragment.this.showDeleteDialog(2);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_4_weeks).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCFragment.this.showDeleteDialog(3);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_delete_all).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCFragment.this.showDeleteDialog(4);
            }
        });
        inflate.findViewById(C2303R.C2306id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LogCFragment.this.sheetDialog != null && LogCFragment.this.sheetDialog.isShowing()) {
                    LogCFragment.this.sheetDialog.dismiss();
                }
            }
        });
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C2303R.style.dialog);
        this.sheetDialog = bottomSheetDialog;
        bottomSheetDialog.setContentView(inflate);
        this.sheetDialog.setCancelable(true);
        BottomSheetBehavior.from((View) inflate.getParent()).setState(3);
        this.sheetDialog.show();
    }

    public void onSecond() {
        ((LogCModel) this.viewModel).onName.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    public void setDegree(boolean z) {
        LogCAdapter logCAdapter = this.adapter;
        if (logCAdapter != null) {
            logCAdapter.setDegree(z);
        }
    }

    private class LogObservable implements Observer<PagedList<Log>> {
        private LogObservable() {
        }

        public void onChanged(PagedList<Log> pagedList) {
            int i = 0;
            if (pagedList != null) {
                Iterator it = pagedList.iterator();
                while (it.hasNext() && ((Log) it.next()).time > LogCFragment.this.adapter.getStart()) {
                    i++;
                }
            }
            RxBus.getDefault().post(new BluetoothEvent((byte) 1, LogCFragment.this.mac, i));
            LogCFragment.this.adapter.submitList(pagedList);
        }
    }
}
