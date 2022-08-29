package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, mo27512d2 = {"androidx/core/animation/AnimatorKt$addPauseListener$listener$1", "Landroid/animation/Animator$AnimatorPauseListener;", "onAnimationPause", "", "animator", "Landroid/animation/Animator;", "onAnimationResume", "core-ktx_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Animator.kt */
public final class AnimatorKt$addPauseListener$listener$1 implements Animator.AnimatorPauseListener {
    final /* synthetic */ Function1<Animator, Unit> $onPause;
    final /* synthetic */ Function1<Animator, Unit> $onResume;

    public AnimatorKt$addPauseListener$listener$1(Function1<? super Animator, Unit> function1, Function1<? super Animator, Unit> function12) {
        this.$onPause = function1;
        this.$onResume = function12;
    }

    public void onAnimationPause(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        this.$onPause.invoke(animator);
    }

    public void onAnimationResume(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        this.$onResume.invoke(animator);
    }
}
