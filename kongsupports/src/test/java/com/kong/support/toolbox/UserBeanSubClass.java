package com.kong.support.toolbox;

import com.kong.support.annotations.UnThreadSafe;
import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.DataInteractionLifeCycle;
import com.kong.support.socket.helper.SocketSession;

import java.net.SocketException;
import java.nio.charset.Charset;

@SuppressWarnings("warning")
@UnThreadSafe
public class UserBeanSubClass<T> extends UserBean implements DataInteractionLifeCycle<T> {

    public UserBeanSubClass(String subString) {
        this.subString = subString;
    }

    public UserBeanSubClass() {
    }

    @UnThreadSafe
    private String subString;

    @UnThreadSafe
    public String getSubString() {
        return subString;
    }

    public void setSubString(String subString) {
        this.subString = subString;
    }

    @Override
    public String format(T dataObject, Charset charset) throws ClassFormatException {
        return null;
    }

    @Override
    public T parse(Class<T> t, byte[] text, Charset charset) throws DataParserException {
        return null;
    }

    @Override
    public byte[] enCrypto(byte[] data) throws CryptoExceptions {
        return new byte[0];
    }

    @Override
    public byte[] deCrypto(byte[] data) throws CryptoExceptions {
        return new byte[0];
    }

    @Override
    public byte[] encoding(byte[] data) throws EncodingException {
        return new byte[0];
    }

    @Override
    public byte[] decoding(byte[] data) throws DecodingException {
        return new byte[0];
    }

    @Override
    public byte[] buildResponseObject(SocketSession socketSession, T datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException {
        return new byte[0];
    }

    @Override
    public T accept(Class<T> t, SocketSession socketSession, byte[] datas, Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException {
        return null;
    }
}
