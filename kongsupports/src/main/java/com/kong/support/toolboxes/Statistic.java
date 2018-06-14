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

package com.kong.support.toolboxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File Name Statistic
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 * 统计类
 */
public class Statistic {
    Logger logger = LoggerFactory.getLogger(Statistic.class);
    private Map<Object,AtomicInteger> statistic = new HashMap<>();

    public synchronized void increment(Object key){
        AtomicInteger orDefault = statistic.getOrDefault(key, new AtomicInteger());
        orDefault.incrementAndGet();
        statistic.put(key,orDefault);
    }

    public synchronized void decrement(Object key){
        AtomicInteger orDefault = statistic.getOrDefault(key, new AtomicInteger());
        orDefault.decrementAndGet();
        statistic.put(key,orDefault);
    }

    public synchronized int get(Object key){
        return statistic.getOrDefault(key, new AtomicInteger()).get();

    }

    public synchronized void remove(Object key){
        statistic.remove(key);
    }


    public synchronized void println(){
        logger.debug("Statistic : 统计");
        Set<Object> objects = statistic.keySet();
        objects.stream().forEach((n)->{
            logger.debug("\t[{}] : [{}]",n,statistic.get(n).get());
        });
    }

}
