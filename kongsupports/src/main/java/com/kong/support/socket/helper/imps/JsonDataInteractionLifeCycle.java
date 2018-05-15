package com.kong.support.socket.helper.imps;

import com.google.gson.Gson;
import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.socket.DataParserException;

import java.nio.charset.Charset;

public class JsonDataInteractionLifeCycle<I> extends AbstractDataInteractionLifeCycle<I> {



    @Override
    public String format(I dataObject, Charset charset) throws ClassFormatException {
        String s = new Gson().toJson(dataObject);
        return s;
    }

    @Override
    public I parse(Class<I> t, byte[] text, Charset charset) throws DataParserException {

        return new Gson().fromJson(new String(text,charset),t);
    }
}
