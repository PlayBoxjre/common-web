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

package com.kong.support.thread.framework.core;

import com.kong.support.thread.framework.Event;
import com.kong.support.thread.framework.EventBundle;
import com.kong.support.thread.framework.EventType;
import com.kong.support.thread.transitpoint.EventTransitPointManager;
import com.kong.support.thread.framework.callback.*;
import com.kong.support.toolboxes.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Predicate;

/**
 * File Name EventRunnable
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 *
 * 线程处理优先级
 * 用户Event级的 EventTask > 创建Thread级的 EventTask > 全局配置的 处理周期监听事件
 * @see Event
 * @see com.kong.support.thread.framework.core.ThreadPoolManagerImpl
 * @see EventTransitPointManager
 *
 */
public class EventRunnable extends LoopRunnable{
    Logger logger = LoggerFactory.getLogger(EventRunnable.class);
    private String name;
    private EventType eventType;
    private EventTransitPointManager transitPointManager;
    private OnPostEventListener onPostEventListener;
    private OnPreEventListener onPreEventListener;
    private OnEventProcessorListener onEventProcessorListener;
    private OnEventCancelListener onEventCancelListener;
    private boolean isShutDown;
    private Predicate<Boolean> onCreateSuccess;
    private Statistic statistic;


    public EventRunnable(String name, EventTransitPointManager transitPointManager, OnEventProcessorListener onEventProcessorListener ) {
        this(name,transitPointManager,null,onEventProcessorListener,null,null,false,null);
    }

    public EventRunnable(String name, EventTransitPointManager transitPointManager, OnPreEventListener onPreEventListener, OnEventProcessorListener onEventProcessorListener, OnPostEventListener onPostEventListener) {
        this(name,transitPointManager,onPreEventListener,onEventProcessorListener,onPostEventListener,null,false,null);
    }

    public EventRunnable(String name, EventTransitPointManager transitPointManager, OnPreEventListener onPreEventListener, OnEventProcessorListener onEventProcessorListener, OnPostEventListener onPostEventListener, OnEventCancelListener onEventCancelListener) {
        this(name,transitPointManager,onPreEventListener,onEventProcessorListener,onPostEventListener,onEventCancelListener,false,null);
     }

    public EventRunnable(String name, EventTransitPointManager transitPointManager, OnPreEventListener onPreEventListener, OnEventProcessorListener onEventProcessorListener, OnPostEventListener onPostEventListener, OnEventCancelListener onEventCancelListener, boolean isShutDown, Predicate<Boolean> onCreateSuccess) {
        this.name = name;
        this.transitPointManager = transitPointManager;
        this.onPostEventListener = onPostEventListener;
        this.onPreEventListener = onPreEventListener;
        this.onEventProcessorListener = onEventProcessorListener;
        this.onEventCancelListener = onEventCancelListener;
        this.isShutDown = isShutDown;
        this.onCreateSuccess = onCreateSuccess;
    }

    @Override
    protected String configThreadName() {
        return this.name;
    }

    @Override
    protected void preHandler() {
        if (onCreateSuccess!=null) {
            boolean test = onCreateSuccess.test(true);
        }
    }

    @Override
    public boolean loopCondition() {
        return !isShutDown && !Thread.currentThread().isInterrupted();
    }

    @Override
    public void loopFrame() throws Exception {
        if (statistic!=null){
            statistic.increment(Thread.currentThread().getName());
        }
        //根据事件类型获取事件，如果没有根据 TransitPointManager的策略来决定是否返回 TODO
        Event event = transitPointManager.outEvent(eventType);
        if (event != null) {
            if (event.isCancel()){
                onEventCancelListener.onEventCancel(event);
                return;
            }
            EventTask userProcessor = event.getEventTask();
            EventBundle eventBundle = null;
            if (userProcessor != null){
                UserDefineEventTask(event, userProcessor);
                return;
            }else if(this.onEventProcessorListener != null){
                if (this.onPreEventListener != null)
                    onPreEventListener.onPre(event);
                eventBundle = this.onEventProcessorListener.onEventProcessor(event);
            }else{
                if (event.isCancel()){
                    onEventCancelListener.onEventCancel(event);
                    return;
                }
                if (onPostEventListener!=null)
                    onPostEventListener.onError(new IllegalArgumentException("线程没有执行体错误"));
                isShutDown = true;
                return;
            }
            if (event.isCancel()){
                onEventCancelListener.onEventCancel(event);
                return;
            }
            if (onPostEventListener !=null )
                onPostEventListener.onPost(eventBundle);
        }
    }




    /**
     * 用户自定义单个事件处理过程
     * @param event
     * @param userProcessor
      * @return
     */
    private void UserDefineEventTask(Event event, EventTask userProcessor) {
        try {
            userProcessor.onPre(event);
            EventBundle eventBundle = userProcessor.onEventProcessor(event);
            if (event.isCancel())
                userProcessor.onEventCancel(event);
            userProcessor.onPost(eventBundle);
        }catch (Exception ex){
            if (event.isCancel())
                userProcessor.onEventCancel(event);
            userProcessor.onError(ex);
        }
    }

    public void shutDown(){
        if(!Thread.currentThread().isInterrupted())
            Thread.currentThread().interrupt();
        isShutDown = true;
    }

    public OnEventCancelListener getOnEventCancelListener() {
        return onEventCancelListener;
    }

    public void setOnEventCancelListener(OnEventCancelListener onEventCancelListener) {
        this.onEventCancelListener = onEventCancelListener;
    }

    // --- getter -- setter --
    public EventTransitPointManager getTransitPointManager() {
        return transitPointManager;
    }

    public void setTransitPointManager(EventTransitPointManager transitPointManager) {
        this.transitPointManager = transitPointManager;
    }

    public OnPostEventListener getOnPostEventListener() {
        return onPostEventListener;
    }

    public void setOnPostEventListener(OnPostEventListener onPostEventListener) {
        this.onPostEventListener = onPostEventListener;
    }

    public OnPreEventListener getOnPreEventListener() {
        return onPreEventListener;
    }

    public void setOnPreEventListener(OnPreEventListener onPreEventListener) {
        this.onPreEventListener = onPreEventListener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OnEventProcessorListener getOnEventProcessorListener() {
        return onEventProcessorListener;
    }

    public void setOnEventProcessorListener(OnEventProcessorListener onEventProcessorListener) {
        this.onEventProcessorListener = onEventProcessorListener;
    }

    public boolean isShutDown() {
        return isShutDown;
    }

    public void setShutDown(boolean shutDown) {
        isShutDown = shutDown;
    }

    public void setOnCreateSuccess(Predicate<Boolean> onCreateSuccess) {
        this.onCreateSuccess = onCreateSuccess;
    }

    public Predicate<Boolean> getOnCreateSuccess() {
        return onCreateSuccess;
    }


    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
