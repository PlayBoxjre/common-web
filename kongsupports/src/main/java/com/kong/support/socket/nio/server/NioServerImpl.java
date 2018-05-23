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

import com.kong.support.socket.SocketConfiguration;
import com.kong.support.socket.helper.accept.SocketSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * File Name NioServerImpl
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-22
 * EMAIL     playboxjre@Gmail.com
 */
public class NioServerImpl implements NioServer {
    Logger logger = LoggerFactory.getLogger(NioServerImpl.class);

    private long count = 0;

    private SocketConfiguration configuration;

    @Override
    public void configure(SocketConfiguration configuration) {
        this.configuration = configuration;
    }



    @Override
    public boolean start() {
        final boolean isBlocking = configuration.isBlocking();
        final int port = configuration.getPort();
        final int registerKey = SelectionKey.OP_ACCEPT & SelectionKey.OP_READ &
                SelectionKey.OP_WRITE;



        try {
            logger.debug("start build provider ...");
            SelectorProvider provider = SelectorProvider.provider();
            logger.debug("build provider successful");
            logger.debug("open selector ...");
            Selector selector = provider.openSelector();
            logger.debug("open selector successful");
            logger.debug("bind server to port {} ...",port);
            ServerSocketChannel serverSocketChannel = provider.openServerSocketChannel();
            serverSocketChannel.bind(new InetSocketAddress(port));
            logger.debug("bind server successful");
            logger.debug("configurate non-blocking mode ...");
            serverSocketChannel.configureBlocking(isBlocking);
            logger.debug("configurate register key : accept , write ,connect , read");
            serverSocketChannel.register(selector,registerKey);
            logger.debug("start server listener ...");

            while (!Thread.currentThread().isInterrupted()){
                    try {
                        int select = selector.select();
                        if(select == 0) continue;
                        logger.debug("select ready channel num {} start ...",select);

                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                            Iterator<SelectionKey> iterator = selectionKeys.iterator();
                            logger.debug("");
                            while (iterator.hasNext()) {
                                count++;//次数
                                SelectionKey next = iterator.next();
                                if (next.isAcceptable()) {
                                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                                    SocketChannel accept = channel.accept();
                                    boolean connected = accept.isConnected();
                                    if(connected){
                                        logger.debug(" socket [{}] is build connected ",accept.getRemoteAddress());
                                    }
                                    if (next.attachment() == null){
                                        next.attach(new SocketSession());
                                    }
                                    SocketSession attachment = (SocketSession) next.attachment();
                                    Socket socket = accept.socket();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 8);

                                    int read = accept.read(byteBuffer);

                                    byte[] bytes = new byte[read];
                                    byteBuffer.get(bytes,0,read);

                                    logger.info(" get client : {} accetp info :{}",accept.getRemoteAddress(),new
                                    String(bytes));


                                    attachment.setSocket(socket);
                                    attachment.setSocketChannel(accept);
                                    attachment.setSocketStatus(SocketSession.SOCKET_STATUS.SOCKET_CONNECTED);
                                    attachment.setClose(false);

                                    if (configuration.getSocketConnectionListener()!=null){
                                        configuration.getSocketConnectionListener().onSocketConnected(attachment);
                                    }


                                }else if( next.isReadable()){
                                }else if(next.isWritable()){

                                }else {

                                }




                        }
                    }catch (Exception ex){
                        //系统异常
                    }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public void run() {
        start();
    }
}
