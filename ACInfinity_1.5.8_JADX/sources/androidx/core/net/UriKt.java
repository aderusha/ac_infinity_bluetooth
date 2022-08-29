package androidx.core.net;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\b¨\u0006\u0005"}, mo27512d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Uri.kt */
public final class UriKt {
    public static final Uri toUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Uri parse = Uri.parse(str);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(this)");
        return parse;
    }

    public static final Uri toUri(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Uri fromFile = Uri.fromFile(file);
        Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(this)");
        return fromFile;
    }

    public static final File toFile(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            String path = uri.getPath();
            if (path != null) {
                return new File(path);
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Uri path is null: ", uri).toString());
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Uri lacks 'file' scheme: ", uri).toString());
    }
}
