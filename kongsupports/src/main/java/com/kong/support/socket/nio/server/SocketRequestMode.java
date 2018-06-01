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

/**
 * File Name SocketRequestMode
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-01
 * EMAIL     playboxjre@Gmail.com
 * socket 连接的模式
 * -1。 连接一次成功返回断开 （http-mode)
 * -2. 一次长连接，可返回大数据 (socket-mode)
 * -3. 一次长连接，但每次返回数据限制大小 (socket-limit-mode)
 */
public enum  SocketRequestMode  {
    HTTP_MODE,
    SOCKET_LIMIT_MODE,
    SOCKET_UNLIMIT_MODE
}
