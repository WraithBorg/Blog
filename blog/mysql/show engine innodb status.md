#### 信息结构
```
mysql> show engine innodb status;
BACKGROUND THREAD 后台进程
SEMAPHORES 信号量 (解决高并发性能瓶颈用)
LATEST FOREIGN KEY ERROR 最近一次外键错误
LATEST DETECTED DEADLOCK 最近一次死锁
TRANSACTIONS 事务
FILE I/O 文件I/O
INSERT BUFFER AND ADAPTIVE HASH INDEX 显示了insert buffer和adaptive hash index两个部分的结构的状态
LOG 关于innodb事务日志（重做日志）子系统的统计
```

#### 创建外键错误
```
mysql> create table parent(parent_id int not null,primary key(parent_id)) engine = innodb;
mysql> create table child(child_id int not null,key child_id(child_id),constraint i_child foreign key(child_id) references parent(parent_id)) engine = innodb;
mysql> insert into parent(parent_id) values (1);
mysql> insert into child(child_id) values (1);

mysql> delete from parent;
ERROR 1451 (23000): Cannot delete or update a parent row: a foreign key constraint fails (`test`.`child`, CONSTRAINT `i_child` FOREIGN KEY (`child_id`) REFERENCES `parent` (`parent_id`))
mysql> alter table parent modify parent_id int unsigned not null;
ERROR 1833 (HY000): Cannot change column 'parent_id': used in a foreign key constraint 'i_child' of table 'test.child'

```
创建死锁错误
```
两种死锁，
	1:在等待关系图里是一个真正的循环，
	2:在等待关系图里，因代价昂贵而无法检测它是不是包含了循环，如果innodb要在关系图里检查超过100W个锁，或者在检查过程中，innodb要重做200个以上的事务，那它会放弃，并宣布这里有一个死锁，
mysql> create table test_deadlock(id int not null primary key auto_increment,test int unsigned not null);
mysql> insert into test_deadlock(test) values(1),(2),(3),(4),(5);

按步骤执行，打开两个窗口
窗口一
mysql> set autocommit = 0;
mysql> select * from test_deadlock where id = 1 for update;
窗口二
mysql> set autocommit = 0;
窗口一
mysql> select * from test_deadlock where id=2 for update;
窗口二
mysql> select * from test_deadlock where id = 1 for update;
ERROR 1213 (40001): Deadlock found when trying to get lock; try restarting transaction
此时产生死锁
```
