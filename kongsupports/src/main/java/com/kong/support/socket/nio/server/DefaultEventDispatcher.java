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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kong.support.ExceptionCodeTable;
import com.kong.support.annotations.NoUse;
import com.kong.support.exceptions.socket.SocketDisconnectionException;
import com.kong.support.socket.SocketContext;
import com.kong.support.socket.helper.send.SocketResponse;
import com.kong.support.socket.nio.callbacks.OnAfterAcceptDataListener;
import com.kong.support.socket.nio.callbacks.OnPreResonpseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * File Name DefaultEventDispatcher
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-31
 * EMAIL     playboxjre@Gmail.com
 */
public class DefaultEventDispatcher extends AbstractEventDispatcher {

    private Logger logger = LoggerFactory.getLogger(DefaultEventDispatcher.class);

    @NoUse("这是一个发送相应结果二进制数据之前的处理器")
    private OnPreResonpseListener onPreResonpseListener;

    @NoUse("这是一个处理接收信息的处理器")
    private OnAfterAcceptDataListener onAfterAcceptDataListener;


    @Override
    protected void writeToChannel( SocketResponse response, SocketChannel channel) throws IOException {
        assert response !=null ;
        assert channel !=null;
        SocketContext socketContext = response.getSocketContext();
        String charsetName = socketContext.getGolabeCharsetName();
        char prefix = socketContext.getPrefixChar();
        char suffix = socketContext.getSuffixChar();
        Writer writer = Channels.newWriter(channel, charsetName);
        PrintWriter printWriter = new PrintWriter(writer);
        response.getSession().setWriter(printWriter);
        byte[] result = buildResult(response,charsetName);
        String resultStr = String.format("%s%s%s",prefix,new String(result,charsetName),suffix);
        logger.debug("Wirte to {} data 【{}】",channel.getRemoteAddress(),resultStr);
        printWriter.print(resultStr);
        printWriter.flush();
        //printWriter
    }

    private final byte[] buildResult(SocketResponse response, String charsetName) throws JsonProcessingException {
        if (onPreResonpseListener!=null)
            return onPreResonpseListener.onPreResponse(response,charsetName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(response);
    }

    @Override
    protected SocketResponse readFromChannel(RequestContext requestContext,SocketChannel channel) throws IOException, SocketDisconnectionException {
        SocketSession session = requestContext.getSocketSession();
        if (channel.isConnected() && channel.isOpen()){
            int buffSize = 1024;
            byte[] buffer = new byte[buffSize];
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            byteBuffer.clear();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            while (byteBuffer.hasRemaining() && (len = channel.read(byteBuffer))>0){
                byteArrayOutputStream.write(buffer,0,len);
                byteBuffer.clear();
            }
            if (len == -1 ) throw new SocketDisconnectionException(1,"客户端断开连接",session);// client disconnected this socket
            if (len == 0) ;//read end and wait for next data;
            // ----   get useful data
            byte[] bytes =  byteArrayOutputStream.toByteArray();
            logger.debug("接收的字节数：{} -- \n接收的字符：{}",byteArrayOutputStream.size(),
                    new String(bytes,requestContext.getSocketContext().getGolabeCharsetName()));
            // 如果接收的数据是空数据，session 的错误计数+1
            if (byteArrayOutputStream.size() == 0) {
                session.errorCount.incrementAndGet();
                return null;
            }
            SocketResponse socketResponse = null;
            if (onAfterAcceptDataListener!=null) {
                socketResponse = onAfterAcceptDataListener.onAfterAcceptData(session, bytes, requestContext.getSocketContext().getGolabeCharsetName());
            }else if (requestContext.getSocketContext().isAlwaysReturnMessage()){
                socketResponse = new SocketResponse();
                socketResponse.setCode(0);
                socketResponse.setMessage("no message return ");
                socketResponse.setSession(session);
                socketResponse.setSocketContext(requestContext.getSocketContext());
            }
            return socketResponse;
        }else{
            throw new SocketDisconnectionException(ExceptionCodeTable.EX_SOCKET_HAS_DISCONNECT.CODE,
                    ExceptionCodeTable.EX_SOCKET_HAS_DISCONNECT.MESSAGE,session);
        }
    }

    public OnPreResonpseListener getOnPreResonpseListener() {
        return onPreResonpseListener;
    }

    public void setOnPreResonpseListener(OnPreResonpseListener onPreResonpseListener) {
        this.onPreResonpseListener = onPreResonpseListener;
    }

    public OnAfterAcceptDataListener getOnAfterAcceptDataListener() {
        return onAfterAcceptDataListener;
    }

    public void setOnAfterAcceptDataListener(OnAfterAcceptDataListener onAfterAcceptDataListener) {
        this.onAfterAcceptDataListener = onAfterAcceptDataListener;
    }
}
