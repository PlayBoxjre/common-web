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
import com.kong.support.thread.framework.callback.OnPostEventListener;
import com.kong.support.thread.framework.callback.OnPreEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Predicate;

/**
 * File Name EventRunnable
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public class EventRunnable implements Runnable{
    Logger logger = LoggerFactory.getLogger(EventRunnable.class);
    private String name;
    private TransitPointManager transitPointManager;
    private OnPostEventListener onPostEventListener;
    private OnPreEventListener onPreEventListener;
    private OnEventProcessorListener onEventProcessorListener;
    private boolean isShutDown;
    private Predicate<Boolean> onCreateSuccess;

    public EventRunnable(String name, TransitPointManager transitPointManager, OnEventProcessorListener onEventProcessorListener) {
        this.name = name;
        this.transitPointManager = transitPointManager;
        this.onEventProcessorListener = onEventProcessorListener;
    }


    @Override
    public void run() {
        if (name != null)
            Thread.currentThread().setName(name);
        if (onCreateSuccess!=null) {
            boolean test = onCreateSuccess.test(true);

        }
        while (!isShutDown && !Thread.currentThread().isInterrupted()) {
            Event event = transitPointManager.outEvent();
            if (event == null) {

            } else {
                if (this.onPreEventListener != null)
                    onPreEventListener.onPre(event);
                OnEventProcessorListener userProcessor = event.getOnEventProcessorListener();
                EventBundle eventBundle = null;
                if (userProcessor != null){
                     eventBundle = userProcessor.onEventProcessor(event);
                }else if(this.onEventProcessorListener != null){
                    eventBundle = this.onEventProcessorListener.onEventProcessor(event);
                }else{
                    if (onPostEventListener!=null)
                        onPostEventListener.onError(new IllegalArgumentException("线程没有执行体错误"));
                    isShutDown = true;
                    break;
                }
                if (onPostEventListener !=null)
                    onPostEventListener.onPost(eventBundle);
            }
        }
    }

    public TransitPointManager getTransitPointManager() {
        return transitPointManager;
    }

    public void setTransitPointManager(TransitPointManager transitPointManager) {
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
    public void shutDown(){
        if(!Thread.currentThread().isInterrupted())
            Thread.currentThread().interrupt();
    }
}
