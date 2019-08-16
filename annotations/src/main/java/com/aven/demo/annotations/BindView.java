package com.aven.demo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ${Aven.Gong} on 2019/7/1 0001.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface BindView {
    int value() default 1;
}
