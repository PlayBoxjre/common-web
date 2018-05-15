package com.kong.support.exceptions;

public class ClassNotFoundException extends BaseException {
    public ClassNotFoundException(int code) {
        super(code);
    }

    public ClassNotFoundException(int code, String message) {
        super(code, message);
    }
}
