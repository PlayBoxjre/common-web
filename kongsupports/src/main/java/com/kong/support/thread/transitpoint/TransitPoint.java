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

package com.kong.support.thread.transitpoint;

import com.kong.support.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name TransitPoint
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-12
 * EMAIL     playboxjre@Gmail.com
 * 线程安全的类，用于多线程模型中
 * 传输点
 * 对物件的流水线传输
 */
@ThreadSafe
public interface TransitPoint<T,R> {
    /**
     * 将加工物料 放到传输点
     * @param thing
     */
    public void consume(T thing) throws InterruptedException;

    /**
     * 获取到产品
     * @return
     */
    public R product() throws InterruptedException;

}
