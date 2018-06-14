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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * File Name EventTransitPoint
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 */
public class EventTransitPoint extends AbstractTransitPoint<Event,Event> {
    Logger logger = LoggerFactory.getLogger(EventTransitPoint.class);

    private TransitPointRecorder transitPointRecorder;

    public EventTransitPoint(int capacity, String transitPointName, int transitPriority, Object flag) {
        super(capacity, transitPointName, transitPriority, flag);
        transitPointRecorder = new TransitPointRecorder();
         transitPointRecorder.setCapacity(capacity);
         transitPointRecorder.setCreateDate(new Date());
         transitPointRecorder.setTransitPointName(transitPointName);
         transitPointRecorder.setFlag(flag);
    }


    @Override
    public synchronized void consume(Event thing) throws InterruptedException {
        super.consume(thing);
        transitPointRecorder.incrementTotalEvents();
        transitPointRecorder.incrementEventTypeCount(thing.getEventType().getEventTypeName());
     }

    @Override
    public final Event transit(Event thing) {
        return thing;
    }

    @Override
    public synchronized Event product() throws InterruptedException {
        Event product = super.product();
        transitPointRecorder.incrementTotalProcessEvents();
        return product;
    }

    public TransitPointRecorder getTransitPointRecorder() {
        return transitPointRecorder;
    }
}
