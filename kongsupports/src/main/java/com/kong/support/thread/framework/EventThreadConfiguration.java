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

/**
 * File Name EventThreadConfiguration
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public class EventThreadConfiguration {
    Logger logger = LoggerFactory.getLogger(EventThreadConfiguration.class);

    private int maxThreadCount = Runtime.getRuntime().availableProcessors() + 1;

    private int loadBalanceStrategy = 0x00000001;

    private String defaultName = "default";

    private int initialThreadCount = 1;

    private int initialTransitPointCount = initialThreadCount;

    public int getMaxThreadCount() {
        return maxThreadCount;
    }

    public int getLoadBalanceStrategy() {
        return loadBalanceStrategy;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public int getInitialThreadCount() {
        return initialThreadCount;
    }

    public int getInitialTransitPointCount() {
        return initialTransitPointCount;
    }


    public static class Builder{
        private EventThreadConfiguration configuration;
        public Builder(){
            configuration = new EventThreadConfiguration();
        }

        public Builder setMaxThreadCount(int maxThreadCount) {
            configuration.maxThreadCount = maxThreadCount;
            return this;
        }

        public Builder setLoadBalanceStrategy(int loadBalanceStrategy) {
            configuration.loadBalanceStrategy = loadBalanceStrategy;
            return this;
        }

        public Builder setDefaultName(String defaultName) {
            configuration.defaultName = defaultName;
            return this;
        }

        public Builder setInitialThreadCount(int initialThreadCount) {
            configuration.initialThreadCount = initialThreadCount;
            return this;
        }

        public Builder setInitialTransitPointCount(int initialTransitPointCount) {
            configuration.initialTransitPointCount = initialTransitPointCount;
            return this;
        }


        public EventThreadConfiguration build(){
            return configuration;
        }
    }
}
