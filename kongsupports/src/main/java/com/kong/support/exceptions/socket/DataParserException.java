package com.kong.support.exceptions.socket;

import com.kong.support.exceptions.BaseException;

public class DataParserException extends BaseException {


    public DataParserException(int code) {
        super(code);
    }

    public DataParserException(int code, String message) {
        super(code, message);
    }
}
