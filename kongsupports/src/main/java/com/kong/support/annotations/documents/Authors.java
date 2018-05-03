package com.kong.support.annotations.documents;

import java.lang.annotation.*;

/**
 * PROJECT     :   common-web
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/5/3 22:30 星期四
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD,ElementType.PACKAGE})
@Author(chineseName = "孔")
public @interface Authors {
    Author[] value();
}
