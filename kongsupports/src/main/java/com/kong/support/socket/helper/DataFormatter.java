package com.kong.support.socket.helper;

import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

/**
 * 数据格式化
 */
public interface DataFormatter  {

    public void preFormat();

    public <T> byte[] format(T data, Charset charset) throws DataFormatException;

    public byte[] afterFormat(byte[] t);

}
