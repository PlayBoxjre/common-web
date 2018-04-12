package com.kong.support.resources.defines;

/**
 * 加载选择器，选择加载资源的方式
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 22:45 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public interface LoadSelector {
    public RealByteLoader selectLoadMethod(String  scheme) throws Exception;
}
