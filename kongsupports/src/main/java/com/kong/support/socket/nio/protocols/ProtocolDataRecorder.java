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

package com.kong.support.socket.nio.protocols;

/**
 * File Name ProtocolDataRecorder
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-31
 * EMAIL     playboxjre@Gmail.com
 */
public class ProtocolDataRecorder {

    /**
     * 临时存储一条数据的容器
     */
    private StringBuilder dataBuffer;
    /**
     * 解析数据的需要的位置
     * 0 start 1 end
     */
    protected int parsePositionFlag = -1;

    protected int errorCount;

    public StringBuilder getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(StringBuilder dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

}
