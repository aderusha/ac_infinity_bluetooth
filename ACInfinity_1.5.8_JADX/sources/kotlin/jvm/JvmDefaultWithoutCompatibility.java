package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

@Target({ElementType.TYPE})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Metadata(mo27511d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002"}, mo27512d2 = {"Lkotlin/jvm/JvmDefaultWithoutCompatibility;", "", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: JvmDefault.kt */
public @interface JvmDefaultWithoutCompatibility {
}
