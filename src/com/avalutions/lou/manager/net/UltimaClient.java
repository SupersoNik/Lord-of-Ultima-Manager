package com.avalutions.lou.manager.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class UltimaClient {

    private static UltimaClient _instance;

    public static UltimaClient getInstance() {
        if (_instance == null) {
            _instance = new UltimaClient();
        }
        return _instance;
    }

    public static JSONObject[] getJSONObjectArray(JSONArray values) throws JSONException {
        JSONObject[] result = new JSONObject[values.length()];
        for(int i = 0; i < values.length(); i++) {
            result[i] = values.getJSONObject(i);
        }
        return result;
    }

    private HttpClient _client;
    private CookieStore _cookies;
    private HttpContext _context;

    public UltimaClient() {
        _cookies = new BasicCookieStore();
        _context = new BasicHttpContext();
        _context.setAttribute(ClientContext.COOKIE_STORE, _cookies);
        HttpParams parameters = new BasicHttpParams();
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        sslSocketFactory
                .setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        ClientConnectionManager manager = new ThreadSafeClientConnManager(
                parameters, schemeRegistry);
        _client = new DefaultHttpClient(manager, parameters);
    }
    
    private String getURL(Session session, String action) {
        return "http://prodgame" + session.game + ".lordofultima.com/" + session.instance + "/Presentation/Service.svc/ajaxEndpoint/" + action;
    }

    public String get(String url) {
        final HttpGet httpget = new HttpGet(url);

        // sending the request and retrieving the response:
        HttpResponse response = null;
        try {
            response = _client.execute(httpget, _context);
            return convertStreamToString(response.getEntity().getContent());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String post(Session session, String action, String content) {
        final HttpPost httppost = new HttpPost(getURL(session, action));
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Content-type", "application/json");
        try {
            httppost.setEntity(new StringEntity(content));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // sending the request and retrieving the response:
        HttpResponse response = null;
        try {
            response = _client.execute(httppost, _context);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String post(String url, List<BasicNameValuePair> map) {
        final HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity p_entity = null;
        try {
            p_entity = new UrlEncodedFormEntity(map, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        httppost.setEntity(p_entity);

        // sending the request and retrieving the response:
        HttpResponse response = null;
        try {
            response = _client.execute(httppost, _context);
            return convertStreamToString(response.getEntity().getContent());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
