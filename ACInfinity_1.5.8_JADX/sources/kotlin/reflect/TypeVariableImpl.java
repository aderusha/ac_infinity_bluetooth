package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0011\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\b\b\u0000\u0010\u0010*\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0012¢\u0006\u0002\u0010\u0013J\u0011\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\fH\u0016¢\u0006\u0002\u0010\u0017J\u0011\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u0019\u001a\u00020\u0002H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, mo27512d2 = {"Lkotlin/reflect/TypeVariableImpl;", "Ljava/lang/reflect/TypeVariable;", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/TypeImpl;", "typeParameter", "Lkotlin/reflect/KTypeParameter;", "(Lkotlin/reflect/KTypeParameter;)V", "equals", "", "other", "", "getAnnotatedBounds", "", "", "()[Ljava/lang/annotation/Annotation;", "getAnnotation", "T", "annotationClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "getAnnotations", "getBounds", "Ljava/lang/reflect/Type;", "()[Ljava/lang/reflect/Type;", "getDeclaredAnnotations", "getGenericDeclaration", "getName", "", "getTypeName", "hashCode", "", "toString", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* compiled from: TypesJVM.kt */
final class TypeVariableImpl implements TypeVariable<GenericDeclaration>, TypeImpl {
    private final KTypeParameter typeParameter;

    public final Annotation[] getAnnotatedBounds() {
        return new Annotation[0];
    }

    public final <T extends Annotation> T getAnnotation(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "annotationClass");
        return null;
    }

    public final Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    public final Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    public TypeVariableImpl(KTypeParameter kTypeParameter) {
        Intrinsics.checkNotNullParameter(kTypeParameter, "typeParameter");
        this.typeParameter = kTypeParameter;
    }

    public String getName() {
        return this.typeParameter.getName();
    }

    public GenericDeclaration getGenericDeclaration() {
        throw new NotImplementedError("An operation is not implemented: " + ("getGenericDeclaration() is not yet supported for type variables created from KType: " + this.typeParameter));
    }

    public Type[] getBounds() {
        Iterable<KType> upperBounds = this.typeParameter.getUpperBounds();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBounds, 10));
        for (KType access$computeJavaType : upperBounds) {
            arrayList.add(TypesJVMKt.computeJavaType(access$computeJavaType, true));
        }
        Object[] array = ((List) arrayList).toArray(new Type[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        return (Type[]) array;
    }

    public String getTypeName() {
        return getName();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) obj;
            return Intrinsics.areEqual((Object) getName(), (Object) typeVariable.getName()) && Intrinsics.areEqual((Object) getGenericDeclaration(), (Object) typeVariable.getGenericDeclaration());
        }
    }

    public int hashCode() {
        return getName().hashCode() ^ getGenericDeclaration().hashCode();
    }

    public String toString() {
        return getTypeName();
    }
}
