package com.kong.support.lifecycle;

import java.util.Map;

public interface BeanInitializeLifcycle {
    /**
     * 扫描获取类
     * @param base
     */
    public void scanAllClasses(String base);

    public void buildClassDefine(String clazz);


    public void scanClassAnnotations(Class<?> clz);

    /**
     * 配置注解对应处理映射
     * @param annotationProcessorMapper
     */
    public void configureAnnotationProcessor(Map<String,String> annotationProcessorMapper);


}
