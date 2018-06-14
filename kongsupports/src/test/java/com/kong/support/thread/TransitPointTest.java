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

package com.kong.support.thread;

import com.kong.support.thread.framework.Event;
import com.kong.support.thread.framework.ThreadPoolManager;
import com.kong.support.thread.framework.core.ThreadPoolManagerImpl;
import com.kong.support.thread.transitpoint.EventTransitPoint;
import com.kong.support.thread.transitpoint.TransitPoint;
import com.kong.support.toolboxes.Statistic;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name TransitPointTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 */
public class TransitPointTest {
    Logger logger = LoggerFactory.getLogger(TransitPointTest.class);

    private Statistic statistic = new Statistic();


    @Test
    public void testEventTransitPoint(){
        TransitPoint transitPoint = new EventTransitPoint(128);
        ((EventTransitPoint) transitPoint).setStatistic(statistic);

        ThreadPoolManagerImpl threadPoolManager = new ThreadPoolManagerImpl();
        threadPoolManager.executeRunnable(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        transitPoint.consume(new Event("default"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadPoolManager.executeRunnable(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        transitPoint.product();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            statistic.println();

        }

    }
}
