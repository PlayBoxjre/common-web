package com.kong.support.resources.defines;

import com.kong.support.exceptions.ObjectConvertException;

/**
 * 转换器
 */
public interface Transverter<T,R> {

    public R convert(T t) throws ObjectConvertException;
}
