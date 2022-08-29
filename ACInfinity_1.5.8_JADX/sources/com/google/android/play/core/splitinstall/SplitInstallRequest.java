package com.google.android.play.core.splitinstall;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class SplitInstallRequest {
    private final List zza;
    private final List zzb;

    /* synthetic */ SplitInstallRequest(Builder builder, zzaj zzaj) {
        this.zza = new ArrayList(builder.zza);
        this.zzb = new ArrayList(builder.zzb);
    }

    public static Builder newBuilder() {
        return new Builder((zzai) null);
    }

    public List<Locale> getLanguages() {
        return this.zzb;
    }

    public List<String> getModuleNames() {
        return this.zza;
    }

    public String toString() {
        return String.format("SplitInstallRequest{modulesNames=%s,languages=%s}", new Object[]{this.zza, this.zzb});
    }

    /* compiled from: com.google.android.play:core@@1.10.3 */
    public static class Builder {
        /* access modifiers changed from: private */
        public final List zza = new ArrayList();
        /* access modifiers changed from: private */
        public final List zzb = new ArrayList();

        private Builder() {
        }

        public Builder addLanguage(Locale locale) {
            this.zzb.add(locale);
            return this;
        }

        public Builder addModule(String str) {
            this.zza.add(str);
            return this;
        }

        public SplitInstallRequest build() {
            return new SplitInstallRequest(this, (zzaj) null);
        }

        /* synthetic */ Builder(zzai zzai) {
        }
    }
}
