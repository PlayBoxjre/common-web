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

import com.kong.support.socket.nio.callbacks.OnEventDispatcherListener;
import com.kong.support.socket.nio.callbacks.OnSocketConnectionListener;

/**
 * File Name SocketContext
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-30
 * EMAIL     playboxjre@Gmail.com
 */
public class SocketContext {
    /**
     * 事件分发监听
     */
    private OnEventDispatcherListener onEventDispatcherListener;

    /**
     * socket 建立连接监听
     */
    private OnSocketConnectionListener onSocketConnectionListener;

    public OnSocketConnectionListener getOnSocketConnectionListener() {
        return onSocketConnectionListener;
    }

    public void setOnSocketConnectionListener(OnSocketConnectionListener onSocketConnectionListener) {
        this.onSocketConnectionListener = onSocketConnectionListener;
    }

    public OnEventDispatcherListener getOnEventDispatcherListener() {
        return onEventDispatcherListener;
    }

    public void setOnEventDispatcherListener(OnEventDispatcherListener onEventDispatcherListener) {
        this.onEventDispatcherListener = onEventDispatcherListener;
    }
}
