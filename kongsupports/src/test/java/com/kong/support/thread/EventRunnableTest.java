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

import com.kong.support.thread.framework.*;
import com.kong.support.thread.framework.callback.*;
import com.kong.support.thread.framework.core.EventRunnable;
import com.kong.support.thread.framework.core.ThreadPoolManagerImpl;
import com.kong.support.thread.transitpoint.EventTransitPointManager;
import com.kong.support.thread.transitpoint.TransitPoint;
import com.kong.support.thread.transitpoint.callback.OnTransitPointTransitListner;
import com.kong.support.toolboxes.Statistic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File Name EventRunnableTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public class EventRunnableTest {
    Logger logger = LoggerFactory.getLogger(EventRunnableTest.class);


    private String threadName;
    private OnEventCancelListener onEventCancelListener;
    private OnEventProcessorListener onEventProcessorListener ;
    private OnPreEventListener onPreEventListener;
    private OnPostEventListener onPostEventListener;
    private Map<String,Runnable> threadRecords;
    private EventTransitPointManager transitPointManager;
    private ThreadPoolManager threadPoolManager;

    private EventTask eventTask;
    private Statistic statistic;
    private EventTask createEventTask;

    @Before
    public void before(){
        threadName = "defalut";
        statistic = new Statistic();
        threadRecords = new ConcurrentHashMap<>();



        onEventProcessorListener = new OnEventProcessorListener() {
            @Override
            public EventBundle onEventProcessor(Event event) {
                logger.debug("PROCESS .... ");
                logger.debug("\tevent : id {} uid {} type {} ",event.getId(),event.getCode(),event.getEventType().getEventTypeName());

                EventBundle bundle = new EventBundle();
                bundle.setBundleChar('a');
                return bundle;
            }
        };

        transitPointManager = new EventTransitPointManager() {
            int count = 0;
            @Override
            public void enterEvent(Event event) {

            }

            @Override
            public Event outEvent(EventType eventType) {
                return outEvent(eventType.getEventTypeName());
            }

            @Override
            public TransitPoint createTransitPoint(int capacity, String transitPointName, int transitPriority, Object flag, OnTransitPointTransitListner<Event, Event> onTransitPointTransitListner) {
                return null;
            }

            @Override
            public TransitPoint createTransitPoint(String transitPointName, int transitPriority, Object flag, OnTransitPointTransitListner<Event, Event> onTransitPointTransitListner) {
                return null;
            }

            @Override
            public TransitPoint createTransitPoint(String transitPointName, Object flag) {
                return null;
            }

            @Override
            public TransitPoint createTransitPoint(String transitPointName, OnTransitPointTransitListner<Event, Event> onTransitPointTransitListner) {
                return null;
            }

            @Override
            public synchronized Event outEvent(String name) {
                 count ++ ;
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EventType eventType =  EventType.createOrGetEventType("default");
                Event event = new Event("default");
                if (count % 2 ==0)
                     event.setEventTask(eventTask);
                //event.setOnEventProcessorListener(onEventProcessorListener);
                return event;
            }


            @Override
            public TransitPoint createTransitPoint(String eventType) {
                return null;
            }


        };


        onPostEventListener = new OnPostEventListener() {
            @Override
            public void onPost(EventBundle t) {
                assert  t != null && t.getBundleChar() == 'a';
                logger.debug("POST\t :...{}",t == null?"null":t.toString() );
            }

            @Override
            public void onError(Exception ex) {
                assert  ex != null;
                ex.printStackTrace();
                logger.error("ERROR \t ex is {}",ex.getMessage());
            }
        };

        onPreEventListener = new OnPreEventListener() {
            @Override
            public void onPre(Event event) {
                logger.debug("PRE \t  ..{} {} {}",event.getId(),event.getCode(),event.getEventType().getEventTypeName());
            }
        };

        onEventCancelListener = new OnEventCancelListener() {
            @Override
            public void onEventCancel(Event event) {
                logger.debug("CANCEL \t ");
            }
        };
        eventTask = new EventTask() {
            @Override
            public void onPre(Event event) {
                logger.debug("USER_PRE \t user 1 -- > pre:");
            }

            @Override
            public void onPost(EventBundle t) {
                assert t.getBundleChar() == 'b';
                logger.debug("USER_POST 3 -- > post:");
            }

            @Override
            public void onError(Exception ex) {
                logger.debug("USER_ERROR -- 3 > error:");
            }

            @Override
            public void onEventCancel(Event event) {
                logger.debug("USER_CANCEL -- 3 > cancel:");
            }

            @Override
            public EventBundle onEventProcessor(Event event) {
                logger.debug("USER_PROCESS -- 2 > process:");
                EventBundle eventBundle = new EventBundle();
                eventBundle.setBundleChar('b');
                return eventBundle;
            }
        };

        createEventTask = new EventTask() {
            @Override
            public void onPre(Event event) {
                logger.debug("CREATE_PRE \t user 1 -- > pre:");
            }

            @Override
            public void onPost(EventBundle t) {
                assert t.getBundleChar() == 'b';
                logger.debug("CREATE_POST 3 -- > post:");
            }

            @Override
            public void onError(Exception ex) {
                logger.debug("CRETATE_ERROR -- 3 > error:");
            }

            @Override
            public void onEventCancel(Event event) {
                logger.debug("CREATE_CANCEL -- 3 > cancel:");
            }

            @Override
            public EventBundle onEventProcessor(Event event) {
                logger.debug("CREATE_PROCESS -- 2 > process:");
                EventBundle eventBundle = new EventBundle();
                eventBundle.setBundleChar('b');
                return eventBundle;
            }
        };

        ThreadPoolManagerImpl threadPoolManager = new ThreadPoolManagerImpl();
        threadPoolManager.setOnPreEventListener(onPreEventListener);
        threadPoolManager.setOnPostEventListener( onPostEventListener);
        threadPoolManager.setOnEventProcessorListener(onEventProcessorListener);
        threadPoolManager.setThreadRecorder(threadRecords);
        threadPoolManager.setTransitPointManager(transitPointManager);
        threadPoolManager.setOnEventCancelListener(onEventCancelListener);
        threadPoolManager.setStatistic(statistic);
        this.threadPoolManager = threadPoolManager;
    }


    @Test
    public void testEventThread(){


        EventRunnable aDefault = this.threadPoolManager.createEventThread(threadName, (Boolean n) -> {
            logger.debug("create event thread is " + n);
            return true;
        });


        EventRunnable f = this.threadPoolManager.createEventThread(threadName+"1", (Boolean n) -> {
            logger.debug("create event thread is " + n);
            return true;
        });


     }


     @Test
     public void testUserEventTask(){
         threadPoolManager.createEventThread("user create--1","default",createEventTask,null);
         threadPoolManager.createEventThread("user**0","default",null);
        threadPoolManager.createEventThread("user==2","default",null);



     }




    @After
    public void destroy(){
        logger.debug("destroy is call");
        int count = 10;
        while (count-- > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        statistic.println();


    }



}
