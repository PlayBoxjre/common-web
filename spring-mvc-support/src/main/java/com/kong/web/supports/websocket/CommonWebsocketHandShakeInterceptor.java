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

package com.kong.web.supports.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * File Name CommonWebsocketHandShakeInterceptor
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-06
 * EMAIL     playboxjre@Gmail.com
 * websocket 握手
 * 获取http session
 * @see HttpSessionHandshakeInterceptor
 * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer
 */
public class CommonWebsocketHandShakeInterceptor implements HandshakeInterceptor {
    Logger logger = LoggerFactory.getLogger(CommonWebsocketHandShakeInterceptor.class);


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.debug("before handshake :: ");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        logger.debug("after handshake :: ");
    }
}
