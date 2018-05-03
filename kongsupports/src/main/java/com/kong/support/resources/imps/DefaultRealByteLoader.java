package com.kong.support.resources.imps;

import com.kong.support.exceptions.ResourceAccessException;
import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.resources.defines.Resource;
import com.kong.support.toolboxes.StreamTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 23:02 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class DefaultRealByteLoader implements RealByteLoader {
    Logger logger = LoggerFactory.getLogger(DefaultRealByteLoader.class);

    @Override
    public byte[] readBytes(URI uri, Resource.OnResourceAccessListener listener) throws ResourceAccessException {
        try {
            URL url = uri.toURL();
            logger.info("real loader : uri {} - url {}", uri.toASCIIString(), url.toString());
            URLConnection urlConnection = url.openConnection();
            int total = urlConnection.getContentLength();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                return StreamTool.readByteFromInputStreamForProcess(inputStream,(currentLength)->{
                    if (listener!=null) {
                        listener.onResourceAccessing(uri,currentLength,total);
                    }

                });
            }
        } catch (Exception e) {
           throw new ResourceAccessException(10000,e.getMessage());
        }
        return null;
    }
}
