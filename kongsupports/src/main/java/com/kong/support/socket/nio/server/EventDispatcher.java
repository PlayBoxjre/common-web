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

import com.kong.support.exceptions.socket.SocketSessionException;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * File Name EventDispatcher
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-30
 * EMAIL     playboxjre@Gmail.com
 * @see
 */
public interface EventDispatcher {

    public void dispatchEvent(SocketContext socketContext,Event<SelectionKey> event) throws SocketSessionException, IOException;
}
