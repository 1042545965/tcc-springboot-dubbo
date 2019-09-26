package com.tcc.core.result;

import java.io.Serializable;

/**
 * rest返回对象
 *^
 * @param <T>
 */
public class RestResponseBo<T> implements Serializable {

    /**
     * 服务器响应数据
     */
    private T payload;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code = 200;


    public RestResponseBo() {

    }

    public RestResponseBo(boolean success) {

        this.success = success;
    }

    public RestResponseBo(boolean success, T payload) {

        this.success = success;
        this.payload = payload;
    }

    public RestResponseBo(boolean success, T payload, int code) {

        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public RestResponseBo(boolean success, String msg) {

        this.success = success;
        this.msg = msg;
    }

    public RestResponseBo(boolean success, String msg, int code) {

        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static RestResponseBo ok() {
        return new RestResponseBo(true);
    }

    public static <T> RestResponseBo ok(T payload) {
        return new RestResponseBo(true, payload);
    }

    public static <T> RestResponseBo ok(int code) {
        return new RestResponseBo(true, null, code);
    }

    public static <T> RestResponseBo ok(T payload, int code) {
        return new RestResponseBo(true, payload, code);
    }

    public static RestResponseBo fail() {
        return new RestResponseBo(false);
    }

    public static RestResponseBo fail(String msg) {
        return new RestResponseBo(false, msg , 500);
    }

    public static RestResponseBo fail(int code) {
        return new RestResponseBo(false, null, code);
    }

    public static RestResponseBo fail(int code, String msg) {
        return new RestResponseBo(false, msg, code);
    }

}