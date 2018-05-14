package com.kong.support.exceptions.socket;

import com.kong.support.exceptions.BaseException;

public class EncodingException extends BaseException {
    public EncodingException(int code) {
        super(code);
    }

    public EncodingException(int code, String message) {
        super(code, message);
    }
}
