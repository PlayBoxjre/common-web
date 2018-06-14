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

import com.kong.support.thread.framework.callback.EventTask;
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
    private boolean cancel;

    public Event(String eventType){
        this.id = atomicInteger.incrementAndGet();
        this.code = UUID.randomUUID().toString();
        this.eventType = EventType.createOrGetEventType(eventType);
    }
    private EventType eventType;
    private EventBundle eventBundle;
    private EventTask eventTask;


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

    public EventTask getEventTask() {
        return eventTask;
    }

    public void setEventTask(EventTask eventTask) {
        this.eventTask = eventTask;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
