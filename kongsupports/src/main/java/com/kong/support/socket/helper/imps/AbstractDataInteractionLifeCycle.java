package com.kong.support.socket.helper.imps;

import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.*;
import com.kong.support.socket.helper.accept.SocketSession;
import com.kong.support.toolboxes.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.zip.DataFormatException;

/**
 * 抽象类客户端与数据端交互周期定义实现
 */
public abstract class AbstractDataInteractionLifeCycle implements DataInteractionLifeCycle   {
    Logger logger  = LoggerFactory.getLogger(AbstractDataInteractionLifeCycle.class);
    private Encoder encoder;
    private Decoder decoder;
    private Cryptor cryptor;
    private DataFormatter dataFormatter;
    private DataParser dataParser;

    public AbstractDataInteractionLifeCycle() {
    }

    @Override
    public <I> I parse(Class<I> t, byte[] text, Charset charset) throws DataParserException {
        Objects.requireNonNull(dataParser,"[ SOCKET ] SocketLifeCircle object need dataParser ");
        dataParser.preParse();
        I parser = dataParser.parser(t,text , charset);
        return dataParser.afterParse(parser,null);
    }

    @Override
    public <I> byte[] format(I dataObject, Charset charset) throws DataFormatException {
        if (dataFormatter == null)
            throw new NullPointerException("[ SOCKET ] SocketLifeCircle object need dataFormatter ");
        dataFormatter.preFormat();
        byte[] format = dataFormatter.format(dataObject, charset);
        return dataFormatter.afterFormat(format);
    }

    public AbstractDataInteractionLifeCycle(Encoder encoder, Decoder decoder, Cryptor cryptor, DataFormatter  dataFormatter, DataParser  dataParser) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.cryptor = cryptor;
        this.dataFormatter = dataFormatter;
        this.dataParser = dataParser;
    }

    @Override
    public byte[] enCrypto(byte[] data) throws CryptoExceptions {
        if (cryptor == null)
            return data;
        return cryptor.enCrypto(data);
    }

    @Override
    public byte[] deCrypto(byte[] data) throws CryptoExceptions {
        if (cryptor == null )
            return data;
        return cryptor.deCrypto(data);
    }

    @Override
    public byte[] encoding(byte[] data) throws EncodingException {
        if (encoder == null)
            return data;
        return encoder.encoding(data);
    }

    @Override
    public byte[] decoding(byte[] data) throws DecodingException {
        if (decoder == null)
            return data;
        return decoder.decoding(data);
    }

    @Override
    public  <I> byte[] buildResponseObject(SocketSession socketSession, I datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException, DataFormatException {
        byte[] format = format(datas, charset);
        logger.debug("format --> {}",format);
        byte[] encoding = encoding(format);
        logger.debug("encoding --> {}",StringTool.toString(encoding));
        byte[] bytes = enCrypto(encoding);
        logger.debug("enCrypto --> {}",StringTool.hex(bytes));
        return bytes;
    }

    @Override
    public  <I> I accept(Class<I> tClass,SocketSession socketSession, byte[] datas,Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException {
        //放到外部，构建socketsession的地方
//        String ex = null;
//        if (socket == null) {
//            throw new NullPointerException("socket session is null point ");
//        }
//        if (!socket.isConnected()) {
//            ex = "socket connect status exception";
//            socketSession.setSocketStatus(SocketSession.SOCKET_STATUS.SOCKET_DISCONNECT);
//            throw new SocketConnectionException(ex.hashCode(),ex);
//        }
//        if (socket.isClosed()){
//            ex = "socket has closed";
//            socketSession.setSocketStatus(SocketSession.SOCKET_STATUS.SOCKET_CLOSE);
//            throw new SocketConnectionException(ex.hashCode(),ex);
//        }
        if (datas==null)
            return null;
        // 解密
        byte[] bytes = deCrypto(datas);
        logger.debug("deCrypto --> {}",StringTool.toString(bytes));
        byte[] decoding = decoding(bytes);
        logger.debug("decoding --> {}",StringTool.toString(decoding));
        return parse(tClass, decoding, charset);
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    public Cryptor getCryptor() {
        return cryptor;
    }

    public void setCryptor(Cryptor cryptor) {
        this.cryptor = cryptor;
    }

    public DataFormatter  getDataFormatter() {
        return dataFormatter;
    }

    public void setDataFormatter(DataFormatter  dataFormatter) {
        this.dataFormatter = dataFormatter;
    }

    public DataParser getDataParser() {
        return dataParser;
    }

    public void setDataParser(DataParser dataParser) {
        this.dataParser = dataParser;
    }
}
