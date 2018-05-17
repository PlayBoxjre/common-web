package com.kong.support.toolboxes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 *
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/11 0:08 星期三
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 * /**
 *  * <pre>
 *  *     Class类之主要方法简介
 *  *  Class<?> cls = String.class;
 *  * 一）类自身的信息
 *  * 1. 包路径    cls.getPackage();
 *  * 2. 类名      cls.getName();cls.getSimpleName();
 *  * 3. 类修饰符  cls.getModifiers();
 *  * 4. 类属性    cls.getFields();cls.getDeclaredFields();
 *  * 5. 类方法    cls.getMethods();cls.getDeclaredMethods();
 *  * 6. 类构造器  cls.getConstructors();
 *  * 7. 标注信息  cls.getAnnotations();
 *  * 8. 是否为基本类型数据 cls.isPrimitive();
 *  * 9. 获得系统类的 getClassLoader();   这个也较为常用，加载类的时候
 *  *
 *  *
 *  * </pre>
 *
 *
 * @see com.sun.deploy.util.ReflectionUtil
 */
public class ReflectTool {
    static Logger logger = LoggerFactory.getLogger(ReflectTool.class);

    public void createInstance() {
    }


    /**
     * 获取Class
     *
     * @param classpath
     * @param classLoader
     * @return
     */
    public static Class getClass(String classpath, ClassLoader classLoader) {
        if (null == classLoader) {
            classLoader = ReflectTool.class.getClassLoader();
        }
        try {
            return Class.forName(classpath, false, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Constructor getConstructor(String classpath, Class[] params, ClassLoader classLoader) throws Exception {
        Class clazz = null;
        Constructor constructor = null;
        try {
            clazz = getClass(classpath, classLoader);
            if (null == clazz)
                throw new Exception("Class: '" + classpath + "' not found");
            else {
                try {
                    constructor = clazz.getDeclaredConstructor(params);
                } catch (NoSuchMethodException var6) {
                    throw new Exception("Constructor: '" + classpath + "' (" + params + ") not found");
                }
                return constructor;
            }
        } catch (Exception ex) {
            logger.debug("ERROR", ex);
            throw new Exception(ex);
        }
    }

    private static Constructor getConstructor(String classpath, ClassLoader classLoader) throws Exception {
        return getConstructor(classpath, new Class[0], classLoader);
    }

    /**
     * new instance with reflect
     *
     * @param classpath
     * @param paramsType
     * @param paramsObjects
     * @param classLoader
     * @return
     * @throws Exception
     */
    public static Object createInstance(String classpath, Class[] paramsType, Object[] paramsObjects, ClassLoader classLoader) throws Exception {
        Constructor constructor = null;
        try {
            constructor = getConstructor(classpath, paramsType, classLoader);
            return constructor.newInstance(paramsObjects);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static Object createInstance(String classpath, String[] paramsType, Object[] paramsObjects, ClassLoader classLoader) throws Exception {
        Class[] classes = new Class[paramsType.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = getClass(paramsType[i], classLoader);
            if (null == classes[i]) {
                throw new Exception("Class: '" + paramsType[i] + "' not found");
            }
        }
        return createInstance(classpath, classes, paramsObjects, classLoader);
    }

    public static Object createInstance(String classpath, ClassLoader classLoader) throws Exception {
        return createInstance(classpath, new Class[0], new Object[0], classLoader);
    }



}
