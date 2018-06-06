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

package com.kong.support;

/**
 * File Name ExceptionCodeTable
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-01
 * EMAIL     playboxjre@Gmail.com
 */
public class ExceptionCodeTable {

    private static final int SERVER_INNER_ERROR = 50000;
    private static final int CUSTOM_ERROR = 30000;
    private static final int SYSTEM_ERROR = 10000;

    /**
     * 读取客户数据错误超过指定limit次
     */
    public static final Code EX_READ_CLIENT_ERROR_LIMIT = new Code(SERVER_INNER_ERROR + 1,"读取客户数据错误超过指定limit次");
    public static final Code EX_SOCKET_HAS_DISCONNECT = new Code(SERVER_INNER_ERROR + 2 , "客户端连接已经断开");
    public static final Code EX_SOCKET_ACCEPT_IO_EXCEPTION = new Code(SERVER_INNER_ERROR + 3 , "服务端socket 接收异常");
    public static final Code EX_SOCKET_WRITE_IO_EXCEPTION = new Code(SERVER_INNER_ERROR + 4 , "服务端socket 写异常");
    public static final Code EX_SOCKET_READ_IO_EXCEPTION = new Code(SERVER_INNER_ERROR + 5 , "服务端socket 读异常");




    public static final class Code{
        public Code(int code, String message) {
            this.CODE = code;
            this.MESSAGE = message;
        }
        public int CODE;
        public String MESSAGE;
    }
}
