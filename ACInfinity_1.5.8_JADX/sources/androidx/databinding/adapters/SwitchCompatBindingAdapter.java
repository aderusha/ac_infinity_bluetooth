package androidx.databinding.adapters;

import android.content.Context;
import androidx.appcompat.widget.SwitchCompat;

public class SwitchCompatBindingAdapter {
    public static void setSwitchTextAppearance(SwitchCompat switchCompat, int i) {
        switchCompat.setSwitchTextAppearance((Context) null, i);
    }
}
