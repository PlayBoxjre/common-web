package com.kong.support.socket.helper.imps;

import com.kong.support.exceptions.common.ClassFormatException;
import com.kong.support.exceptions.common.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.*;
import com.kong.support.socket.nio.request.RequestHeader;
import com.kong.support.socket.nio.server.RequestContext;
import com.kong.support.socket.nio.server.SocketSession;
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
public abstract class AbstractDataInteractionLifeCycle<I> implements DataInteractionLifeCycle<I>   {
    Logger logger  = LoggerFactory.getLogger(AbstractDataInteractionLifeCycle.class);
    private Encoder encoder;
    private Decoder decoder;
    private Cryptor cryptor;
    private DataFormatter dataFormatter;
    private DataParser dataParser;
    private Class<I> requestClassType;
    public AbstractDataInteractionLifeCycle() {
    }

    @Override
    public final I parse( byte[] text, Charset charset) throws DataParserException {
        Objects.requireNonNull(dataParser,"[ SOCKET ] SocketLifeCircle object need dataParser ");
        dataParser.preParse();
        I parser = dataParser.parser(requestClassType,text , charset);
        return dataParser.afterParse(parser,null);
    }

    @Override
    public  final <T> byte[] format(T dataObject, Charset charset) throws DataFormatException {
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
    public final byte[] enCrypto(int cryptoAlgo ,String key,byte[] data) throws CryptoExceptions {
        if (cryptor == null)
            return data;
        return cryptor.enCrypto(data);
    }

    @Override
    public final byte[] deCrypto(int cryptoAlgo ,String key,byte[] data) throws CryptoExceptions {
        if (cryptor == null )
            return data;
        return cryptor.deCrypto(data);
    }

    @Override
    public final byte[] encoding(int encodeAlgo ,String key,byte[] data) throws EncodingException {
        if (encoder == null)
            return data;
        return encoder.encoding(data);
    }

    @Override
    public final byte[] decoding(int decodeAlgo ,String key,byte[] data) throws DecodingException {
        if (decoder == null)
            return data;
        return decoder.decoding(data);
    }

    @Override
    public  final <T> byte[] buildResponseObject(SocketSession socketSession, T datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException, DataFormatException {
        byte[] format = format(datas, charset);

        final int crypto = 0;
        final String cryptoKey = null;

        final int encode = 0;
        final String  encodeKey  = null;


        logger.debug("format --> {}",format);
        byte[] encoding = encoding(encode,encodeKey,format);
        logger.debug("encoding --> {}",StringTool.toString(encoding));
        byte[] bytes = enCrypto(crypto,cryptoKey,encoding);
        logger.debug("enCrypto --> {}",StringTool.hex(bytes));
        return bytes;
    }

    @Override
    public  final byte[] receiveOriginAndResponse(RequestContext requestContext, byte[] datas, Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException, ClassFormatException, EncodingException, DataFormatException {
        if (datas==null)
            return null;
        RequestHeader requestHeader = requestContext.getRequestHeader();
        final int crypto = requestHeader.getCryptoAlgothem();
        final String cryptoKey = requestHeader.getCryptoAlgothemKey() ;
        final int decodeAlgo = requestHeader.getDecodeAlgothem();
        final String decodeKey = requestHeader.getDecodeAlgothemKey();
        // 解密
        byte[] bytes = deCrypto(crypto,cryptoKey,datas);
        logger.debug("deCrypto --> {}",StringTool.toString(bytes));
        byte[] decoding = decoding(decodeAlgo,decodeKey,bytes);
        logger.debug("decoding --> {}",StringTool.toString(decoding));
        I parse = parse(decoding, charset);
         return buildResponseObject(requestContext.getSocketSession(),onCreateInstance(requestContext,parse),Charset.forName(requestContext.getSocketContext().getGolabeCharsetName()));
    }


    public abstract  Object onCreateInstance(RequestContext requestContext,I dataObject);



    public Class<I> getRequestClassType() {
        return requestClassType;
    }

    public void setRequestClassType(Class<I> requestClassType) {
        this.requestClassType = requestClassType;
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
