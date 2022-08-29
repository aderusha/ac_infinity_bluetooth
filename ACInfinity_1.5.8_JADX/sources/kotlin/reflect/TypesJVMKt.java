package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

@Metadata(mo27511d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\"\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0003\u001a\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0003\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00078BX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\b\u001a\u0004\b\u0005\u0010\t¨\u0006\u0015"}, mo27512d2 = {"javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType$annotations", "(Lkotlin/reflect/KType;)V", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "Lkotlin/reflect/KTypeProjection;", "(Lkotlin/reflect/KTypeProjection;)V", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/reflect/Type;", "createPossiblyInnerType", "jClass", "Ljava/lang/Class;", "arguments", "", "typeToString", "", "type", "computeJavaType", "forceWrapper", "", "kotlin-stdlib"}, mo27513k = 2, mo27514mv = {1, 5, 1})
/* compiled from: TypesJVM.kt */
public final class TypesJVMKt {

    @Metadata(mo27513k = 3, mo27514mv = {1, 5, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[KVariance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[KVariance.IN.ordinal()] = 1;
            iArr[KVariance.INVARIANT.ordinal()] = 2;
            iArr[KVariance.OUT.ordinal()] = 3;
            int[] iArr2 = new int[KVariance.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[KVariance.INVARIANT.ordinal()] = 1;
            iArr2[KVariance.IN.ordinal()] = 2;
            iArr2[KVariance.OUT.ordinal()] = 3;
        }
    }

    public static /* synthetic */ void getJavaType$annotations(KType kType) {
    }

    private static /* synthetic */ void getJavaType$annotations(KTypeProjection kTypeProjection) {
    }

    public static final Type getJavaType(KType kType) {
        Type javaType;
        Intrinsics.checkNotNullParameter(kType, "$this$javaType");
        if (!(kType instanceof KTypeBase) || (javaType = ((KTypeBase) kType).getJavaType()) == null) {
            return computeJavaType$default(kType, false, 1, (Object) null);
        }
        return javaType;
    }

    static /* synthetic */ Type computeJavaType$default(KType kType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return computeJavaType(kType, z);
    }

    /* access modifiers changed from: private */
    public static final Type computeJavaType(KType kType, boolean z) {
        int i;
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KTypeParameter) {
            return new TypeVariableImpl((KTypeParameter) classifier);
        }
        if (classifier instanceof KClass) {
            KClass kClass = (KClass) classifier;
            Class javaObjectType = z ? JvmClassMappingKt.getJavaObjectType(kClass) : JvmClassMappingKt.getJavaClass(kClass);
            List<KTypeProjection> arguments = kType.getArguments();
            if (arguments.isEmpty()) {
                return javaObjectType;
            }
            if (!javaObjectType.isArray()) {
                return createPossiblyInnerType(javaObjectType, arguments);
            }
            Class<?> componentType = javaObjectType.getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "jClass.componentType");
            if (componentType.isPrimitive()) {
                return javaObjectType;
            }
            KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.singleOrNull(arguments);
            if (kTypeProjection != null) {
                KVariance component1 = kTypeProjection.component1();
                KType component2 = kTypeProjection.component2();
                if (component1 == null || (i = WhenMappings.$EnumSwitchMapping$0[component1.ordinal()]) == 1) {
                    return javaObjectType;
                }
                if (i == 2 || i == 3) {
                    Intrinsics.checkNotNull(component2);
                    Type computeJavaType$default = computeJavaType$default(component2, false, 1, (Object) null);
                    Type type = javaObjectType;
                    if (!(computeJavaType$default instanceof Class)) {
                        type = new GenericArrayTypeImpl(computeJavaType$default);
                    }
                    return type;
                }
                throw new NoWhenBranchMatchedException();
            }
            throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + kType);
        }
        throw new UnsupportedOperationException("Unsupported type classifier: " + kType);
    }

    private static final Type createPossiblyInnerType(Class<?> cls, List<KTypeProjection> list) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            Iterable<KTypeProjection> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KTypeProjection javaType : iterable) {
                arrayList.add(getJavaType(javaType));
            }
            return new ParameterizedTypeImpl(cls, (Type) null, (List) arrayList);
        } else if (Modifier.isStatic(cls.getModifiers())) {
            Type type = declaringClass;
            Iterable<KTypeProjection> iterable2 = list;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (KTypeProjection javaType2 : iterable2) {
                arrayList2.add(getJavaType(javaType2));
            }
            return new ParameterizedTypeImpl(cls, type, (List) arrayList2);
        } else {
            int length = cls.getTypeParameters().length;
            Type createPossiblyInnerType = createPossiblyInnerType(declaringClass, list.subList(length, list.size()));
            Iterable<KTypeProjection> subList = list.subList(0, length);
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList, 10));
            for (KTypeProjection javaType3 : subList) {
                arrayList3.add(getJavaType(javaType3));
            }
            return new ParameterizedTypeImpl(cls, createPossiblyInnerType, (List) arrayList3);
        }
    }

    private static final Type getJavaType(KTypeProjection kTypeProjection) {
        KVariance variance = kTypeProjection.getVariance();
        if (variance == null) {
            return WildcardTypeImpl.Companion.getSTAR();
        }
        KType type = kTypeProjection.getType();
        Intrinsics.checkNotNull(type);
        int i = WhenMappings.$EnumSwitchMapping$1[variance.ordinal()];
        if (i == 1) {
            return computeJavaType(type, true);
        }
        if (i == 2) {
            return new WildcardTypeImpl((Type) null, computeJavaType(type, true));
        }
        if (i == 3) {
            return new WildcardTypeImpl(computeJavaType(type, true), (Type) null);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* access modifiers changed from: private */
    public static final String typeToString(Type type) {
        String str;
        if (!(type instanceof Class)) {
            return type.toString();
        }
        Class cls = (Class) type;
        if (cls.isArray()) {
            Sequence generateSequence = SequencesKt.generateSequence(type, TypesJVMKt$typeToString$unwrap$1.INSTANCE);
            str = ((Class) SequencesKt.last(generateSequence)).getName() + StringsKt.repeat("[]", SequencesKt.count(generateSequence));
        } else {
            str = cls.getName();
        }
        Intrinsics.checkNotNullExpressionValue(str, "if (type.isArray) {\n    …\n        } else type.name");
        return str;
    }
}
