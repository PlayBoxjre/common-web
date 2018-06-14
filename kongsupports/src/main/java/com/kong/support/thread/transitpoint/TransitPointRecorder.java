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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File Name TransitPointRecorder
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 */
public class TransitPointRecorder {
    Logger logger = LoggerFactory.getLogger(TransitPointRecorder.class);
    /**
     * 传输点容量
     */
    private int capacity;
    /**
     * 传输点名称
     */
    private String transitPointName;
    /**
     * 传输点标记特征
     */
    private Object flag;

    /**
     * 提供给外部的总个数
     */
    private AtomicInteger totalProcessEvents = new AtomicInteger();
    /**
     * 总共入列的数量
     */
    private AtomicInteger totalEvents = new AtomicInteger();
    /**
     * 传输点创建事件
     */
    private Date createDate;
    /**
     * 传输点处理的事件类型个数统计
     */
    private Map<String,Integer> eventTypeCount = new HashMap<>();







    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTransitPointName() {
        return transitPointName;
    }

    public void setTransitPointName(String transitPointName) {
        this.transitPointName = transitPointName;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public int getTotalProcessEvents() {
        return totalProcessEvents.get();
    }

    public void incrementTotalProcessEvents() {
        this.totalProcessEvents.incrementAndGet();
    }

    public int getTotalEvents() {
        return totalEvents.get();
    }

    public void incrementTotalEvents() {
        this.totalEvents.incrementAndGet();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getEventTypeCount(String eventType) {
        Integer integer = eventTypeCount.get(eventType);
        if (integer == null)
            return 0;
        else
            return integer;
    }

    public void incrementEventTypeCount(String eventType){
        Integer integer = eventTypeCount.get(eventType);
        if (integer == null){
            eventTypeCount.put(eventType,1);
        }else{
            eventTypeCount.put(eventType,integer +1);
        }
    }

}
