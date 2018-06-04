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

import com.kong.support.ExceptionCodeTable;
import com.kong.support.exceptions.common.ClassFormatException;
import com.kong.support.exceptions.common.CryptoExceptions;
import com.kong.support.exceptions.socket.*;
import com.kong.support.socket.helper.DataInteractionLifeCycle;
import com.kong.support.socket.helper.send.SocketResponse;
import com.kong.support.socket.nio.callbacks.OnAfterAcceptDataListener;
import com.kong.support.socket.nio.request.RequestHeader;
import com.kong.support.socket.nio.server.RequestContext;

import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

/**
 * File Name ProtocolDataProcessor
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-04
 * EMAIL     playboxjre@Gmail.com
 */

public class ProtocolDataProcessor  implements OnAfterAcceptDataListener {

    private ProtocolParser protocolParser;


    private DataInteractionLifeCycle lifeCycle;

    public ProtocolDataProcessor(ProtocolParser protocolParser, DataInteractionLifeCycle lifeCycle) {
        this.protocolParser = protocolParser;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public SocketResponse onAfterAcceptData(RequestContext requestContext, byte[] bytes, String charset) throws SocketSessionException, SocketConnectionException, SocketException {

        int code = 1;
        String message  = "successful";
        byte[] data = null;
        try {
            //协议解析获取 数据信息
            boolean b = protocolParser.onceAccept(requestContext.getSocketSession(), bytes);
            if (!b) return null;
            String[] lines = protocolParser.getLines();

            for (String line : lines){// 合法的信息条数信息
                line = buildRequestHeader(requestContext,line);// 解析头信息 如果存在配置
                //lifeCycle.accept()
                Charset charset1 = Charset.forName(requestContext.getSocketContext().getGolabeCharsetName());
                try {
                    byte[] retData =  lifeCycle.receiveOriginAndResponse(requestContext, line.getBytes(
                            charset1
                            ),
                            charset1);
                    if (retData == null)
                        return null;
                    data = retData;
                    code = 200;
                    message="服务端获取成功";
                }  catch (EncodingException e) {
                    message="指定解码算法异常";
                    e.printStackTrace();
                } catch (DataParserException e) {
                    message ="数据解析异常 --> object ";
                    e.printStackTrace();
                } catch (CryptoExceptions cryptoExceptions) {
                    message= "数据指定加密/解密异常";
                    cryptoExceptions.printStackTrace();
                } catch (DataFormatException e) {
                    message="日期格式化错误";
                    e.printStackTrace();
                } catch (DecodingException e) {
                    message="用户指定decode错误异常";
                    e.printStackTrace();
                } catch (ClassFormatException e) {
                    message = "服务器内部错误";
                    e.printStackTrace();
                }


                SocketResponse response = new SocketResponse();
                response.setCode(code);
                response.setMessage(message);
                response.setSession(requestContext.getSocketSession());
                response.setSocketContext(requestContext.getSocketContext());
                response.setData(data);
                return response;


            }

        } catch (SocketAcceptException e) {// 用户处理异常
            e.printStackTrace();
        }  catch (IllegalCharException e) {
            e.printStackTrace();
        }


        return null;
    }




    private String buildRequestHeader(RequestContext requestContext, String line) {
        RequestHeader header = new RequestHeader();
        requestContext.setRequestHeader(header);
        return line;
    }

    public DataInteractionLifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(DataInteractionLifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public ProtocolParser getProtocolParser() {
        return protocolParser;
    }

    public void setProtocolParser(ProtocolParser protocolParser) {
        this.protocolParser = protocolParser;
    }
}
