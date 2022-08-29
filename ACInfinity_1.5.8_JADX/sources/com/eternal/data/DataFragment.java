package com.eternal.data;

import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import com.eternal.base.BaseFragment;
import com.eternal.base.IConfirmAction;
import com.eternal.base.IConnectAction;
import com.eternal.base.IDegreeAction;
import com.eternal.base.ISoftKeyBoardAction;
import com.eternal.base.ITFPAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.TipDialog;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.base.utils.TimeUtil;
import com.eternal.data.databinding.FragmentDataBinding;
import com.eternal.data.p007ui.GraphAdapter;
import com.eternal.data.p007ui.GraphHumBarView;
import com.eternal.data.p007ui.GraphTmpBarView;
import com.eternal.data.p007ui.GraphView;
import com.eternal.data.p007ui.GraphVpdBarView;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.BarUtils;
import com.eternal.framework.utils.GsonUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.SPUtils;
import com.eternal.framework.utils.ScreenUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class DataFragment extends BaseFragment<FragmentDataBinding, DataModel> implements GraphAdapter.TimeCallback, IConnectAction, IDegreeAction, IConfirmAction, ISoftKeyBoardAction, ITFPAction, IToolBarAction {
    public static final String SET_PORT_COUNT = "set port count";
    public static final String SHOW_DELETE_DIALOG = "show delete dialog";
    public static final String SHOW_FILTER_POPUP = "show filter popup dialog";
    public static final String SHOW_POPUP = "show popup dialog";
    public static final byte TAG_FILTER_HUMIDITY = 101;
    public static final byte TAG_FILTER_TEMPERATURE = 100;
    public static final byte TAG_FILTER_VPD = 102;
    public static final String UPDATE_FILTER = "update filter";
    public static final String UPDATE_TARGET = "update target";
    private static final int[] portSelectors = {C1835R.C1837drawable.filter_port1_selector, C1835R.C1837drawable.filter_port2_selector, C1835R.C1837drawable.filter_port3_selector, C1835R.C1837drawable.filter_port4_selector};
    /* access modifiers changed from: private */
    public GraphAdapter adapter;
    private Calendar calendar;
    private Consumer<Boolean> consumer;
    private Consumer<ActivityEvent> consumer2;
    /* access modifiers changed from: private */
    public SimpleDateFormat dateFormat;
    /* access modifiers changed from: private */
    public byte deviceType;
    /* access modifiers changed from: private */
    public TextView endTime;
    /* access modifiers changed from: private */
    public GraphView graphView;
    private GraphHumBarView humBar;
    private Disposable init;
    private boolean isResume;
    private boolean isVisible;
    /* access modifiers changed from: private */
    public ImageView ivEnd;
    /* access modifiers changed from: private */
    public ImageView ivStart;
    PopupWindow mFilterPopWindow;
    PopupWindow mPopWindow;
    /* access modifiers changed from: private */
    public String mac;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view.getId() != C1835R.C1838id.tv_zoom) {
                if (view.getId() == C1835R.C1838id.tv_hour) {
                    ((DataModel) DataFragment.this.viewModel).onHour.execute();
                } else if (view.getId() == C1835R.C1838id.tv_day) {
                    ((DataModel) DataFragment.this.viewModel).onDay.execute();
                } else if (view.getId() == C1835R.C1838id.tv_week) {
                    ((DataModel) DataFragment.this.viewModel).onWeek.execute();
                } else if (view.getId() == C1835R.C1838id.tv_month) {
                    ((DataModel) DataFragment.this.viewModel).onMonth.execute();
                } else if (view.getId() == C1835R.C1838id.tv_year) {
                    ((DataModel) DataFragment.this.viewModel).onYear.execute();
                }
            }
            if (DataFragment.this.mPopWindow != null && DataFragment.this.mPopWindow.isShowing()) {
                DataFragment.this.mPopWindow.dismiss();
            }
        }
    };
    public View.OnClickListener onFilter = new View.OnClickListener() {
        public void onClick(View view) {
            view.setSelected(!view.isSelected());
            Byte b = (Byte) view.getTag();
            if (!view.isSelected()) {
                ((DataModel) DataFragment.this.viewModel).filterTypes.remove(b);
            } else if (!((DataModel) DataFragment.this.viewModel).filterTypes.contains(b)) {
                ((DataModel) DataFragment.this.viewModel).filterTypes.add(b);
            }
            DataFragment.this.updateFilterView();
            if (((DataModel) DataFragment.this.viewModel).isReady() || !((DataModel) DataFragment.this.viewModel).isConnect()) {
                DataFragment.this.graphView.updatePositionScroller();
            }
            final String json = GsonUtils.toJson(((DataModel) DataFragment.this.viewModel).filterTypes);
            Single.create(new SingleOnSubscribe<Boolean>() {
                public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                    SPUtils instance = SPUtils.getInstance();
                    instance.put(ActivityEvent.SHARED_PREFERENCES_KEY_DATA_CHECKED_TYPES + DataFragment.this.mac, json);
                }
            }).subscribeOn(Schedulers.m983io()).ignoreElement().subscribe();
        }
    };
    private byte port;
    BottomSheetDialog sheetDialog;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetTmp.editTarget.setCursorVisible(true);
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetHum.editTarget.setCursorVisible(true);
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetVpd.editTarget.setCursorVisible(true);
        }

        public void onSoftKeyboardClosed() {
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetTmp.editTarget.setCursorVisible(false);
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetTmp.editTarget.clearFocus();
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetHum.editTarget.setCursorVisible(false);
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetHum.editTarget.clearFocus();
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetVpd.editTarget.setCursorVisible(false);
            ((FragmentDataBinding) DataFragment.this.binding).layoutTargetVpd.editTarget.clearFocus();
        }
    };
    /* access modifiers changed from: private */
    public TextView startTime;
    /* access modifiers changed from: private */
    public SimpleDateFormat targetDateFormat;
    /* access modifiers changed from: private */
    public GraphTmpBarView tempBar;
    /* access modifiers changed from: private */
    public byte type;
    /* access modifiers changed from: private */
    public byte version;
    private GraphVpdBarView vpdBar;

    public void onNameUpdate(List<DeviceName> list) {
    }

    public void onSoftKeyboardClosed() {
    }

    public void onSoftKeyboardOpened(int i) {
    }

    public void setEnd(String str, boolean z) {
    }

    public void setStart(String str) {
    }

    public int initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return C1835R.layout.fragment_data;
    }

    public void connected() {
        updateState();
    }

    public void disconnected() {
        Disposable disposable = this.init;
        if (disposable != null && !disposable.isDisposed()) {
            this.init.dispose();
        }
        ((DataModel) this.viewModel).onPause();
    }

    public void setConnectType(String str, byte b) {
        ((DataModel) this.viewModel).devId = str;
        ((DataModel) this.viewModel).connectType = b;
    }

    public void onHiddenChanged(boolean z) {
        this.isVisible = !z;
        updateState();
    }

    public void onResume() {
        super.onResume();
        this.isResume = true;
        updateState();
    }

    public void onPause() {
        super.onPause();
        this.isResume = false;
        updateState();
    }

    private void updateState() {
        if (!this.isVisible || !this.isResume) {
            ((DataModel) this.viewModel).onPause();
            Disposable disposable = this.init;
            if (disposable != null) {
                disposable.dispose();
                return;
            }
            return;
        }
        if (!((DataModel) this.viewModel).isReady()) {
            initHistory();
        } else {
            ((DataModel) this.viewModel).onResume();
        }
        ((DataModel) this.viewModel).startTFPBle();
    }

    public int initVariableId() {
        return C1765BR.model;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.port = getActivity().getIntent().getByteExtra(ActivityEvent.DEVICE_PORT, (byte) 0);
        this.deviceType = getActivity().getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 1);
        this.version = getActivity().getIntent().getByteExtra(ActivityEvent.DEVICE_VERSION, (byte) 0);
        byte byteExtra = getActivity().getIntent().getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, (byte) 0);
        this.mac = getActivity().getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        String stringExtra = getActivity().getIntent().getStringExtra(ActivityEvent.DEVICE_ID);
        this.targetDateFormat = new SimpleDateFormat("MMM dd, yyyy, h:mm aa", Locale.ENGLISH);
        this.dateFormat = new SimpleDateFormat("MM/dd/yyyy, h:mm aa", Locale.ENGLISH);
        ((DataModel) this.viewModel).init(RepositoryInjection.providerHistoryRepository(), this.mac, stringExtra, this.port, this.deviceType, this.version, byteExtra);
        init(view);
        registerMassage();
        KLog.m62d("Data Fragment onViewCreated");
    }

    private void init(View view) {
        this.calendar = Calendar.getInstance();
        GraphAdapter graphAdapter = new GraphAdapter(!ProtocolTransformer.isDeviceC(this.deviceType), this.port);
        this.adapter = graphAdapter;
        graphAdapter.setTimeCallback(this);
        initView(view);
    }

    private void initHistory() {
        Disposable disposable = this.init;
        if (disposable == null || disposable.isDisposed()) {
            this.init = ((DataModel) this.viewModel).initHistory(this.consumer, this.consumer2).compose(bindToLifecycle()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                    if (((DataModel) DataFragment.this.viewModel).isConnect() || ProtocolTransformer.isWorkWiFi(DataFragment.this.deviceType, ((DataModel) DataFragment.this.viewModel).connectType)) {
                        RxBus.getDefault().post(new ProgressEvent((byte) 0));
                    }
                }
            }).subscribe(this.consumer, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                    ((DataModel) DataFragment.this.viewModel).isLoading.setValue(false);
                }
            });
        }
    }

    private void initView(View view) {
        byte b;
        boolean z = false;
        if (this.version < 3) {
            ((FragmentDataBinding) this.binding).llContent.removeView(((FragmentDataBinding) this.binding).llTimeTool);
            ((FragmentDataBinding) this.binding).llRoot.addView(((FragmentDataBinding) this.binding).llTimeTool, 0);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ((FragmentDataBinding) this.binding).srl.getLayoutParams();
            layoutParams.topMargin = 0;
            ((FragmentDataBinding) this.binding).srl.setLayoutParams(layoutParams);
        }
        if (this.deviceType == 6) {
            ((DataModel) this.viewModel).humVisible.setValue(false);
            ((DataModel) this.viewModel).vpdVisible.setValue(false);
        } else {
            ((DataModel) this.viewModel).humVisible.setValue(true);
            ((DataModel) this.viewModel).vpdVisible.setValue(true);
        }
        GraphView graphView2 = (GraphView) view.findViewById(C1835R.C1838id.graph_tmp);
        this.graphView = graphView2;
        graphView2.setAdapter(this.adapter);
        boolean z2 = !ProtocolTransformer.isDeviceC(this.deviceType);
        this.graphView.setShowBottomGraph(z2);
        this.graphView.setHumVisible(((DataModel) this.viewModel).humVisible.getValue().booleanValue());
        this.graphView.setVpdVisible(((DataModel) this.viewModel).vpdVisible.getValue().booleanValue());
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1835R.dimen.dp_500);
        if (!z2) {
            dimensionPixelSize -= this.graphView.GRAPH_LEVEL_HEIGHT;
        }
        if (!((DataModel) this.viewModel).vpdVisible.getValue().booleanValue()) {
            dimensionPixelSize -= (int) (((float) this.graphView.GRAPH_HEIGHT) * 0.3f);
        }
        if (dimensionPixelSize != getResources().getDimensionPixelSize(C1835R.dimen.dp_515)) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.graphView.getLayoutParams();
            layoutParams2.height = dimensionPixelSize;
            this.graphView.setLayoutParams(layoutParams2);
        }
        GraphView graphView3 = this.graphView;
        if (this.version >= 3 && ((b = this.deviceType) == 8 || b == 11 || b == 7 || b == 1 || b == 6)) {
            z = true;
        }
        graphView3.setShowLevel(z);
        GraphTmpBarView graphTmpBarView = (GraphTmpBarView) view.findViewById(C1835R.C1838id.bar_tmp);
        this.tempBar = graphTmpBarView;
        graphTmpBarView.setDataDegree(!ProtocolTransformer.isDeviceC(this.deviceType));
        this.tempBar.setAdapter(this.adapter);
        GraphHumBarView graphHumBarView = (GraphHumBarView) view.findViewById(C1835R.C1838id.bar_hum);
        this.humBar = graphHumBarView;
        graphHumBarView.setAdapter(this.adapter);
        GraphVpdBarView graphVpdBarView = (GraphVpdBarView) view.findViewById(C1835R.C1838id.bar_vpd);
        this.vpdBar = graphVpdBarView;
        graphVpdBarView.setAdapter(this.adapter);
        this.consumer = new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                KLog.m68i("init history is end");
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                ((DataModel) DataFragment.this.viewModel).isLoading.setValue(false);
                ((DataModel) DataFragment.this.viewModel).isDegree = bool.booleanValue();
                if (((DataModel) DataFragment.this.viewModel).isDegree) {
                    ((DataModel) DataFragment.this.viewModel).targetTmpModel.setData(0, 90, 3, "°C");
                } else {
                    ((DataModel) DataFragment.this.viewModel).targetTmpModel.setData(32, 162, 6, "°F");
                }
                ((DataModel) DataFragment.this.viewModel).targetHumModel.setData(0, 100, 5, "%");
                ((DataModel) DataFragment.this.viewModel).targetVpdModel.setData(0, 99, 3, "kPa");
                ((DataModel) DataFragment.this.viewModel).initTargetData();
                DataFragment.this.graphView.setDegree(bool.booleanValue());
                DataFragment.this.tempBar.setDegree(bool.booleanValue());
                DataFragment.this.observerHistory();
            }
        };
        this.consumer2 = new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) throws Exception {
                if (activityEvent.what == 3) {
                    DataFragment.this.setLeafTemperatureC(((Byte) activityEvent.obj).byteValue());
                }
            }
        };
        ((DataModel) this.viewModel).selectTab.observe(this, new Observer<Byte>() {
            public void onChanged(Byte b) {
                byte unused = DataFragment.this.type = b.byteValue();
                if (b == null || b.byteValue() == 0) {
                    ((FragmentDataBinding) DataFragment.this.binding).tvDistance.setText("HOUR");
                } else if (b.byteValue() == 1) {
                    ((FragmentDataBinding) DataFragment.this.binding).tvDistance.setText("DAY");
                } else if (b.byteValue() == 2) {
                    ((FragmentDataBinding) DataFragment.this.binding).tvDistance.setText("WEEK");
                } else if (b.byteValue() == 3) {
                    ((FragmentDataBinding) DataFragment.this.binding).tvDistance.setText("MONTH");
                } else if (b.byteValue() == 4) {
                    ((FragmentDataBinding) DataFragment.this.binding).tvDistance.setText("YEAR");
                } else {
                    ((FragmentDataBinding) DataFragment.this.binding).tvDistance.setText("ZOOM");
                    ((FragmentDataBinding) DataFragment.this.binding).tvStart.setVisibility(0);
                }
            }
        });
        ((DataModel) this.viewModel).timeDistance.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                DataFragment.this.graphView.setScrollerOpen(false);
                if (num == null) {
                    DataFragment.this.adapter.setDistance(3600);
                } else {
                    DataFragment.this.adapter.setDistance(num.intValue());
                }
            }
        });
        ((DataModel) this.viewModel).onHour.execute();
        this.startTime = (TextView) view.findViewById(C1835R.C1838id.tv_start);
        this.endTime = (TextView) view.findViewById(C1835R.C1838id.tv_end);
        this.ivEnd = (ImageView) view.findViewById(C1835R.C1838id.iv_end);
        C17957 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.changeTime(false);
            }
        };
        view.findViewById(C1835R.C1838id.tv_start).setOnClickListener(r0);
        ImageView imageView = (ImageView) view.findViewById(C1835R.C1838id.iv_start);
        this.ivStart = imageView;
        imageView.setOnClickListener(r0);
        C17968 r02 = new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.changeTime(true);
            }
        };
        view.findViewById(C1835R.C1838id.tv_end).setOnClickListener(r02);
        view.findViewById(C1835R.C1838id.iv_end).setOnClickListener(r02);
        C17979 r8 = new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                DataFragment.this.onConfirm();
                return false;
            }
        };
        ((FragmentDataBinding) this.binding).layoutTargetTmp.editTarget.setOnEditorActionListener(r8);
        ((FragmentDataBinding) this.binding).layoutTargetHum.editTarget.setOnEditorActionListener(r8);
        ((FragmentDataBinding) this.binding).layoutTargetVpd.editTarget.setOnEditorActionListener(r8);
        ((FragmentDataBinding) this.binding).llTmpTitle.setTag(Byte.valueOf(TAG_FILTER_TEMPERATURE));
        ((FragmentDataBinding) this.binding).llTmpTitle.setOnClickListener(this.onFilter);
        ((FragmentDataBinding) this.binding).llHumTitle.setTag(Byte.valueOf(TAG_FILTER_HUMIDITY));
        ((FragmentDataBinding) this.binding).llHumTitle.setOnClickListener(this.onFilter);
        ((FragmentDataBinding) this.binding).llVpdTitle.setTag(Byte.valueOf(TAG_FILTER_VPD));
        ((FragmentDataBinding) this.binding).llVpdTitle.setOnClickListener(this.onFilter);
        new SoftKeyBroadManager(((FragmentDataBinding) this.binding).llRoot).addSoftKeyboardStateListener(this.softKeyboardStateListener);
    }

    /* access modifiers changed from: private */
    public void setLeafTemperatureC(byte b) {
        if (((DataModel) this.viewModel).leafTemperatureC != b) {
            ((DataModel) this.viewModel).leafTemperatureC = b;
            RxBus.getDefault().post(new ProgressEvent((byte) 1));
            try {
                this.graphView.hidePositionScroller(false);
                this.vpdBar.hidePositionScroller(false);
                this.vpdBar.updatePath(0);
            } catch (Exception e) {
                e.printStackTrace();
                KLog.m65e(e);
            }
            observerHistory();
        }
    }

    /* access modifiers changed from: private */
    public void observerHistory() {
        if (((DataModel) this.viewModel).history != null) {
            ((DataModel) this.viewModel).history.removeObservers(this);
            ((DataModel) this.viewModel).history.observe(this, new Observer<PagedList<TmpHum>>() {
                public void onChanged(PagedList<TmpHum> pagedList) {
                    if (pagedList == null || pagedList.size() >= DataFragment.this.adapter.getSize()) {
                        boolean z = false;
                        try {
                            DataFragment.this.tempBar.hidePositionScroller(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                            KLog.m65e(e);
                        }
                        DataFragment.this.graphView.setDegree(((DataModel) DataFragment.this.viewModel).isDegree);
                        DataFragment.this.tempBar.setDegree(((DataModel) DataFragment.this.viewModel).isDegree);
                        DataFragment.this.adapter.setLeafTemperatureC(((DataModel) DataFragment.this.viewModel).leafTemperatureC);
                        GraphAdapter access$2400 = DataFragment.this.adapter;
                        if (DataFragment.this.version < 3) {
                            z = true;
                        }
                        access$2400.submitList(pagedList, z);
                        return;
                    }
                    DataFragment.this.updateAdapter();
                }
            });
        }
    }

    private void setTmpDegree(boolean z) {
        if (((DataModel) this.viewModel).isReady() && ((DataModel) this.viewModel).isDegree != z) {
            ((DataModel) this.viewModel).isDegree = z;
            if (((DataModel) this.viewModel).isDegree) {
                ((DataModel) this.viewModel).targetTmpModel.setData(0, 90, 3, "°C");
            } else {
                ((DataModel) this.viewModel).targetTmpModel.setData(32, 162, 6, "°F");
            }
            ((DataModel) this.viewModel).initTargetData();
            RxBus.getDefault().post(new ProgressEvent((byte) 1));
            this.graphView.setDegree(z);
            this.tempBar.setDegree(z);
            try {
                this.graphView.hidePositionScroller(false);
                this.tempBar.hidePositionScroller(false);
                this.tempBar.updatePath(0);
            } catch (Exception e) {
                e.printStackTrace();
                KLog.m65e(e);
            }
            observerHistory();
        }
    }

    public void onDestroyView() {
        Disposable disposable = this.init;
        if (disposable != null) {
            disposable.dispose();
        }
        PopupWindow popupWindow = this.mPopWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.mPopWindow.dismiss();
        }
        PopupWindow popupWindow2 = this.mFilterPopWindow;
        if (popupWindow2 != null && popupWindow2.isShowing()) {
            this.mFilterPopWindow.dismiss();
        }
        unregisterMassage();
        super.onDestroyView();
    }

    private void registerMassage() {
        Messenger.getDefault().register((Object) this, (Object) "show delete dialog", (BindingAction) new BindingAction() {
            public void call() {
                DataFragment.this.showBottomSheep();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) SHOW_POPUP, (BindingAction) new BindingAction() {
            public void call() {
                if (DataFragment.this.mPopWindow == null || !DataFragment.this.mPopWindow.isShowing()) {
                    DataFragment.this.showPopupWindow();
                } else {
                    DataFragment.this.mPopWindow.dismiss();
                }
            }
        });
        Messenger.getDefault().register((Object) this, (Object) SHOW_FILTER_POPUP, (BindingAction) new BindingAction() {
            public void call() {
                if (DataFragment.this.mFilterPopWindow == null || !DataFragment.this.mFilterPopWindow.isShowing()) {
                    DataFragment.this.showFilterPopupWindow();
                } else {
                    DataFragment.this.mFilterPopWindow.dismiss();
                }
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_TARGET, (BindingAction) new BindingAction() {
            public void call() {
                DataFragment.this.updateTargetView();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) SET_PORT_COUNT, Integer.class, new BindingConsumer<Integer>() {
            public void call(Integer num) {
                DataFragment.this.graphView.setPortCount(num.intValue(), DataFragment.this.deviceType);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) UPDATE_FILTER, (BindingAction) new BindingAction() {
            public void call() {
                DataFragment.this.updateFilterView();
            }
        });
        ((DataModel) this.viewModel).targetTmpModel.expand.observe(this, new Observer<Integer>() {
            public void onChanged(Integer num) {
                if (num.intValue() == 0 || num.intValue() == 1) {
                    DataFragment.this.closeKeyboard();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateFilterView() {
        boolean contains = ((DataModel) this.viewModel).filterTypes.contains(Byte.valueOf(TAG_FILTER_TEMPERATURE));
        this.graphView.setShowTmpLine(contains);
        ((DataModel) this.viewModel).tmpGraphVisible.setValue(Boolean.valueOf(contains));
        ((FragmentDataBinding) this.binding).llTmpTitle.setSelected(contains);
        boolean contains2 = ((DataModel) this.viewModel).filterTypes.contains(Byte.valueOf(TAG_FILTER_HUMIDITY));
        ((DataModel) this.viewModel).humGraphVisible.setValue(Boolean.valueOf(contains2));
        this.graphView.setShowHumLine(contains2);
        ((FragmentDataBinding) this.binding).llHumTitle.setSelected(contains2);
        boolean contains3 = ((DataModel) this.viewModel).filterTypes.contains(Byte.valueOf(TAG_FILTER_VPD));
        this.graphView.setShowVpdLine(contains3);
        ((DataModel) this.viewModel).vpdGraphVisible.setValue(Boolean.valueOf(contains3));
        ((FragmentDataBinding) this.binding).llVpdTitle.setSelected(contains3);
        int i = 0;
        for (byte b = 0; b < ((DataModel) this.viewModel).portCount; b = (byte) (b + 1)) {
            if (((DataModel) this.viewModel).filterTypes.contains(Byte.valueOf(b))) {
                i |= 1 << b;
            }
        }
        this.graphView.setShowPortLines(i);
        if (((DataModel) this.viewModel).isReady() || !((DataModel) this.viewModel).isConnect()) {
            this.adapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void showFilterPopupWindow() {
        View inflate = LayoutInflater.from(getContext()).inflate(C1835R.layout.layout_filter_popup, (ViewGroup) null, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C1835R.C1838id.ll_row);
        addItem("Temperature", Byte.valueOf(TAG_FILTER_TEMPERATURE), linearLayout, C1835R.C1837drawable.filter_tmp_selector);
        if (this.deviceType != 6) {
            addItem("Humidity", Byte.valueOf(TAG_FILTER_HUMIDITY), linearLayout, C1835R.C1837drawable.filter_hum_selector);
            addItem("VPD", Byte.valueOf(TAG_FILTER_VPD), linearLayout, C1835R.C1837drawable.filter_vpd_selector);
        }
        if (!ProtocolTransformer.isDeviceC(this.deviceType)) {
            if (ProtocolTransformer.isDeviceMultiPort(this.deviceType)) {
                for (byte b = 1; b < ((DataModel) this.viewModel).portCount; b = (byte) (b + 1)) {
                    Byte valueOf = Byte.valueOf(b);
                    int[] iArr = portSelectors;
                    addItem("Port " + b, valueOf, linearLayout, iArr[(b - 1) % iArr.length]);
                }
            } else {
                addItem("Port", (byte) 0, linearLayout, portSelectors[1]);
            }
        }
        PopupWindow popupWindow = new PopupWindow(inflate);
        this.mFilterPopWindow = popupWindow;
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
            }
        });
        this.mFilterPopWindow.setWidth(getResources().getDimensionPixelSize(C1835R.dimen.dp_144));
        this.mFilterPopWindow.setHeight(-2);
        this.mFilterPopWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mFilterPopWindow.setOutsideTouchable(true);
        this.mFilterPopWindow.setTouchable(true);
        this.mFilterPopWindow.setFocusable(true);
        Point displayDimensions = ScreenUtils.getDisplayDimensions(getActivity());
        int[] iArr2 = new int[2];
        ((FragmentDataBinding) this.binding).ivShowFilter.getLocationOnScreen(iArr2);
        inflate.measure(0, 0);
        int measuredHeight = inflate.getMeasuredHeight();
        if ((displayDimensions.y - iArr2[1]) - BarUtils.getStatusBarHeight() < measuredHeight) {
            this.mFilterPopWindow.showAtLocation(((FragmentDataBinding) this.binding).ivShowFilter, 0, iArr2[0], iArr2[1] - measuredHeight);
        } else {
            this.mFilterPopWindow.showAsDropDown(((FragmentDataBinding) this.binding).ivShowFilter);
        }
    }

    private void addItem(String str, Byte b, LinearLayout linearLayout, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(C1835R.dimen.dp_32));
        View inflate = LayoutInflater.from(getContext()).inflate(C1835R.layout.filter_item, (ViewGroup) null);
        inflate.setLayoutParams(layoutParams);
        inflate.setTag(b);
        ((ImageView) inflate.findViewById(C1835R.C1838id.iv_check)).setBackgroundResource(i);
        ((TextView) inflate.findViewById(C1835R.C1838id.tv_title)).setText(str);
        inflate.setOnClickListener(this.onFilter);
        inflate.setSelected(((DataModel) this.viewModel).filterTypes.contains(b));
        linearLayout.addView(inflate);
    }

    /* access modifiers changed from: private */
    public void showPopupWindow() {
        ((FragmentDataBinding) this.binding).clDistance.setSelected(true);
        View inflate = LayoutInflater.from(getContext()).inflate(C1835R.layout.layout_popup, (ViewGroup) null, false);
        PopupWindow popupWindow = new PopupWindow(inflate);
        this.mPopWindow = popupWindow;
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                ((FragmentDataBinding) DataFragment.this.binding).clDistance.setSelected(false);
            }
        });
        this.mPopWindow.setWidth(getResources().getDimensionPixelSize(C1835R.dimen.dp_83));
        this.mPopWindow.setHeight(-2);
        this.mPopWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mPopWindow.setOutsideTouchable(true);
        this.mPopWindow.setTouchable(true);
        this.mPopWindow.setFocusable(true);
        setTextView(inflate, C1835R.C1838id.tv_hour, 0);
        setTextView(inflate, C1835R.C1838id.tv_day, 1);
        setTextView(inflate, C1835R.C1838id.tv_week, 2);
        setTextView(inflate, C1835R.C1838id.tv_month, 3);
        setTextView(inflate, C1835R.C1838id.tv_year, 4);
        setTextView(inflate, C1835R.C1838id.tv_zoom, 5);
        Point displayDimensions = ScreenUtils.getDisplayDimensions(getActivity());
        int[] iArr = new int[2];
        ((FragmentDataBinding) this.binding).clDistance.getLocationOnScreen(iArr);
        inflate.measure(0, 0);
        int measuredHeight = inflate.getMeasuredHeight();
        if ((displayDimensions.y - iArr[1]) - BarUtils.getStatusBarHeight() < measuredHeight) {
            this.mPopWindow.showAtLocation(((FragmentDataBinding) this.binding).clDistance, 0, iArr[0], iArr[1] - measuredHeight);
        } else {
            this.mPopWindow.showAsDropDown(((FragmentDataBinding) this.binding).clDistance);
        }
    }

    /* access modifiers changed from: package-private */
    public void setTextView(View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        textView.setOnClickListener(this.onClickListener);
        if (this.type == i2) {
            textView.setSelected(true);
            textView.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C1835R.font.avenir_medium));
            return;
        }
        textView.setSelected(false);
        textView.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C1835R.font.avenir_book));
    }

    /* access modifiers changed from: private */
    public void showBottomSheep() {
        View inflate = LayoutInflater.from(getContext()).inflate(C2663R.layout.bottom_sheet, (ViewGroup) null, false);
        inflate.findViewById(C1835R.C1838id.tv_1_hours).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.showDeleteDialog(0);
            }
        });
        inflate.findViewById(C1835R.C1838id.tv_24_hours).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.showDeleteDialog(1);
            }
        });
        inflate.findViewById(C1835R.C1838id.tv_7_days).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.showDeleteDialog(2);
            }
        });
        inflate.findViewById(C1835R.C1838id.tv_4_weeks).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.showDeleteDialog(3);
            }
        });
        inflate.findViewById(C1835R.C1838id.tv_delete_all).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.showDeleteDialog(4);
            }
        });
        inflate.findViewById(C1835R.C1838id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DataFragment.this.sheetDialog != null && DataFragment.this.sheetDialog.isShowing()) {
                    DataFragment.this.sheetDialog.dismiss();
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

    /* access modifiers changed from: private */
    public void showDeleteDialog(final int i) {
        BottomSheetDialog bottomSheetDialog = this.sheetDialog;
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            this.sheetDialog.dismiss();
        }
        TipDialog.showTipDialog(getContext(), "Delete Data", i == 0 ? "Delete all data except for the past \"1 Hour\"." : i == 1 ? "Delete all data except for the past \"24 Hours\"." : i == 2 ? "Delete all data except for the past \"7 Days\"." : i == 3 ? "Delete all data except for the past \"4 Weeks\"." : "Delete all data history.", getResources().getString(C1835R.string.tip_cancel_lowercase), getResources().getString(C1835R.string.tip_confirm_lowercase), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                DataFragment.this.closeScrollerDetail();
                int i = i;
                if (i == 4) {
                    ((DataModel) DataFragment.this.viewModel).deleteAll();
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
                ((DataModel) DataFragment.this.viewModel).deleteOldData(j / 1000);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateAdapter() {
        this.calendar = Calendar.getInstance();
        GraphAdapter graphAdapter = new GraphAdapter(!ProtocolTransformer.isDeviceC(this.deviceType), this.port);
        this.adapter = graphAdapter;
        graphAdapter.setTimeCallback(this);
        this.graphView.setAdapter(this.adapter);
        this.tempBar.setAdapter(this.adapter);
        this.humBar.setAdapter(this.adapter);
        this.vpdBar.setAdapter(this.adapter);
        ((DataModel) this.viewModel).onHour.execute();
        observerHistory();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    public void setData(long j, int i, boolean z, boolean z2, int i2, int i3, int i4, int i5, int i6, int i7, float f, float f2, float f3, int i8, int i9, int i10, int i11, int i12, int i13) {
        final long j2 = j;
        final int i14 = i;
        final boolean z3 = z;
        final int i15 = i2;
        final int i16 = i3;
        final int i17 = i4;
        final int i18 = i5;
        final int i19 = i6;
        final int i20 = i7;
        final float f4 = f;
        final float f5 = f2;
        final float f6 = f3;
        final int i21 = i8;
        final int i22 = i9;
        final int i23 = i10;
        final int i24 = i11;
        final int i25 = i12;
        final int i26 = i13;
        Completable.create(new CompletableOnSubscribe(this) {
            final /* synthetic */ DataFragment this$0;

            {
                this.this$0 = r4;
            }

            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                if (!z3) {
                    int i = i14;
                    if (i == 3600) {
                        ((DataModel) this.this$0.viewModel).selectTab.setValue((byte) 0);
                    } else if (i == 86400) {
                        ((DataModel) this.this$0.viewModel).selectTab.setValue((byte) 1);
                    } else if (i == 604800) {
                        ((DataModel) this.this$0.viewModel).selectTab.setValue((byte) 2);
                    } else if (i == 2678400) {
                        ((DataModel) this.this$0.viewModel).selectTab.setValue((byte) 3);
                    } else if (i == 31536000) {
                        ((DataModel) this.this$0.viewModel).selectTab.setValue((byte) 4);
                    } else {
                        ((DataModel) this.this$0.viewModel).selectTab.setValue((byte) 5);
                    }
                }
                if (this.this$0.graphView.graphTmp != null) {
                    ((DataModel) this.this$0.viewModel).maxTmp.set(this.this$0.graphView.graphTmp.formatValue((float) i15));
                    ((DataModel) this.this$0.viewModel).minTmp.set(this.this$0.graphView.graphTmp.formatValue((float) i16));
                    ((DataModel) this.this$0.viewModel).avgTmp.set(this.this$0.graphView.graphTmp.formatValue(f4));
                }
                if (this.this$0.graphView.graphHum != null) {
                    ((DataModel) this.this$0.viewModel).maxHum.set(this.this$0.graphView.graphHum.formatValue((float) i17));
                    ((DataModel) this.this$0.viewModel).minHum.set(this.this$0.graphView.graphHum.formatValue((float) i18));
                    ((DataModel) this.this$0.viewModel).avgHum.set(this.this$0.graphView.graphHum.formatValue(f5));
                }
                if (this.this$0.graphView.graphVpd != null) {
                    ((DataModel) this.this$0.viewModel).maxVpd.set(this.this$0.graphView.graphVpd.formatValue((float) i19));
                    ((DataModel) this.this$0.viewModel).minVpd.set(this.this$0.graphView.graphVpd.formatValue((float) i20));
                    ((DataModel) this.this$0.viewModel).avgVpd.set(this.this$0.graphView.graphVpd.formatValue(f6));
                }
                long j = j2 * 1000;
                long j2 = (((long) i14) * 1000) + j;
                String upperCase = this.this$0.targetDateFormat.format(Long.valueOf(j)).toUpperCase();
                String upperCase2 = this.this$0.targetDateFormat.format(Long.valueOf(j2)).toUpperCase();
                String desc = ((DataModel) this.this$0.viewModel).targetTmpModel.setDesc(upperCase, upperCase2, ((DataModel) this.this$0.viewModel).selectTab.getValue() == null ? 0 : ((DataModel) this.this$0.viewModel).selectTab.getValue().byteValue(), i21, i22);
                MutableLiveData<String> mutableLiveData = ((DataModel) this.this$0.viewModel).targetTmpTxt;
                if (((DataModel) this.this$0.viewModel).targetTmpModel.getTarget() == -32768) {
                    desc = "";
                }
                mutableLiveData.setValue(desc);
                String desc2 = ((DataModel) this.this$0.viewModel).targetHumModel.setDesc(upperCase, upperCase2, ((DataModel) this.this$0.viewModel).selectTab.getValue() == null ? 0 : ((DataModel) this.this$0.viewModel).selectTab.getValue().byteValue(), i23, i24);
                MutableLiveData<String> mutableLiveData2 = ((DataModel) this.this$0.viewModel).targetHumTxt;
                if (((DataModel) this.this$0.viewModel).targetHumModel.getTarget() == -32768) {
                    desc2 = "";
                }
                mutableLiveData2.setValue(desc2);
                String desc3 = ((DataModel) this.this$0.viewModel).targetVpdModel.setDesc(upperCase, upperCase2, ((DataModel) this.this$0.viewModel).selectTab.getValue() == null ? 0 : ((DataModel) this.this$0.viewModel).selectTab.getValue().byteValue(), i25, i26);
                MutableLiveData<String> mutableLiveData3 = ((DataModel) this.this$0.viewModel).targetVpdTxt;
                if (((DataModel) this.this$0.viewModel).targetVpdModel.getTarget() == -32768) {
                    desc3 = "";
                }
                mutableLiveData3.setValue(desc3);
                String upperCase3 = this.this$0.dateFormat.format(Long.valueOf(j)).toUpperCase();
                String upperCase4 = this.this$0.dateFormat.format(Long.valueOf(j2)).toUpperCase();
                boolean isEnd = this.this$0.adapter.isEnd();
                this.this$0.endTime.setText(upperCase4);
                if (this.this$0.type == 5) {
                    this.this$0.ivEnd.setVisibility(8);
                } else if (this.this$0.ivEnd.getVisibility() == 0 && isEnd) {
                    this.this$0.ivEnd.setVisibility(8);
                } else if (this.this$0.ivEnd.getVisibility() == 8 && !isEnd) {
                    this.this$0.ivEnd.setVisibility(0);
                }
                this.this$0.startTime.setText(upperCase3);
                if (this.this$0.type == 5) {
                    this.this$0.ivStart.setVisibility(8);
                } else {
                    this.this$0.ivStart.setVisibility(0);
                }
                completableEmitter.onComplete();
            }
        }).compose(bindToLifecycle()).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    /* access modifiers changed from: private */
    public void changeTime(boolean z) {
        KLog.m65e("change time");
        this.calendar.setTimeInMillis(this.adapter.getMinTime());
        if (z) {
            byte b = this.type;
            if (b == 0) {
                this.calendar.add(11, 1);
            } else if (b == 1) {
                this.calendar.add(5, 1);
            } else if (b == 2) {
                this.calendar.add(5, 7);
            } else if (b == 3) {
                this.calendar.add(2, 1);
            } else if (b == 4) {
                this.calendar.add(1, 1);
            }
        } else {
            byte b2 = this.type;
            if (b2 == 0) {
                this.calendar.add(11, -1);
            } else if (b2 == 1) {
                this.calendar.add(5, -1);
            } else if (b2 == 2) {
                this.calendar.add(5, -7);
            } else if (b2 == 3) {
                this.calendar.add(2, -1);
            } else if (b2 == 4) {
                this.calendar.add(1, -1);
            }
        }
        this.adapter.setMinTime((int) (this.calendar.getTimeInMillis() / 1000));
        closeScrollerDetail();
    }

    /* access modifiers changed from: package-private */
    public void closeScrollerDetail() {
        this.graphView.setScrollerOpen(false);
        this.tempBar.setScrollerOpen(false);
        this.humBar.setScrollerOpen(false);
        this.vpdBar.setScrollerOpen(false);
    }

    public void setDegree(boolean z) {
        setTmpDegree(z);
    }

    public void onConfirm() {
        updateTargetView();
        int i = -32768;
        if (((DataModel) this.viewModel).isDegree) {
            DataModel dataModel = (DataModel) this.viewModel;
            if (((DataModel) this.viewModel).targetTmpC != -32768) {
                i = Math.round(ProtocolTransformer.getFah((float) ((DataModel) this.viewModel).targetTmpC));
            }
            dataModel.targetTmpF = i;
        } else {
            DataModel dataModel2 = (DataModel) this.viewModel;
            if (((DataModel) this.viewModel).targetTmpF != -32768) {
                i = Math.round(ProtocolTransformer.getDegree((float) ((DataModel) this.viewModel).targetTmpF));
            }
            dataModel2.targetTmpC = i;
        }
        RepositoryInjection.providerDeviceRepository().updateTarget(this.mac, ((DataModel) this.viewModel).targetTmpF, ((DataModel) this.viewModel).targetTmpC, ((DataModel) this.viewModel).targetHum, ((DataModel) this.viewModel).targetVpd);
    }

    /* access modifiers changed from: private */
    public void updateTargetView() {
        if (TextUtils.isEmpty(((DataModel) this.viewModel).targetTmpModel.targetTxt.getValue())) {
            this.adapter.setMaxTargetTmp(-32768);
            this.adapter.setMinTargetTmp(-32768);
            ((DataModel) this.viewModel).targetTmpC = -32768;
            ((DataModel) this.viewModel).targetTmpF = -32768;
            ((DataModel) this.viewModel).targetTmpModel.setTarget(-32768);
            ((DataModel) this.viewModel).targetTmpTxt.setValue("");
        } else {
            try {
                int floatValue = (int) Float.valueOf(((DataModel) this.viewModel).targetTmpModel.targetTxt.getValue()).floatValue();
                if (floatValue < ((DataModel) this.viewModel).targetTmpModel.getMin()) {
                    floatValue = ((DataModel) this.viewModel).targetTmpModel.getMin();
                }
                if (floatValue > ((DataModel) this.viewModel).targetTmpModel.getMax()) {
                    floatValue = ((DataModel) this.viewModel).targetTmpModel.getMax();
                }
                if (((DataModel) this.viewModel).isDegree) {
                    ((DataModel) this.viewModel).targetTmpC = floatValue;
                    if (this.adapter.isDataDegree()) {
                        this.adapter.setMaxTargetTmp((((DataModel) this.viewModel).targetTmpModel.getStep() + floatValue) * 100);
                        this.adapter.setMinTargetTmp((floatValue - ((DataModel) this.viewModel).targetTmpModel.getStep()) * 100);
                    } else {
                        this.adapter.setMaxTargetTmp(Math.round(ProtocolTransformer.getFah((float) (((DataModel) this.viewModel).targetTmpModel.getStep() + floatValue)) * 100.0f));
                        this.adapter.setMinTargetTmp(Math.round(ProtocolTransformer.getFah((float) (floatValue - ((DataModel) this.viewModel).targetTmpModel.getStep())) * 100.0f));
                    }
                } else {
                    ((DataModel) this.viewModel).targetTmpF = floatValue;
                    if (this.adapter.isDataDegree()) {
                        this.adapter.setMaxTargetTmp(Math.round(ProtocolTransformer.getDegree((float) (((DataModel) this.viewModel).targetTmpModel.getStep() + floatValue)) * 100.0f));
                        this.adapter.setMinTargetTmp(Math.round(ProtocolTransformer.getDegree((float) (floatValue - ((DataModel) this.viewModel).targetTmpModel.getStep())) * 100.0f));
                    } else {
                        this.adapter.setMaxTargetTmp((((DataModel) this.viewModel).targetTmpModel.getStep() + floatValue) * 100);
                        this.adapter.setMinTargetTmp((floatValue - ((DataModel) this.viewModel).targetTmpModel.getStep()) * 100);
                    }
                }
                ((DataModel) this.viewModel).targetTmpModel.setTarget(floatValue);
            } catch (NumberFormatException unused) {
                if (((DataModel) this.viewModel).isDegree) {
                    ((DataModel) this.viewModel).targetTmpC = -32768;
                    this.adapter.setMaxTargetTmp(-32768);
                    this.adapter.setMinTargetTmp(-32768);
                } else {
                    ((DataModel) this.viewModel).targetTmpF = -32768;
                    this.adapter.setMaxTargetTmp(-32768);
                    this.adapter.setMinTargetTmp(-32768);
                }
                ((DataModel) this.viewModel).targetTmpModel.setTarget(-32768);
                ((DataModel) this.viewModel).targetTmpTxt.setValue("");
            }
        }
        if (TextUtils.isEmpty(((DataModel) this.viewModel).targetHumModel.targetTxt.getValue())) {
            ((DataModel) this.viewModel).targetHum = -32768;
            this.adapter.setMaxTargetHum(-32768);
            this.adapter.setMinTargetHum(-32768);
            ((DataModel) this.viewModel).targetHumModel.setTarget(-32768);
            ((DataModel) this.viewModel).targetHumTxt.setValue("");
        } else {
            try {
                int floatValue2 = (int) Float.valueOf(((DataModel) this.viewModel).targetHumModel.targetTxt.getValue()).floatValue();
                if (floatValue2 < ((DataModel) this.viewModel).targetHumModel.getMin()) {
                    floatValue2 = ((DataModel) this.viewModel).targetHumModel.getMin();
                }
                if (floatValue2 > ((DataModel) this.viewModel).targetHumModel.getMax()) {
                    floatValue2 = ((DataModel) this.viewModel).targetHumModel.getMax();
                }
                ((DataModel) this.viewModel).targetHum = floatValue2;
                this.adapter.setMaxTargetHum((((DataModel) this.viewModel).targetHumModel.getStep() + floatValue2) * 100);
                this.adapter.setMinTargetHum((floatValue2 - ((DataModel) this.viewModel).targetHumModel.getStep()) * 100);
                ((DataModel) this.viewModel).targetHumModel.setTarget(floatValue2);
                MutableLiveData<String> mutableLiveData = ((DataModel) this.viewModel).targetHumTxt;
                mutableLiveData.setValue(floatValue2 + ((DataModel) this.viewModel).targetHumModel.getUnit());
            } catch (NumberFormatException unused2) {
                ((DataModel) this.viewModel).targetHum = -32768;
                this.adapter.setMaxTargetHum(-32768);
                this.adapter.setMinTargetHum(-32768);
                ((DataModel) this.viewModel).targetHumModel.setTarget(-32768);
                ((DataModel) this.viewModel).targetHumTxt.setValue("");
            }
        }
        if (TextUtils.isEmpty(((DataModel) this.viewModel).targetVpdModel.targetTxt.getValue())) {
            ((DataModel) this.viewModel).targetVpd = -32768;
            this.adapter.setMaxTargetVpd(-32768);
            this.adapter.setMinTargetVpd(-32768);
            ((DataModel) this.viewModel).targetVpdModel.setTarget(-32768);
            ((DataModel) this.viewModel).targetVpdTxt.setValue("");
        } else {
            try {
                int floatValue3 = (int) (Float.valueOf(((DataModel) this.viewModel).targetVpdModel.targetTxt.getValue()).floatValue() * 10.0f);
                if (floatValue3 < ((DataModel) this.viewModel).targetVpdModel.getMin()) {
                    floatValue3 = ((DataModel) this.viewModel).targetVpdModel.getMin();
                }
                if (floatValue3 > ((DataModel) this.viewModel).targetVpdModel.getMax()) {
                    floatValue3 = ((DataModel) this.viewModel).targetVpdModel.getMax();
                }
                ((DataModel) this.viewModel).targetVpd = floatValue3;
                this.adapter.setMaxTargetVpd((((DataModel) this.viewModel).targetVpdModel.getStep() + floatValue3) * 10);
                this.adapter.setMinTargetVpd((floatValue3 - ((DataModel) this.viewModel).targetVpdModel.getStep()) * 10);
                ((DataModel) this.viewModel).targetVpdModel.setTarget(floatValue3);
                MutableLiveData<String> mutableLiveData2 = ((DataModel) this.viewModel).targetVpdTxt;
                mutableLiveData2.setValue(String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) floatValue3) / 10.0f)}) + ((DataModel) this.viewModel).targetVpdModel.getUnit());
            } catch (NumberFormatException unused3) {
                ((DataModel) this.viewModel).targetVpd = -32768;
                this.adapter.setMaxTargetVpd(-32768);
                this.adapter.setMinTargetVpd(-32768);
                ((DataModel) this.viewModel).targetVpdModel.setTarget(-32768);
                ((DataModel) this.viewModel).targetVpdTxt.setValue("");
            }
        }
        if (((DataModel) this.viewModel).isReady() || !((DataModel) this.viewModel).isConnect()) {
            this.adapter.notifyDataSetChanged();
        }
    }

    public void closeKeyboard() {
        SoftKeyBroadManager.closeKeyboard(getContext(), ((FragmentDataBinding) this.binding).layoutTargetTmp.editTarget);
    }

    public void refreshTFP(DeviceTFP deviceTFP) {
        ((DataModel) this.viewModel).refreshTFP(deviceTFP);
    }

    public void onSecond() {
        int i;
        if (((DataModel) this.viewModel).overlayVisible.getValue() == null || ((DataModel) this.viewModel).overlayVisible.getValue() == Boolean.FALSE) {
            if (this.deviceType == 6) {
                i = C1835R.C1837drawable.overlay_data_3;
            } else if (this.version < 3) {
                i = C1835R.C1837drawable.overlay_data_2;
            } else {
                i = C1835R.C1837drawable.overlay_data_1;
            }
            ((DataModel) this.viewModel).overlayRes.setValue(Integer.valueOf(i));
            ((DataModel) this.viewModel).overlayVisible.setValue(true);
            return;
        }
        ((DataModel) this.viewModel).overlayVisible.setValue(false);
    }
}
