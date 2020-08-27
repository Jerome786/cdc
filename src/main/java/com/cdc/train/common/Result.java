package com.cdc.train.common;

/**
 * @author Jerome
 */
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }
    public Result (ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode,T obj){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = obj;
    }
}
