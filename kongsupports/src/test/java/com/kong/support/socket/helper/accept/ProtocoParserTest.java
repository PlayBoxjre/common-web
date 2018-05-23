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
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;

/**
 * File Name ProtocoParserTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 */
public class ProtocoParserTest  {
    Logger logger = LoggerFactory.getLogger(ProtocoParserTest.class);
    private ProtocolParser protocolParser;

    private String oneLine ;
    private String[] multiLine ;
    SocketSession session;
    @Before
    public void setUP(){
        protocolParser = new ProtocolParserImpl();
        char end = protocolParser.defineEndSeparator();
        char start = protocolParser.defineStartSeparator();
        long l = protocolParser.maxAcceptLength();
        oneLine  =   start+"3467890sdfaksdhjfiuengalksdhnf weiunqwmi hwiuh iqweh rihkljsdhfiuhel kq " +
                "ausdhflkhew uhasdn fiua uew aldsf\t sjadifjela tasdfjeija ;dsf " +
                "sdkfjaije tkjasdoijf i"+end;
        multiLine = new String[]{
                start+"1"+end+"",
          "hellowlsiejflad",
          "dhfel"+start+"hhhh",
          "hehfasdf"+end+"hsadfe",
          "hellwo"+end + "sdfeads"+start,
          "hellow+"+start+"123124123"+start+"enenenen"+end
        };
        session = new SocketSession();
        session.setSocketStatus(SocketSession.SOCKET_STATUS.SOCKET_CONNECTED);


    }


    @Test
    public  void  protocolParseTest() throws InterruptedException {
        int count = 0;
        while (count ++ != 1) {
            try {
                long time =System.currentTimeMillis();
                boolean b = protocolParser.onceAccept(session, oneLine.getBytes());
                long time1 = System.currentTimeMillis();
                logger.info(" mills --> {}",(time1-time));                if (b) {
                    if (b) {
                        String[] lines = protocolParser.getLines();
                        Arrays.stream(lines).forEach(n -> {
                            logger.info(" ===== line --> {} ", n);
                        });
                    }
                }
            } catch (SocketAcceptException e) {
                e.printStackTrace();
            } catch (SocketSessionException e) {
                e.printStackTrace();
            } catch (IllegalCharException e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }
    }


    @Test
    public void multiLineTest(){
        for (String s : multiLine){
            try {
                logger.debug(" line ::: {}",s);
                long time = Calendar.getInstance().getTime().getTime();
                protocolParser.startTagStragety(ProtocolParser.START_TAG_STRAGETY.NEW_START_DEUL);
                boolean b = protocolParser.onceAccept(session, s.getBytes());
                long time1 = Calendar.getInstance().getTime().getTime();
                logger.info(" mills --> {}",(time1-time));
                if (b){
                    String[] lines = protocolParser.getLines();
                    Arrays.stream(lines).forEach(n->{
                        logger.info(" ===== line --> {} ",n);
                    });
                }
            } catch (SocketAcceptException e) {
                e.printStackTrace();
            } catch (SocketSessionException e) {
                e.printStackTrace();
            } catch (IllegalCharException e) {
                e.printStackTrace();
            }
        }
    }
}
