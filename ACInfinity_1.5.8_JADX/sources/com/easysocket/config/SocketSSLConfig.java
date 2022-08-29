package com.easysocket.config;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SocketSSLConfig {
    /* access modifiers changed from: private */
    public SSLSocketFactory mCustomSSLFactory;
    /* access modifiers changed from: private */
    public KeyManager[] mKeyManagers;
    /* access modifiers changed from: private */
    public String mProtocol;
    /* access modifiers changed from: private */
    public TrustManager[] mTrustManagers;

    private SocketSSLConfig() {
    }

    public static class Builder {
        private SocketSSLConfig mConfig = new SocketSSLConfig();

        public Builder setProtocol(String str) {
            String unused = this.mConfig.mProtocol = str;
            return this;
        }

        public Builder setTrustManagers(TrustManager[] trustManagerArr) {
            TrustManager[] unused = this.mConfig.mTrustManagers = trustManagerArr;
            return this;
        }

        public Builder setKeyManagers(KeyManager[] keyManagerArr) {
            KeyManager[] unused = this.mConfig.mKeyManagers = keyManagerArr;
            return this;
        }

        public Builder setCustomSSLFactory(SSLSocketFactory sSLSocketFactory) {
            SSLSocketFactory unused = this.mConfig.mCustomSSLFactory = sSLSocketFactory;
            return this;
        }

        public SocketSSLConfig build() {
            return this.mConfig;
        }
    }

    public KeyManager[] getKeyManagers() {
        return this.mKeyManagers;
    }

    public String getProtocol() {
        return this.mProtocol;
    }

    public TrustManager[] getTrustManagers() {
        return this.mTrustManagers;
    }

    public SSLSocketFactory getCustomSSLFactory() {
        return this.mCustomSSLFactory;
    }
}
