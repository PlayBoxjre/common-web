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

package com.kong.support.socket;

import com.kong.support.annotations.NoUse;
import com.kong.support.exceptions.ExceptionHandler;
import com.kong.support.socket.nio.callbacks.OnAfterAcceptDataListener;
import com.kong.support.socket.nio.callbacks.OnEventDispatcherListener;
import com.kong.support.socket.nio.callbacks.OnPreResonpseListener;
import com.kong.support.socket.nio.callbacks.OnSocketConnectionListener;
import com.kong.support.socket.nio.server.EventDispatcher;
import com.kong.support.socket.nio.server.SocketRequestMode;

/**
 * File Name SocketConfiguration
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-22
 * EMAIL     playboxjre@Gmail.com
 */
public class SocketConfiguration {


    private boolean alwaysReturnMessage;
    private boolean blocking;
    private int port;

    private SocketRequestMode requestMode;
     /**
     * 事件分发监听
     */
    private OnEventDispatcherListener onEventDispatcherListener;

    /**
     * socket 建立连接监听
     */
    private OnSocketConnectionListener socketConnectionListener;

    private EventDispatcher eventDispatcher;

    private ExceptionHandler exceptionHandler;

    private OnPreResonpseListener onPreResonpseListener;

    private OnAfterAcceptDataListener onAfterAcceptDataListener;

    @NoUse("TODO 读使用异步")
    private boolean readAsync;
    @NoUse("TODO 异步写")
    private boolean writeAsync;
    private String defaultGolabeCharsetName;
    private char prefixChar ='\uEEFE';
    private char suffixChar = '\uEEFF';
    private int limitsOfErrorCount;// 每个连接读取空字符错误次数限制


    public SocketRequestMode getRequestMode() {
        return requestMode;
    }

    public SocketConfiguration setRequestMode(SocketRequestMode requestMode) {
        this.requestMode = requestMode;
        return this;
    }

    public boolean isAlwaysReturnMessage() {
        return alwaysReturnMessage;
    }
    public SocketConfiguration setAlwaysReturnMessage(boolean alwaysReturnMessage) {
        this.alwaysReturnMessage = alwaysReturnMessage;
        return this;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public SocketConfiguration setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
        return this;
    }

    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public SocketConfiguration setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }

    public OnPreResonpseListener getOnPreResonpseListener() {
        return onPreResonpseListener;
    }

    public SocketConfiguration setOnPreResonpseListener(OnPreResonpseListener onPreResonpseListener) {
        this.onPreResonpseListener = onPreResonpseListener;
        return this;
    }

    public OnAfterAcceptDataListener getOnAfterAcceptDataListener() {
        return onAfterAcceptDataListener;
    }

    public SocketConfiguration setOnAfterAcceptDataListener(OnAfterAcceptDataListener onAfterAcceptDataListener) {
        this.onAfterAcceptDataListener = onAfterAcceptDataListener;
        return this;
    }

    public boolean isReadAsync() {
        return readAsync;
    }

    public SocketConfiguration setReadAsync(boolean readAsync) {
        this.readAsync = readAsync;
        return this;
    }

    public boolean isWriteAsync() {
        return writeAsync;
    }

    public SocketConfiguration setWriteAsync(boolean writeAsync) {
        this.writeAsync = writeAsync;
        return this;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public SocketConfiguration setBlocking(boolean blocking) {
        this.blocking = blocking;
        return  this;
    }

    public int getPort() {
        return port;
    }

    public SocketConfiguration setPort(int port) {
        this.port = port;
        return this;
    }

    public OnSocketConnectionListener getSocketConnectionListener() {
        return socketConnectionListener;
    }

    public SocketConfiguration setSocketConnectionListener(OnSocketConnectionListener socketConnectionListener) {
        this.socketConnectionListener = socketConnectionListener;
        return this;
    }

    public String getDefaultGolabeCharsetName() {
        return defaultGolabeCharsetName;
    }

    public SocketConfiguration setDefaultGolabeCharsetName(String defaultGolabeCharsetName) {
        this.defaultGolabeCharsetName = defaultGolabeCharsetName;
        return this;
    }

    public char getPrefixChar() {
        return prefixChar;
    }

    public SocketConfiguration setPrefixChar(char prefixChar) {
        this.prefixChar = prefixChar;
        return this;
    }

    public char getSuffixChar() {
        return suffixChar;
    }

    public SocketConfiguration setSuffixChar(char suffixChar) {
        this.suffixChar = suffixChar;
        return this;
    }

    public int getLimitsOfErrorCount() {
        return limitsOfErrorCount;
    }

    public SocketConfiguration setLimitsOfErrorCount(int limitsOfErrorCount) {
        this.limitsOfErrorCount = limitsOfErrorCount;
        return this;
    }

    public OnEventDispatcherListener getOnEventDispatcherListener() {
        return onEventDispatcherListener;
    }

    public SocketConfiguration setOnEventDispatcherListener(OnEventDispatcherListener onEventDispatcherListener) {
        this.onEventDispatcherListener = onEventDispatcherListener;
        return this;
    }
}
