package com.avalutions.lou.manager.net;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author olamy
 * @version $Id: EasyX509TrustManager.java 765355 2009-04-15 20:59:07Z evenisse $
 * @since 1.2.3
 */
public class EasyX509TrustManager
    implements X509TrustManager
{
	private static TrustManager[] trustManagers; 
    private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {}; 
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { 
    } 
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { 
    } 
    public boolean isClientTrusted(X509Certificate[] chain) { 
            return true; 
    } 
    public boolean isServerTrusted(X509Certificate[] chain) { 
            return true; 
    } 
    public X509Certificate[] getAcceptedIssuers() { 
            return _AcceptedIssuers; 
    } 
    public static void allowAllSSL() { 
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){

				public boolean verify(String arg0, SSLSession arg1) {
					// TODO Auto-generated method stub
					return true;
				}
            	
            }); 
            SSLContext context = null; 
            if (trustManagers == null) { 
                    trustManagers = new TrustManager[] { new EasyX509TrustManager() }; 
            } 
            try { 
                    context = SSLContext.getInstance("TLS"); 
                    context.init(null, trustManagers, new SecureRandom()); 
            } catch (NoSuchAlgorithmException e) { 
                    e.printStackTrace(); 
            } catch (KeyManagementException e) { 
                    e.printStackTrace(); 
            } 
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory()); 
    }

}