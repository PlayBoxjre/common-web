package com.kong.support.exceptions;

public class ClassFormatException extends BaseException {

    public ClassFormatException(int code) {
        super(code);
    }

    public ClassFormatException(int code, String message) {
        super(code, message);
    }
}
