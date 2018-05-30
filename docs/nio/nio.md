#Problem

1. 服务端一直读事件，读的都是null字符
    
    ```
        使用NIO，select()到读事件时，要处理4种情况：
        
        1. channel还有数据，继续读。
        
        2. channel中暂时没数据，但channel还没断开，这是读取到的数据个数为0，结束读，继续到select()处阻塞等待数据。
        
        3. 另一端channel.close()关闭连接，这时候读channel返回的读取数是-1，表示已经到末尾，跟读文件到末尾时是一样的。既然已经结束了，就把对应的SelectionKey给cancel掉，表示selector不再监听这个channel上的读事件。并且关闭连接，本端channel.close()。
        
        4. 另一端被强制关闭，也就是channel没有close()就被强制断开了，这时候本端会抛出一个IOException异常，要处理这个异常。
        
         
        
        之前对 另一端channel.close()关闭连接 没有细究，不清楚 读channel返回的读取数-1 是什么意思。然后没有cancel对应的SelectionKey，也没关闭连接，结果就是selector.select()一直返回读事件，但是没有数据。
        
     
    ```
    