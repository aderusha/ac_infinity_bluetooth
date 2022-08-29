package kotlin.p016io.path;

import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo27511d1 = {"\u0000²\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0001H\b\u001a*\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00012\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001a\"\u00020\u0001H\b¢\u0006\u0002\u0010\u001b\u001a?\u0010\u001c\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0007¢\u0006\u0002\u0010!\u001a6\u0010\u001c\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\b¢\u0006\u0002\u0010\"\u001aK\u0010#\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0007¢\u0006\u0002\u0010%\u001aB\u0010#\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\b¢\u0006\u0002\u0010&\u001a\u001c\u0010'\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020\u00022\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*H\u0001\u001a\r\u0010+\u001a\u00020\u0002*\u00020\u0002H\b\u001a\r\u0010,\u001a\u00020\u0001*\u00020\u0002H\b\u001a.\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a\"\u000200H\b¢\u0006\u0002\u00101\u001a\u001f\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u00102\u001a\u000203H\b\u001a.\u00104\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\b¢\u0006\u0002\u00105\u001a.\u00106\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\b¢\u0006\u0002\u00105\u001a.\u00107\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\b¢\u0006\u0002\u00105\u001a\u0015\u00108\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u0002H\b\u001a6\u00109\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\b¢\u0006\u0002\u0010:\u001a\r\u0010;\u001a\u00020<*\u00020\u0002H\b\u001a\r\u0010=\u001a\u000203*\u00020\u0002H\b\u001a\u0015\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010?\u001a\u00020\u0002H\n\u001a\u0015\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010?\u001a\u00020\u0001H\n\u001a&\u0010@\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010B\u001a2\u0010C\u001a\u0002HD\"\n\b\u0000\u0010D\u0018\u0001*\u00020E*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010F\u001a4\u0010G\u001a\u0004\u0018\u0001HD\"\n\b\u0000\u0010D\u0018\u0001*\u00020E*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010F\u001a\r\u0010H\u001a\u00020I*\u00020\u0002H\b\u001a\r\u0010J\u001a\u00020K*\u00020\u0002H\b\u001a.\u0010L\u001a\u00020<*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00012\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020<0OH\bø\u0001\u0000\u001a0\u0010P\u001a\u0004\u0018\u00010Q*\u00020\u00022\u0006\u0010R\u001a\u00020\u00012\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010S\u001a&\u0010T\u001a\u00020U*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010V\u001a(\u0010W\u001a\u0004\u0018\u00010X*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010Y\u001a,\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010]\u001a&\u0010^\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010B\u001a\r\u0010_\u001a\u000203*\u00020\u0002H\b\u001a\r\u0010`\u001a\u000203*\u00020\u0002H\b\u001a\r\u0010a\u001a\u000203*\u00020\u0002H\b\u001a&\u0010b\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010B\u001a\u0015\u0010c\u001a\u000203*\u00020\u00022\u0006\u0010?\u001a\u00020\u0002H\b\u001a\r\u0010d\u001a\u000203*\u00020\u0002H\b\u001a\r\u0010e\u001a\u000203*\u00020\u0002H\b\u001a\u001c\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00020g*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u0001H\u0007\u001a.\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a\"\u000200H\b¢\u0006\u0002\u00101\u001a\u001f\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u00102\u001a\u000203H\b\u001a&\u0010i\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010B\u001a2\u0010j\u001a\u0002Hk\"\n\b\u0000\u0010k\u0018\u0001*\u00020l*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010m\u001a<\u0010j\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010Q0n*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00012\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010o\u001a\r\u0010p\u001a\u00020\u0002*\u00020\u0002H\b\u001a\u0014\u0010q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a\u0016\u0010r\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a\u0014\u0010s\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a8\u0010t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010R\u001a\u00020\u00012\b\u0010u\u001a\u0004\u0018\u00010Q2\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\b¢\u0006\u0002\u0010v\u001a\u0015\u0010w\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020UH\b\u001a\u0015\u0010x\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020XH\b\u001a\u001b\u0010y\u001a\u00020\u0002*\u00020\u00022\f\u0010u\u001a\b\u0012\u0004\u0012\u00020\\0[H\b\u001a\r\u0010z\u001a\u00020\u0002*\u00020{H\b\u001a@\u0010|\u001a\u0002H}\"\u0004\b\u0000\u0010}*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00012\u0018\u0010~\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0012\u0004\u0012\u0002H}0OH\bø\u0001\u0000¢\u0006\u0003\u0010\u0001\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0007\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\"\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u001e\u0010\u0010\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006\"\u001f\u0010\u0013\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0004\u001a\u0004\b\u0015\u0010\u0006\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0001"}, mo27512d2 = {"extension", "", "Ljava/nio/file/Path;", "getExtension$annotations", "(Ljava/nio/file/Path;)V", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "getInvariantSeparatorsPath", "invariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "getInvariantSeparatorsPathString", "name", "getName$annotations", "getName", "nameWithoutExtension", "getNameWithoutExtension$annotations", "getNameWithoutExtension", "pathString", "getPathString$annotations", "getPathString", "Path", "path", "base", "subpaths", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", "createTempDirectory", "directory", "prefix", "attributes", "Ljava/nio/file/attribute/FileAttribute;", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createTempFile", "suffix", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "fileAttributeViewNotAvailable", "", "attributeViewClass", "Ljava/lang/Class;", "absolute", "absolutePathString", "copyTo", "target", "options", "Ljava/nio/file/CopyOption;", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/CopyOption;)Ljava/nio/file/Path;", "overwrite", "", "createDirectories", "(Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createDirectory", "createFile", "createLinkPointingTo", "createSymbolicLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "deleteExisting", "", "deleteIfExists", "div", "other", "exists", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "fileAttributesView", "V", "Ljava/nio/file/attribute/FileAttributeView;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileAttributeView;", "fileAttributesViewOrNull", "fileSize", "", "fileStore", "Ljava/nio/file/FileStore;", "forEachDirectoryEntry", "glob", "action", "Lkotlin/Function1;", "getAttribute", "", "attribute", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "getLastModifiedTime", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileTime;", "getOwner", "Ljava/nio/file/attribute/UserPrincipal;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/UserPrincipal;", "getPosixFilePermissions", "", "Ljava/nio/file/attribute/PosixFilePermission;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;", "isDirectory", "isExecutable", "isHidden", "isReadable", "isRegularFile", "isSameFileAs", "isSymbolicLink", "isWritable", "listDirectoryEntries", "", "moveTo", "notExists", "readAttributes", "A", "Ljava/nio/file/attribute/BasicFileAttributes;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/BasicFileAttributes;", "", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/util/Map;", "readSymbolicLink", "relativeTo", "relativeToOrNull", "relativeToOrSelf", "setAttribute", "value", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;", "setLastModifiedTime", "setOwner", "setPosixFilePermissions", "toPath", "Ljava/net/URI;", "useDirectoryEntries", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 1, mo27517xs = "kotlin/io/path/PathsKt")
/* renamed from: kotlin.io.path.PathsKt__PathUtilsKt */
/* compiled from: PathUtils.kt */
class PathsKt__PathUtilsKt extends PathsKt__PathReadWriteKt {
    public static /* synthetic */ void getExtension$annotations(Path path) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use invariantSeparatorsPathString property instead.", replaceWith = @ReplaceWith(expression = "invariantSeparatorsPathString", imports = {}))
    public static /* synthetic */ void getInvariantSeparatorsPath$annotations(Path path) {
    }

