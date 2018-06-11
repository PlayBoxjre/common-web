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

import com.kong.support.socket.configuration.SocketConfiguration;
import com.kong.support.socket.configuration.SocketConfigurationBuilder;
import com.kong.support.socket.server.SocketServer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name ConfigTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-11
 * EMAIL     playboxjre@Gmail.com
 */
public class ConfigTest {
    Logger logger = LoggerFactory.getLogger(ConfigTest.class);

    @Before
    public void config(){
        SocketServer socketServer = new SocketConfigurationBuilder().buildServerConfiguration()

                .buildServer();
     }


    @Test
    public void testConfigServerBuild(){

    }

}

