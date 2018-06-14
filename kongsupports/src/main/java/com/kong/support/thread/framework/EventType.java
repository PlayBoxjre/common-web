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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File Name EventType
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-11
 * EMAIL     playboxjre@Gmail.com
 * 事件类型类
 * 用户可指定配置
 */

public class EventType {
    Logger logger = LoggerFactory.getLogger(EventType.class);
    AtomicInteger atomicInteger = new AtomicInteger();

    private static Map<String,EventType> map =new HashMap<>();
    public  static EventType createOrGetEventType(String eventTypeName,int eventPriority,String description){
        assert eventTypeName != null;
        synchronized (EventType.class){
            EventType eventType = map.get(eventTypeName);
            if (eventType == null) {
                eventType = new EventType(eventTypeName,eventPriority,description);
                map.put(eventTypeName,eventType);
            }
            return eventType;
        }
    }

    public  static EventType createOrGetEventType(String eventTypeName,String description){
       return createOrGetEventType(eventTypeName,5,description);
    }

    public  static EventType createOrGetEventType(String eventTypeName){
        return createOrGetEventType(eventTypeName,5,eventTypeName);
    }

    public EventType(  String eventTypeName, int eventPriority, String description) {
        this.code = atomicInteger.incrementAndGet();
        this.eventTypeName = eventTypeName;
        this.eventPriority = eventPriority;
        this.description = description;
    }

    /**
     * 事件编号
     */
    private int code;
    /**
     * 事件类型名
     */
    private String eventTypeName;

    /**
     * 事件优先级 0 - 10  0 最低 10 最高 5 默认
     */
    private int eventPriority = 5;
    /**
     * 事件类型说明
     */
    private String description;

    public int getCode() {
        return code;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public int getEventPriority() {
        return eventPriority;
    }

    public String getDescription() {
        return description;
    }
}
