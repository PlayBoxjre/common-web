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
 * File Name UserException
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 * 用户级异常 ： 用户处理程序级别的错误，只中断本次用户处理
 */
public class UserException extends BaseException {
    Logger logger = LoggerFactory.getLogger(UserException.class);

    public UserException(int code) {
        super(code);
    }

    public UserException(int code, String message) {
        super(code, message);
    }
}
