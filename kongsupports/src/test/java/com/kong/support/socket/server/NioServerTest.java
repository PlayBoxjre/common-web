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
import com.kong.support.socket.nio.callbacks.OnEventDispatcherListener;
import com.kong.support.socket.nio.server.DefaultEventDispatcher;
import com.kong.support.socket.nio.server.NioServer;
import com.kong.support.socket.nio.server.NioServerImpl;
import com.kong.support.toolboxes.StreamTool;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

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
        configuration.setExceptionHandler(null)
                .setLimitsOfErrorCount(10)
                .setOnAfterAcceptDataListener(null)
                .setOnPreResonpseListener(null)
                .setOnEventDispatcherListener(new OnEventDispatcherListener() {
                    @Override
                    public void onEventDispatch(int eventId, String eventUUID) {
                        logger.info(" event dispatcher : event id {} , event uuid {}",eventId,eventUUID);
                    }
                })
                .setEventDispatcher(new DefaultEventDispatcher())
                .setReadAsync( false)
                .setWriteAsync(false)
                .setAlwaysReturnMessage(true)
                .setDefaultGolabeCharsetName("UTF-8");

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
    public void clientTest() throws IOException, InterruptedException {
//        try {
//            // 获得一个Socket通道
//            SocketChannel channel = SocketChannel.open();
//            // 设置通道为非阻塞
//            channel.configureBlocking(false);
//            // 获得一个通道管理器
//            Selector selector = Selector.open();
//
//            // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
//            //用channel.finishConnect();才能完成连接
//            channel.connect(new InetSocketAddress("127.0.0.1", 9999));
//            //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
//            channel.register(selector, SelectionKey.OP_CONNECT);
//            // 启动读取线程
//            new TCPClientReadThread(selector, "222");
//        } catch (Exception e) {
//
//         }

        Socket socket =new Socket("localhost",9999);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("123456".getBytes());
        outputStream.flush();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                byte[] bytes = StreamTool.readByteFromInputStreamSocket(inputStream, (n) -> {
                    logger.debug("---{}", new String(n));
                });

                String s = new String(bytes);
                logger.debug(s);
            }
        };
        new Thread(runnable).start();




        while (true){
            Thread.sleep(100);
        }




}
}
