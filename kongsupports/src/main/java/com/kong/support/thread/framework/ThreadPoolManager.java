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
import com.kong.support.thread.framework.core.EventRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * File Name ThreadPoolManager
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public interface ThreadPoolManager {
    Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);

    public EventRunnable createEventThread(String name, Predicate<Boolean> onSuccessCreate);



    /**
     * 创建一个 线程名  绑定的事件类型 的新事件线程
     * @param ThreadName
     * @param eventType
     * @param onSuccessCreate
     * @return
     */
    public EventRunnable createEventThread(String ThreadName, String eventType, Predicate<Boolean> onSuccessCreate);

    /**
     * 创建一个自定义执行线程
     * 如果 自定义事件任务是null
     * 那么使用通用的事件任务创建线程（全局配置的任务）
     * @param ThreadName
     * @param eventType
     * @param eventTask
     * @param onSuccessCreate
     * @return
     */
    public EventRunnable createEventThread(String ThreadName, String eventType, EventTask eventTask,Predicate<Boolean> onSuccessCreate);

    public void executeRunnable(Runnable runnable);

    public EventRunnable pickEventThread(String name);

    public void destroy(EventRunnable eventThread);

    public void destroy(String threadName);



}
