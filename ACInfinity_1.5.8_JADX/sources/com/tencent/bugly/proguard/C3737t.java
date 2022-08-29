package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.eternal.export.CSVUtil;
import com.tencent.bugly.crashreport.common.info.C3626a;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: com.tencent.bugly.proguard.t */
/* compiled from: BUGLY */
public final class C3737t {

    /* renamed from: a */
    private final SimpleDateFormat f1008a;

    /* renamed from: b */
    private final C3734r f1009b;

    /* renamed from: com.tencent.bugly.proguard.t$a */
    /* compiled from: BUGLY */
    static class C3739a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C3737t f1012a = new C3737t((byte) 0);
    }

    /* synthetic */ C3737t(byte b) {
        this();
    }

    private C3737t() {
        this.f1008a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US);
        this.f1009b = new C3734r();
    }

    /* renamed from: a */
    public static C3737t m875a() {
        return C3739a.f1012a;
    }

    /* renamed from: b */
    public final void mo24316b() {
        List<C3740b> c = m879c();
        if (c == null || c.isEmpty()) {
            C3749y.m939c("sla local data is null", new Object[0]);
            return;
        }
        C3749y.m939c("sla load local data list size:%s", Integer.valueOf(c.size()));
        Iterator<C3740b> it = c.iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            C3740b next = it.next();
            if (next.mo24321b() < C3695ab.m683b() - 604800000) {
                C3749y.m939c("sla local data is expired:%s", next.mo24323c());
                arrayList.add(next);
                it.remove();
            }
        }
        m882e(arrayList);
        m878b(c);
    }

    /* renamed from: a */
    public final void mo24314a(C3741c cVar) {
        if (cVar == null || TextUtils.isEmpty(cVar.mo24326b())) {
            C3749y.m940d("sla report event is null", new Object[0]);
            return;
        }
        C3749y.m939c("sla report single event", new Object[0]);
        mo24315a((List<C3741c>) Collections.singletonList(cVar));
    }

    /* renamed from: a */
    public final void mo24315a(List<C3741c> list) {
        if (list == null || list.isEmpty()) {
            C3749y.m940d("sla batch report event is null", new Object[0]);
            return;
        }
        C3749y.m939c("sla batch report event size:%s", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        for (C3741c next : list) {
            C3740b bVar = null;
            if (next == null || TextUtils.isEmpty(next.mo24326b())) {
                C3749y.m940d("sla convert event is null", new Object[0]);
            } else {
                C3626a b = C3626a.m339b();
                if (b == null) {
                    C3749y.m940d("sla convert failed because ComInfoManager is null", new Object[0]);
                } else {
                    String str = "&app_version=" + b.f501i + "&app_name=" + b.f502j + "&app_bundle_id=" + b.f495c + "&client_type=android&user_id=" + b.mo24072g() + "&sdk_version=" + b.f498f + "&event_code=" + next.mo24326b() + "&event_result=" + (next.mo24328d() ? 1 : 0) + "&event_time=" + this.f1008a.format(new Date(next.mo24327c())) + "&event_cost=" + next.mo24329e() + "&device_id=" + b.mo24079k() + "&debug=" + (b.f512t ? 1 : 0) + "&param_0=" + next.mo24330f() + "&param_1=" + next.mo24325a() + "&param_2=ext" + "&param_4=" + b.mo24070f();
                    if (!TextUtils.isEmpty(next.f1022g)) {
                        str = str + "&param_3=" + next.f1022g;
                    }
                    C3749y.m939c("sla convert eventId:%s eventType:%s, eventTime:%s success:%s cost:%s from:%s uploadMsg:", next.mo24325a(), next.mo24326b(), Long.valueOf(next.mo24327c()), Boolean.valueOf(next.mo24328d()), Long.valueOf(next.mo24329e()), next.mo24330f(), next.mo24331g());
                    C3740b bVar2 = new C3740b();
                    bVar2.mo24320a(next.mo24325a() + "-" + next.mo24326b());
                    bVar2.mo24319a(next.mo24327c());
                    bVar2.mo24322b(str);
                    bVar = bVar2;
                }
            }
            if (bVar != null) {
                arrayList.add(bVar);
            }
        }
        m881d(arrayList);
        m878b(arrayList);
    }

    /* renamed from: b */
    private void m878b(final List<C3740b> list) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            C3747x.m926a().mo24344a(new Runnable() {
                public final void run() {
                    C3737t.this.m880c(list);
                }
            });
        } else {
            m880c(list);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m880c(List<C3740b> list) {
        if (list == null || list.isEmpty()) {
            C3749y.m939c("sla batch report data is empty", new Object[0]);
            return;
        }
        C3749y.m939c("sla batch report list size:%s", Integer.valueOf(list.size()));
        if (list.size() > 30) {
            list = list.subList(0, 29);
        }
        ArrayList arrayList = new ArrayList();
        for (C3740b c : list) {
            arrayList.add(c.mo24323c());
        }
        Pair<Integer, String> a = this.f1009b.mo24309a(arrayList);
        C3749y.m939c("sla batch report result, rspCode:%s rspMsg:%s", a.first, a.second);
        if (((Integer) a.first).intValue() == 200) {
            m882e(list);
        }
    }

    /* renamed from: d */
    private static void m881d(List<C3740b> list) {
        for (C3740b next : list) {
            C3749y.m939c("sla save id:%s time:%s msg:%s", next.mo24318a(), Long.valueOf(next.mo24321b()), next.mo24323c());
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_id", next.mo24318a());
                contentValues.put("_tm", Long.valueOf(next.mo24321b()));
                contentValues.put("_dt", next.mo24323c());
                C3730o.m839a().mo24292a("t_sla", contentValues, (C3729n) null, true);
            } catch (Throwable th) {
                C3749y.m938b(th);
            }
        }
    }

    /* renamed from: e */
    private void m882e(List<C3740b> list) {
        if (list == null || list.isEmpty()) {
            C3749y.m939c("sla batch delete list is null", new Object[0]);
            return;
        }
        C3749y.m939c("sla batch delete list size:%s", Integer.valueOf(list.size()));
        try {
            String str = "_id in (" + m876a(CSVUtil.COLUMN_SEPARATOR, (Iterable<C3740b>) list) + ")";
            C3749y.m939c("sla batch delete where:%s", str);
            C3730o.m839a().mo24291a("t_sla", str, (String[]) null, (C3729n) null, true);
        } catch (Throwable th) {
            C3749y.m938b(th);
        }
    }

    /* renamed from: a */
    private static String m876a(String str, Iterable<C3740b> iterable) {
        Iterator<C3740b> it = iterable.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        sb.append(it.next().f1013a);
        sb.append("'");
        while (it.hasNext()) {
            sb.append(str);
            sb.append("'");
            sb.append(it.next().f1013a);
            sb.append("'");
        }
        return sb.toString();
    }

    /* renamed from: c */
    private static List<C3740b> m879c() {
        Cursor a = C3730o.m839a().mo24294a(false, "t_sla", new String[]{"_id", "_tm", "_dt"}, (String) null, (String[]) null, (String) null, (String) null, "_tm", "30", (C3729n) null, true);
        if (a == null) {
            return null;
        }
        if (a.getCount() <= 0) {
            a.close();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (a.moveToNext()) {
            try {
                C3740b bVar = new C3740b();
                bVar.mo24320a(a.getString(a.getColumnIndex("_id")));
                bVar.mo24319a(a.getLong(a.getColumnIndex("_tm")));
                bVar.mo24322b(a.getString(a.getColumnIndex("_dt")));
                C3749y.m939c(bVar.toString(), new Object[0]);
                arrayList.add(bVar);
            } catch (Throwable th) {
                a.close();
                throw th;
            }
        }
        a.close();
        return arrayList;
    }

    /* renamed from: com.tencent.bugly.proguard.t$c */
    /* compiled from: BUGLY */
    public static class C3741c {

        /* renamed from: a */
        private String f1016a;

        /* renamed from: b */
        private String f1017b;

        /* renamed from: c */
        private long f1018c;

        /* renamed from: d */
        private boolean f1019d;

        /* renamed from: e */
        private long f1020e;

        /* renamed from: f */
        private String f1021f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public String f1022g;

        public C3741c(String str, String str2, long j, boolean z, long j2, String str3, String str4) {
            this.f1016a = str;
            this.f1017b = str2;
            this.f1018c = j;
            this.f1019d = z;
            this.f1020e = j2;
            this.f1021f = str3;
            this.f1022g = str4;
        }

        public C3741c() {
        }

        /* renamed from: a */
        public final String mo24325a() {
            return this.f1016a;
        }

        /* renamed from: b */
        public final String mo24326b() {
            return this.f1017b;
        }

        /* renamed from: c */
        public final long mo24327c() {
            return this.f1018c;
        }

        /* renamed from: d */
        public final boolean mo24328d() {
            return this.f1019d;
        }

        /* renamed from: e */
        public final long mo24329e() {
            return this.f1020e;
        }

        /* renamed from: f */
        public final String mo24330f() {
            return this.f1021f;
        }

        /* renamed from: g */
        public final String mo24331g() {
            return this.f1022g;
        }
    }

    /* renamed from: com.tencent.bugly.proguard.t$b */
    /* compiled from: BUGLY */
    public static class C3740b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public String f1013a;

        /* renamed from: b */
        private long f1014b;

        /* renamed from: c */
        private String f1015c;

        /* renamed from: a */
        public final String mo24318a() {
            return this.f1013a;
        }

        /* renamed from: a */
        public final void mo24320a(String str) {
            this.f1013a = str;
        }

        /* renamed from: b */
        public final long mo24321b() {
            return this.f1014b;
        }

        /* renamed from: a */
        public final void mo24319a(long j) {
            this.f1014b = j;
        }

        /* renamed from: c */
        public final String mo24323c() {
            return this.f1015c;
        }

        /* renamed from: b */
        public final void mo24322b(String str) {
            this.f1015c = str;
        }

        public final String toString() {
            return "SLAData{uuid='" + this.f1013a + '\'' + ", time=" + this.f1014b + ", data='" + this.f1015c + '\'' + '}';
        }
    }
}
