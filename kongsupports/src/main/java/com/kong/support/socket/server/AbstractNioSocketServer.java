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

import com.kong.support.socket.SocketContext;
import com.kong.support.socket.configuration.ServerConfiguration;

/**
 * 抽象nio实现的socket服务端
 */
public abstract class AbstractNioSocketServer implements SocketServer {

	private SocketContext socketContext;
	private ServerConfiguration serverConfiguration;


	@Override
	public void configConfiguration(ServerConfiguration serverConfiguration) {
		// socket context 需要记录的配置
		if (socketContext == null)
			socketContext = new SocketContext();
		recordConfiguration(socketContext,serverConfiguration);
	}

	protected abstract void recordConfiguration(SocketContext socketContext, ServerConfiguration serverConfiguration);

	public SocketContext getSocketContext() {
		return socketContext;
	}

	public void setSocketContext(SocketContext socketContext) {
		this.socketContext = socketContext;
	}
}
