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

import com.kong.support.thread.framework.*;
import com.kong.support.thread.framework.callback.*;
import com.kong.support.thread.transitpoint.EventTransitPointManager;
import com.kong.support.toolboxes.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * File Name ThreadPoolManagerImpl
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public class ThreadPoolManagerImpl implements ThreadPoolManager {
    Logger logger = LoggerFactory.getLogger(ThreadPoolManagerImpl.class);
    private static final boolean PRINT_STATISTIC = false;
    private Statistic statistic;

    private Map<String,Runnable> threadRecorder= new ConcurrentHashMap<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private EventTransitPointManager transitPointManager;

    private OnPreEventListener onPreEventListener;

    private OnPostEventListener onPostEventListener;

    private OnEventProcessorListener onEventProcessorListener;

    private OnEventCancelListener onEventCancelListener;


    @Override
    public EventRunnable createEventThread(String threadName, Predicate<Boolean> onSuccessCreate) {
        return createEventThread(threadName,null,onSuccessCreate);
    }

    @Override
    public EventRunnable createEventThread(String threadName, String eventType, Predicate<Boolean> onSuccessCreate) {
      return createEventThread(threadName,eventType,null,onSuccessCreate);
    }

    @Override
    public EventRunnable createEventThread(String ThreadName, String eventType, EventTask eventTask, Predicate<Boolean> onSuccessCreate) {
        if (ThreadName == null)
            throw new NullPointerException("线程名字不能为null");
        if (checkThreadExist(ThreadName)){
            logger.error("thread has exist :{}",ThreadName);
            throw new IllegalArgumentException("线程 "+ ThreadName +" 已经存在");
        }
        final OnPreEventListener onPreEventListener;
        final OnEventProcessorListener onEventProcessorListener;
        final OnPostEventListener onPostEventListener;
        final OnEventCancelListener onEventCancelListener;
        if (eventTask==null){
            onPreEventListener = this.onPreEventListener;
            onEventCancelListener = this.onEventCancelListener;
            onPostEventListener = this.onPostEventListener;
            onEventProcessorListener = this.onEventProcessorListener;
        }else{
            onPreEventListener = eventTask;
            onEventCancelListener = eventTask;
            onPostEventListener = eventTask;
            onEventProcessorListener = eventTask;
        }
        EventRunnable eventThread = new EventRunnable(ThreadName,transitPointManager,onEventProcessorListener);
        eventThread.setOnPreEventListener(onPreEventListener);
        eventThread.setOnPostEventListener(onPostEventListener);
        eventThread.setOnEventCancelListener(onEventCancelListener);
        eventThread.setShutDown(false);
        eventThread.setOnCreateSuccess(onSuccessCreate);
        eventThread.setStatistic(statistic);
        if (eventType != null)
            eventThread.setEventType(EventType.createOrGetEventType(eventType));
        executorService.execute(eventThread);
        this.threadRecorder.put(ThreadName,eventThread);
        return eventThread;
    }

    @Override
    public void executeRunnable(Runnable runnable) {
        executorService.execute(runnable);
    }


    @Override
    public EventRunnable pickEventThread(String name) {
        return (EventRunnable) this.threadRecorder.get(name);
    }

    private boolean checkThreadExist(String name) {
        return threadRecorder.get(name) != null;
    }

    @Override
    public void destroy(EventRunnable eventThread) {
        //printStatistic();
        eventThread.setShutDown(true);
        eventThread.shutDown();
        threadRecorder.remove(eventThread.getName());
    }

    @Override
    public void destroy(String threadName) {
        //printStatistic();
        Runnable runnable = threadRecorder.remove(threadName);
        if (runnable == null)
            throw new NullPointerException(threadName +"线程不存在");
        if (runnable instanceof EventRunnable) {
            EventRunnable runnable1 = (EventRunnable) runnable;
            runnable1.setShutDown(true);
            runnable1.shutDown();
        }
    }

    public Map<String, Runnable> getThreadRecorder() {
        return threadRecorder;
    }

    public void setThreadRecorder(Map<String, Runnable> threadRecorder) {
        this.threadRecorder = threadRecorder;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public EventTransitPointManager getTransitPointManager() {
        return transitPointManager;
    }

    public void setTransitPointManager(EventTransitPointManager transitPointManager) {
        this.transitPointManager = transitPointManager;
    }

    public OnPreEventListener getOnPreEventListener() {
        return onPreEventListener;
    }

    public void setOnPreEventListener(OnPreEventListener onPreEventListener) {
        this.onPreEventListener = onPreEventListener;
    }

    public OnPostEventListener getOnPostEventListener() {
        return onPostEventListener;
    }

    public void setOnPostEventListener(OnPostEventListener onPostEventListener) {
        this.onPostEventListener = onPostEventListener;
    }

    public OnEventProcessorListener getOnEventProcessorListener() {
        return onEventProcessorListener;
    }

    public void setOnEventProcessorListener(OnEventProcessorListener onEventProcessorListener) {
        this.onEventProcessorListener = onEventProcessorListener;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public OnEventCancelListener getOnEventCancelListener() {
        return onEventCancelListener;
    }

    public void setOnEventCancelListener(OnEventCancelListener onEventCancelListener) {
        this.onEventCancelListener = onEventCancelListener;
    }
}
