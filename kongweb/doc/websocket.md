# Spring mvc 方式实现websocket
https://www.cnblogs.com/interdrp/p/7903736.html
https://www.tpyyes.com/a/javaweb/2017/0119/41.html
# websocket 实现方式

## spring websocket解决跨域访问问题
https://blog.csdn.net/sdmxdzb/article/details/78351939
## spring xml 配置websocket
https://www.cnblogs.com/zhjh256/p/6052102.html
## servletEndPoint javaEE 获取HttpSession （spring内置可获取）
https://www.cnblogs.com/zhuxiaojie/p/6238826.html

> spring websocket 提供的shakeInterceptor 已经内置获取HttpSession的方法

## spring 配置关键步骤
- 1.(your class) implements HandshakeInterceptor
- 2.(your class ) implements WebSocketHandler
- 3.Java 配置

    ```@see com.kong.web.websocket.WebSocketConfig.java```
- 4.Xml 配置

    ```@see appliction-web.xml```