package com.kong.support.resources;

import java.io.InputStream;
import java.util.Properties;

/**
 * 资源接口
 */
public interface Resource {
    /**
     * 资源的位置 path
     * @return
     */
    public String location();

    /**
     * 资源的 uri
     * @return
     */
    public String uri();

    /**
     * 资源的数据体
     * @return
     */
    public byte[] getBytes();

    /**
     * 资源的大小 unit：byte
     * @return
     */
    public int size();

    /**
     * 资源转化为流
     * @return
     */
    public InputStream openInputStream();

    public boolean isClose();

    public boolean exist();

    public Properties getProperties();

    public String getName();

    public String[] getAlias();

    public int getId();

}
