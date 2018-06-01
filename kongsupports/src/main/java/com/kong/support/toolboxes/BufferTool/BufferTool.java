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

package com.kong.support.toolboxes.BufferTool;

import com.kong.support.exceptions.socket.SocketDisconnectionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;

/**
 * File Name BufferTool
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 */
public class BufferTool {

    public static String getString(CharBuffer dataBuffer){
        if (dataBuffer.hasArray()) {
            char[] array = dataBuffer.array();
           return new String(array);
        }else {
            char[] chars = new char[dataBuffer.length()];
            dataBuffer.get(chars);
            return new String(chars);
        }
    }

    public static byte[] getBytes(SocketChannel channel,int buffSize) throws IOException {
        byte[] buffer = new byte[buffSize];
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.clear();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        while (byteBuffer.hasRemaining() && (len = channel.read(byteBuffer))>0){
            byteArrayOutputStream.write(buffer,0,len);
            byteBuffer.clear();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
