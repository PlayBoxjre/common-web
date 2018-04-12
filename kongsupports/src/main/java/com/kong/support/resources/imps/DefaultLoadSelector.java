package com.kong.support.resources.imps;

import com.kong.support.resources.defines.LoadSelector;
import com.kong.support.resources.defines.RealByteLoader;
import com.kong.support.toolboxes.ReflectTool;

import java.io.IOException;
import java.util.Properties;

/**
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/10 23:01 星期二
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class DefaultLoadSelector implements LoadSelector {
    private Properties schemaLoaderMapper;

    public DefaultLoadSelector() {
        schemaLoaderMapper = new Properties();
        try {
            schemaLoaderMapper.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DefaultLoadSelector(Properties schemaLoaderMapper) {
        this.schemaLoaderMapper = schemaLoaderMapper;
    }

    @Override
    public RealByteLoader selectLoadMethod(String scheme ) throws Exception {

        String classPath = schemaLoaderMapper.getProperty(scheme);
        if (classPath == null) {
            classPath = schemaLoaderMapper.getProperty("default");
            if (classPath==null)
                classPath = DefaultRealByteLoader.class.getName();
        }
        return (RealByteLoader) ReflectTool.createInstance(classPath, getClass().getClassLoader());
    }

    public Properties getSchemaLoaderMapper() {
        return schemaLoaderMapper;
    }

    public void setSchemaLoaderMapper(Properties schemaLoaderMapper) {
        this.schemaLoaderMapper = schemaLoaderMapper;
    }
}
