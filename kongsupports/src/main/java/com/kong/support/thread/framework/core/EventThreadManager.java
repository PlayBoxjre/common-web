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
import com.kong.support.thread.framework.EventThreadConfiguration;
import com.kong.support.thread.framework.EventThreadInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * File Name EventThreadManager
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 * 入口类
 */
public class EventThreadManager implements EventThreadInitializer {
    Logger logger = LoggerFactory.getLogger(EventThreadManager.class);
    private int statue = 0;
    @Override
    public void init(EventThreadConfiguration configuration) {

    }

    @Override
    public void createFlowLine(String workpieceName, boolean newTrasitPoint, Predicate<Boolean> onCreateSuccess) {

    }


    @Override
    public void destroy() {

    }

    @Override
    public void publish(Event event) {

    }



}
