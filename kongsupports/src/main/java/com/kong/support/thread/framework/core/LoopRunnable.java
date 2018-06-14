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

import com.kong.support.exceptions.ExceptionHandler;
import com.kong.support.exceptions.SystemException;
import com.kong.support.exceptions.ThreadLoopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name LoopRunnable
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 */
public abstract class LoopRunnable implements Runnable {
    Logger logger = LoggerFactory.getLogger(LoopRunnable.class);

    private ExceptionHandler systemExceptionHandler;
    private ExceptionHandler threadLoopExceptionHandler;

    protected abstract String configThreadName();

    /**
     * 开始循环前预处理事件
     */
    protected abstract void preHandler();

    protected abstract boolean loopCondition();

    protected   abstract void loopFrame() throws Exception;

    @Override
    public final void run() {
        String threadName = configThreadName();
        if (threadName != null)
            Thread.currentThread().setName(threadName);

        preHandler();

        try{
            while (loopCondition()){
                try {
                    loopFrame();
                }catch (Exception ex){
                    ex.printStackTrace();
                    if (threadLoopExceptionHandler!=null)
                        if(!threadLoopExceptionHandler.exceptionHandler(ex))
                            throw ex;
                    // 线程循环异常
                }
            }
        }catch (Exception ex){// 系统级错误 线程挂掉
            if (systemExceptionHandler!=null)
                if(!systemExceptionHandler.exceptionHandler(ex))
                    ex.printStackTrace();
        }
    }

    public ExceptionHandler getSystemExceptionHandler() {
        return systemExceptionHandler;
    }

    public void setSystemExceptionHandler(ExceptionHandler systemExceptionHandler) {
        this.systemExceptionHandler = systemExceptionHandler;
    }

    public ExceptionHandler getThreadLoopExceptionHandler() {
        return threadLoopExceptionHandler;
    }

    public void setThreadLoopExceptionHandler(ExceptionHandler threadLoopExceptionHandler) {
        this.threadLoopExceptionHandler = threadLoopExceptionHandler;
    }
}
