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

import com.kong.support.annotations.Scope;
import com.kong.support.annotations.documents.ScopyPolicy;
import com.kong.support.socket.nio.callbacks.OnEventDispatcherListener;
import com.kong.support.socket.nio.callbacks.OnSocketConnectionListener;
import com.kong.support.socket.nio.server.SocketRequestMode;
import com.kong.support.socket.nio.server.SocketSession;

import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * File Name SocketContext
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-30
 * EMAIL     playboxjre@Gmail.com
 */
@Scope(ScopyPolicy.APPLICATION)
public class SocketContext {


    private SocketRequestMode socketRequestMode;

     /**
     * 事件分发监听
     */
    private OnEventDispatcherListener onEventDispatcherListener;

    /**
     * socket 建立连接监听
     */
    private OnSocketConnectionListener onSocketConnectionListener;
    private String golabeCharsetName = Charset.defaultCharset().name();
    private char prefixChar ='\uEEFE';
    private char suffixChar = '\uEEFF';
    /** 读取数据最大错误次数 否则断开，认为为恶意攻击*/
    private int limitsOfErrorCount;
    private boolean alwaysReturnMessage = false;


    //************************************************
    //          setter and getter
    public OnSocketConnectionListener getOnSocketConnectionListener() {
        return onSocketConnectionListener;
    }

    public void setOnSocketConnectionListener(OnSocketConnectionListener onSocketConnectionListener) {
        this.onSocketConnectionListener = onSocketConnectionListener;
    }

    public OnEventDispatcherListener getOnEventDispatcherListener() {
        return onEventDispatcherListener;
    }

    public void setOnEventDispatcherListener(OnEventDispatcherListener onEventDispatcherListener) {
        this.onEventDispatcherListener = onEventDispatcherListener;
    }

    public String getGolabeCharsetName() {
        return golabeCharsetName;
    }

    public void setGolabeCharsetName(String golabeCharsetName) {
        this.golabeCharsetName = golabeCharsetName;
    }

    public char getPrefixChar() {
        return prefixChar;
    }

    public void setPrefixChar(char prefixChar) {
        this.prefixChar = prefixChar;
    }

    public char getSuffixChar() {
        return suffixChar;
    }

    public void setSuffixChar(char suffixChar) {
        this.suffixChar = suffixChar;
    }

    public int getLimitsOfErrorCount() {
        return limitsOfErrorCount;
    }

    public void setLimitsOfErrorCount(int limitsOfErrorCount) {
        this.limitsOfErrorCount = limitsOfErrorCount;
    }

    public boolean isAlwaysReturnMessage() {
        return alwaysReturnMessage;
    }

    public void setAlwaysReturnMessage(boolean alwaysReturnMessage) {
        this.alwaysReturnMessage = alwaysReturnMessage;
    }

    public SocketRequestMode getSocketRequestMode() {
        return socketRequestMode;
    }

    public void setSocketRequestMode(SocketRequestMode socketRequestMode) {
        this.socketRequestMode = socketRequestMode;
    }
}
