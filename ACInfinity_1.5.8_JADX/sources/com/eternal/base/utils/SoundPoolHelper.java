package com.eternal.base.utils;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import com.eternal.base.C1323R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

public class SoundPoolHelper {
    public static final int RING_TYPE_ALARM = 2;
    public static final int RING_TYPE_MUSIC = 4;
    public static final int RING_TYPE_RING = 1;
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_MUSIC = 3;
    public static final int TYPE_NOTIFICATION = 5;
    public static final int TYPE_RING = 2;
    private int NOW_RINGTONE_TYPE;
    private String mac;
    private int maxStream;
    private Map<String, Integer> ringtoneIds;
    private SoundPool soundPool;
    private Map<String, Integer> streamIds;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RING_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    public SoundPoolHelper() {
        this(1, 3);
    }

    public SoundPoolHelper(int i) {
        this(i, 4);
    }

    public SoundPoolHelper(int i, int i2) {
        this.NOW_RINGTONE_TYPE = 2;
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(1);
        AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
        builder2.setLegacyStreamType(i2);
        builder.setAudioAttributes(builder2.build());
        this.soundPool = builder.build();
        this.maxStream = i;
        this.ringtoneIds = new HashMap();
        this.streamIds = new HashMap();
    }

    public SoundPoolHelper setRingtoneType(int i) {
        this.NOW_RINGTONE_TYPE = i;
        return this;
    }

    public SoundPoolHelper load(Context context, String str, int i) {
        int i2 = this.maxStream;
        if (i2 == 0) {
            return this;
        }
        this.maxStream = i2 - 1;
        this.ringtoneIds.put(str, Integer.valueOf(this.soundPool.load(context, i, 1)));
        return this;
    }

    public SoundPoolHelper loadDefault(Context context) {
        Uri systemDefaultRingtoneUri = getSystemDefaultRingtoneUri(context);
        if (systemDefaultRingtoneUri == null) {
            load(context, "default", C1323R.raw.alert_one_beep);
        } else {
            load(context, "default", uri2Path(context, systemDefaultRingtoneUri));
        }
        return this;
    }

    public SoundPoolHelper load(Context context, String str, String str2) {
        int i = this.maxStream;
        if (i == 0) {
            return this;
        }
        this.maxStream = i - 1;
        this.ringtoneIds.put(str, Integer.valueOf(this.soundPool.load(str2, 1)));
        return this;
    }

    public void play(String str, boolean z, String str2) {
        this.mac = str2;
        if (this.ringtoneIds.containsKey(str)) {
            this.streamIds.put(str, Integer.valueOf(this.soundPool.play(this.ringtoneIds.get(str).intValue(), 1.0f, 1.0f, 1, z ? -1 : 0, 1.0f)));
        }
    }

    public void stop(String str) {
        this.mac = null;
        Integer num = this.streamIds.get(str);
        if (num != null) {
            this.soundPool.stop(num.intValue());
        }
    }

    public void playDefault() {
        play("default", false, (String) null);
    }

    public String getMac() {
        return this.mac;
    }

    public void release() {
        SoundPool soundPool2 = this.soundPool;
        if (soundPool2 != null) {
            soundPool2.release();
        }
    }

    private Uri getSystemDefaultRingtoneUri(Context context) {
        try {
            return RingtoneManager.getActualDefaultRingtoneUri(context, this.NOW_RINGTONE_TYPE);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String uri2Path(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }
}
