package com.kong.support.socket.helper;

/**
 * 数据格式化
 */
public interface DataFormatter {

    public void preFormat();

    public <T> T format(Class<T> tClass,String data);

    public <T> void afterFormat(T t);

}
