package com.kong.support.toolbox;

import com.kong.support.annotations.UnThreadSafe;
import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.DataInteractionLifeCycle;
import com.kong.support.socket.helper.accept.SocketSession;

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
    public <I> byte[] format(I dataObject, Charset charset) throws ClassFormatException, DataFormatException {
        return new byte[0];
    }

    @Override
    public <I> I parse(Class<I> t, byte[] text, Charset charset) throws DataParserException {
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
    public <I> byte[] buildResponseObject(SocketSession socketSession, I datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException, DataFormatException {
        return new byte[0];
    }

    @Override
    public <I> I accept(Class<I> t, SocketSession socketSession, byte[] datas, Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException {
        return null;
    }


}
