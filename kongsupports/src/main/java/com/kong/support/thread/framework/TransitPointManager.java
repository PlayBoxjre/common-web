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

package com.kong.support.thread.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name TransitPointManager
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public interface TransitPointManager {
    Logger logger = LoggerFactory.getLogger(TransitPointManager.class);

    public void enterEvent(Event event);

    public Event outEvent();

    /**
     * 一个事件类型绑定的传输点
     * 可以用来只对于某一类的事件进行传输
     * @param eventType
     * @return
     */
    public TransitPoint createTransitPoint(EventType eventType);
    /**
     * 一个事件类型绑定的传输点
     * 可以用来只对于某一类的事件进行传输
     * @param eventType
     * @return
     */
    public TransitPoint createTransitPoint(String eventType);

    /**
     * 创建一个默认传输点，可以正对任何的事件进行传输
     * 默认连接点的事件类型为 default
     * @return
     */
    public TransitPoint createTransitPoint();



}
