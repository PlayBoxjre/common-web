package com.kong.support.socket.helper;

import com.kong.support.exceptions.common.ClassFormatException;
import com.kong.support.exceptions.common.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.nio.server.RequestContext;
import com.kong.support.socket.nio.server.SocketSession;

import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

/**
 * 数据交互周期
 * 用来定义数据在客户端和服务端的数据传输（交互）流程
 */
public interface DataInteractionLifeCycle<I>  {

    /**
     * 将数据对象格式化字符串
     * @param dataObject

     * @return
     */
    public <T> byte[]  format(T dataObject, Charset charset) throws ClassFormatException, DataFormatException;

    /**
     * 将字符串解析成指定对象
     * @param text
     * @param charset

     * @return
     */
    public  I parse(byte[] text,Charset charset) throws DataParserException;

    /**
     * 加密数据
     * @param data
     * @return
     * @throws CryptoExceptions
     */
    public byte[] enCrypto(int cryptoAlgo,String key,byte[] data) throws CryptoExceptions;

    /**
     * 解密数据
     * @param data
     * @return
     * @throws CryptoExceptions
     */
    public byte[] deCrypto(int cryptoAlgo ,String key,byte[] data)throws CryptoExceptions;

    /**
     * 数据编码
     * @param data
     * @return
     * @throws EncodingException
     */
    public byte[] encoding(int encodeAlgo,String key,byte[] data) throws EncodingException;

    /**
     * 数据解码
     * @param data
     * @return
     * @throws DecodingException
     */
    public byte[] decoding(int decodeAlgo ,String key,byte[] data)throws DecodingException;


    /**
     * 发送数据到对端
     * @param datas
     * @param charset
     */
    public <T>  byte[] buildResponseObject(SocketSession socketSession, T datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException, DataFormatException;

    /**
     * 接收远程的数据
     * @param datas
     * @param charset
     * @return
     */
    public   byte[] receiveOriginAndResponse(RequestContext requestContext, byte[] datas, Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException, ClassFormatException, EncodingException, DataFormatException;
}
