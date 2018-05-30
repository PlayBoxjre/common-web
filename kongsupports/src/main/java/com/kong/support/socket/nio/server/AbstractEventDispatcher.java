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

package com.kong.support.socket.nio.server;

import com.kong.support.exceptions.socket.SocketSessionException;
import com.kong.support.socket.helper.accept.SocketSession;
import com.kong.support.socket.nio.callbacks.OnEventDispatcherListener;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 抽象时间分发器
 * File Name AbstractEventDispatcher
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-30
 * EMAIL     playboxjre@Gmail.com
 */
public abstract class AbstractEventDispatcher implements EventDispatcher {
    @Override
    public void dispatchEvent(SocketContext socketContext, Event<SelectionKey> event) throws SocketSessionException, IOException {
        SelectionKey key = event.getData();
        OnEventDispatcherListener onEventDispatcherListener = socketContext.getOnEventDispatcherListener();
        if (onEventDispatcherListener != null)
            onEventDispatcherListener.onEventDispatch(event.getEventId(),event.getEventUUID());
        if (key!=null){
            if (key.isAcceptable()){
                SocketSession socketSession = accept((ServerSocketChannel) key.channel());
                if (socketContext.getOnSocketConnectionListener()!=null)
                    socketContext.getOnSocketConnectionListener().onSocketConnected(socketSession);
            }else if(key.isReadable()){
                Object attachment = key.attachment();
                if (attachment!=null){
                    SocketSession session = (SocketSession) attachment;
                    SocketChannel channel = (SocketChannel) key.channel();
                    readFromChannel(session,channel);
                }else{
                    String ex = "[read] socket accept failed";
                    throw new SocketSessionException(ex.hashCode(),ex);
                }
            }else if(key.isWritable()){
                Object attachment = key.attachment();
                if (attachment!=null){
                    SocketSession session = (SocketSession) attachment;
                    SocketChannel channel = (SocketChannel) key.channel();
                    writeToChannel(session,channel);
                }else {
                    String ex = "[write] socket accept failed";
                    throw new SocketSessionException(ex.hashCode(), ex);
                }
            }

        }else{
            throw new NullPointerException("Select key is not null ");
        }
    }

    protected abstract void writeToChannel(SocketSession session, SocketChannel channel) throws IOException;

    protected abstract void readFromChannel(SocketSession session, SocketChannel channel) throws IOException;

    protected abstract SocketSession accept(ServerSocketChannel channel);
}
