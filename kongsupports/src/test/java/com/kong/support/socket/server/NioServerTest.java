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

package com.kong.support.socket.server;

import com.kong.support.socket.SocketConfiguration;
import com.kong.support.socket.nio.server.NioServer;
import com.kong.support.socket.nio.server.NioServerImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * File Name NioServerTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-22
 * EMAIL     playboxjre@Gmail.com
 */
public class NioServerTest {
    Logger logger = LoggerFactory.getLogger(NioServerTest.class);

    @Test
    public void serverTest(){
        SocketConfiguration configuration = new SocketConfiguration();

        configuration.setPort(9999);
        configuration.setBlocking(false);
        configuration.setSocketConnectionListener((session)->{
            logger.info("session ::: {}",session.getSocketChannel().getRemoteAddress());
        });

        NioServer nioServer = new NioServerImpl();
        nioServer.configure(configuration);

        new Thread(nioServer).start();

        try {
            while (true)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void clientTest() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Inet4Address inet4Address = (Inet4Address) Inet4Address.getLoopbackAddress();
        socketChannel.connect(new InetSocketAddress(inet4Address,9999));
        socketChannel.write(ByteBuffer.wrap("hello".getBytes()));

        while(! socketChannel.finishConnect() ){
            //wait, or do something else...

        }

    }
}
