 ### MySQL中有六种日志文件，   
https://www.cnblogs.com/wy123/p/8365234.html  
分别是：  
重做日志（redo log）、    
&emsp;&emsp;确保事务的持久性。  
&emsp;&emsp;防止在发生故障的时间点，尚有脏页未写入磁盘，  
&emsp;&emsp;在重启mysql服务的时候，根据redo log进行重做，从而达到事务的持久性这一特性。  
回滚日志（undo log）、   
&emsp;&emsp;保存了事务发生之前的数据的一个版本，可以用于回滚，  
&emsp;&emsp;同时可以提供多版本并发控制下的读（MVCC），也即非锁定读  
二进制日志（binlog）、  
&emsp;&emsp;作用：  
&emsp;&emsp;1，用于复制，在主从复制中，从库利用主库上的binlog进行重播，实现主从同步。  
&emsp;&emsp;2，用于数据库的基于时间点的还原。  
错误日志（errorlog）、  
慢查询日志（slow query log）、  
一般查询日志（general log），  
中继日志（relay log）  
