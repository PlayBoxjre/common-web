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

import com.kong.support.thread.framework.callback.OnEventProcessorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File Name Event
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-11
 * EMAIL     playboxjre@Gmail.com
 */
public class Event {
    static AtomicInteger atomicInteger = new AtomicInteger();
    Logger logger = LoggerFactory.getLogger(Event.class);
    private int id;
    private String code;

    public Event(String eventType){
        this.id = atomicInteger.incrementAndGet();
        this.code = UUID.randomUUID().toString();
        this.eventType = new EventType();
        this.eventType.setEventTypePriority(5);
        this.eventType.setEventTypeName(eventType);
        this.eventType.setDescription("This is "+ eventType);
    }
    private EventType eventType;
    private EventBundle eventBundle;
    private OnEventProcessorListener onEventProcessorListener;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventBundle getEventBundle() {
        return eventBundle;
    }

    public void setEventBundle(EventBundle eventBundle) {
        this.eventBundle = eventBundle;
    }

    public OnEventProcessorListener getOnEventProcessorListener() {
        return onEventProcessorListener;
    }

    public void setOnEventProcessorListener(OnEventProcessorListener onEventProcessorListener) {
        this.onEventProcessorListener = onEventProcessorListener;
    }
}
