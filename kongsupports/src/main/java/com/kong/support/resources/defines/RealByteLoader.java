package com.kong.support.resources.defines;

import java.net.URI;

/**
 * 实际加载byte数据的加载定义
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 22:58 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public interface RealByteLoader {
    public byte[] readBytes(URI url);
}
