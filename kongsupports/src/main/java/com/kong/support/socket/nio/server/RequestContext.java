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

package com.kong.support.socket.nio.server;

import com.kong.support.socket.SocketContext;
import com.kong.support.socket.nio.request.RequestHeader;


/**
 * File Name RequestContext
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-01
 * EMAIL     playboxjre@Gmail.com
 */
public class RequestContext {
    private SocketSession socketSession;
    private SocketContext socketContext;
    private RequestHeader requestHeader;


    public SocketSession getSocketSession() {
        return socketSession;
    }

    public void setSocketSession(SocketSession socketSession) {
        this.socketSession = socketSession;
    }

    public SocketContext getSocketContext() {
        return socketContext;
    }

    public void setSocketContext(SocketContext socketContext) {
        this.socketContext = socketContext;
    }


    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
}
