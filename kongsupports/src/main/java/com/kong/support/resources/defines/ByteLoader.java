package com.kong.support.resources.defines;

import com.kong.support.exceptions.common.ResourceAccessException;

/**
 * 字节加载器
 * <pre>字节码加载 ，高层抽象接口定义，需需要知道源如何进行加载</pre>
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 22:38 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public interface ByteLoader<T> {
    public byte[] byteLoading(T uri, Resource.OnResourceAccessListener listener) throws ResourceAccessException;
}
