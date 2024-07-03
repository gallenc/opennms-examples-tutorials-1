package org.ni2.v01.api.utils;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * This dumb X509TrustManager trusts all certificate. TThis SHOULD NOT be used in Production. 
 */
public class BlindTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain,
            String authType) throws java.security.cert.CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain,
            String authType) throws java.security.cert.CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
