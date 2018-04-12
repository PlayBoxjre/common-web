package com.kong.support.resources.imps;

import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.toolboxes.StreamTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DefaultHttpReadByteLoader implements RealByteLoader
{
    @Override
    public byte[] readBytes(URI uri) {
        try {
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf8");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(30000);

            conn.connect();

            InputStream inputStream = conn.getInputStream();
            byte[] bytes = StreamTool.readByteFromInputStream(inputStream);
            return bytes;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }
}
