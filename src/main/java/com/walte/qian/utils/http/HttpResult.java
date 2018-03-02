package com.walte.qian.utils.http;

import java.util.Map;

/**
 * <p>ClassName: HttpResult</p>
 * <p>Description: </p>
 * 
 * @author wangqian
 * @date 2018-03-02 10:02
 */
public class HttpResult {

    private int code;
    private byte[] body;
    private Map<String, String> responseHeaders;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte[] getBody() {
        return body;
    }

    public HttpResult setBody(byte[] body) {
        this.body = body;
        return this;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public HttpResult setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }
}
