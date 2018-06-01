package com.kong.support.resources.imps;

import com.kong.support.exceptions.common.ResourceAccessException;
import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.resources.defines.Resource;
import com.kong.support.toolboxes.StreamTool;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class DefaultHttpReadByteLoader implements RealByteLoader
{
    @Override
    public byte[] readBytes(URI uri, Resource.OnResourceAccessListener listener) throws ResourceAccessException {
        try {
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf8");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(30000);
            int contentLength = conn.getContentLength();
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            byte[] bytes = StreamTool.readByteFromInputStreamForProcess(inputStream,(current)->{
                if (listener!=null)
                    listener.onResourceAccessing(uri,current,contentLength);
            });
            return bytes;
        } catch (Exception e) {
            throw new ResourceAccessException(10000,"网络资源获取失败");
        }
    }
}
