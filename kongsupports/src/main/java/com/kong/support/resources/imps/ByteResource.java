package com.kong.support.resources.imps;

import com.kong.support.resources.defines.ByteLoader;
import com.kong.support.resources.defines.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * 字节资源类
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 22:20 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class ByteResource implements Resource {

    private ByteLoader<URI> byteLoader;
    private String location;
    private URI uri;
    private byte[] data;
    private boolean isClose;


    public ByteResource() {
    }

    public ByteResource(String path) {
        this(path, new DefaultByteLoader());
    }

    public ByteResource(URI uri) {
        this(uri, new DefaultByteLoader());
    }

    public ByteResource(URI uri, ByteLoader<URI> byteLoader) {
        this.location = uri.toASCIIString();
        this.uri = uri;
        this.byteLoader = byteLoader;
        openInputStream();
    }

    public ByteResource(String path, ByteLoader<URI> byteLoader) {
        this.location = path;
        this.uri = URI.create(path);
        this.byteLoader = byteLoader;
        openInputStream();
    }

    @Override
    public byte[] getBytes() {

        if (data != null)
            return data;
        return byteLoader.byteLoading(getURI());

    }

    @Override
    public String getLocaiton() {
        return location;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public boolean isExist() {
        return location != null && data != null;
    }

    @Override
    public boolean isClose() {
        return isClose;
    }


    @Override
    public int size() {
        if (data == null)
            return 0;
        return data.length;
    }

    @Override
    public InputStream openInputStream() {
        if (data == null)
            data = this.byteLoader.byteLoading(uri);
        return data == null ? null : new ByteArrayInputStream(data);
    }

    @Override
    public void close() throws IOException {
        this.data = null;
        isClose = true;
    }

    public ByteLoader<URI> getByteLoader() {
        return byteLoader;
    }

    public void setByteLoader(ByteLoader<URI> byteLoader) {
        this.byteLoader = byteLoader;
    }
}
