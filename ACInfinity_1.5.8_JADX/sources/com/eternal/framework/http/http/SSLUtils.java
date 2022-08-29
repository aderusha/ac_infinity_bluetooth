package com.eternal.framework.http.http;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SSLUtils {
    public static HostnameVerifier UnSafeHostnameVerifier = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };
    public static X509TrustManager UnSafeTrustManager = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;
    }

    public static SSLParams getSslSocketFactory() {
        return getSslSocketFactoryBase((X509TrustManager) null, (InputStream) null, (String) null, new InputStream[0]);
    }

    public static SSLParams getSslSocketFactory(X509TrustManager x509TrustManager) {
        return getSslSocketFactoryBase(x509TrustManager, (InputStream) null, (String) null, new InputStream[0]);
    }

    public static SSLParams getSslSocketFactory(InputStream... inputStreamArr) {
        return getSslSocketFactoryBase((X509TrustManager) null, (InputStream) null, (String) null, inputStreamArr);
    }

    public static SSLParams getSslSocketFactory(InputStream inputStream, String str, InputStream... inputStreamArr) {
        return getSslSocketFactoryBase((X509TrustManager) null, inputStream, str, inputStreamArr);
    }

    public static SSLParams getSslSocketFactory(InputStream inputStream, String str, X509TrustManager x509TrustManager) {
        return getSslSocketFactoryBase(x509TrustManager, inputStream, str, new InputStream[0]);
    }

    private static SSLParams getSslSocketFactoryBase(X509TrustManager x509TrustManager, InputStream inputStream, String str, InputStream... inputStreamArr) {
        SSLParams sSLParams = new SSLParams();
        try {
            KeyManager[] prepareKeyManager = prepareKeyManager(inputStream, str);
            TrustManager[] prepareTrustManager = prepareTrustManager(inputStreamArr);
            if (x509TrustManager == null) {
                if (prepareTrustManager != null) {
                    x509TrustManager = chooseTrustManager(prepareTrustManager);
                } else {
                    x509TrustManager = UnSafeTrustManager;
                }
            }
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(prepareKeyManager, new TrustManager[]{x509TrustManager}, (SecureRandom) null);
            sSLParams.sSLSocketFactory = instance.getSocketFactory();
            sSLParams.trustManager = x509TrustManager;
            return sSLParams;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyManagementException e2) {
            throw new AssertionError(e2);
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream inputStream, String str) {
        if (!(inputStream == null || str == null)) {
            try {
                KeyStore instance = KeyStore.getInstance("BKS");
                instance.load(inputStream, str.toCharArray());
                KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                instance2.init(instance, str.toCharArray());
                return instance2.getKeyManagers();
            } catch (Exception e) {
                Log.e("ssl", e.getMessage());
            }
        }
        return null;
    }

    private static TrustManager[] prepareTrustManager(InputStream... inputStreamArr) {
        if (inputStreamArr != null && inputStreamArr.length > 0) {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
                instance2.load((KeyStore.LoadStoreParameter) null);
                int length = inputStreamArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    InputStream inputStream = inputStreamArr[i];
                    int i3 = i2 + 1;
                    instance2.setCertificateEntry(Integer.toString(i2), instance.generateCertificate(inputStream));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            Log.e("ssl", e.getMessage());
                        }
                    }
                    i++;
                    i2 = i3;
                }
                TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance3.init(instance2);
                return instance3.getTrustManagers();
            } catch (Exception e2) {
                Log.e("ssl", e2.getMessage());
            }
        }
        return null;
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (X509TrustManager x509TrustManager : trustManagerArr) {
            if (x509TrustManager instanceof X509TrustManager) {
                return x509TrustManager;
            }
        }
        return null;
    }
}
