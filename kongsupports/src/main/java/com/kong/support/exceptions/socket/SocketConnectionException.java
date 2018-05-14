package com.kong.support.exceptions.socket;

import com.kong.support.exceptions.BaseException;

public class SocketConnectionException extends BaseException {
    public SocketConnectionException(int code) {
        super(code);
    }

    public SocketConnectionException(int code, String message) {
        super(code, message);
    }
}