    public static /* synthetic */ void getInvariantSeparatorsPathString$annotations(Path path) {
    }

    public static /* synthetic */ void getName$annotations(Path path) {
    }

    public static /* synthetic */ void getNameWithoutExtension$annotations(Path path) {
    }

    public static /* synthetic */ void getPathString$annotations(Path path) {
    }

    public static final String getName(Path path) {
        Intrinsics.checkNotNullParameter(path, "$this$name");
        Path fileName = path.getFileName();
        String obj = fileName != null ? fileName.toString() : null;
        return obj != null ? obj : "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r3 = kotlin.text.StringsKt.substringBeforeLast$default((r3 = r3.toString()), com.alibaba.android.arouter.utils.Consts.DOT, (java.lang.String) null, 2, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getNameWithoutExtension(java.nio.file.Path r3) {
        /*
            java.lang.String r0 = "$this$nameWithoutExtension"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.nio.file.Path r3 = r3.getFileName()
            if (r3 == 0) goto L_0x001c
            java.lang.String r3 = r3.toString()
            if (r3 == 0) goto L_0x001c
            r0 = 2
            java.lang.String r1 = "."
            r2 = 0
            java.lang.String r3 = kotlin.text.StringsKt.substringBeforeLast$default((java.lang.String) r3, (java.lang.String) r1, (java.lang.String) r2, (int) r0, (java.lang.Object) r2)
            if (r3 == 0) goto L_0x001c
            goto L_0x001e
        L_0x001c:
            java.lang.String r3 = ""
        L_0x001e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.getNameWithoutExtension(java.nio.file.Path):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        r2 = kotlin.text.StringsKt.substringAfterLast((r2 = r2.toString()), '.', "");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getExtension(java.nio.file.Path r2) {
        /*
            java.lang.String r0 = "$this$extension"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.nio.file.Path r2 = r2.getFileName()
            java.lang.String r0 = ""
            if (r2 == 0) goto L_0x001c
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L_0x001c
            r1 = 46
            java.lang.String r2 = kotlin.text.StringsKt.substringAfterLast((java.lang.String) r2, (char) r1, (java.lang.String) r0)
            if (r2 == 0) goto L_0x001c
            r0 = r2
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.getExtension(java.nio.file.Path):java.lang.String");
    }

    private static final String getPathString(Path path) {
        return path.toString();
    }

    public static final String getInvariantSeparatorsPathString(Path path) {
        Intrinsics.checkNotNullParameter(path, "$this$invariantSeparatorsPathString");
        FileSystem fileSystem = path.getFileSystem();
        Intrinsics.checkNotNullExpressionValue(fileSystem, "fileSystem");
        String separator = fileSystem.getSeparator();
        if (!(!Intrinsics.areEqual((Object) separator, (Object) "/"))) {
            return path.toString();
        }
        String obj = path.toString();
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        return StringsKt.replace$default(obj, separator, "/", false, 4, (Object) null);
    }

    private static final String getInvariantSeparatorsPath(Path path) {
        return PathsKt.getInvariantSeparatorsPathString(path);
    }

    private static final Path absolute(Path path) {
        Path absolutePath = path.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath()");
        return absolutePath;
    }

    private static final String absolutePathString(Path path) {
        return path.toAbsolutePath().toString();
    }

    public static final Path relativeTo(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$relativeTo");
        Intrinsics.checkNotNullParameter(path2, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, path2);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            throw new IllegalArgumentException(Intrinsics.stringPlus(message, "\nthis path: " + path + "\nbase path: " + path2), e);
        }
    }

    public static final Path relativeToOrSelf(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$relativeToOrSelf");
        Intrinsics.checkNotNullParameter(path2, "base");
        Path relativeToOrNull = PathsKt.relativeToOrNull(path, path2);
        return relativeToOrNull != null ? relativeToOrNull : path;
    }

    public static final Path relativeToOrNull(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$relativeToOrNull");
        Intrinsics.checkNotNullParameter(path2, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, path2);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static final Path copyTo(Path path, Path path2, boolean z) throws IOException {
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path copy = Files.copy(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(copy, "Files.copy(this, target, *options)");
        return copy;
    }

    static /* synthetic */ Path copyTo$default(Path path, Path path2, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path copy = Files.copy(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(copy, "Files.copy(this, target, *options)");
        return copy;
    }

    private static final Path copyTo(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        Path copy = Files.copy(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(copy, "Files.copy(this, target, *options)");
        return copy;
    }

    private static final boolean exists(Path path, LinkOption... linkOptionArr) {
        return Files.exists(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final boolean notExists(Path path, LinkOption... linkOptionArr) {
        return Files.notExists(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final boolean isRegularFile(Path path, LinkOption... linkOptionArr) {
        return Files.isRegularFile(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final boolean isDirectory(Path path, LinkOption... linkOptionArr) {
        return Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final boolean isSymbolicLink(Path path) {
        return Files.isSymbolicLink(path);
    }

    private static final boolean isExecutable(Path path) {
        return Files.isExecutable(path);
    }

    private static final boolean isHidden(Path path) throws IOException {
        return Files.isHidden(path);
    }

    private static final boolean isReadable(Path path) {
        return Files.isReadable(path);
    }

    private static final boolean isWritable(Path path) {
        return Files.isWritable(path);
    }

    private static final boolean isSameFileAs(Path path, Path path2) throws IOException {
        return Files.isSameFile(path, path2);
    }

    public static /* synthetic */ List listDirectoryEntries$default(Path path, String str, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = "*";
        }
        return PathsKt.listDirectoryEntries(path, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List<java.nio.file.Path> listDirectoryEntries(java.nio.file.Path r2, java.lang.String r3) throws java.io.IOException {
        /*
            java.lang.String r0 = "$this$listDirectoryEntries"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "glob"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r2, r3)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r0 = r2
            java.nio.file.DirectoryStream r0 = (java.nio.file.DirectoryStream) r0     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ all -> 0x0025 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x0025 }
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r0)     // Catch:{ all -> 0x0025 }
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            return r0
        L_0x0025:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r0 = move-exception
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.listDirectoryEntries(java.nio.file.Path, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r4 != null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <T> T useDirectoryEntries(java.nio.file.Path r4, java.lang.String r5, kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<? extends java.nio.file.Path>, ? extends T> r6) throws java.io.IOException {
        /*
            java.nio.file.DirectoryStream r4 = java.nio.file.Files.newDirectoryStream(r4, r5)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r0 = 0
            r1 = 1
            r2 = r4
            java.nio.file.DirectoryStream r2 = (java.nio.file.DirectoryStream) r2     // Catch:{ all -> 0x0034 }
            java.lang.String r3 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0034 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x0034 }
            kotlin.sequences.Sequence r2 = kotlin.collections.CollectionsKt.asSequence(r2)     // Catch:{ all -> 0x0034 }
            java.lang.Object r6 = r6.invoke(r2)     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r0 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r0 == 0) goto L_0x002a
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
            goto L_0x0030
        L_0x002a:
            if (r4 != 0) goto L_0x002d
            goto L_0x0030
        L_0x002d:
            r4.close()
        L_0x0030:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0034:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r0 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r0 != 0) goto L_0x0046
            if (r4 == 0) goto L_0x0049
            r4.close()     // Catch:{ all -> 0x0049 }
            goto L_0x0049
        L_0x0046:
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
        L_0x0049:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.useDirectoryEntries(java.nio.file.Path, java.lang.String, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (r2 != null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0051, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object useDirectoryEntries$default(java.nio.file.Path r2, java.lang.String r3, kotlin.jvm.functions.Function1 r4, int r5, java.lang.Object r6) throws java.io.IOException {
        /*
            r6 = 1
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0006
            java.lang.String r3 = "*"
        L_0x0006:
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r2, r3)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r5 = 0
            r0 = r2
            java.nio.file.DirectoryStream r0 = (java.nio.file.DirectoryStream) r0     // Catch:{ all -> 0x0039 }
            java.lang.String r1 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ all -> 0x0039 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x0039 }
            kotlin.sequences.Sequence r0 = kotlin.collections.CollectionsKt.asSequence(r0)     // Catch:{ all -> 0x0039 }
            java.lang.Object r4 = r4.invoke(r0)     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 == 0) goto L_0x002f
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            goto L_0x0035
        L_0x002f:
            if (r2 != 0) goto L_0x0032
            goto L_0x0035
        L_0x0032:
            r2.close()
        L_0x0035:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r4
        L_0x0039:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x003b }
        L_0x003b:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 != 0) goto L_0x004b
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ all -> 0x004e }
            goto L_0x004e
        L_0x004b:
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
        L_0x004e:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.useDirectoryEntries$default(java.nio.file.Path, java.lang.String, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        if (r4 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void forEachDirectoryEntry(java.nio.file.Path r4, java.lang.String r5, kotlin.jvm.functions.Function1<? super java.nio.file.Path, kotlin.Unit> r6) throws java.io.IOException {
        /*
            java.nio.file.DirectoryStream r4 = java.nio.file.Files.newDirectoryStream(r4, r5)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r0 = 0
            r1 = 1
            r2 = r4
            java.nio.file.DirectoryStream r2 = (java.nio.file.DirectoryStream) r2     // Catch:{ all -> 0x0040 }
            java.lang.String r3 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0040 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x0040 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0040 }
        L_0x0019:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0040 }
            if (r3 == 0) goto L_0x0027
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0040 }
            r6.invoke(r3)     // Catch:{ all -> 0x0040 }
            goto L_0x0019
        L_0x0027:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r6 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r6 == 0) goto L_0x0036
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
            goto L_0x003c
        L_0x0036:
            if (r4 != 0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            r4.close()
        L_0x003c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return
        L_0x0040:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r0 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r0 != 0) goto L_0x0052
            if (r4 == 0) goto L_0x0055
            r4.close()     // Catch:{ all -> 0x0055 }
            goto L_0x0055
        L_0x0052:
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
        L_0x0055:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.forEachDirectoryEntry(java.nio.file.Path, java.lang.String, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        if (r2 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0057, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void forEachDirectoryEntry$default(java.nio.file.Path r2, java.lang.String r3, kotlin.jvm.functions.Function1 r4, int r5, java.lang.Object r6) throws java.io.IOException {
        /*
            r6 = 1
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0006
            java.lang.String r3 = "*"
        L_0x0006:
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r2, r3)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r5 = 0
            r0 = r2
            java.nio.file.DirectoryStream r0 = (java.nio.file.DirectoryStream) r0     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ all -> 0x0045 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x0045 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0045 }
        L_0x001e:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x002c
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0045 }
            r4.invoke(r1)     // Catch:{ all -> 0x0045 }
            goto L_0x001e
        L_0x002c:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r4 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r4 == 0) goto L_0x003b
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            goto L_0x0041
        L_0x003b:
            if (r2 != 0) goto L_0x003e
            goto L_0x0041
        L_0x003e:
            r2.close()
        L_0x0041:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return
        L_0x0045:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 != 0) goto L_0x0057
            if (r2 == 0) goto L_0x005a
            r2.close()     // Catch:{ all -> 0x005a }
            goto L_0x005a
        L_0x0057:
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
        L_0x005a:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathUtilsKt.forEachDirectoryEntry$default(java.nio.file.Path, java.lang.String, kotlin.jvm.functions.Function1, int, java.lang.Object):void");
    }

    private static final long fileSize(Path path) throws IOException {
        return Files.size(path);
    }

    private static final void deleteExisting(Path path) throws IOException {
        Files.delete(path);
    }

    private static final boolean deleteIfExists(Path path) throws IOException {
        return Files.deleteIfExists(path);
    }

    private static final Path createDirectory(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path createDirectory = Files.createDirectory(path, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createDirectory, "Files.createDirectory(this, *attributes)");
        return createDirectory;
    }

    private static final Path createDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path createDirectories = Files.createDirectories(path, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createDirectories, "Files.createDirectories(this, *attributes)");
        return createDirectories;
    }

    private static final Path moveTo(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        Path move = Files.move(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(move, "Files.move(this, target, *options)");
        return move;
    }

    private static final Path moveTo(Path path, Path path2, boolean z) throws IOException {
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path move = Files.move(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(move, "Files.move(this, target, *options)");
        return move;
    }

    static /* synthetic */ Path moveTo$default(Path path, Path path2, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path move = Files.move(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(move, "Files.move(this, target, *options)");
        return move;
    }

    private static final FileStore fileStore(Path path) throws IOException {
        FileStore fileStore = Files.getFileStore(path);
        Intrinsics.checkNotNullExpressionValue(fileStore, "Files.getFileStore(this)");
        return fileStore;
    }

    private static final Object getAttribute(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        return Files.getAttribute(path, str, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final Path setAttribute(Path path, String str, Object obj, LinkOption... linkOptionArr) throws IOException {
        Path attribute = Files.setAttribute(path, str, obj, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(attribute, "Files.setAttribute(this,…tribute, value, *options)");
        return attribute;
    }

    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesView(Path path, LinkOption... linkOptionArr) {
        Intrinsics.reifiedOperationMarker(4, "V");
        V fileAttributeView = Files.getFileAttributeView(path, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        if (fileAttributeView != null) {
            return fileAttributeView;
        }
        Intrinsics.reifiedOperationMarker(4, "V");
        PathsKt.fileAttributeViewNotAvailable(path, FileAttributeView.class);
        throw new KotlinNothingValueException();
    }

    public static final Void fileAttributeViewNotAvailable(Path path, Class<?> cls) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(cls, "attributeViewClass");
        throw new UnsupportedOperationException("The desired attribute view type " + cls + " is not available for the file " + path + '.');
    }

    private static final /* synthetic */ <A extends BasicFileAttributes> A readAttributes(Path path, LinkOption... linkOptionArr) throws IOException {
        Intrinsics.reifiedOperationMarker(4, "A");
        A readAttributes = Files.readAttributes(path, BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(readAttributes, "Files.readAttributes(thi… A::class.java, *options)");
        return readAttributes;
    }

    private static final Map<String, Object> readAttributes(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        Map<String, Object> readAttributes = Files.readAttributes(path, str, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(readAttributes, "Files.readAttributes(this, attributes, *options)");
        return readAttributes;
    }

    private static final FileTime getLastModifiedTime(Path path, LinkOption... linkOptionArr) throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(lastModifiedTime, "Files.getLastModifiedTime(this, *options)");
        return lastModifiedTime;
    }

    private static final Path setLastModifiedTime(Path path, FileTime fileTime) throws IOException {
        Path lastModifiedTime = Files.setLastModifiedTime(path, fileTime);
        Intrinsics.checkNotNullExpressionValue(lastModifiedTime, "Files.setLastModifiedTime(this, value)");
        return lastModifiedTime;
    }

    private static final UserPrincipal getOwner(Path path, LinkOption... linkOptionArr) throws IOException {
        return Files.getOwner(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final Path setOwner(Path path, UserPrincipal userPrincipal) throws IOException {
        Path owner = Files.setOwner(path, userPrincipal);
        Intrinsics.checkNotNullExpressionValue(owner, "Files.setOwner(this, value)");
        return owner;
    }

    private static final Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... linkOptionArr) throws IOException {
        Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(posixFilePermissions, "Files.getPosixFilePermissions(this, *options)");
        return posixFilePermissions;
    }

    private static final Path setPosixFilePermissions(Path path, Set<? extends PosixFilePermission> set) throws IOException {
        Path posixFilePermissions = Files.setPosixFilePermissions(path, set);
        Intrinsics.checkNotNullExpressionValue(posixFilePermissions, "Files.setPosixFilePermissions(this, value)");
        return posixFilePermissions;
    }

    private static final Path createLinkPointingTo(Path path, Path path2) throws IOException {
        Path createLink = Files.createLink(path, path2);
        Intrinsics.checkNotNullExpressionValue(createLink, "Files.createLink(this, target)");
        return createLink;
    }

    private static final Path createSymbolicLinkPointingTo(Path path, Path path2, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path createSymbolicLink = Files.createSymbolicLink(path, path2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createSymbolicLink, "Files.createSymbolicLink…his, target, *attributes)");
        return createSymbolicLink;
    }

    private static final Path readSymbolicLink(Path path) throws IOException {
        Path readSymbolicLink = Files.readSymbolicLink(path);
        Intrinsics.checkNotNullExpressionValue(readSymbolicLink, "Files.readSymbolicLink(this)");
        return readSymbolicLink;
    }

    private static final Path createFile(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path createFile = Files.createFile(path, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createFile, "Files.createFile(this, *attributes)");
        return createFile;
    }

    static /* synthetic */ Path createTempFile$default(String str, String str2, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        Path createTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createTempFile, "Files.createTempFile(prefix, suffix, *attributes)");
        return createTempFile;
    }

    private static final Path createTempFile(String str, String str2, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path createTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createTempFile, "Files.createTempFile(prefix, suffix, *attributes)");
        return createTempFile;
    }

    public static /* synthetic */ Path createTempFile$default(Path path, String str, String str2, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return PathsKt.createTempFile(path, str, str2, fileAttributeArr);
    }

    public static final Path createTempFile(Path path, String str, String str2, FileAttribute<?>... fileAttributeArr) throws IOException {
        Intrinsics.checkNotNullParameter(fileAttributeArr, "attributes");
        if (path != null) {
            Path createTempFile = Files.createTempFile(path, str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
            Intrinsics.checkNotNullExpressionValue(createTempFile, "Files.createTempFile(dir…fix, suffix, *attributes)");
            return createTempFile;
        }
        Path createTempFile2 = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createTempFile2, "Files.createTempFile(prefix, suffix, *attributes)");
        return createTempFile2;
    }

    static /* synthetic */ Path createTempDirectory$default(String str, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        Path createTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createTempDirectory, "Files.createTempDirectory(prefix, *attributes)");
        return createTempDirectory;
    }

    private static final Path createTempDirectory(String str, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path createTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createTempDirectory, "Files.createTempDirectory(prefix, *attributes)");
        return createTempDirectory;
    }

    public static /* synthetic */ Path createTempDirectory$default(Path path, String str, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        return PathsKt.createTempDirectory(path, str, fileAttributeArr);
    }

    public static final Path createTempDirectory(Path path, String str, FileAttribute<?>... fileAttributeArr) throws IOException {
        Intrinsics.checkNotNullParameter(fileAttributeArr, "attributes");
        if (path != null) {
            Path createTempDirectory = Files.createTempDirectory(path, str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
            Intrinsics.checkNotNullExpressionValue(createTempDirectory, "Files.createTempDirector…ory, prefix, *attributes)");
            return createTempDirectory;
        }
        Path createTempDirectory2 = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        Intrinsics.checkNotNullExpressionValue(createTempDirectory2, "Files.createTempDirectory(prefix, *attributes)");
        return createTempDirectory2;
    }

    private static final Path div(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$div");
        Path resolve = path.resolve(path2);
        Intrinsics.checkNotNullExpressionValue(resolve, "this.resolve(other)");
        return resolve;
    }

    private static final Path div(Path path, String str) {
        Intrinsics.checkNotNullParameter(path, "$this$div");
        Path resolve = path.resolve(str);
        Intrinsics.checkNotNullExpressionValue(resolve, "this.resolve(other)");
        return resolve;
    }

    private static final Path Path(String str) {
        Path path = Paths.get(str, new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "Paths.get(path)");
        return path;
    }

    private static final Path Path(String str, String... strArr) {
        Path path = Paths.get(str, (String[]) Arrays.copyOf(strArr, strArr.length));
        Intrinsics.checkNotNullExpressionValue(path, "Paths.get(base, *subpaths)");
        return path;
    }

    private static final Path toPath(URI uri) {
        Path path = Paths.get(uri);
        Intrinsics.checkNotNullExpressionValue(path, "Paths.get(this)");
        return path;
    }
}
