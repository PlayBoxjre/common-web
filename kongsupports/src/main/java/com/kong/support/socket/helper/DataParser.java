package com.kong.support.socket.helper;

import com.kong.support.exceptions.BaseException;
import com.kong.support.exceptions.socket.DataParserException;

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
    public byte[] parser(String text) throws DataParserException;

    /**
     * 完成解析
     * @param parseByte
     * @param ex
     * @return
     */
    public byte[] afterParse(byte[] parseByte, BaseException ex);
}
