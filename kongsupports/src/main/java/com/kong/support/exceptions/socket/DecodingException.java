package com.kong.support.exceptions.socket;

import com.kong.support.exceptions.BaseException;

public class DecodingException extends BaseException {
    public DecodingException(int code) {
        super(code);
    }

    public DecodingException(int code, String message) {
        super(code, message);
    }
}
