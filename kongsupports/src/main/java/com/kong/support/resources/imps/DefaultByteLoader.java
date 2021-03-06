package com.kong.support.resources.imps;

import com.kong.support.exceptions.common.ResourceAccessException;
import com.kong.support.resources.defines.ByteLoader;
import com.kong.support.resources.defines.LoadSelector;
import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.resources.defines.Resource;

import java.io.File;
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
    public byte[] byteLoading(URI uri, Resource.OnResourceAccessListener listener) throws ResourceAccessException {
        RealByteLoader realByteLoader = null;
        String scheme = uri.getScheme();
        if (scheme == null){
            scheme = "default";
            uri = new File(uri.toASCIIString()).toURI();
        }
        try {
            realByteLoader = getLoadSelector().selectLoadMethod(scheme);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listener!=null)
           listener.onPreResourceAccess(uri);
        Objects.requireNonNull(realByteLoader, "not find RealByteLoader subclass");
        return realByteLoader.readBytes(uri,listener);
    }

    public LoadSelector getLoadSelector() {
        return loadSelector;
    }

    public void setLoadSelector(LoadSelector loadSelector) {
        this.loadSelector = loadSelector;
    }


}
