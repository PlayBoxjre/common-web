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

import com.kong.support.thread.framework.EventRunnable;
import com.kong.support.thread.framework.ThreadPoolManager;
import com.kong.support.thread.framework.TransitPointManager;
import com.kong.support.thread.framework.callback.OnEventProcessorListener;
import com.kong.support.thread.framework.callback.OnPostEventListener;
import com.kong.support.thread.framework.callback.OnPreEventListener;
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

    private Map<String,Runnable> threadRecorder= new ConcurrentHashMap<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private TransitPointManager transitPointManager;

    private OnPreEventListener onPreEventListener;

    private OnPostEventListener onPostEventListener;

    private OnEventProcessorListener onEventProcessorListener;



    @Override
    public EventRunnable createEventThread(String name, Predicate<Boolean> onSuccessCreate) {
        if (name == null)
            throw new NullPointerException("线程名字不能为null");
        if (checkThreadExist(name)){
            logger.error("thread has exist :{}",name);
            throw new IllegalArgumentException("线程 "+ name +" 已经存在");
        }

        EventRunnable eventThread = new EventRunnable(name,transitPointManager,onEventProcessorListener);
        eventThread.setOnPreEventListener(onPreEventListener);
        eventThread.setOnPostEventListener(onPostEventListener);
        eventThread.setShutDown(false);
        eventThread.setOnCreateSuccess(onSuccessCreate);
        executorService.execute(eventThread);
        this.threadRecorder.put(name,eventThread);
        return eventThread;
    }

    private boolean checkThreadExist(String name) {
        return threadRecorder.get(name) != null;
    }

    @Override
    public void destroy(EventRunnable eventThread) {
        eventThread.setShutDown(true);
        eventThread.shutDown();
    }

    @Override
    public void destroy(String threadName) {
        Runnable runnable = threadRecorder.get(threadName);
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

    public TransitPointManager getTransitPointManager() {
        return transitPointManager;
    }

    public void setTransitPointManager(TransitPointManager transitPointManager) {
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
}
