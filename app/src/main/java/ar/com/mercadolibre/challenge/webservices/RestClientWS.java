package ar.com.mercadolibre.challenge.webservices;


import android.content.Context;
import com.loopj.android.http.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.HttpEntity;


public class RestClientWS {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context baseContext, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.setTimeout(6000);
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.setTimeout(6000);
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public static void post(Context base, String url, HttpEntity entity, AsyncHttpResponseHandler responseHandler) {
        try {
            client.setTimeout(6000);
            client.post(base, getAbsoluteUrl(url), entity, "application/json", responseHandler);
        }
        catch (Exception e) {
            throw e;
        }
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return URLClientWS.BASE_URL + relativeUrl;
    }

}
