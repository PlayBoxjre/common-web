package com.kong.support.socket.helper;

import com.kong.support.exceptions.BaseException;
import com.kong.support.exceptions.socket.DataParserException;

import java.nio.charset.Charset;

/**
 * 数据解析器
 */
public interface DataParser {
    /**
     * 解析数据之前的操作
     */
    public void preParse();

    /**
     * 解析数据
     * @param text
     * @return
     * @throws DataParserException
     */
    public <T> T parser(Class<T> tClass, byte[] text, Charset charset) throws DataParserException;

    /**
     * 完成解析
     * @param parseByte
     * @param ex
     * @return
     */
    public <T> T afterParse(T parseByte, BaseException ex);
}
