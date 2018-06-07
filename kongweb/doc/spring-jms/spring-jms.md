# spring JMS 配置
--- 
## 1 What is JMS
[JMS介绍](https://blog.csdn.net/qh_java/article/details/55224259)

## 2 ActiveMQ 安装与配置
[ActiveMQ安装](https://www.cnblogs.com/shamo89/p/7645651.html)
~/apps/apache-activemq-5.15.4
* [ActiveMQ配置管理端和消费者认证](http://www.68idc.cn/help/jiabenmake/qita/20160512614853.html)
    ```$xslt
        修改activemq.xml配置,需要新增一个插件,在<broker>节点里面<systemUsage>节点前面添加如下
    
                <plugins>
                 	<simpleAuthenticationPlugin>
                 		<users>
                 			<authenticationUser username="${activemq.username}" password="${activemq.password}" groups="users,admins"/>
                 		</users>
                 	</simpleAuthenticationPlugin>
                 </plugins>
    ```
   
- [ActiveMQ 基于JAAS的安全机制](https://blog.csdn.net/sunshine_bean/article/details/44118665)

- [*ActiveMq认证与授权配置](https://blog.csdn.net/chao_1990/article/details/55270922)

## ActiveMQ详细入门使用教程
[ActiveMQ详细入门使用教程](https://blog.csdn.net/liuyuanq123/article/details/79109218)
-  Spring JMS - ActiveMQ [Spring JMS ActiveMQ](https://www.cnblogs.com/ll409546297/p/6898155.html)

