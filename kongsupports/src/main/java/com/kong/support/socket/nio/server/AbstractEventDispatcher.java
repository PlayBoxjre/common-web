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

import com.kong.support.ExceptionCodeTable;
import com.kong.support.exceptions.socket.SocketBaseException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.exceptions.socket.SocketDisconnectionException;
import com.kong.support.exceptions.socket.SocketSessionException;
import com.kong.support.socket.SocketContext;
import com.kong.support.socket.helper.send.SocketResponse;
import com.kong.support.socket.nio.callbacks.OnEventDispatcherListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private Logger logger = LoggerFactory.getLogger(AbstractEventDispatcher.class);
    @Override
    public final void dispatchEvent(SocketContext socketContext, Event<SelectionKey> event) throws SocketBaseException, SocketConnectionException {
        SelectionKey key = event.getData();
        OnEventDispatcherListener onEventDispatcherListener = socketContext.getOnEventDispatcherListener();
        if (onEventDispatcherListener != null)
            onEventDispatcherListener.onEventDispatch(event.getEventId(), event.getEventUUID());
            if (key != null) {
                if (key.isAcceptable()) {
                    try {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel accept = channel.accept();
                        SocketSession socketSession = new SocketSession();
                        socketSession.setSocket(accept.socket());
                        socketSession.setRemoteAddress(accept.getRemoteAddress());
                        socketSession.setLocalAddress(accept.getLocalAddress());
                        socketSession.setSocketStatus(SocketSession.SOCKET_STATUS.SOCKET_CONNECTED);
                        socketSession.setClose(false);
                        socketSession.setSocketChannel(accept);
                        socketSession.setSelector(key.selector());
                        logger.debug("Accept Client 【{}】connect",accept.getRemoteAddress());
                        accept.configureBlocking(false);
                        key.attach(socketSession);
                        accept.register(key.selector(), SelectionKey.OP_READ,socketSession);
                        if (socketContext.getOnSocketConnectionListener() != null)
                            socketContext.getOnSocketConnectionListener().onSocketConnected(socketSession);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new SocketBaseException(ExceptionCodeTable.EX_SOCKET_ACCEPT_IO_EXCEPTION.CODE
                                , ExceptionCodeTable.EX_SOCKET_ACCEPT_IO_EXCEPTION.MESSAGE, null);
                    }

                }
                if (key.isReadable()) {
                    Object attachment = key.attachment();
                    SocketSession session = null;
                    if (attachment != null) {
                         session = (SocketSession) attachment;
                    } else {
                        String ex = "[read] socket protocols failed";
                        throw new SocketSessionException(ex.hashCode(), ex);
                    }
                    RequestContext requestContext = new RequestContext();
                    requestContext.setSocketContext(socketContext);
                    requestContext.setSocketSession(session);
                    // 恶意攻击防御
                    if (session.errorCount.get() >= socketContext.getLimitsOfErrorCount()) {
                        String exm = "read illegal data count than " + session.errorCount.get();
                        throw new SocketBaseException(ExceptionCodeTable.EX_READ_CLIENT_ERROR_LIMIT.CODE, ExceptionCodeTable.EX_READ_CLIENT_ERROR_LIMIT.MESSAGE, session);
                    }
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        SocketResponse socketResponse = readFromChannel(requestContext,  channel);
                        if (socketResponse==null)
                            return;
                        channel.register(key.selector(),SelectionKey.OP_WRITE,socketResponse);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        throw new SocketBaseException(ExceptionCodeTable.EX_SOCKET_READ_IO_EXCEPTION.CODE
                                , ExceptionCodeTable.EX_SOCKET_READ_IO_EXCEPTION.MESSAGE, null);
                    }
                }
                if (key.isWritable()) {
                        Object attachment = key.attachment();
                        SocketResponse response = null;
                        if (attachment != null) {
                            response = (SocketResponse) attachment;
                        } else {
                             String ex = "[write] socket protocols failed";
                             throw new SocketSessionException(ex.hashCode(), ex);
                        }
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        writeToChannel(response, channel);
                        //取消写监听
                        key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                        key.attach(response.getSession());

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new SocketBaseException(ExceptionCodeTable.EX_SOCKET_WRITE_IO_EXCEPTION.CODE
                                , ExceptionCodeTable.EX_SOCKET_WRITE_IO_EXCEPTION.MESSAGE,response.getSession() );
                    }
                }
            }else{
                throw new NullPointerException("Select key is not null ");
            }
    }

    protected abstract void writeToChannel(SocketResponse response, SocketChannel channel) throws IOException;

    protected abstract SocketResponse readFromChannel(RequestContext socketContext,SocketChannel channel) throws IOException, SocketDisconnectionException, SocketSessionException, SocketConnectionException;
 }
