package com.eternal.base;

import android.os.Looper;
import android.text.TextUtils;
import com.eternal.base.config.AppUrlConfig;
import com.eternal.base.config.ModuleLifecycleConfig;
import com.eternal.base.database.BaseDatabase;
import com.eternal.base.global.ActivityEvent;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseApplication;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.config.OkHttpConfig;
import com.eternal.framework.http.cookie.store.SPCookieStore;
import com.eternal.framework.http.interceptor.CacheInterceptor.C2214CacheInterceptor;
import com.eternal.framework.http.interfaces.BuildHeadersListener;
import com.eternal.framework.utils.KLog;
import com.tencent.bugly.crashreport.CrashReport;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class Application extends BaseApplication {
    public void onCreate() {
        super.onCreate();
        setApplication(this, new BaseApplication.AppStateChangeListener() {
            public void appTurnIntoForeground() {
                RxBus.getDefault().post(new ActivityEvent(10));
            }

            public void appTurnIntoBackGround() {
                RxBus.getDefault().post(new ActivityEvent(11));
            }
        });
        ModuleLifecycleConfig.initAhead(this);
        ModuleLifecycleConfig.initLow(this);
        initRxHttpUtils();
        CrashReport.initCrashReport(getApplicationContext(), "1bb56d5d3b", false);
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                BaseDatabase.getInstance().deviceDao().checkDB();
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe(new Action() {
            public void run() throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                BaseDatabase.resetDB();
            }
        });
    }

    private void initRxHttpUtils() {
        RxHttpUtils.getInstance().init(this).config().setBaseUrl(AppUrlConfig.base_url).setOkClient(createOkHttp());
    }

    private OkHttpClient createOkHttp() {
        return new OkHttpConfig.Builder(this).setHeaders(new BuildHeadersListener() {
            public Map<String, String> buildHeaders() {
                HashMap hashMap = new HashMap();
                String token = UserCache.getInstance().getToken();
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    KLog.m65e("主线程 不能在主线程操作请求网络！");
                }
                if (!TextUtils.isEmpty(token)) {
                    hashMap.put("token", token);
                }
                return hashMap;
            }
        }).setCache(false).setHasNetCacheTime(15).setCookieType(new SPCookieStore(this)).setAddInterceptor(new C2214CacheInterceptor(this)).setReadTimeout(15).setWriteTimeout(15).setConnectTimeout(15).setDebug(false).build();
    }
}
