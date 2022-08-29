package kotlin.jvm.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.reflect.KDeclarationContainer;

@Metadata(mo27511d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\n"}, mo27512d2 = {"Lkotlin/jvm/internal/MutableLocalVariableReference;", "Lkotlin/jvm/internal/MutablePropertyReference0;", "()V", "get", "", "getOwner", "Lkotlin/reflect/KDeclarationContainer;", "set", "", "value", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* compiled from: localVariableReferences.kt */
public class MutableLocalVariableReference extends MutablePropertyReference0 {
    public KDeclarationContainer getOwner() {
        Void unused = LocalVariableReferencesKt.notSupportedError();
        throw new KotlinNothingValueException();
    }

    public Object get() {
        Void unused = LocalVariableReferencesKt.notSupportedError();
        throw new KotlinNothingValueException();
    }

    public void set(Object obj) {
        Void unused = LocalVariableReferencesKt.notSupportedError();
        throw new KotlinNothingValueException();
    }
}
