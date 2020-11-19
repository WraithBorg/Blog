## MVCC多版本并发控制
+ 脏写（Dirty Write） 如果一个事务修改了另一个未提交事务修改过的数据，那就意味着发生了脏写，
+ 脏读（Dirty Read） 如果一个事务读到了另一个未提交事务修改过的数据，那就意味着发生了脏读，
+ 不可重复读（Non-Repeatable Read） 如果一个事务只能读到另一个已经提交的事务修改过的数据，并且其他事务每对该数据进行一次修改并提交后，该事务都能查询得到最新值
+ 幻读（Phantom） 如果一个事务先根据某些条件查询出一些记录，之后另一个事务又向表中插入了符合这些条件的记录，原先的事务再次按照该条件查询时，能把另一个事务插入的 记录也读出来，那就意味着发生了幻读，


+ READ UNCOMMITTED隔离级别下，可能发生脏读、不可重复读和幻读问题。
+ READ COMMITTED隔离级别下，可能发生不可重复读和幻读问题，但是不可以发生脏读问题
+ REPEATABLE READ隔离级别下，可能发生幻读问题，但是不可以发生脏读和不可重复读的问题。
+ SERIALIZABLE隔离级别下，各种问题都不可以发生。

### MVCC原理-版本链
+ trx_id：每次一个事务对某条聚簇索引记录进行改动时，都会把该事务的事务id赋值给trx_id隐藏列。
+ roll_pointer：每次对某条聚簇索引记录进行改动时，都会把旧的版本写入到undo日志中，然后这个隐藏列就相当于一个指针，可以通过它来找到该记录修改前的 信息。
+ 使用READCOMMITTED隔离级别的事务在每次查询开始时都会生成一个独立的ReadView。

MVCC（Multi-VersionConcurrencyControl，多版本并发控制）
指的就是在使用READ COMMITTD、REPEATABLE READ这两种隔离级别的 事务在执行普通的SEELCT操作时访问记录的版本链的过程，
这样子可以使不同事务的读-写、写-读操作并发执行，从而提升系统性能。
READ COMMITTD、REPEATABLE READ这两个隔离级别的一个很大不同就是：
生成ReadView的时机不同，READCOMMITTD在每一次进行普通SELECT操作前都会生成一个ReadView，
而REPEATABLE READ只在第一次进行普通SELECT操作前生成一个ReadView，之后的查询操作都重复使用这个ReadView就好了。

在执行SELECT语句时会先生成一个ReadView，ReadView的m_ids列表的内容就是[100, 200]，
min_trx_id(当前最小事物id)为100，max_trx_id为201(下一个事物最大事物id)，creator_trx_id为0。
使用READ COMMITTED隔离级别的事务 能读到小于当前最小事物id的内容，根据trx_id找到roll_pointer，再根据roll_pointer在undo日志找到相关信息


insert undo在事务提交之后就可以被释放掉了，而update undo由于还需要支持MVCC，不能立即删除掉
