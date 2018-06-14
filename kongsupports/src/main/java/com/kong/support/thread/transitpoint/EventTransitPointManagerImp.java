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

package com.kong.support.thread.transitpoint;

import com.kong.support.thread.framework.Event;
import com.kong.support.thread.framework.EventType;
import com.kong.support.thread.transitpoint.callback.OnTransitPointTransitListner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * File Name EventTransitPointManagerImp
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 */
public class EventTransitPointManagerImp implements EventTransitPointManager{
    Logger logger = LoggerFactory.getLogger(EventTransitPointManagerImp.class);

    // 传输点选择器
    //传输点放入者
    @Override
    public void enterEvent(Event event) {

    }

    @Override
    public Event outEvent(String eventType) {
        return null;
    }

    @Override
    public Event outEvent(EventType eventType) {
        return null;
    }

    @Override
    public TransitPoint createTransitPoint(int capacity, String transitPointName, int transitPriority, Object flag, OnTransitPointTransitListner<Event, Event> onTransitPointTransitListner) {
        return null;
    }

    @Override
    public TransitPoint createTransitPoint(String transitPointName, int transitPriority, Object flag, OnTransitPointTransitListner<Event, Event> onTransitPointTransitListner) {
        return null;
    }

    @Override
    public TransitPoint createTransitPoint(String transitPointName, Object flag) {
        return null;
    }

    @Override
    public TransitPoint createTransitPoint(String transitPointName, OnTransitPointTransitListner<Event, Event> onTransitPointTransitListner) {
        return null;
    }

    @Override
    public TransitPoint createTransitPoint(String transitPointName) {
        return null;
    }
}
