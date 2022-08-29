package com.eternal.widget.loadingdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.widget.C2779R;
import java.util.ArrayList;
import java.util.List;

public class LoadingDialog implements FinishDrawListener {
    public static final int STYLE_LINE = 1;
    public static final int STYLE_RING = 0;

    /* renamed from: s */
    private static StyleManager f256s = StyleManager.getDefault();
    public final String TAG = "LoadingDialog";
    /* access modifiers changed from: private */

    /* renamed from: d */
    public DismissListener f257d;

    /* renamed from: h */
    private Handler f258h = new Handler() {
        public void handleMessage(Message message) {
            LoadingDialog.this.close();
            if (LoadingDialog.this.f259o != null) {
                LoadingDialog.this.f259o.onFinish();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean interceptBack = true;
    private LinearLayout layout;
    private String loadFailedStr;
    private int loadStyle = 0;
    private String loadSuccessStr;
    private TextView loadingText;
    private LoadCircleView mCircleLoadView;
    private WrongDiaView mFailedView;
    private MaterialDialog mLoadingDialog;
    private LVCircularRing mLoadingView;
    private RightDiaView mSuccessView;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public OnFinshListener f259o;
    private boolean openFailedAnim = true;
    private boolean openSuccessAnim = true;
    private int speed = 1;
    private long time = 1000;
    private List<View> viewList;

    public interface DismissListener {
        void dimiss();
    }

    public interface OnFinshListener {
        void onFinish();
    }

    public enum Speed {
        SPEED_ONE,
        SPEED_TWO
    }

    public LoadingDialog setTextSize(float f) {
        return this;
    }

    public LoadingDialog setTextSizeWithPx(float f) {
        return this;
    }

    public LoadingDialog(Context context) {
        View inflate = LayoutInflater.from(context).inflate(C2779R.layout.loading_dialog_view, (ViewGroup) null);
        initView(inflate);
        MaterialDialog build = new MaterialDialog.Builder(context).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).cancelable(!this.interceptBack).canceledOnTouchOutside(!this.interceptBack).autoDismiss(true ^ this.interceptBack).build();
        this.mLoadingDialog = build;
        build.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (!LoadingDialog.this.interceptBack) {
                    LoadingDialog.this.close();
                }
            }
        });
        this.mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (LoadingDialog.this.f257d != null) {
                    LoadingDialog.this.f257d.dimiss();
                }
            }
        });
        initStyle();
    }

    private void initView(View view) {
        this.mLoadingView = (LVCircularRing) view.findViewById(C2779R.C2782id.lv_circularring);
        this.loadingText = (TextView) view.findViewById(C2779R.C2782id.loading_text);
        this.mSuccessView = (RightDiaView) view.findViewById(C2779R.C2782id.rdv_right);
        this.mFailedView = (WrongDiaView) view.findViewById(C2779R.C2782id.wv_wrong);
        this.mCircleLoadView = (LoadCircleView) view.findViewById(C2779R.C2782id.lcv_circleload);
        initData();
    }

    private void initData() {
        ArrayList arrayList = new ArrayList();
        this.viewList = arrayList;
        arrayList.add(this.mLoadingView);
        this.viewList.add(this.mSuccessView);
        this.viewList.add(this.mFailedView);
        this.viewList.add(this.mCircleLoadView);
        this.mSuccessView.setOnDrawFinishListener(this);
        this.mFailedView.setOnDrawFinishListener(this);
    }

    public void dispatchFinishEvent(View view) {
        if (view instanceof WrongDiaView) {
            this.f258h.sendEmptyMessageDelayed(2, this.time);
        } else {
            this.f258h.sendEmptyMessageDelayed(1, this.time);
        }
    }

    private void hideAll() {
        for (View next : this.viewList) {
            if (next.getVisibility() != 8) {
                next.setVisibility(8);
            }
        }
    }

    private void setParams(int i) {
        if (i >= 0) {
            ViewGroup.LayoutParams layoutParams = this.mSuccessView.getLayoutParams();
            layoutParams.height = i;
            layoutParams.width = i;
            this.mSuccessView.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = this.mFailedView.getLayoutParams();
            layoutParams2.height = i;
            layoutParams2.width = i;
            this.mFailedView.setLayoutParams(layoutParams2);
            ViewGroup.LayoutParams layoutParams3 = this.mLoadingView.getLayoutParams();
            layoutParams3.height = i;
            layoutParams3.width = i;
        }
    }

    private void initStyle() {
        StyleManager styleManager = f256s;
        if (styleManager != null) {
            setInterceptBack(styleManager.isInterceptBack());
            setRepeatCount(f256s.getRepeatTime());
            setParams(f256s.getContentSize());
            setTextSize((float) f256s.getTextSize());
            setShowTime(f256s.getShowTime());
            if (!f256s.isOpenAnim()) {
                closeFailedAnim();
                closeSuccessAnim();
            }
            setLoadingText(f256s.getLoadText());
            setSuccessText(f256s.getSuccessText());
            setFailedText(f256s.getFailedText());
            setLoadStyle(f256s.getLoadStyle());
        }
    }

    public void show() {
        hideAll();
        int i = this.loadStyle;
        if (i == 0) {
            this.mLoadingView.setVisibility(0);
            this.mCircleLoadView.setVisibility(8);
            this.mLoadingDialog.show();
            this.mLoadingView.startAnim();
            Log.i("show", "style_ring");
        } else if (i == 1) {
            this.mCircleLoadView.setVisibility(0);
            this.mLoadingView.setVisibility(8);
            this.mLoadingDialog.show();
            Log.i("show", "style_line");
        }
    }

    public boolean isShowing() {
        MaterialDialog materialDialog = this.mLoadingDialog;
        return materialDialog != null && materialDialog.isShowing();
    }

    public LoadingDialog setLoadStyle(int i) {
        if (i < 3) {
            this.loadStyle = i;
            return this;
        }
        throw new IllegalArgumentException("Your style is wrong: style = " + i);
    }

    public void close() {
        this.f258h.removeCallbacksAndMessages((Object) null);
        if (this.mLoadingDialog != null) {
            this.mLoadingView.stopAnim();
            if (this.mLoadingDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
            }
        }
    }

    public LoadingDialog setLoadingText(String str) {
        if (str != null) {
            this.loadingText.setVisibility(0);
            this.loadingText.setText(str);
        } else {
            this.loadingText.setVisibility(8);
        }
        return this;
    }

    public LoadingDialog setSuccessText(String str) {
        this.loadSuccessStr = str;
        return this;
    }

    public LoadingDialog setFailedText(String str) {
        this.loadFailedStr = str;
        return this;
    }

    public void loadSuccess() {
        this.mLoadingView.stopAnim();
        hideAll();
        this.mSuccessView.setDrawDynamic(this.openSuccessAnim);
        this.mSuccessView.setVisibility(0);
        if (this.loadSuccessStr == null) {
            this.loadingText.setVisibility(8);
            return;
        }
        this.loadingText.setVisibility(0);
        this.loadingText.setText(this.loadSuccessStr);
    }

    public void loadFailed() {
        this.mLoadingView.stopAnim();
        hideAll();
        this.mFailedView.setDrawDynamic(this.openFailedAnim);
        this.mFailedView.setVisibility(0);
        if (this.loadFailedStr == null) {
            this.loadingText.setVisibility(8);
            return;
        }
        this.loadingText.setVisibility(0);
        this.loadingText.setText(this.loadFailedStr);
    }

    public LoadingDialog closeSuccessAnim() {
        this.openSuccessAnim = false;
        return this;
    }

    public LoadingDialog closeFailedAnim() {
        this.openFailedAnim = false;
        return this;
    }

    public LoadingDialog setInterceptBack(boolean z) {
        this.interceptBack = z;
        this.mLoadingDialog.setCancelable(!z);
        return this;
    }

    public boolean getInterceptBack() {
        return this.interceptBack;
    }

    public LoadingDialog setLoadSpeed(Speed speed2) {
        if (speed2 == Speed.SPEED_ONE) {
            this.speed = 1;
            this.mSuccessView.setSpeed(1);
            this.mFailedView.setSpeed(1);
        } else {
            this.speed = 2;
            this.mSuccessView.setSpeed(2);
            this.mFailedView.setSpeed(2);
        }
        return this;
    }

    public int getSpeed() {
        return this.speed;
    }

    private LoadingDialog setDrawColor(int i) {
        this.mFailedView.setDrawColor(i);
        this.mSuccessView.setDrawColor(i);
        this.loadingText.setTextColor(i);
        this.mLoadingView.setColor(i);
        return this;
    }

    public LoadingDialog setSize(int i) {
        if (i <= 50) {
            return this;
        }
        setParams(i);
        return this;
    }

    public LoadingDialog setRepeatCount(int i) {
        this.mFailedView.setRepeatTime(i);
        this.mSuccessView.setRepeatTime(i);
        return this;
    }

    public LoadingDialog setShowTime(long j) {
        if (j < 0) {
            return this;
        }
        this.time = j;
        return this;
    }

    public static void initStyle(StyleManager styleManager) {
        if (styleManager != null) {
            f256s = styleManager;
        }
    }

    public void setOnFinishListener(OnFinshListener onFinshListener) {
        this.f259o = onFinshListener;
    }

    public LoadingDialog setDimissListener(DismissListener dismissListener) {
        this.f257d = dismissListener;
        return this;
    }
}
