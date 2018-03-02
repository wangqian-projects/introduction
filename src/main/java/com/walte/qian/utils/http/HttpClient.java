package com.walte.qian.utils.http;

import com.walte.qian.utils.io.IoTool;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * <p>ClassName: HttpClient</p>
 * <p>Description: HTTP通信工具</p>
 * <p>
 * <!-- Maven HTTP-->
 * <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpclient</artifactId>
 * <version>4.5.5</version>
 * </dependency>
 * <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpcore -->
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpcore</artifactId>
 * <version>4.4.9</version>
 * </dependency>
 * <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime -->
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpmime</artifactId>
 * <version>4.5.5</version>
 * </dependency>
 *
 * @author wangqian
 * @date 2018-03-01 11:50
 */
public class HttpClient {

    private int getCode;

    private int postCode;

    private RequestConfig config;

    private final static String DEFAULT_SCHEME_NAME = "http";

    private final static String DEFAULT_CHARSET = "UTF-8";

    private Header[] responseHeads;

    private Map<String, String> requestHeads;

    private LayeredConnectionSocketFactory ssf;

    /**
     * 获取get响应码
     *
     * @return int
     */
    public int getGetCode() {
        return getCode;
    }

    /**
     * 获取post响应码
     *
     * @return int
     */
    public int getPostCode() {
        return postCode;
    }

    /**
     * 构造器
     */
    public HttpClient() {
        this.config = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(10000).build();
    }

    /**
     * 构造器
     *
     * @param poxy   代理地址
     * @param port   端口号
     * @param scheme http/https
     */
    public HttpClient(String poxy, int port, String scheme) {
        this.config = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setProxy(new HttpHost(poxy, port, scheme))
                .build();
    }

    /**
     * 构造器
     *
     * @param poxy 代理地址
     * @param port 端口号
     */
    public HttpClient(String poxy, int port) {
        this(poxy, port, DEFAULT_SCHEME_NAME);
    }

    /**
     * 设置请求头
     *
     * @param requestHeads Map<String, String>
     */
    public void setRequestHeads(Map<String, String> requestHeads) {
        this.requestHeads = requestHeads;
    }


    /**
     * 获取响应头
     *
     * @return Header[]
     */
    public Header[] getResponseHeads() {
        return this.responseHeads;
    }

    /**
     * 设置ssl
     *
     * @param ssf
     */
    public void setSsf(LayeredConnectionSocketFactory ssf) {
        this.ssf = ssf;
    }

    /**
     * 根据目录加载ssl证书
     *
     * @param filePath 证书路径
     * @throws Exception 异常
     */
    public void loadSSL(String filePath) throws Exception {

        FileInputStream inputStream = new FileInputStream(filePath);
        SSLContext sslContext = SSLContext.getInstance("SSL");

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "changeit".toCharArray());
        inputStream.close();

