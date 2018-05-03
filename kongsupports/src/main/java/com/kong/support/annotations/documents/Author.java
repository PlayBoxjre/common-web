package com.kong.support.annotations.documents;

import java.lang.annotation.*;
import java.util.Date;

/**
 * PROJECT     :   common-web
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/5/3 21:58 星期四
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :   作者注解，用来注释一个 类、方法、字段，（通常是一个类）的作者
 */
@Documented
@Inherited
@Repeatable(Authors.class)
public @interface Author {

    /**
     * 姓名
     * NAME
     * @return
     */
    String chineseName() default "";
    String firstName() default "";
    String lastName() default "";

    /**
     * 年龄
     * @return
     */
    int age() default 0;

    /**
     * 国籍
     * @return
     */
    String nation() default "cn";

    /**
     * 邮箱
     * @return
     */
    String email() default "";

    /**
     * 联系电话
     * @return
     */
    String phone() default "";

    /**
     * 公司
     * @return
     */
    String company() default "";

    /**
     * 生日
     * @return
     */
    String  birth() default "";



}
