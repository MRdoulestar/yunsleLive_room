package com.test.entity;

/**
 * Created by Doublestar on 2017/11/28 19:01).
 */
public class StatusMessage {
    private int code;
    private String message;
    private String data;

    public StatusMessage(){}
    public StatusMessage(int code, String message, String data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
}
