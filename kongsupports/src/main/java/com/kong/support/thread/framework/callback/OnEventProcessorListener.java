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

package com.kong.support.thread.framework.callback;

import com.kong.support.thread.framework.Event;
import com.kong.support.thread.framework.EventBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name OnEventProcessorListener
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public interface OnEventProcessorListener {
    Logger logger = LoggerFactory.getLogger(OnEventProcessorListener.class);

    /**
     *
     * 事件处理
     * 如果事件设置，则跳过通用的处理
     * @param event
      */
    public EventBundle onEventProcessor(Event event);
}
