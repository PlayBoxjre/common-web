/**
 * @description <pre>
 *     This is resource abstracting package. Make all resource to be simply abstraction.
 *     这是将资源进行抽象概括，形成简单的结构去取代资源的复杂性。总结资源的相同属性，扩展资源不同部分。
 *     完成对资源访问时的简单化和有效化。
 * </pre>
 *
 * @author kong xiang
 * @since 1.0
 * @date 2018/04/08
 * @email playboxjre@Gmail.com
 */
package com.kong.support.resources;
/*
    RUP
1 https://www.ibm.com/developerworks/cn/rational/r-rupbp/
    1 边界： 只作为资源的抽象层次定义，不关注其他任何外部东西。可以提供给外部使用
        1。本地文件资源  local ： 文件
        2。网络资源      remote ： 网络资源
     2 成本控制： 抽象定义出高层接口即可
     3 时间： 3天左右
     4 用例： 通过统一资源定位获取资源。
             对资源可以实现多种数据格式见的转换


 */
