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

package com.kong.support.socket.helper.accept;

import com.kong.support.exceptions.socket.IllegalCharException;
import com.kong.support.exceptions.socket.SocketAcceptException;
import com.kong.support.exceptions.socket.SocketSessionException;
import com.kong.support.socket.nio.server.SocketSession;

/**
 * File Name ProtocolParser
 * Author    aaron & kong xiang
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 * DESC     :
 *  这是一个协议解析器
 *  定义如何解析传输的二进制数据
 */
public interface ProtocolParser {


    /**
     * 定义原始流数据的起始分割标记
     * @return
     */
    public char defineStartSeparator();

    /**
     * 定义原始流数据的结束分割标记
     * @return
     */
    public char defineEndSeparator();

    /**
     * 每次接收最大的字节长度
     * 起始-结束的总长度
     * @return
     */
    public long maxAcceptLength();

    /**
     * 接收数据
     * @param session
     * @return  一条记录是否接收完整
     */
    public boolean onceAccept(SocketSession session, byte[] datas) throws SocketAcceptException, SocketSessionException, IllegalCharException;

    /**
     * h获取解析成功的数据行
     * @return
     */
    public String[] getLines();

    /**
     * 若存在开始标签，那么下一个还是开始标签将忽略。默认报错
     * @return
     */
    public START_TAG_STRAGETY startTagStragety(START_TAG_STRAGETY s);


    public boolean isContinueAtSuccessParse();

    public enum START_TAG_STRAGETY{
        /**
         * 报错 此数据停止解析
         */
        EXCEPTION_DEUL,
        /**
         * 以新的start_tag为开始，丢弃新开始标记之前的数据
         */
        NEW_START_DEUL,
        /**
         * 忽略新开始标签。继续解析
         */
        IGNORE_CONTENT_START_DUEL
    }

}
