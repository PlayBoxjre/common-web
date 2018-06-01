package com.kong.support.resources.imps;

import com.kong.support.exceptions.common.ResourceAccessException;
import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.resources.defines.Resource;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileRealByteLoader implements RealByteLoader {
    @Override
    public byte[] readBytes(URI url, Resource.OnResourceAccessListener listener) throws ResourceAccessException {
        try {
            FileInputStream fileInputStream = new FileInputStream(url.toURL().getFile());
            FileChannel channel = fileInputStream.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            if (map.hasArray()){
                byte[] array = map.array();
                return array;
            }else{
                ByteArrayOutputStream o = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024 ] ;

                while (map.hasRemaining()){
                    map.slice();
                    int readLen = bytes.length > map.remaining()?map.remaining():bytes.length;
                    map.get(bytes,0,readLen);
                    o.write(bytes,0,readLen);
                }
                return o.toByteArray();
            }

        } catch (IOException e) {
            throw new ResourceAccessException(10001,"资源没有找到，URL错误");
        }
    }
}
