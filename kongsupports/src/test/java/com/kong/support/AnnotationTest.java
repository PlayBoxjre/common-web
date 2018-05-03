package com.kong.support;

import com.kong.support.annotations.documents.Author;
import com.kong.support.annotations.documents.Authors;
import com.kong.support.classes.Function1;
import com.kong.support.classes.Function2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.*;
import java.util.Objects;

/**
 * PROJECT     :   common-web
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/5/3 23:09 星期四
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class AnnotationTest {
    Logger logger = LoggerFactory.getLogger(AnnotationTest.class);
    @Test
    public void testInher() throws IllegalAccessException, InvocationTargetException {

       // assert declaredAnnotations == null || declaredAnnotations.length == 0;

         Annotation[] declaredAnnotations = Function2.class.getAnnotations();
        for (Annotation annotation : declaredAnnotations){
            System.out.printf("-- %s :  \n",annotation.annotationType() );
            Method[] methods = annotation.annotationType().getDeclaredMethods();
            for (Method method : methods){
                logger.info("method : {}",method.getName());
                Class<?> returnType = method.getReturnType();
                logger.info("return type : {}",returnType.getCanonicalName());
                boolean isArray = returnType.isArray();
                logger.info("is array :{}",isArray);

                if (isArray) {// is array
                    Class<?> componentType = returnType.getComponentType();
                    logger.info("conponent type : {}", componentType.getCanonicalName());
                    if (componentType.isAnnotation()){
                        Object cast = method.invoke(annotation);
                        int length = Array.getLength(cast);
                        for (int i = 0; i < length; i++) {
                            Object o = Array.get(cast, i);
                            Method[] methods1 = componentType.getDeclaredMethods();
                            for (Method method1 : methods1) {
                                logger.info(" method :  {}",method1.getName());
                                System.out.printf("\t%s = %s \n", method1.getName(), method1.invoke(o));
                            }
                        }// for i
                    }// component  is anno

                }else{ // is anno

                }


            }
        }
    }

}
