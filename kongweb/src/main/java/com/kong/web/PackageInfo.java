package com.kong.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageInfo {
    String name() default "";

    String value() default "";

    String description() default "";

    String author() default "";

    String license() default "";

    String creatime() default "";
}
