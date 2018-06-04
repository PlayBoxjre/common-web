package com.kong.support.toolbox;

import com.kong.support.annotations.UnThreadSafe;
import com.kong.support.exceptions.common.ClassFormatException;
import com.kong.support.exceptions.common.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.DataInteractionLifeCycle;
import com.kong.support.socket.nio.server.RequestContext;
import com.kong.support.socket.nio.server.SocketSession;

import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

@SuppressWarnings("warning")
@UnThreadSafe
public class UserBeanSubClass<T> extends UserBean implements DataInteractionLifeCycle  {

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
    public byte[] format(Object dataObject, Charset charset) throws ClassFormatException, DataFormatException {
        return new byte[0];
    }

    @Override
    public Object parse(byte[] text, Charset charset) throws DataParserException {
        return null;
    }

    @Override
    public byte[] enCrypto(int cryptoAlgo, String key, byte[] data) throws CryptoExceptions {
        return new byte[0];
    }

    @Override
    public byte[] deCrypto(int cryptoAlgo, String key, byte[] data) throws CryptoExceptions {
        return new byte[0];
    }

    @Override
    public byte[] encoding(int encodeAlgo, String key, byte[] data) throws EncodingException {
        return new byte[0];
    }

    @Override
    public byte[] decoding(int decodeAlgo, String key, byte[] data) throws DecodingException {
        return new byte[0];
    }

    @Override
    public byte[] receiveOriginAndResponse(RequestContext requestContext, byte[] datas, Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException, ClassFormatException, EncodingException, DataFormatException {
        return new byte[0];
    }

    @Override
    public byte[] buildResponseObject(SocketSession socketSession, Object datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException, DataFormatException {
        return new byte[0];
    }
}
