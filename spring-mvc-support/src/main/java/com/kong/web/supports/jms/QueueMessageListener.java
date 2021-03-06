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

package com.kong.web.supports.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * File Name QueueMessageListener
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-06
 * EMAIL     playboxjre@Gmail.com
 */
public class QueueMessageListener implements MessageListener {
    Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage message1 = (TextMessage) message;
            try {
                logger.debug(" queue message receive : {}",message1.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
