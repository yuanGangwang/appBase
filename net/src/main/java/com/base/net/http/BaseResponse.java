package com.base.net.http;

/**
 *
 */
public class BaseResponse<T> {

    public String msg;
    public T data;
    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return "0".equals(code);
    }

    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tretMsg='" + msg + "\'\n" +//
                "\tretData=" + data + "\n" +//
                '}';
    }

}
