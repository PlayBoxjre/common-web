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

package com.kong.support.socket.helper.send;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kong.support.socket.SocketContext;
import com.kong.support.socket.nio.server.SocketSession;

/**
 * File Name SocketResponse
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-31
 * EMAIL     playboxjre@Gmail.com
 */
public class SocketResponse {
    @JsonIgnore
    private SocketSession session;

    @JsonIgnore
    private SocketContext socketContext;

    @JsonInclude
    private int code ;
    @JsonInclude
    private String message;
    @JsonInclude
    private byte[] data;

    public SocketContext getSocketContext() {
        return socketContext;
    }

    public void setSocketContext(SocketContext socketContext) {
        this.socketContext = socketContext;
    }

    public SocketSession getSession() {
        return session;
    }

    public void setSession(SocketSession session) {
        this.session = session;
    }

    public void setData(byte[] data){
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
