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

/**
 * File Name EventTransitPointManager
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public interface EventTransitPointManager {
    Logger logger = LoggerFactory.getLogger(EventTransitPointManager.class);

    public void enterEvent(Event event);


    public Event outEvent(String eventType);
    /**
     * 查找 事件类型
     * @param eventType
     * @return
     */
    public Event outEvent(EventType eventType);

    /**
     * 创建一个事件传输点 （阻塞队列）队列满放 或 队列空取 将会阻塞
     * @param capacity      队列容量
     * @param transitPointName  传输点名字
     * @param transitPriority   传输点的优先级 数值越大 ，越高
     *                          这个是用来管理器选择next event事件时所要考虑的权重
     * @param flag              传输点的特征标记（ 用来表示传输点的一些性质）如：传输点只对某一类事件传输
     * @param onTransitPointTransitListner 如果设置，将会替代方法 transit() 完成 产品的生产传输
     * @return                  最终产品（外部使用）
     */
    public TransitPoint createTransitPoint(int capacity, String transitPointName, int transitPriority, Object flag,OnTransitPointTransitListner<Event,Event> onTransitPointTransitListner);

    public TransitPoint createTransitPoint(String transitPointName, int transitPriority, Object flag,OnTransitPointTransitListner<Event,Event> onTransitPointTransitListner);

    public TransitPoint createTransitPoint(String transitPointName, Object flag);

    public TransitPoint createTransitPoint(String transitPointName,OnTransitPointTransitListner<Event,Event> onTransitPointTransitListner);

    public TransitPoint createTransitPoint(String transitPointName);

}
