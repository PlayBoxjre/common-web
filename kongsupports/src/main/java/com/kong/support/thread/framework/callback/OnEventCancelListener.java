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

package com.kong.support.thread.framework.callback;

import com.kong.support.thread.framework.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name OnEventCancelListener
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 * 事件被取消监听 执行时动态监听
 * 最后周期为 处理过程完成之后，() 返回post监听之前
 */
public interface OnEventCancelListener {

    public void onEventCancel(Event event);
}
