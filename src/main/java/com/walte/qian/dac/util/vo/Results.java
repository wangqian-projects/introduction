package com.walte.qian.dac.util.vo;

import com.walte.qian.dac.util.EmptyUtil;

import java.io.Serializable;

/**
 * <p>ClassName: Results</p>
 * <p>Description: </p>
 *
 * @author wangqian
 * @date 2018-03-09 16:10
 */
public class Results<T, K> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T result;

    private String msg;

    private K obj; //返回结果中带有其他返回信息

    public Results() {
    }

    public Results(T result) {
        this.result = result;
    }

    public Results(T result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public static <M, N> Results<M, N> newResult() {
        return new Results<>();
    }

    public static <M, N> Results<M, N> newResilt(M m) {
        return new Results<>(m);
    }

    /**
     * 向后添加一段信息
     *
     * @param msg 信息
     */
    public void appendMsg(String msg) {
        String str = getMsg();
        if (str == null) {
            setMsg(msg);
        } else {
            setMsg(str.concat(msg));
        }
    }

    /**
     * 判断返回结构是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return result == null;
    }

    /**
     * 判断返回信息是否为空
     *
     * @return boolean
     */
    public boolean isEmptyMsg() {
        return EmptyUtil.isEmpty(this.msg);
    }

    /**
     * 在头部插入一段信息
     *
     * @param msg
     */
    public void prependMsg(String msg) {
        if (null != msg) {
            setMsg(msg.concat(getMsg()));
        }
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public K getObj() {
        return obj;
    }

    public void setObj(K obj) {
        this.obj = obj;
    }
}
