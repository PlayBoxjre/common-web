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

package com.kong.support.socket.nio;

import com.kong.support.exceptions.OnExceptionHandleProcesser;
import com.kong.support.exceptions.socket.SocketBaseException;
import com.kong.support.exceptions.socket.SocketDisconnectionException;
import com.kong.support.socket.nio.server.SocketSession;

import java.io.IOException;

/**
 * File Name SocketDisconnectedExceptionHandler
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-01
 * EMAIL     playboxjre@Gmail.com
 */
public class SocketDisconnectedExceptionHandler implements OnExceptionHandleProcesser {
    @Override
    public boolean onExceptionHandler(Exception ex) {
        if (ex instanceof SocketBaseException){
            SocketBaseException exception = (SocketBaseException) ex;
            SocketSession socketSession = exception.getSocketSession();
            if (socketSession == null)
                return true;
            if (socketSession.getSocketChannel()!=null){
                try {
                    socketSession.getSocketChannel().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketSession.close();
            return true;
        }
        return false;
    }
}
