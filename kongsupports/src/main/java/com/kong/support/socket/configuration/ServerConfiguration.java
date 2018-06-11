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

package com.kong.support.socket.configuration;

import com.kong.support.socket.server.SocketServer;

/**
 * 服务端所需要的参数字段定义
 */
public class ServerConfiguration extends SocketConfiguration {
	/**服务主机端口 默认端口19191*/
	private int port = 19191;
	/**是否使用阻塞io*/
	private boolean isBlocking = false;

	private int readProcessNumber;
	private int writeProcessNumber;






	public SocketServer buildServer() {
		return null;
	}

}
