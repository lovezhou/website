/**
 * @(#)CertificateVerifier.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.security.impl;

import java.security.cert.X509Certificate;

import com.jessrun.common.security.Verifier;

/**
 * 证书认证校验器
 * 
 * @author luoyifan
 * @version 1.0,2011-3-10
 */
public class CertificateVerifier implements Verifier {

    private X509Certificate cert;

    public X509Certificate getCert() {
        return cert;
    }

    public void setCert(X509Certificate cert) {
        this.cert = cert;
    }

    public CertificateVerifier(X509Certificate cert){
        this.cert = cert;
    }
}
