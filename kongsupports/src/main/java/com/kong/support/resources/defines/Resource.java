package com.kong.support.resources.defines;

import com.kong.support.exceptions.BaseException;
import com.kong.support.exceptions.common.ResourceAccessException;

import java.io.Closeable;
import java.io.InputStream;
import java.net.URI;

/**
 * 资源接口
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 22:13 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public interface Resource extends Closeable {

    public byte[] getBytes() ;

    public String getLocaiton();

    public URI getURI();

    public boolean isExist();

    public boolean isClose();

    public int size();

    public InputStream openInputStream() throws ResourceAccessException;


    public interface OnResourceAccessListener{

        public void onPreResourceAccess(URI uri);

        public byte[] onResourceAccessing(URI uri,int length,int total);

        public byte[] onResourceAccessFinish(Resource resource);

        public void onResourceAccessFailed(BaseException ex);
    }

}
