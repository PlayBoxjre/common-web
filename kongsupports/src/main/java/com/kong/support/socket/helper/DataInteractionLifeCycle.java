package com.kong.support.socket.helper;

import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.accept.SocketSession;

import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

/**
 * 数据交互周期
 * 用来定义数据在客户端和服务端的数据传输（交互）流程
 */
public interface DataInteractionLifeCycle  {

    /**
     * 将数据对象格式化字符串
     * @param dataObject

     * @return
     */
    public <I> byte[]  format(I dataObject, Charset charset) throws ClassFormatException, DataFormatException;

    /**
     * 将字符串解析成指定对象
     * @param t
     * @param text
     * @param charset

     * @return
     */
    public <I> I parse(Class<I> t,byte[] text,Charset charset) throws DataParserException;

    /**
     * 加密数据
     * @param data
     * @return
     * @throws CryptoExceptions
     */
    public byte[] enCrypto(byte[] data) throws CryptoExceptions;

    /**
     * 解密数据
     * @param data
     * @return
     * @throws CryptoExceptions
     */
    public byte[] deCrypto(byte[] data)throws CryptoExceptions;

    /**
     * 数据编码
     * @param data
     * @return
     * @throws EncodingException
     */
    public byte[] encoding(byte[] data) throws EncodingException;

    /**
     * 数据解码
     * @param data
     * @return
     * @throws DecodingException
     */
    public byte[] decoding(byte[] data)throws DecodingException;


    /**
     * 发送数据到对端
     * @param datas
     * @param charset
     */
    public <I> byte[] buildResponseObject(SocketSession socketSession, I datas, Charset charset) throws ClassFormatException, CryptoExceptions, EncodingException, DataFormatException;

    /**
     * 接收远程的数据
     * @param datas
     * @param charset
     * @return
     */
    public <I> I accept(Class<I>  t, SocketSession socketSession, byte[] datas, Charset charset) throws SocketException, SocketConnectionException, CryptoExceptions, DecodingException, DataParserException;

}
