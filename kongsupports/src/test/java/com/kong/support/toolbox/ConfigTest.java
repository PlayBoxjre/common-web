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

package com.kong.support.toolbox;

import com.kong.support.thread.framework.Event;
import com.kong.support.thread.framework.EventBundle;
import com.kong.support.thread.framework.EventThreadConfiguration;
import com.kong.support.thread.framework.callback.OnEventProcessorListener;
import com.kong.support.thread.framework.callback.OnPostEventListener;
import com.kong.support.thread.framework.callback.OnPreEventListener;
import com.kong.support.thread.framework.core.EventThreadManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name ConfigTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public class ConfigTest {
    Logger logger = LoggerFactory.getLogger(ConfigTest.class);

    @Test
    public void testInitConfig(){
        EventThreadConfiguration.Builder builder = new EventThreadConfiguration.Builder();
        EventThreadConfiguration configuration = builder.setDefaultName("defalut")

                .setLoadBalanceStrategy(1)
                .setMaxThreadCount(10)
                .setInitialThreadCount(1)
                .setInitialTransitPointCount(1).build();



    }




}
