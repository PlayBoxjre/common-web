package com.kong.support.exceptions;

public class ResourceAccessException extends BaseException{


    public ResourceAccessException(int code) {
        super(code);
    }

    public ResourceAccessException(int code, String message) {
        super(code, message);
    }
}
