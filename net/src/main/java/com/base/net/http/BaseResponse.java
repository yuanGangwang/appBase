package com.base.net.http;

/**
 *
 */
public class BaseResponse<T> {

    public String retMsg;
    public T retData;
    public String retCode;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }


    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }

        public boolean isOk() {
        return "200".equals(retCode);
    }

    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tretMsg='" + retMsg + "\'\n" +//
                "\tretData=" + retData + "\n" +//
                '}';
    }

//    private String code;
//    private String message;
//    private T result;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public T getResult() {
//        return result;
//    }
//
//    public void setResult(T result) {
//        this.result = result;
//    }
//
//    public boolean isOk() {
//        return "200".equals(code);
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
