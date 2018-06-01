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

import com.kong.support.annotations.Scope;
import com.kong.support.annotations.documents.ScopyPolicy;

import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 保存socket会话信息
 * this is a session ， keep socket object save util it's close or disconnect .
 * this is
 */
@Scope(ScopyPolicy.SESSION)
public class SocketSession {



    private Object attach;
    /**
     * session 是否关闭或者断开，一旦断开将不会在使用。gc清楚
     */
    private boolean isClose;
    /**
     * socket 的状态
     */
    private SOCKET_STATUS socketStatus;

    /**
     * socket的引用
     */
    private Socket socket;

    private Reader reader;

    private Writer writer;

    /**
     * 临时存储一条数据的容器
     */
    private StringBuilder dataBuffer;
    /**
     * 解析数据的起始和结束的位置
     * 0 start 1 end
     */
    public int parsePositionFlag = -1;

    public AtomicInteger errorCount = new AtomicInteger();
    private SocketChannel socketChannel;
    private SocketAddress remoteAddress;
    private SocketAddress localAddress;
    private Selector selector;

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setRemoteAddress(SocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setLocalAddress(SocketAddress localAddress) {
        this.localAddress = localAddress;
    }

    public SocketAddress getLocalAddress() {
        return localAddress;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public Selector getSelector() {
        return selector;
    }

    public void close(){
        localAddress = null;
        remoteAddress = null;
        errorCount = null;
        parsePositionFlag = -1;
        socketChannel = null;
        reader = null;
        writer = null;
        attach = null;
        socket = null;
        isClose = true;
    }


    public static enum SOCKET_STATUS{
        SOCKET_DISCONNECT,SOCKET_CONNECTED,SOCKET_EXCEPTION_CLOSE,
        SOCKET_RECONNECTED,SOCKET_CLOSE
    }

    public SOCKET_STATUS getSocketStatus() {
        return socketStatus;
    }

    public void setSocketStatus(SOCKET_STATUS socketStatus) {
        this.socketStatus = socketStatus;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public StringBuilder getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(StringBuilder dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    public Reader getReader() {
        return reader;
    }

    protected void setReader(Reader reader) {
        this.reader = reader;
    }

    public Writer getWriter() {
        return writer;
    }

    protected void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void attch(Object attach){
        this.attach = attach;
    }

    public Object attachment(){
        return this.attach;
    }
}
