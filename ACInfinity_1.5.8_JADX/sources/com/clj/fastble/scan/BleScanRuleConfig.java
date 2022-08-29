package com.clj.fastble.scan;

import java.util.UUID;

public class BleScanRuleConfig {
    /* access modifiers changed from: private */
    public boolean mAutoConnect = false;
    /* access modifiers changed from: private */
    public boolean mContinuous = false;
    /* access modifiers changed from: private */
    public String mDeviceMac = null;
    /* access modifiers changed from: private */
    public String[] mDeviceNames = null;
    /* access modifiers changed from: private */
    public boolean mFuzzy = false;
    /* access modifiers changed from: private */
    public boolean mNeedConnect = false;
    /* access modifiers changed from: private */
    public long mScanTimeOut = 10000;
    /* access modifiers changed from: private */
    public UUID[] mServiceUuids = null;

    public UUID[] getServiceUuids() {
        return this.mServiceUuids;
    }

    public String[] getDeviceNames() {
        return this.mDeviceNames;
    }

    public String getDeviceMac() {
        return this.mDeviceMac;
    }

    public boolean isAutoConnect() {
        return this.mAutoConnect;
    }

    public boolean isContinuous() {
        return this.mContinuous;
    }

    public boolean isNeedConnect() {
        return this.mNeedConnect;
    }

    public boolean isFuzzy() {
        return this.mFuzzy;
    }

    public long getScanTimeOut() {
        return this.mScanTimeOut;
    }

    public static class Builder {
        private boolean mAutoConnect = false;
        private boolean mContinuous = false;
        private String mDeviceMac = null;
        private String[] mDeviceNames = null;
        private boolean mFuzzy = false;
        private boolean mNeedConnect = false;
        private UUID[] mServiceUuids = null;
        private long mTimeOut = 10000;

        public Builder setServiceUuids(UUID[] uuidArr) {
            this.mServiceUuids = uuidArr;
            return this;
        }

        public Builder setDeviceName(boolean z, String... strArr) {
            this.mFuzzy = z;
            this.mDeviceNames = strArr;
            return this;
        }

        public Builder setDeviceMac(String str) {
            this.mDeviceMac = str;
            return this;
        }

        public Builder setAutoConnect(boolean z) {
            this.mAutoConnect = z;
            return this;
        }

        public Builder setContinuous(boolean z) {
            this.mContinuous = z;
            return this;
        }

        public Builder setNeedConnect(boolean z) {
            this.mNeedConnect = z;
            return this;
        }

        public Builder setScanTimeOut(long j) {
            this.mTimeOut = j;
            return this;
        }

        /* access modifiers changed from: package-private */
        public void applyConfig(BleScanRuleConfig bleScanRuleConfig) {
            UUID[] unused = bleScanRuleConfig.mServiceUuids = this.mServiceUuids;
            String[] unused2 = bleScanRuleConfig.mDeviceNames = this.mDeviceNames;
            String unused3 = bleScanRuleConfig.mDeviceMac = this.mDeviceMac;
            boolean unused4 = bleScanRuleConfig.mAutoConnect = this.mAutoConnect;
            boolean unused5 = bleScanRuleConfig.mFuzzy = this.mFuzzy;
            long unused6 = bleScanRuleConfig.mScanTimeOut = this.mTimeOut;
            boolean unused7 = bleScanRuleConfig.mContinuous = this.mContinuous;
            boolean unused8 = bleScanRuleConfig.mNeedConnect = this.mNeedConnect;
        }

        public BleScanRuleConfig build() {
            BleScanRuleConfig bleScanRuleConfig = new BleScanRuleConfig();
            applyConfig(bleScanRuleConfig);
            return bleScanRuleConfig;
        }
    }
}
