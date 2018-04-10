package com.kong.support.resources.imps;

import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.tools.StreamTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.net.MalformedURLException;
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
    public byte[] readBytes(URI uri) {
        try {
            URL url = uri.toURL();
            logger.info("real loader : uri {} - url {}", uri.toASCIIString(), url.toString());
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                return StreamTool.readByteFromInputStream(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
