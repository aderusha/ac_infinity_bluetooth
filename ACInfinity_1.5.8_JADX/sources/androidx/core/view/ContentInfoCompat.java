package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContentInfo;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ContentInfoCompat {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_APP = 0;
    public static final int SOURCE_AUTOFILL = 4;
    public static final int SOURCE_CLIPBOARD = 1;
    public static final int SOURCE_DRAG_AND_DROP = 3;
    public static final int SOURCE_INPUT_METHOD = 2;
    public static final int SOURCE_PROCESS_TEXT = 5;
    private final Compat mCompat;

    private interface BuilderCompat {
        ContentInfoCompat build();

        void setClip(ClipData clipData);

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setLinkUri(Uri uri);

        void setSource(int i);
    }

    private interface Compat {
        ClipData getClip();

        Bundle getExtras();

        int getFlags();

        Uri getLinkUri();

        int getSource();

        ContentInfo getWrapped();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Source {
    }

    static String sourceToString(int i) {
        if (i == 0) {
            return "SOURCE_APP";
        }
        if (i == 1) {
            return "SOURCE_CLIPBOARD";
        }
        if (i == 2) {
            return "SOURCE_INPUT_METHOD";
        }
        if (i == 3) {
            return "SOURCE_DRAG_AND_DROP";
        }
        if (i != 4) {
            return i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT";
        }
        return "SOURCE_AUTOFILL";
    }

    static String flagsToString(int i) {
        return (i & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i);
    }

    ContentInfoCompat(Compat compat) {
        this.mCompat = compat;
    }

    public static ContentInfoCompat toContentInfoCompat(ContentInfo contentInfo) {
        return new ContentInfoCompat(new Compat31Impl(contentInfo));
    }

    public ContentInfo toContentInfo() {
        return this.mCompat.getWrapped();
    }

    public String toString() {
        return this.mCompat.toString();
    }

    public ClipData getClip() {
        return this.mCompat.getClip();
    }

    public int getSource() {
        return this.mCompat.getSource();
    }

    public int getFlags() {
        return this.mCompat.getFlags();
    }

    public Uri getLinkUri() {
        return this.mCompat.getLinkUri();
    }

    public Bundle getExtras() {
        return this.mCompat.getExtras();
    }

    public Pair<ContentInfoCompat, ContentInfoCompat> partition(Predicate<ClipData.Item> predicate) {
        ClipData clip = this.mCompat.getClip();
        ContentInfoCompat contentInfoCompat = null;
        if (clip.getItemCount() == 1) {
            boolean test = predicate.test(clip.getItemAt(0));
            ContentInfoCompat contentInfoCompat2 = test ? this : null;
            if (!test) {
                contentInfoCompat = this;
            }
            return Pair.create(contentInfoCompat2, contentInfoCompat);
        }
        Pair<ClipData, ClipData> partition = partition(clip, predicate);
        if (partition.first == null) {
            return Pair.create((Object) null, this);
        }
        if (partition.second == null) {
            return Pair.create(this, (Object) null);
        }
        return Pair.create(new Builder(this).setClip((ClipData) partition.first).build(), new Builder(this).setClip((ClipData) partition.second).build());
    }

    static Pair<ClipData, ClipData> partition(ClipData clipData, Predicate<ClipData.Item> predicate) {
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (int i = 0; i < clipData.getItemCount(); i++) {
            ClipData.Item itemAt = clipData.getItemAt(i);
            if (predicate.test(itemAt)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(itemAt);
            } else {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(itemAt);
            }
        }
        if (arrayList == null) {
            return Pair.create((Object) null, clipData);
        }
        if (arrayList2 == null) {
            return Pair.create(clipData, (Object) null);
        }
        return Pair.create(buildClipData(clipData.getDescription(), arrayList), buildClipData(clipData.getDescription(), arrayList2));
    }

    static ClipData buildClipData(ClipDescription clipDescription, List<ClipData.Item> list) {
        ClipData clipData = new ClipData(new ClipDescription(clipDescription), list.get(0));
        for (int i = 1; i < list.size(); i++) {
            clipData.addItem(list.get(i));
        }
        return clipData;
    }

    public static Pair<ContentInfo, ContentInfo> partition(ContentInfo contentInfo, java.util.function.Predicate<ClipData.Item> predicate) {
        return Api31Impl.partition(contentInfo, predicate);
    }

    private static final class Api31Impl {
        private Api31Impl() {
        }

        public static Pair<ContentInfo, ContentInfo> partition(ContentInfo contentInfo, java.util.function.Predicate<ClipData.Item> predicate) {
            ClipData clip = contentInfo.getClip();
            if (clip.getItemCount() == 1) {
                boolean test = predicate.test(clip.getItemAt(0));
                ContentInfo contentInfo2 = test ? contentInfo : null;
                if (test) {
                    contentInfo = null;
                }
                return Pair.create(contentInfo2, contentInfo);
            }
            Objects.requireNonNull(predicate);
            Pair<ClipData, ClipData> partition = ContentInfoCompat.partition(clip, (Predicate<ClipData.Item>) new ContentInfoCompat$Api31Impl$$ExternalSyntheticLambda0(predicate));
            if (partition.first == null) {
                return Pair.create((Object) null, contentInfo);
            }
            if (partition.second == null) {
                return Pair.create(contentInfo, (Object) null);
            }
            return Pair.create(new ContentInfo.Builder(contentInfo).setClip((ClipData) partition.first).build(), new ContentInfo.Builder(contentInfo).setClip((ClipData) partition.second).build());
        }
    }

    private static final class CompatImpl implements Compat {
        private final ClipData mClip;
        private final Bundle mExtras;
        private final int mFlags;
        private final Uri mLinkUri;
        private final int mSource;

        public ContentInfo getWrapped() {
            return null;
        }

        CompatImpl(BuilderCompatImpl builderCompatImpl) {
            this.mClip = (ClipData) Preconditions.checkNotNull(builderCompatImpl.mClip);
            this.mSource = Preconditions.checkArgumentInRange(builderCompatImpl.mSource, 0, 5, "source");
            this.mFlags = Preconditions.checkFlagsArgument(builderCompatImpl.mFlags, 1);
            this.mLinkUri = builderCompatImpl.mLinkUri;
            this.mExtras = builderCompatImpl.mExtras;
        }

        public ClipData getClip() {
            return this.mClip;
        }

        public int getSource() {
            return this.mSource;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("ContentInfoCompat{clip=");
            sb.append(this.mClip.getDescription());
            sb.append(", source=");
            sb.append(ContentInfoCompat.sourceToString(this.mSource));
            sb.append(", flags=");
            sb.append(ContentInfoCompat.flagsToString(this.mFlags));
            String str2 = "";
            if (this.mLinkUri == null) {
                str = str2;
            } else {
                str = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
            }
            sb.append(str);
            if (this.mExtras != null) {
                str2 = ", hasExtras";
            }
            sb.append(str2);
            sb.append("}");
            return sb.toString();
        }
    }

    private static final class Compat31Impl implements Compat {
        private final ContentInfo mWrapped;

        Compat31Impl(ContentInfo contentInfo) {
            this.mWrapped = (ContentInfo) Preconditions.checkNotNull(contentInfo);
        }

        public ContentInfo getWrapped() {
            return this.mWrapped;
        }

        public ClipData getClip() {
            return this.mWrapped.getClip();
        }

        public int getSource() {
            return this.mWrapped.getSource();
        }

        public int getFlags() {
            return this.mWrapped.getFlags();
        }

        public Uri getLinkUri() {
            return this.mWrapped.getLinkUri();
        }

        public Bundle getExtras() {
            return this.mWrapped.getExtras();
        }

        public String toString() {
            return "ContentInfoCompat{" + this.mWrapped + "}";
        }
    }

    public static final class Builder {
        private final BuilderCompat mBuilderCompat;

        public Builder(ContentInfoCompat contentInfoCompat) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.mBuilderCompat = new BuilderCompat31Impl(contentInfoCompat);
            } else {
                this.mBuilderCompat = new BuilderCompatImpl(contentInfoCompat);
            }
        }

        public Builder(ClipData clipData, int i) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.mBuilderCompat = new BuilderCompat31Impl(clipData, i);
            } else {
                this.mBuilderCompat = new BuilderCompatImpl(clipData, i);
            }
        }

        public Builder setClip(ClipData clipData) {
            this.mBuilderCompat.setClip(clipData);
            return this;
        }

        public Builder setSource(int i) {
            this.mBuilderCompat.setSource(i);
            return this;
        }

        public Builder setFlags(int i) {
            this.mBuilderCompat.setFlags(i);
            return this;
        }

        public Builder setLinkUri(Uri uri) {
            this.mBuilderCompat.setLinkUri(uri);
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mBuilderCompat.setExtras(bundle);
            return this;
        }

        public ContentInfoCompat build() {
            return this.mBuilderCompat.build();
        }
    }

    private static final class BuilderCompatImpl implements BuilderCompat {
        ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        int mSource;

        BuilderCompatImpl(ClipData clipData, int i) {
            this.mClip = clipData;
            this.mSource = i;
        }

        BuilderCompatImpl(ContentInfoCompat contentInfoCompat) {
            this.mClip = contentInfoCompat.getClip();
            this.mSource = contentInfoCompat.getSource();
            this.mFlags = contentInfoCompat.getFlags();
            this.mLinkUri = contentInfoCompat.getLinkUri();
            this.mExtras = contentInfoCompat.getExtras();
        }

        public void setClip(ClipData clipData) {
            this.mClip = clipData;
        }

        public void setSource(int i) {
            this.mSource = i;
        }

        public void setFlags(int i) {
            this.mFlags = i;
        }

        public void setLinkUri(Uri uri) {
            this.mLinkUri = uri;
        }

        public void setExtras(Bundle bundle) {
            this.mExtras = bundle;
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }
    }

    private static final class BuilderCompat31Impl implements BuilderCompat {
        private final ContentInfo.Builder mPlatformBuilder;

        BuilderCompat31Impl(ClipData clipData, int i) {
            this.mPlatformBuilder = new ContentInfo.Builder(clipData, i);
        }

        BuilderCompat31Impl(ContentInfoCompat contentInfoCompat) {
            this.mPlatformBuilder = new ContentInfo.Builder(contentInfoCompat.toContentInfo());
        }

        public void setClip(ClipData clipData) {
            this.mPlatformBuilder.setClip(clipData);
        }

        public void setSource(int i) {
            this.mPlatformBuilder.setSource(i);
        }

        public void setFlags(int i) {
            this.mPlatformBuilder.setFlags(i);
        }

        public void setLinkUri(Uri uri) {
            this.mPlatformBuilder.setLinkUri(uri);
        }

        public void setExtras(Bundle bundle) {
            this.mPlatformBuilder.setExtras(bundle);
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(new Compat31Impl(this.mPlatformBuilder.build()));
        }
    }
}
