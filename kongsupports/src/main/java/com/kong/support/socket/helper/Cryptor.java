package com.kong.support.socket.helper;

import com.kong.support.exceptions.CryptoExceptions;

/**
 * 加解密接口。用来对接收的数据和发送的数据进行加解密的处理
 */
public interface Cryptor {

    public byte[] enCrypto(byte[] datas) throws CryptoExceptions;

    public byte[] deCrypto(byte[] datas) throws CryptoExceptions;
}
