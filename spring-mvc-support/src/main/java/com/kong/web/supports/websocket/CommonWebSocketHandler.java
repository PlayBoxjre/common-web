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

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.socket.*;

/**
 * File Name CommonWebSocketHandler
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-06
 * EMAIL     playboxjre@Gmail.com
 * websocket的连接处理类
 */
public class CommonWebSocketHandler implements WebSocketHandler {
    Logger logger = LoggerFactory.getLogger(CommonWebSocketHandler.class);


     private static JmsTemplate jmsTemplate;

     private static ActiveMQQueue destination;

     @Autowired
    public static void setJmsTemplate(JmsTemplate jmsTemplate) {
        CommonWebSocketHandler.jmsTemplate = jmsTemplate;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("新连接建立");
        TextMessage textMessage = new TextMessage("建立连接");
        session.sendMessage(textMessage);
         jmsTemplate.convertAndSend(textMessage.getPayload());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.debug("获取消息：{}",message.getPayload());
        TextMessage textMessage = new TextMessage(message.getPayload().toString());
        session.sendMessage(textMessage);
        jmsTemplate.convertAndSend(message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.debug("发生错误信息");
        TextMessage message = new TextMessage("发生错误信息");
        session.sendMessage(message);
        jmsTemplate.convertAndSend(message.getPayload());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("连接断开");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
