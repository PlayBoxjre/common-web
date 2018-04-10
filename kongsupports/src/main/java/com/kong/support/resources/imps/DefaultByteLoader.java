package com.kong.support.resources.imps;

import com.kong.support.resources.defines.ByteLoader;
import com.kong.support.resources.defines.LoadSelector;
import com.kong.support.resources.defines.RealByteLoader;

import java.net.URI;
import java.util.Objects;

/**
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 23:02 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class DefaultByteLoader implements ByteLoader<URI> {

    private LoadSelector loadSelector;


    public DefaultByteLoader(LoadSelector loadSelector) {
        this.loadSelector = loadSelector;

    }

    public DefaultByteLoader() {
        this(new DefaultLoadSelector());
    }

    @Override
    public byte[] byteLoading(URI uri) {
        RealByteLoader realByteLoader = null;
        try {
            realByteLoader = getLoadSelector().selectLoadMethod(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(realByteLoader, "not find RealByteLoader subclass");
        return realByteLoader.readBytes(uri);
    }

    public LoadSelector getLoadSelector() {
        return loadSelector;
    }

    public void setLoadSelector(LoadSelector loadSelector) {
        this.loadSelector = loadSelector;
    }


}
