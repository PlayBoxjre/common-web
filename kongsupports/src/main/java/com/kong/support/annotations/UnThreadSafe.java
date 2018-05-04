package com.kong.support.annotations;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
public @interface UnThreadSafe {
    String value() default "";
}
