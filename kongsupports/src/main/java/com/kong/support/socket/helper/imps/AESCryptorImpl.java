package com.kong.support.socket.helper.imps;

import com.kong.support.exceptions.common.CryptoExceptions;
import com.kong.support.socket.helper.Cryptor;
import com.kong.support.toolboxes.CryptoTool;

public class AESCryptorImpl implements Cryptor {
    private String key = "123";
    @Override
    public byte[] enCrypto(byte[] datas) throws CryptoExceptions {
        try {
            return CryptoTool.AESEncrypt(datas,key);
        } catch (Exception ex){
            String exS = "AES crypto exception";
            throw new CryptoExceptions(exS.hashCode(),exS);
        }
    }

    @Override
    public byte[] deCrypto(byte[] datas) throws CryptoExceptions {
        try {
            return CryptoTool.AESDecrypt(datas,key);
        } catch (Exception ex){
            String exS = "AES crypto exception";
            throw new CryptoExceptions(exS.hashCode(),exS);
        }    }
}
