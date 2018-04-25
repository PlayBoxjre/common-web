package com.kong.support.exceptions;

public class CryptoExceptions extends BaseException {


    public CryptoExceptions(int code) {
        super(code);
    }

    public CryptoExceptions(int code, String message) {
        super(code, message);
    }
}
