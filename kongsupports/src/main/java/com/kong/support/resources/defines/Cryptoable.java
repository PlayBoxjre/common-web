package com.kong.support.resources.defines;

/**
 * 可加解密算法
 */
public interface Cryptoable {

    public byte[] encryption(byte[] originDatas);

    public byte[] decryption(byte[] cryptoString);
}
