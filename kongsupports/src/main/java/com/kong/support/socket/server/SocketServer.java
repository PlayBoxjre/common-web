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

import com.kong.support.socket.configuration.ServerConfiguration;

/**
 * 服务器接口
 */
public interface SocketServer extends Runnable{

	public void configConfiguration(ServerConfiguration serverConfiguration);

	public boolean start();

	public abstract void buildConnection();

	public abstract void readData();

}
