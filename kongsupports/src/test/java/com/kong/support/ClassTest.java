package com.kong.support;

import com.kong.support.exceptions.BaseException;
import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.network.defines.NetWork;
import com.kong.support.resources.defines.Codable;
import com.kong.support.toolbox.UserBeanSubClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.tools.java.ClassPath;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;

public class ClassTest {
    Logger logger = LoggerFactory.getLogger(ClassTest.class);
    @Test
    public void reflectTest(){
       int[] array = (int[]) Array.newInstance(int.class,19);
       Array.setInt(array,0,1);
       Array.set(array,1,2);
       Array.set(array,2,11);
       print(array);

        Object c = Array.newInstance(int.class, 2, 2);

        Object r = Array.get(c,0);
        Array.set(r,0,1);
        Array.set(r,1,2);
        Array.set(c,0,r);
        print(c);

    }

    @Test
    public void test1() {


        String packagePath = "";
        String classpath = "";

        logger.info("{}","12346".substring(1));

        String fileSeparatorChar = "" + File.separatorChar;
        String var1 = System.getProperty("sun.boot.class.path");
        String var2 = System.getProperty("env.class.path");
        if (var2 == null) {
            var2 = ".";
        }

        File file = new File("");

        logger.info("file : {}",file.getAbsolutePath());


        String var3 = var1 + File.pathSeparator + var2;
        logger.info("Class path :::: init before:{}",var3);


        logger.info("sun boot.class apth :{}",classpath);
        classpath = getClass().getResource("/").getPath();
        assert null !=  classpath;

        logger.info("classpath:{}",classpath);

        //classpath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        ClassPath classPath = new ClassPath();
        classpath = classPath.getDirectory("/").getAbsoluteName();


        logger.info("classpath:{}",classpath);


        logger.info("classpath:{}",classpath);



        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                logger.info(" shut down ....");
                super.run();
            }
        });





        Class cls = BaseException.class;
        //
       boolean assignableFrom = cls.isAssignableFrom(CryptoExceptions.class);

        NetWork work = new NetWork() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };

        boolean anonymousClass = work.getClass().isAnonymousClass();
        assert anonymousClass;

        try {
            cls = Class.forName("java.lang.AbstractStringBuilder");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        AnnotatedType[] annotatedInterfaces = cls.getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass = cls.getAnnotatedSuperclass();
        for (AnnotatedType t :
                annotatedInterfaces){
            Annotation[] declaredAnnotations = t.getDeclaredAnnotations();
            for (Annotation a :
                    declaredAnnotations) {
                logger.info("annotation: {}",a.annotationType());
            }

            logger.info("{}",t.getType().getTypeName());

        }


        assert assignableFrom;

        cls = ArrayList.class;
        assert !cls.isInstance(1);
        assert cls.isInstance(new ArrayList<Integer>());


        printAllSuperType(ArrayList.class);
        TypeVariable[] typeParameters = ArrayList.class.getTypeParameters();
        print(typeParameters);
    }





    public void printAllSuperClass(Class clazz){
        Class cls = clazz;
        Class superCls = null;
        while ((superCls = cls.getSuperclass())!=null){
            cls = superCls;
            logger.info("\t{} has super class --> {}",cls,superCls);
        }
    }
    public void printAllSuperType(Class clazz){
        Class cls = clazz;
        Type superCls = null;
        if ((superCls = cls.getGenericSuperclass())!=null){

            logger.info("\t{} has super class --> {}",cls,superCls);
        }
    }

    private void print(Object array){
        for (int i = 0; i < Array.getLength(array); i++) {
           logger.info("position: {} - value : {}", i,Array.get(array,i).toString());
        }
    }


    @Test
    public void detailClassTest(){
        Class claz = UserBeanSubClass.class;
        Class[] interfaces = claz.getInterfaces();

        print(interfaces);
        Type[] genericInterfaces =  claz.getGenericInterfaces();

        for (Type c: genericInterfaces)
        {

                    logger.info("type :{}", ((ParameterizedType)c).getActualTypeArguments());

        }
        print(genericInterfaces);
        for (Class cl: interfaces){
            boolean anonymousClass = cl.isAnonymousClass();//匿名类
            boolean anInterface = cl.isInterface();         // 接口类
            boolean localClass = cl.isLocalClass();         //
            boolean memberClass = cl.isMemberClass();       // 成员类
            boolean primitive = cl.isPrimitive();
            int modifiers = cl.getModifiers();
            int r = Modifier.interfaceModifiers();
            int i = Modifier.classModifiers();

            logger.info("interface modifiers {}",Integer.toBinaryString(r) );
            logger.info("class modifiers {}",Integer.toBinaryString(i) );

            boolean synthetic = cl.isSynthetic();
            logger.info(" {} {} {} {} {} {} " +
                    "{}",anonymousClass,anInterface,localClass,memberClass,primitive,synthetic);
        }


    }



    @Test
    public void testClass(){
        Class<UserBeanSubClass> userBeanSubClassClass = UserBeanSubClass.class;

        Class<Class> classClass = Class.class;

        Method[] declaredMethods = classClass.getDeclaredMethods();
        for (Method m :
                declaredMethods) {
            TypeVariable<Method>[] typeParameters = m.getTypeParameters();
            Class<?>[] parameterTypes = m.getParameterTypes();
            print(parameterTypes);
            print(typeParameters);
            logger.info("Method  -->  【{}】 ", m.getName());
            if (parameterTypes!=null&& parameterTypes.length == 0) {
                try {
                    Object invoke = m.invoke(userBeanSubClassClass);
                    logger.info("\t invoke --> {}",invoke==null?null:invoke.getClass());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Test
    public void testSimple(){
        URL resource = Class.class.getResource("http://www.baidu.com");


        //Class<?> proxyClass = Proxy.getProxyClass(ClassLoader.getSystemClassLoader(), Codable.class);

        //byte[] bytes = ProxyGenerator.generateProxyClass("com.kong.support.generate.$Proxy1", new Class[]{Codable.class});

        Codable o = (Codable) Proxy.newProxyInstance(Class.class.getClassLoader(),
                new Class[]{Codable.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });



    }

}
