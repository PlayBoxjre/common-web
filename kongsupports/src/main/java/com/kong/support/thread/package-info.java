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

/**
 * 线程工具包
 * 1。实现线程基础工具
 * 2。实现异步线程分发模型
 *      2-1 ThreadManager： 线程管理器- 管理线程的创建，事件分发，线程管理，事件准入，事件执行
 *      2-2 EventDispatcher: 分发线程事件，事件准入（过滤，筛选，标记，清除，撤销，重置）
 *          事件类型 作为分发到事件中转的判断依据
 *      2-3 TransitPoint：事件中转队列（优先级策略），中转点数量和门限，目标绑定（线程目标，如果不存在，将创建一个使用）
 *      2-4 ThreadPoolManger: 线程池管理 线程创建，线程维护，线程销毁，线程记录，线程调控，线程管理，线程执行。
 *      2-5 Looper： 线程记录者
 *      2-6 EventHandler 线程消息处理
 *      2-7 OnEventProcessor
 *
 * 3。实现异步线程通用型
 */
package com.kong.support.thread;

/**
 * File Name package-info
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-12
 * EMAIL     playboxjre@Gmail.com
 *
 */
class PackageInfo{

}