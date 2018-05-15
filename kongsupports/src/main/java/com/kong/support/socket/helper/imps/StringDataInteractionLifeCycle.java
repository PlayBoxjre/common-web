package com.kong.support.socket.helper.imps;

import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.socket.DataParserException;

import java.nio.charset.Charset;

public class StringDataInteractionLifeCycle extends AbstractDataInteractionLifeCycle<String> {


    @Override
    public String format(String dataObject, Charset charset) throws ClassFormatException {
        return dataObject;
    }

    @Override
    public String parse(Class<String> t, byte[] text, Charset charset) throws DataParserException {
        return new String(text,charset);
    }
}
