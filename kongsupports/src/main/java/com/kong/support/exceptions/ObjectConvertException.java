package com.kong.support.exceptions;

public class ObjectConvertException extends BaseException {

    public ObjectConvertException(int code) {
        super(code);
    }

    public ObjectConvertException(int code, String message) {
        super(code, message);
    }
}