        //构建Trust Manager
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);
        TrustManager[] tms = trustManagerFactory.getTrustManagers();

        //构建Key Manager
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, "changeit".toCharArray());
        KeyManager[] kms = keyManagerFactory.getKeyManagers();
        sslContext.init(kms, tms, null);

        this.ssf = new SSLConnectionSocketFactory(sslContext);
    }


    public void loadSSLToPrivate() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        }, new SecureRandom());

        this.ssf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

    }

    /**
     * 设置请求头
     *
     * @param base    HttpRequestBase 请求方法
     * @param headers Map<String, String> 请求头
     */
    private void setHeader(HttpRequestBase base, Map<String, String> headers) {
        if (null != headers) {
            for (String key : headers.keySet()) {
                base.setHeader(key, headers.get(key));
            }
        }
    }

    private CloseableHttpClient getCloseableHttpClient() {
        CloseableHttpClient httpClient;
        if (null != ssf) {
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(this.ssf)
                    .setDefaultRequestConfig(this.config).build();
        } else {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(config).build();
        }
        return httpClient;
    }

    /**
     * 发送get请求
     *
     * @param url URL地址
     * @return InputStream 返回结果
     * @throws Exception 异常
     */
    private InputStream doGet(String url) throws Exception {

        CloseableHttpClient httpClient = this.getCloseableHttpClient();
        HttpGet httpGet = new HttpGet(url);

        //设置请求头
        setHeader(httpGet, this.requestHeads);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        this.getCode = httpResponse.getStatusLine().getStatusCode();
        HttpEntity httpEntity = httpResponse.getEntity();

        //获取响应头
        this.responseHeads = httpResponse.getAllHeaders();

        InputStream in = httpEntity.getContent();

        //资源回收
        httpGet.abort();
        httpResponse.close();

        return in;
    }

    /**
     * get请求
     *
     * @param url URL地址
     * @return String 返回结果，默认字符编码格式: UTF-8
     * @throws Exception 异常
     */
    public String get(String url) throws Exception {
        return get(url, DEFAULT_CHARSET);
    }

    /**
     * get请求
     *
     * @param url     URL地址
     * @param charset 字符集格式
     * @return String 返回结果
     * @throws Exception 异常
     */
    public String get(String url, String charset) throws Exception {
        InputStream in = this.doGet(url);
        String response = null;
        if (in != null) {
            response = IoTool.InputStreamToString(in, charset);
            //资源回收
            in.close();
        }
        return response;
    }

    /**
     * get请求下载文件
     *
     * @param url 请求地址
     * @return byte[]
     * @throws Exception 异常
     */
    public byte[] getFileToBytes(String url) throws Exception {
        InputStream in = this.doGet(url);
        byte[] response = null;
        if (in != null) {
            response = IoTool.InputStreamToBytes(in);
            //资源回收
            in.close();
        }
        return response;
    }

    /**
     * @param url    请求地址
     * @param body   请求体
     * @param params List<NameValuePair>
     * @return InputStream
     * @throws Exception 异常
     */
    private InputStream doPost(String url, String body, List<NameValuePair> params, String charset) throws Exception {

        CloseableHttpClient httpClient = this.getCloseableHttpClient();
        HttpPost post = new HttpPost(url);
        HttpEntity data;
        if (null != body) {
            data = new StringEntity(body, Charset.forName(charset));
        } else {
            data = new UrlEncodedFormEntity(params, DEFAULT_CHARSET);
        }

        //设置请求头
        setHeader(post, this.requestHeads);

        //设置提交数据
        post.setEntity(data);

        CloseableHttpResponse httpResponse = httpClient.execute(post);
        this.postCode = httpResponse.getStatusLine().getStatusCode();
        HttpEntity httpEntity = httpResponse.getEntity();

        //获取响应头
        this.responseHeads = httpResponse.getAllHeaders();
        InputStream in = httpEntity.getContent();

        //资源回收
        post.abort();
        httpResponse.close();
        return in;
    }

    /**
     * post请求
     * List<NameValuePair> params = new ArrayList<NameValuePair>();
     * params.add(new BasicNameValuePair("name","snake"));
     * params.add(new BasicNameValuePair("pwd","123"));
     * params.add(new BasicNameValuePair("html","<hr/>"));
     *
     * @param url    URL地址
     * @param params 参数
     * @return String
     * @throws Exception 异常
     */
    public String post(String url, List<NameValuePair> params) throws Exception {
        return post(url, params, DEFAULT_CHARSET);
    }


    public String post(String url, List<NameValuePair> params, String charset) throws Exception {
        InputStream in = doPost(url, null, params, charset);
        String response = null;
        if (null != in) {
            response = IoTool.InputStreamToString(in, charset);
            in.close();
        }
        return response;
    }

    public String post(String url, String body) throws Exception {
        return post(url, body, DEFAULT_CHARSET);
    }

    public String post(String url, String body, String charset) throws Exception {

        InputStream in = doPost(url, body, null, charset);
        String response = null;
        if (null != in) {
            response = IoTool.InputStreamToString(in, charset);
            in.close();
        }
        return response;
    }


    /**
     * post上传文件
     *
     * @param url     URL地址
     * @param name    文件名
     * @param file    File
     * @param charset 编码格式
     * @return String
     * @throws Exception 异常
     */
    public String postFile(String url, String name, File file, String charset) throws Exception {
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpPost post = new HttpPost(url);

        //设置请求头
        setHeader(post, this.requestHeads);

        //设置上传文件
        MultipartEntityBuilder mult = MultipartEntityBuilder.create();

        mult.addBinaryBody(name, file);

        //设置请求数据
        post.setEntity(mult.build());

        CloseableHttpResponse httpResponse = httpClient.execute(post);
        this.postCode = httpResponse.getStatusLine().getStatusCode();
        HttpEntity responseEntity = httpResponse.getEntity();

        //获取响应头
        this.responseHeads = httpResponse.getAllHeaders();

        InputStream in = responseEntity.getContent();
        String response = IoTool.InputStreamToString(in, charset);
        //资源回收
        in.close();
        post.abort();
        httpResponse.close();

        return response;
    }

}
