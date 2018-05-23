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

package com.kong.support.socket;

import com.kong.support.socket.nio.callbacks.OnSocketConnectionListener;

/**
 * File Name SocketConfiguration
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-22
 * EMAIL     playboxjre@Gmail.com
 */
public class SocketConfiguration {


    private boolean blocking;
    private int port;
    private OnSocketConnectionListener socketConnectionListener;

    public boolean isBlocking() {
        return blocking;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public OnSocketConnectionListener getSocketConnectionListener() {
        return socketConnectionListener;
    }

    public void setSocketConnectionListener(OnSocketConnectionListener socketConnectionListener) {
        this.socketConnectionListener = socketConnectionListener;
    }
}
