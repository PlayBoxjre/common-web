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

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File Name Event
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-30
 * EMAIL     playboxjre@Gmail.com
 */
public class Event<T> {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private int eventId;
    private String eventUUID;
    //private EVENT_TYPE eventType;
    private T data;


    public Event(){
        eventId = atomicInteger.incrementAndGet();
        eventUUID = UUID.randomUUID().toString();
    }

    public int getEventId() {
        return eventId;
    }


    public String getEventUUID() {
        return eventUUID;
    }

   //
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

   /* public static enum  EVENT_TYPE{
        READ,WRITE,CONNECTED,ACCEPT,READ_ASYNC,WRITE_ASYNC,READ_WRITE_ASYNC;
    }*/
}
