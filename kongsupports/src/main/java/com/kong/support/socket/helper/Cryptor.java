package com.kong.support.socket.helper;

import com.kong.support.exceptions.CryptoExceptions;

public interface Cryptor {

    public byte[] enCrypto(byte[] datas) throws CryptoExceptions;

    public byte[] deCrypto(byte[] datas) throws CryptoExceptions;
}
