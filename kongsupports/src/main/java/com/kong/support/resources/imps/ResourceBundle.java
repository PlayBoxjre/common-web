package com.kong.support.resources.imps;

import com.kong.support.exceptions.ResourceAccessException;
import com.kong.support.resources.defines.ByteLoader;
import com.kong.support.resources.defines.LoadSelector;
import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.resources.defines.Resource;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class ResourceBundle   {

    private  Properties schemaLoaderMapper;

    private static ResourceBundle instance;

    private ResourceBundle(){
         schemaLoaderMapper = new Properties();
        try {
            schemaLoaderMapper.load(ClassLoader.getSystemResourceAsStream("kong_support_config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResourceBundle getInstance(){
        synchronized (ResourceBundle.class) {
            if (instance == null) {
                instance = new ResourceBundle();
            }
        }
        return instance;
    }


    private boolean lazyLoad;

    private LoadSelector loadSelector ;
    /**
     * 资源加载器
     */
    private ByteLoader<URI> byteLoader;



    public Resource getResource(String path) throws ResourceAccessException {
        return getResource(path,null);
    }

    public Resource getResource(URI uri) throws ResourceAccessException {
        return getResource(uri,null);
    }

    public Resource getResource(URI uri, Resource.OnResourceAccessListener listener) throws ResourceAccessException {
        byteLoader = configByteLoader();
        Resource resource  = new ByteResource(uri,byteLoader,listener);
        if (!lazyLoad)
            resource.openInputStream();
        return resource;
    }

    public Resource getResource(String path, Resource.OnResourceAccessListener listener) throws ResourceAccessException {
        return getResource(URI.create(path),listener);
    }

    public void asyncGetResource(String path, Resource.OnResourceAccessListener onResourceAccessListener) {
        throw new UnsupportedOperationException("不支持的操作");
    }

    public void asyncGetResource(URI uri, Resource.OnResourceAccessListener listener) {
        throw new UnsupportedOperationException("不支持的操作");
    }

    //---------  private method

    private ByteLoader<URI> configByteLoader(){
        ByteLoader<URI> loader = null;
        LoadSelector selector = null;
        RealByteLoader realLoader = null;
        if (this.byteLoader!=null)
            return byteLoader;

        if (this.getLoadSelector()==null){

            loadSelector =new DefaultLoadSelector(schemaLoaderMapper);
        }
        loader = new DefaultByteLoader(loadSelector);
        return loader;
    }

    //---------  setter & getter

    public boolean isLazyLoad() {
        return lazyLoad;
    }

    public void setLazyLoad(boolean lazyLoad) {
        this.lazyLoad = lazyLoad;
    }

    public LoadSelector getLoadSelector() {
        return loadSelector;
    }

    public void setLoadSelector(LoadSelector loadSelector) {
        this.loadSelector = loadSelector;
    }

    public ByteLoader<URI> getByteLoader() {
        return byteLoader;
    }

    public void setByteLoader(ByteLoader<URI> byteLoader) {
        this.byteLoader = byteLoader;
    }


}
