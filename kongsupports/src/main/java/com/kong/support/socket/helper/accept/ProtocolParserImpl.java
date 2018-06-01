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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * File Name ProtocolParserImpl
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 */
public class ProtocolParserImpl implements ProtocolParser {

    Logger logger = LoggerFactory.getLogger(ProtocolParserImpl.class);
    private char startChar = '\uEEFE';
    private char endChar = '\uEEFF';
    private long maxAcceptLength;
    private List<String> lines = new LinkedList<>();

    private String tempStrAfterEndTag;
    /**
     * start tag 处理策略
     */
    private START_TAG_STRAGETY startTagStragety = START_TAG_STRAGETY.EXCEPTION_DEUL;

    /**
     * 解析成功一条数据之后是够继续解析之后的数据
     * @return
     */
    private boolean continueAtSuccessParse;
    @Override
    public char defineStartSeparator() {
        return startChar ;
    }

    @Override
    public char defineEndSeparator() {
        return endChar ;
    }

    @Override
    public long maxAcceptLength() {
        return maxAcceptLength == 0 ? 1024 * 256  - 2 : maxAcceptLength;
    }

    @Override
    public boolean onceAccept(SocketSession session, byte[] datas) throws SocketAcceptException, SocketSessionException, IllegalCharException {
        if (session == null ){
            throw  new NullPointerException("session is not null !!");
        }
        if (session.isClose() ||
                session.getSocketStatus() != SocketSession.SOCKET_STATUS.SOCKET_CONNECTED){
            String ex = "socket status is close or not connected";
            throw new SocketSessionException(ex.hashCode(),ex);
        }
        StringBuilder dataBuffer = session.getDataBuffer();
        if (dataBuffer == null){
            dataBuffer = new StringBuilder((int) maxAcceptLength()) ;
            session.setDataBuffer(dataBuffer);
        }

        logger.debug(" origin source len :{}",datas.length);
        CharBuffer charBuffer = CharBuffer.wrap(new String(datas,Charset.defaultCharset()));
        logger.debug("char buff :position {},limit {} ,length {}",charBuffer.position(),charBuffer.limit()
        ,charBuffer.length());

        int flag = session.parsePositionFlag;
        boolean ret = false;
        final char startChar = defineStartSeparator();
        final char endChar = defineEndSeparator();
        logger.debug("start --> {}",(int)startChar);
        logger.debug("end -->{}",(int)endChar);

        if ( datas.length != 0) {
            if (dataBuffer.length() == 0 ){// 开始
                logger.debug("== start get startTag");
                while (charBuffer.hasRemaining()){
                    char c = charBuffer.get();
                    logger.debug("--> start find start char or end char : {}",(int)c);
                    if (c == startChar){
                        logger.debug("-->  find start char {} ",c);
                        if (flag == 0)
                            selectStartTagStragetyDuel(session,dataBuffer);
                        flag = 0;
                        //dataBuffer.put(c);
                    }else if (c == endChar){
                        if (flag != 0) {
                            session.getDataBuffer().delete(0,session.getDataBuffer().length());
                            String ex = "非法socket数据获取异常 end tag must be find after start tag ";
                            throw new SocketAcceptException(ex.hashCode(),ex);
                        }
                        logger.debug("-->  find end char  {}",c);
                        flag = 1;
                       // dataBuffer.put(c);
                        ret = true;
                        addLine(dataBuffer.toString());
                        session.getDataBuffer().delete(0,session.getDataBuffer().length());
                        if (isContinueAtSuccessParse()){
                            int length = charBuffer.slice().length();
                            char[] chars = new char[length];
                            String s = new String(chars);
                            logger.debug("after string ::: {}",s);
                            onceAccept(session, s.getBytes());
                        }else
                            break;
                    }else{
                        if (flag == 0){
                            logger.debug("-->  find start - {} - end  char  ",c);
                            dataBuffer.append(c);
                        }
                    }
                }

            }else if (flag == 0){//一解析出开始
                logger.debug("== exist start : find end ");
                while (charBuffer.hasRemaining()){
                    char c = charBuffer.get();
                    if (c == startChar){
                        selectStartTagStragetyDuel(session, dataBuffer);

                    }else if (c == endChar){
                        //dataBuffer.put(c);
                        flag = 1;
                        addLine(dataBuffer.toString());
                        session.getDataBuffer().delete(0,dataBuffer.length());
                        ret = true;

                        if (isContinueAtSuccessParse()){
                            int length = charBuffer.slice().length();
                            char[] chars = new char[length];
                            String s = new String(chars);
                            logger.debug("after string ::: {}",s);
                            onceAccept(session, s.getBytes());
                        }else
                            break;
                    }else{
                        dataBuffer.append(c);
                    }
                }
            }else{
                String ex = "非法数据解析异常";
                session.getDataBuffer().delete(0,dataBuffer.length());
                session.parsePositionFlag = -1;
                throw new IllegalCharException(ex.hashCode(),ex);
            }

            resetSessionParseTag(session, flag);

            logger.debug(" parse position : {}",flag);
        }else{ // 数据不标准 可能是 null 可能 长度0

        }
        return ret;
    }

    /**
     * 选取开始标签策略处理
     * @param session
     * @param dataBuffer
     * @throws IllegalCharException
     */
    private void selectStartTagStragetyDuel(SocketSession session, StringBuilder dataBuffer) throws IllegalCharException {
        switch (startTagStragety(null)){
            case EXCEPTION_DEUL:
                String ex = "非法字符异常 已经存在start tag ";
                session.getDataBuffer().delete(0,dataBuffer.length());
                throw new IllegalCharException(ex.hashCode(),ex);
            case NEW_START_DEUL:
                session.getDataBuffer().delete(0,dataBuffer.length());
                break;
            case IGNORE_CONTENT_START_DUEL:

                break;
        }
    }

    private void resetSessionParseTag(SocketSession session, int flag) {
        if (flag == -1){
            session.errorCount.incrementAndGet();
        }
        session.parsePositionFlag = flag == 1?-1:flag;
    }

    @Override
    public START_TAG_STRAGETY startTagStragety(START_TAG_STRAGETY s) {
        return startTagStragety = (s==null?startTagStragety:s);
    }

    @Override
    public boolean isContinueAtSuccessParse() {
        return continueAtSuccessParse;
    }

    @Override
    public synchronized String[] getLines() {
        String[] one = new String[lines.size()] ;
        lines.toArray(one);
        lines.clear();
        return one;
    }

    private synchronized void addLine(String str){
        lines.add(str);
    }

    public char getStartChar() {
        return startChar;
    }

    public void setStartChar(char startChar) {
        this.startChar = startChar;
    }

    public char getEndChar() {
        return endChar;
    }

    public void setEndChar(char endChar) {
        this.endChar = endChar;
    }

    public long getMaxAcceptLength() {
        return maxAcceptLength;
    }

    public void setMaxAcceptLength(long maxAcceptLength) {
        this.maxAcceptLength = maxAcceptLength;
    }

    public START_TAG_STRAGETY getStartTagStragety() {
        return startTagStragety;
    }

    public void setStartTagStragety(START_TAG_STRAGETY startTagStragety) {
        this.startTagStragety = startTagStragety;
    }

    public void setContinueAtSuccessParse(boolean continueAtSuccessParse) {
        this.continueAtSuccessParse = continueAtSuccessParse;
    }



}
