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

import com.kong.support.annotations.NoUse;
import com.kong.support.exceptions.BaseException;
import com.kong.support.exceptions.ExceptionHandler;
import com.kong.support.exceptions.socket.SocketBaseException;
import com.kong.support.socket.SocketConfiguration;
import com.kong.support.socket.SocketContext;
import com.kong.support.socket.helper.DataInteractionLifeCycle;
import com.kong.support.socket.helper.imps.AbstractDataInteractionLifeCycle;
import com.kong.support.socket.nio.protocols.ProtocolDataProcessor;
import com.kong.support.socket.nio.protocols.ProtocolParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * File Name NioServerImpl
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-05-22
 * EMAIL     playboxjre@Gmail.com
 */
public final class NioServerImpl implements NioServer {
    Logger logger = LoggerFactory.getLogger(NioServerImpl.class);
    /**接收处理key的个数*/
    private long count = 0;
    /**socket 配置项*/
    private SocketConfiguration configuration;
    /**key类型分发器*/
    private EventDispatcher eventDispatcher;
    /**
     * 上下文信息记录
     */
    private SocketContext socketContext ;
    /**
     * 异常处理
     */
    @NoUse
    private ExceptionHandler exceptionHandler;

    private DataInteractionLifeCycle dataInteractionLifeCycle ;

    private ProtocolParser protocolParser;
    /**
     * 是否以异步读取
     */
    private boolean readAsync;
    /**
    * 是否是异步写入
    * */
    private boolean writeAsync;

    @Override
    public void configure(SocketConfiguration configuration) {
        socketContext = new SocketContext();
        this.configuration = configuration;
        socketContext.setAlwaysReturnMessage(configuration.isAlwaysReturnMessage());
        socketContext.setGolabeCharsetName(configuration.getDefaultGolabeCharsetName());
        socketContext.setPrefixChar(configuration.getPrefixChar());
        socketContext.setLimitsOfErrorCount(configuration.getLimitsOfErrorCount());
        socketContext.setOnEventDispatcherListener(configuration.getOnEventDispatcherListener());
        socketContext.setOnSocketConnectionListener(configuration.getSocketConnectionListener());
        this.exceptionHandler = configuration.getExceptionHandler();
        this.readAsync = configuration.isReadAsync();
        this.writeAsync = configuration.isWriteAsync();
        this.protocolParser = configuration.getProtocolParser();
        AbstractDataInteractionLifeCycle lifeCycle = (AbstractDataInteractionLifeCycle) configuration.getDataInteractionLifeCycle();
        lifeCycle.setDataParser(configuration.getDataParser());
        lifeCycle.setDataFormatter(configuration.getFormatter());
        lifeCycle.setRequestClassType(configuration.getRequestTypeClass());
        this.dataInteractionLifeCycle = lifeCycle;

        if (this.eventDispatcher == null) {
            DefaultEventDispatcher dispatcher  = new DefaultEventDispatcher();
            dispatcher.setOnAfterAcceptDataListener(
                    new ProtocolDataProcessor(this.protocolParser,this.dataInteractionLifeCycle));
            dispatcher.setOnPreResonpseListener(null);
            this.eventDispatcher = dispatcher;
        }



    }



    @Override
    public boolean start() {
        final boolean isBlocking = configuration.isBlocking();
        final int port = configuration.getPort();
        final int registerKey = SelectionKey.OP_ACCEPT ;

        try {
            logger.debug("start build provider ...");
            SelectorProvider provider = SelectorProvider.provider();
            logger.debug("build provider successful");
            logger.debug("open selector ...");
            Selector selector = provider.openSelector();
            logger.debug("open selector successful");
            logger.debug("bind server to port {} ...", port);
            ServerSocketChannel serverSocketChannel = provider.openServerSocketChannel();
            serverSocketChannel.bind(new InetSocketAddress(port));
            logger.debug("bind server successful");
            logger.debug("configurate non-blocking mode ...");
            serverSocketChannel.configureBlocking(isBlocking);
            logger.debug("configurate register key : protocols , write ,connect , read");
            serverSocketChannel.register(selector, registerKey);


            logger.debug("start server listener ...");

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    int select = selector.select();
                    if (select == 0) continue;
                    logger.debug("select ready channel num {} start ...", select);

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    logger.debug("");
                    while (iterator.hasNext()) {
                        count++;//次数
                        logger.debug("This is 【{}】 Event", count);
                        SelectionKey next = iterator.next();
                        iterator.remove();
                        try {
                            Event<SelectionKey> event = new Event<>();
                            event.setData(next);
                            logger.debug("Dispatcher Event {} - {}", event.getEventId(), event.getEventUUID());
                            this.eventDispatcher.dispatchEvent(this.socketContext, event);
                        } catch (Exception ex) {
                            //runExceptionProcess(ex);
                            // once key event read / write /protocols ex
                            if (ex instanceof SocketBaseException
                                    ) {
                                SocketBaseException ex1 = (SocketBaseException) ex;
                                SocketSession socketSession = ex1.getSocketSession();
                                //socketSession.getSocketChannel()
                                if (socketSession != null) {
                                    socketSession.setSocketStatus(SocketSession.SOCKET_STATUS.SOCKET_EXCEPTION_CLOSE);
                                    socketSession.close();
                                }
                                next.attach(null);
                                if (next.channel() != null) {
                                    next.channel().close();
                                }
                            } else if (ex instanceof RuntimeException) {
                                next.attach(null);
                                if (next.channel() != null) {
                                    next.channel().close();
                                }
                            } else if (ex instanceof BaseException) {

                            } else {
                                throw ex;
                            }
                            ex.printStackTrace();
                        }

                    }
                    logger.info("clear : key count must be  0 ;Now == {}", selectionKeys.size());
                } catch (Exception e) {
                    //selector once
                }
            }
        }catch (Exception e){
            // sys server ex
        }
        return false;
    }

    @Override
    public void run() {
        start();
    }


    private final void runExceptionProcess(Exception e){
        if (exceptionHandler!=null)
            exceptionHandler.exceptionHandler(e);
    }
}
