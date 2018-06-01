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

package com.kong.support.socket.nio.callbacks;

import com.kong.support.socket.helper.send.SocketResponse;
import com.kong.support.socket.nio.server.SocketSession;

/**
 * File Name OnAfterAcceptDataListener
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-01
 * EMAIL     playboxjre@Gmail.com
 * 在服务端接收到一段数据之后调用
 */
public interface OnAfterAcceptDataListener {
    /**
     * 处理从客户端获取的二进制数据
     * @param socketSession
     * @param bytes
     * @param charset
     * @return
     */
    public SocketResponse onAfterAcceptData(SocketSession socketSession, byte[] bytes, String charset);
}
