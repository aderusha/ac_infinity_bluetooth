package android.support.p000v4.media;

import android.media.MediaDescription;
import android.net.Uri;

/* renamed from: android.support.v4.media.MediaDescriptionCompatApi23 */
class MediaDescriptionCompatApi23 {
    public static Uri getMediaUri(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }

    /* renamed from: android.support.v4.media.MediaDescriptionCompatApi23$Builder */
    static class Builder {
        public static void setMediaUri(Object obj, Uri uri) {
            ((MediaDescription.Builder) obj).setMediaUri(uri);
        }

        private Builder() {
        }
    }

    private MediaDescriptionCompatApi23() {
    }
}
