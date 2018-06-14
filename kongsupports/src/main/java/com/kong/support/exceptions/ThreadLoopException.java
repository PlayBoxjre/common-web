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

package com.kong.support.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name ThreadLoopException
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 * 线程级别的异常 线程循环体的一次循环异常报错
 */
public class ThreadLoopException extends BaseException{
    Logger logger = LoggerFactory.getLogger(ThreadLoopException.class);

    public ThreadLoopException(int code, String message) {
        super(code, message);
    }
}
