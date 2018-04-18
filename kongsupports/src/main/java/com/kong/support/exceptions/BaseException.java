package com.kong.support.exceptions;

public abstract class BaseException extends Exception {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseException(int code){
        this(code,"");
    }

    public BaseException(int code,String message){
        super(message);
        this.code = code;
    }
}
