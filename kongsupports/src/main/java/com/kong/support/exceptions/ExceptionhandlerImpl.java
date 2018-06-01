/*
 * Copyright [2018] [ kong&xiang ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kong.support.exceptions;

import com.kong.support.exceptions.socket.SocketBaseException;
import com.kong.support.socket.nio.server.SocketSession;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * File Name ExceptionhandlerImpl
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-01
 * EMAIL     playboxjre@Gmail.com
 */
public class ExceptionhandlerImpl implements ExceptionHandler {
    
    private Map<Class<?>,OnExceptionHandleProcesser> exceptionMap;
    
    
    @Override
    public Map<Class<?>, OnExceptionHandleProcesser> getExceptionMap() {
        return exceptionMap;
    }

    @Override
    public boolean onPreHandler(Exception ex) {
        return false;
    }

    @Override
    public final boolean exceptionHandler(Exception ex) {
        assert ex !=null;
        boolean handler = false;
        if (onPreHandler(ex))return true;
        Map<Class<?>, OnExceptionHandleProcesser> exceptionMap = getExceptionMap();
        if (exceptionMap!=null) {
            OnExceptionHandleProcesser onExceptionHandleProcesser = exceptionMap.get(ex.getClass());
            if (onExceptionHandleProcesser !=null){
               handler =  onExceptionHandleProcesser.onExceptionHandler(ex);
            }
        }
        if (!handler){
            if (ex instanceof SocketBaseException){
                SocketBaseException ex1 = (SocketBaseException) ex;
                SocketSession socketSession = ex1.getSocketSession();
                if (socketSession==null)
                return true;
                SocketChannel socketChannel = socketSession.getSocketChannel();
                if (socketChannel!=null){
                    try {
                        socketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    socketSession.close();
                    ex1.setSocketSession(null);
                }
            }else {
                ex.printStackTrace();
            }
            handler = true;
        }

        return handler;
    }

    public ExceptionhandlerImpl() {
    }

    public ExceptionhandlerImpl(Map<Class<?>, OnExceptionHandleProcesser> exceptionMap) {
        this.exceptionMap = exceptionMap;
    }

    public void setExceptionMap(Map<Class<?>, OnExceptionHandleProcesser> exceptionMap) {
        this.exceptionMap = exceptionMap;
    }
}
