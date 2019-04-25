**PERFORMANCE_SCHEMA 主要用于手机数据库服务器性能参数，并且库里的表存储引擎均为PERFORMANCE_SCHEMA**

### 配置信息表
```
// 查询
 SHOW tables like '%setup%';
// 结果
mysql> show tables like '%setup%';
+----------------------------------------+
| Tables_in_performance_schema (%setup%) |
+----------------------------------------+
| setup_actors                           |
| setup_consumers                        |
| setup_instruments                      |
| setup_objects                          |
| setup_timers                           |
+----------------------------------------+
 >* setup_actors 用户维度表，监控所有用户？
 >* setup_consumers events的消费者类型，即收集的events写入到哪个统计表中？
 >* setup_instruments events 配置具体的instrument，主要包含4大类：idle、stage/xxx、statement/xxx、wait/xxx
    * id 表示socket空闲时间
    * stage 每个执行阶段的统计
    * statement 统计语句维度的信息
    * wait 统计各类等待事件，比如IO，mutux,spin_lock,condition等
 >* setup_objects 配置监控对象，默认不对mysql，performance_schema和information_schema中的表监控
 >* setup_timers 配置每种类型指令的统计时间单位。MICROSECOND表示统计单位是微妙，CYCLE表示统计单位是时钟周期，时间度量与CPU的主频有关，NANOSECOND表示统计单位是纳秒。但无论采用哪种度量单位，最终统计表中统计的时间都会装换到皮秒。（1秒＝1000000000000皮秒）
```
### instance表
```
mysql> show tables like '%instance%';
+-------------------------------------------+
| Tables_in_performance_schema (%instance%) |
+-------------------------------------------+
| cond_instances                            |
| events_waits_summary_by_instance          |
| file_instances                            |
| file_summary_by_instance                  |
| mutex_instances                           |
| prepared_statements_instances             |
| rwlock_instances                          |
| socket_instances                          |
| socket_summary_by_instance                |
+-------------------------------------------+
 >* cond_instances 条件等待对象实例,记录了系统中使用的条件变量的对象，OBJECT_INSTANCE_BEGIN为对象的内存地址
 >* file_instances 记录了系统中打开了文件的对象，包括ibdata文件，redo文件，binlog文件，用户的表文件等，open_count显示当前文件打开的数目，如果重来没有打开过，不会出现在表中
 >* mutex_instances 互斥同步对象实例 表中记录了系统中使用互斥量对象的所有记录，其中name为：wait/synch/mutex/*。LOCKED_BY_THREAD_ID显示哪个线程正持有mutex，若没有线程持有，则为NULL。
 >* rwlock_instances 读写锁同步对象实例 表中记录了系统中使用读写锁对象的所有记录，其中name为 wait/synch/rwlock/*
    * RITE_LOCKED_BY_THREAD_ID为正在持有该对象的thread_id，若没有线程持有，则为NULL
    * READ_LOCKED_BY_COUNT为记录了同时有多少个读者持有读锁。（通过 events_waits_current 表可以知道，哪个线程在等待锁；通过rwlock_instances知道哪个线程持有锁。rwlock_instances的缺陷是，只能记录持有写锁的线程，对于读锁则无能为力）。
 >* socket_instances 活跃会话对象实例 表中记录了thread_id,socket_id,ip和port，其它表可以通过thread_id与socket_instance进行关联，获取IP-PORT信息，能够与应用对接起来
    * event_name主要包含3类：
    * wait/io/socket/sql/server_unix_socket，服务端unix监听socket
    * wait/io/socket/sql/server_tcpip_socket，服务端tcp监听socket
    * wait/io/socket/sql/client_connection，客户端socket
```
### wait表
```
mysql> show tables like '%wait%';
+-----------------------------------------------+
| Tables_in_performance_schema (%wait%)         |
+-----------------------------------------------+
| events_waits_current                          |
| events_waits_history                          |
| events_waits_history_long                     |
| events_waits_summary_by_account_by_event_name |
| events_waits_summary_by_host_by_event_name    |
| events_waits_summary_by_instance              |
| events_waits_summary_by_thread_by_event_name  |
| events_waits_summary_by_user_by_event_name    |
| events_waits_summary_global_by_event_name     |
| table_io_waits_summary_by_index_usage         |
| table_io_waits_summary_by_table               |
| table_lock_waits_summary_by_table             |
+-----------------------------------------------+
 >* events_waits_current：记录了当前线程等待的事件
 >* events_waits_history 记录了每个线程最近等待的10个事件
 >* events_waits_history_long 记录了最近所有线程产生的10000个事件
```
### stage表
```
mysql> show tables like '%stage%';
+------------------------------------------------+
| Tables_in_performance_schema (%stage%)         |
+------------------------------------------------+
| events_stages_current                          |
| events_stages_history                          |
| events_stages_history_long                     |
| events_stages_summary_by_account_by_event_name |
| events_stages_summary_by_host_by_event_name    |
| events_stages_summary_by_thread_by_event_name  |
| events_stages_summary_by_user_by_event_name    |
| events_stages_summary_global_by_event_name     |
+------------------------------------------------+
 >* events_stages_current：记录了当前线程所处的执行阶段
 >* events_stages_history：记录了当前线程所处的执行阶段10条历史记录
 >* events_stages_history_long：记录了当前线程所处的执行阶段10000条历史记录
```
### statement表
```
mysql> show tables like '%statements%';
+----------------------------------------------------+
| Tables_in_performance_schema (%statements%)        |
+----------------------------------------------------+
| events_statements_current                          |
| events_statements_history                          |
| events_statements_history_long                     |
| events_statements_summary_by_account_by_event_name |
| events_statements_summary_by_digest                |
| events_statements_summary_by_host_by_event_name    |
| events_statements_summary_by_program               |
| events_statements_summary_by_thread_by_event_name  |
| events_statements_summary_by_user_by_event_name    |
| events_statements_summary_global_by_event_name     |
| prepared_statements_instances                      |
+----------------------------------------------------+
 >* events_statements_current 通过 thread_id+event_id可以唯一确定一条记录。Statments表只记录最顶层的请求，SQL语句或是COMMAND，每条语句一行。event_name形式为statement/sql/*，或statement/com/*
 >* events_statements_history
 >* events_statements_history_long
```
### connection表
```
mysql> show tables like '%connection%';
+---------------------------------------------+
| Tables_in_performance_schema (%connection%) |
+---------------------------------------------+
| replication_connection_configuration        |
| replication_connection_status               |
+---------------------------------------------+

```
### Summary 表
```
Summary表聚集了各个维度的统计信息包括表维度，索引维度，会话维度，语句维度和锁维度的统计信息
```
### 其他相关表
```
performance_timers：系统支持的统计时间单位
threads：监视服务端的当前运行的线程
```

## events_statements_summary_by_digest表
**  关于SQL维度的统计信息主要集中在events_statements_summary_by_digest表中，通过将SQL语句抽象出digest，可以统计某类SQL语句在各个维度的统计信息 **
#### 查看哪个sql执行最多
```
SELECT
	SCHEMA_NAME,
	DIGEST_TEXT,
	COUNT_STAR,
	SUM_ROWS_SENT,
	SUM_ROWS_EXAMINED,
	FIRST_SEEN,
	LAST_SEEN
FROM
	events_statements_summary_by_digest
ORDER BY
	COUNT_STAR DESC
LIMIT 1
// 返回结果1
SCHEMA_NAME	DIGEST_TEXT	COUNT_STAR	SUM_ROWS_SENT	SUM_ROWS_EXAMINED	FIRST_SEEN	         LAST_SEEN
  null       null        168828813	638289567	    64878294184	        2018-08-28 13:41:22  2019-04-19 10:38:57
// 返回结果2
*************************** 1. row ***************************
      SCHEMA_NAME: employees
      DIGEST_TEXT: INSERT INTO `salaries` VALUES (...) /* , ... */
       COUNT_STAR: 228
    SUM_ROWS_SENT: 0
SUM_ROWS_EXAMINED: 0
       FIRST_SEEN: 2019-04-03 08:33:21
        LAST_SEEN: 2019-04-03 08:36:45
```
#### 查看平均响应时间最长的sql
```
SELECT
	SCHEMA_NAME,
	DIGEST_TEXT,
	COUNT_STAR,
	AVG_TIMER_WAIT,
	SUM_ROWS_SENT,
	SUM_ROWS_EXAMINED,
	FIRST_SEEN,
	LAST_SEEN
FROM
	events_statements_summary_by_digest
ORDER BY
	AVG_TIMER_WAIT DESC
LIMIT 1
// 结果
*************************** 1. row ***************************
      SCHEMA_NAME: employees
      DIGEST_TEXT: INSERT INTO `tchecksum` SELECT @? := `MD5` ( `CONCAT_WS` ( ? , @? , `emp_no` , `salary` , `from_date` , `to_date` ) ) FROM `salaries` ORDER BY `emp_no` , `from_date` , `to_date`
       COUNT_STAR: 1
   AVG_TIMER_WAIT: 8487387027000
    SUM_ROWS_SENT: 0
SUM_ROWS_EXAMINED: 5688094
       FIRST_SEEN: 2019-04-03 08:37:08
        LAST_SEEN: 2019-04-03 08:37:08
平均响应时间为 8487387027000皮秒
1000000000000皮秒=1秒，1皮秒 = 10的-12次方秒
```
#### 扫描行数最多的sql
```
mysql> select schema_name, digest_text, count_star, avg_timer_wait, sum_rows_examined,first_seen, last_seen from events_statements_summary_by_digest order by sum_rows_examined limit 1\G
*************************** 1. row ***************************
      schema_name: NULL
      digest_text: SELECT @@`version_comment` LIMIT ?
       count_star: 15
   avg_timer_wait: 538496000
sum_rows_examined: 0
       first_seen: 2019-04-02 12:17:39
        last_seen: 2019-04-19 01:47:34
```
#### 使用临时表最多的sql
```
mysql> select schema_name, digest_text, count_star, avg_timer_wait, first_seen, last_seen, sum_created_tmp_disk_tables, sum_created_tmp_tables from events_statements_summary_by_digest order by sum_created_tmp_disk_tables desc, sum_created_tmp_tables desc limit 1 \G
*************************** 1. row ***************************
                schema_name: information_schema
                digest_text: SELECT `performance_schema` . `events_waits_summary_global_by_event_name` . `EVENT_NAME` AS `events` , `performance_schema` . `events_waits_summary_global_by_event_name` . `COUNT_STAR` AS `total` , `performance_schema` . `events_waits_summary_global_by_event_name` . `SUM_TIMER_WAIT` AS `total_latency` , `performance_schema` . `events_waits_summary_global_by_event_name` . `AVG_TIMER_WAIT` AS `avg_latency` , `performance_schema` . `events_waits_summary_global_by_event_name` . `MAX_TIMER_WAIT` AS `max_latency` FROM `performance_schema` . `events_waits_summary_global_by_event_name` WHERE ( ( `performance_schema` . `events_waits_summary_global_by_event_name` . `EVENT_NAME` != ? ) AND ( `performance_schema` . `events_waits_summary_global_by_event_name` . `SUM_TIMER_WAIT` > ? ) ) ORDER BY `performance_schema` . `events_waits_summary_global_by_event_name` . `SUM_TIMER_WAIT` DESC
                 count_star: 1
             avg_timer_wait: 205123448000
                 first_seen: 2019-04-12 08:06:00
                  last_seen: 2019-04-12 08:06:00
sum_created_tmp_disk_tables: 17
     sum_created_tmp_tables: 230
1 row in set (0.00 sec)
```

#### 哪个sql返回的结果集最多
```
mysql> select schema_name, digest_text, count_star, avg_timer_wait, first_seen, last_seen, sum_rows_sent from events_statements_summary_by_digest order by sum_rows_sent desc limit 1 \G
*************************** 1. row ***************************
   schema_name: employees
   digest_text: SELECT * FROM `employees`
    count_star: 2
avg_timer_wait: 427906397000
    first_seen: 2019-04-08 11:06:00
     last_seen: 2019-04-08 11:20:13
 sum_rows_sent: 600048
1 row in set (0.00 sec)
```
#### 哪个sql排序最多
```
mysql> select schema_name, digest_text, count_star, avg_timer_wait, first_seen, last_seen, sum_sort_rows from events_statements_summary_by_digest order by sum_sort_rows desc limit 1 \G
*************************** 1. row ***************************
   schema_name: employees
   digest_text: INSERT INTO `tchecksum` SELECT @? := `MD5` ( `CONCAT_WS` ( ? , @? , `emp_no` , `salary` , `from_date` , `to_date` ) ) FROM `salaries` ORDER BY `emp_no` , `from_date` , `to_date`
    count_star: 1
avg_timer_wait: 8487387027000
    first_seen: 2019-04-03 08:37:08
     last_seen: 2019-04-03 08:37:08
 sum_sort_rows: 2844047
1 row in set (0.00 sec)
```

#### 以上参数，可以间接获得某些数据对比
 >* sum_rows_examined 逻辑IO
 >* sum_sort_rows CPU消耗
 >* sum_rows_sent 网络带宽
## table_io_waits_summary_by_index_usage表
**通过file_summary_by_instance表，可以获得系统运行到现在，哪个文件(表)物理IO最多，这可能意味着这个表经常需要访问磁盘IO**

#### 哪个表，文件逻辑IO最多（热数据）
```
mysql> SELECT FILE_NAME,EVENT_NAME,COUNT_READ,SUM_NUMBER_OF_BYTES_READ,COUNT_WRITE,SUM_NUMBER_OF_BYTES_WRITE FROM file_summary_by_instance ORDER BY SUM_NUMBER_OF_BYTES_READ+SUM_NUMBER_OF_BYTES_WRITE DESC LIMIT 2\G
*************************** 1. row ***************************
                FILE_NAME: /var/lib/mysql/ibdata1
               EVENT_NAME: wait/io/file/innodb/innodb_data_file
               COUNT_READ: 549
 SUM_NUMBER_OF_BYTES_READ: 11108352
              COUNT_WRITE: 7164
SUM_NUMBER_OF_BYTES_WRITE: 580288512
*************************** 2. row ***************************
                FILE_NAME: /var/lib/mysql/ib_logfile0
               EVENT_NAME: wait/io/file/innodb/innodb_log_file
               COUNT_READ: 7
 SUM_NUMBER_OF_BYTES_READ: 70144
              COUNT_WRITE: 912
SUM_NUMBER_OF_BYTES_WRITE: 301149184
```

#### 哪个索引使用最多
```
mysql> SELECT OBJECT_NAME, INDEX_NAME, COUNT_FETCH, COUNT_INSERT, COUNT_UPDATE, COUNT_DELETE FROM table_io_waits_summary_by_index_usage ORDER BY SUM_TIMER_WAIT DESC limit 1;
+-------------+------------+-------------+--------------+--------------+--------------+
| OBJECT_NAME | INDEX_NAME | COUNT_FETCH | COUNT_INSERT | COUNT_UPDATE | COUNT_DELETE |
+-------------+------------+-------------+--------------+--------------+--------------+
| salaries    | NULL       |     2844047 |      2844047 |            0 |            0 |
+-------------+------------+-------------+--------------+--------------+--------------+
```
#### 哪个索引没使用过
```
mysql> SELECT OBJECT_SCHEMA, OBJECT_NAME, INDEX_NAME FROM table_io_waits_summary_by_index_usage WHERE INDEX_NAME IS NOT NULL AND COUNT_STAR = 0 AND OBJECT_SCHEMA <> 'mysql' ORDER BY OBJECT_SCHEMA,OBJECT_NAME;
+---------------+--------------+----------------+
| OBJECT_SCHEMA | OBJECT_NAME  | INDEX_NAME     |
+---------------+--------------+----------------+
| employees     | departments  | dept_name      |
| employees     | dept_emp     | dept_no        |
| employees     | dept_emp     | PRIMARY        |
| employees     | dept_manager | dept_no        |
| employees     | dept_manager | PRIMARY        |
| employees     | employees    | PRIMARY        |
| employees     | salaries     | PRIMARY        |
| sys           | sys_config   | PRIMARY        |
+---------------+--------------+----------------+
```

#### 哪个等待事件消耗的时间最多
```
mysql> SELECT EVENT_NAME, COUNT_STAR, SUM_TIMER_WAIT, AVG_TIMER_WAIT FROM events_waits_summary_global_by_event_name WHERE event_name != 'idle' ORDER BY SUM_TIMER_WAIT DESC LIMIT 1;
+---------------------------+------------+----------------+----------------+
| EVENT_NAME                | COUNT_STAR | SUM_TIMER_WAIT | AVG_TIMER_WAIT |
+---------------------------+------------+----------------+----------------+
| wait/io/table/sql/handler |    7237986 | 30361490653890 |        4194630 |
+---------------------------+------------+----------------+----------------+

```
###


## 补充
#### 查看session占用内存
```
SELECT
	SUBSTRING_INDEX(SUBSTRING_INDEX(event_name, '/', 2), '/' ,- 1 ) AS event_type,
	ROUND(SUM( current_number_of_bytes_used ) / 1024 / 1024, 2 ) AS MB_currently_used
FROM
	PERFORMANCE_SCHEMA .memory_summary_global_by_event_name
GROUP BY
	event_type
HAVING
	MB_currently_used > 0
```
## 总结
```
Performance Schema数据库，主要用于收集数据库服务器性能参数：
①提供进程等待的详细信息，包括锁、互斥变量、文件信息；
②保存历史的事件汇总信息，为提供MySQL服务器性能做出详细的判断；
③对于新增和删除监控事件点都非常容易，并可以改变mysql服务器的监控周期，例如（CYCLE、MICROSECOND）
```
类似的监控
```
打开标准的innodb监控：
CREATE TABLE innodb_monitor (a INT) ENGINE=INNODB;
打开innodb的锁监控：
CREATE TABLE innodb_lock_monitor (a INT) ENGINE=INNODB;
打开innodb表空间监控：
CREATE TABLE innodb_tablespace_monitor (a INT) ENGINE=INNODB;
打开innodb表监控：
CREATE TABLE innodb_table_monitor (a INT) ENGINE=INNODB;
```

# 太长 消化不了
```
类似profiling功能：

分析具体某条SQL，该SQL在执行各个阶段的时间消耗，通过events_statements_xxx表和events_stages_xxx表，就可以达到目的。两个表通过event_id与nesting_event_id关联，stages表的nesting_event_id为对应statements表的event_id；针对每个stage可能出现的锁等待，一个stage会对应一个或多个wait，通过stage_xxx表的event_id字段与waits_xxx表的nesting_event_id进行关联。如：


复制代码
比如分析包含count(*)的某条SQL语句，具体如下：

SELECT
EVENT_ID,
sql_text
FROM events_statements_history
WHERE sql_text LIKE '%count(*)%';
+----------+--------------------------------------+
| EVENT_ID | sql_text |
+----------+--------------------------------------+
| 1690 | select count(*) from chuck.test_slow |
+----------+--------------------------------------+
首先得到了语句的event_id为1690，通过查找events_stages_xxx中nesting_event_id为1690的记录，可以达到目的。

a.查看每个阶段的时间消耗：
SELECT
event_id,
EVENT_NAME,
SOURCE,
TIMER_END - TIMER_START
FROM events_stages_history_long
WHERE NESTING_EVENT_ID = 1690;
+----------+--------------------------------+----------------------+-----------------------+
| event_id | EVENT_NAME | SOURCE | TIMER_END-TIMER_START |
+----------+--------------------------------+----------------------+-----------------------+
| 1691 | stage/sql/init | mysqld.cc:990 | 316945000 |
| 1693 | stage/sql/checking permissions | sql_parse.cc:5776 | 26774000 |
| 1695 | stage/sql/Opening tables | sql_base.cc:4970 | 41436934000 |
| 2638 | stage/sql/init | sql_select.cc:1050 | 85757000 |
| 2639 | stage/sql/System lock | lock.cc:303 | 40017000 |
| 2643 | stage/sql/optimizing | sql_optimizer.cc:138 | 38562000 |
| 2644 | stage/sql/statistics | sql_optimizer.cc:362 | 52845000 |
| 2645 | stage/sql/preparing | sql_optimizer.cc:485 | 53196000 |
| 2646 | stage/sql/executing | sql_executor.cc:112 | 3153000 |
| 2647 | stage/sql/Sending data | sql_executor.cc:192 | 7369072089000 |
| 4304138 | stage/sql/end | sql_select.cc:1105 | 19920000 |
| 4304139 | stage/sql/query end | sql_parse.cc:5463 | 44721000 |
| 4304145 | stage/sql/closing tables | sql_parse.cc:5524 | 61723000 |
| 4304152 | stage/sql/freeing items | sql_parse.cc:6838 | 455678000 |
| 4304155 | stage/sql/logging slow query | sql_parse.cc:2258 | 83348000 |
| 4304159 | stage/sql/cleaning up | sql_parse.cc:2163 | 4433000 |
+----------+--------------------------------+----------------------+-----------------------+
通过间接关联，我们能分析得到SQL语句在每个阶段的时间消耗，时间单位以皮秒表示。这里展示的结果很类似profiling功能，有了performance schema，就不再需要profiling这个功能了。另外需要注意的是，由于默认情况下events_stages_history表中只为每个连接记录了最近10条记录，为了确保获取所有记录，需要访问events_stages_history_long表

b.查看某个阶段的锁等待情况
针对每个stage可能出现的锁等待，一个stage会对应一个或多个wait，events_waits_history_long这个表容易爆满[默认阀值10000]。由于select count(*)需要IO(逻辑IO或者物理IO)，所以在stage/sql/Sending data阶段会有io等待的统计。通过stage_xxx表的event_id字段与waits_xxx表的nesting_event_id进行关联。
SELECT
event_id,
event_name,
source,
timer_wait,
object_name,
index_name,
operation,
nesting_event_id
FROM events_waits_history_long
WHERE nesting_event_id = 2647;
+----------+---------------------------+-----------------+------------+-------------+------------+-----------+------------------+
| event_id | event_name | source | timer_wait | object_name | index_name | operation | nesting_event_id |
+----------+---------------------------+-----------------+------------+-------------+------------+-----------+------------------+
| 190607 | wait/io/table/sql/handler | handler.cc:2842 | 1845888 | test_slow | idx_c1 | fetch | 2647 |
| 190608 | wait/io/table/sql/handler | handler.cc:2842 | 1955328 | test_slow | idx_c1 | fetch | 2647 |
| 190609 | wait/io/table/sql/handler | handler.cc:2842 | 1929792 | test_slow | idx_c1 | fetch | 2647 |
| 190610 | wait/io/table/sql/handler | handler.cc:2842 | 1869600 | test_slow | idx_c1 | fetch | 2647 |
| 190611 | wait/io/table/sql/handler | handler.cc:2842 | 1922496 | test_slow | idx_c1 | fetch | 2647 |
+----------+---------------------------+-----------------+------------+-------------+------------+-----------+------------------+
通过上面的实验，我们知道了statement,stage,wait的三级结构，通过nesting_event_id进行关联，它表示某个事件的父event_id。

(2).模拟innodb行锁等待的例子
会话A执行语句update test_icp set y=y+1 where x=1(x为primary key)，不commit；会话B执行同样的语句update test_icp set y=y+1 where x=1，会话B堵塞，并最终报错。通过连接连接查询events_statements_history_long和events_stages_history_long，可以看到在updating阶段花了大约60s的时间。这主要因为实例上的innodb_lock_wait_timeout设置为60，等待60s后超时报错了。

SELECT
statement.EVENT_ID,
stages.event_id,
statement.sql_text,
stages.event_name,
stages.timer_wait
FROM events_statements_history_long statement
join events_stages_history_long stages
on statement.event_id=stages.nesting_event_id
WHERE statement.sql_text = 'update test_icp set y=y+1 where x=1';
+----------+----------+-------------------------------------+--------------------------------+----------------+
| EVENT_ID | event_id | sql_text | event_name | timer_wait |
+----------+----------+-------------------------------------+--------------------------------+----------------+
| 5816 | 5817 | update test_icp set y=y+1 where x=1 | stage/sql/init | 195543000 |
| 5816 | 5819 | update test_icp set y=y+1 where x=1 | stage/sql/checking permissions | 22730000 |
| 5816 | 5821 | update test_icp set y=y+1 where x=1 | stage/sql/Opening tables | 66079000 |
| 5816 | 5827 | update test_icp set y=y+1 where x=1 | stage/sql/init | 89116000 |
| 5816 | 5828 | update test_icp set y=y+1 where x=1 | stage/sql/System lock | 218744000 |
| 5816 | 5832 | update test_icp set y=y+1 where x=1 | stage/sql/updating | 6001362045000 |
| 5816 | 5968 | update test_icp set y=y+1 where x=1 | stage/sql/end | 10435000 |
| 5816 | 5969 | update test_icp set y=y+1 where x=1 | stage/sql/query end | 85979000 |
| 5816 | 5983 | update test_icp set y=y+1 where x=1 | stage/sql/closing tables | 56562000 |
| 5816 | 5990 | update test_icp set y=y+1 where x=1 | stage/sql/freeing items | 83563000 |
| 5816 | 5992 | update test_icp set y=y+1 where x=1 | stage/sql/cleaning up | 4589000 |
+----------+----------+-------------------------------------+--------------------------------+----------------+
查看wait事件：
SELECT
event_id,
event_name,
source,
timer_wait,
object_name,
index_name,
operation,
nesting_event_id
FROM events_waits_history_long
WHERE nesting_event_id = 5832;
*************************** 1. row ***************************
event_id: 5832
event_name: wait/io/table/sql/handler
source: handler.cc:2782
timer_wait: 6005946156624
object_name: test_icp
index_name: PRIMARY
operation: fetch
从结果来看，waits表中记录了一个fetch等待事件，但并没有更细的innodb行锁等待事件统计。

(3).模拟MDL锁等待的例子
会话A执行一个大查询select count(*) from test_slow，会话B执行表结构变更alter table test_slow modify c2 varchar(152);通过如下语句可以得到alter语句的执行过程，重点关注“stage/sql/Waiting for table metadata lock”阶段。

SELECT
statement.EVENT_ID,
stages.event_id,
statement.sql_text,
stages.event_name as stage_name,
stages.timer_wait as stage_time
FROM events_statements_history_long statement
left join events_stages_history_long stages
on statement.event_id=stages.nesting_event_id
WHERE statement.sql_text = 'alter table test_slow modify c2 varchar(152)';
+-----------+-----------+----------------------------------------------+----------------------------------------------------+---------------+
| EVENT_ID | event_id | sql_text | stage_name | stage_time |
+-----------+-----------+----------------------------------------------+----------------------------------------------------+---------------+
| 326526744 | 326526745 | alter table test_slow modify c2 varchar(152) | stage/sql/init | 216662000 |
| 326526744 | 326526747 | alter table test_slow modify c2 varchar(152) | stage/sql/checking permissions | 18183000 |
| 326526744 | 326526748 | alter table test_slow modify c2 varchar(152) | stage/sql/checking permissions | 10294000 |
| 326526744 | 326526750 | alter table test_slow modify c2 varchar(152) | stage/sql/init | 4783000 |
| 326526744 | 326526751 | alter table test_slow modify c2 varchar(152) | stage/sql/Opening tables | 140172000 |
| 326526744 | 326526760 | alter table test_slow modify c2 varchar(152) | stage/sql/setup | 157643000 |
| 326526744 | 326526769 | alter table test_slow modify c2 varchar(152) | stage/sql/creating table | 8723217000 |
| 326526744 | 326526803 | alter table test_slow modify c2 varchar(152) | stage/sql/After create | 257332000 |
| 326526744 | 326526832 | alter table test_slow modify c2 varchar(152) | stage/sql/Waiting for table metadata lock | 1000181831000 |
| 326526744 | 326526835 | alter table test_slow modify c2 varchar(152) | stage/sql/After create | 33483000 |
| 326526744 | 326526838 | alter table test_slow modify c2 varchar(152) | stage/sql/Waiting for table metadata lock | 1000091810000 |
| 326526744 | 326526841 | alter table test_slow modify c2 varchar(152) | stage/sql/After create | 17187000 |
| 326526744 | 326526844 | alter table test_slow modify c2 varchar(152) | stage/sql/Waiting for table metadata lock | 1000126464000 |
| 326526744 | 326526847 | alter table test_slow modify c2 varchar(152) | stage/sql/After create | 27472000 |
| 326526744 | 326526850 | alter table test_slow modify c2 varchar(152) | stage/sql/Waiting for table metadata lock | 561996133000 |
| 326526744 | 326526853 | alter table test_slow modify c2 varchar(152) | stage/sql/After create | 124876000 |
| 326526744 | 326526877 | alter table test_slow modify c2 varchar(152) | stage/sql/System lock | 30659000 |
| 326526744 | 326526881 | alter table test_slow modify c2 varchar(152) | stage/sql/preparing for alter table | 40246000 |
| 326526744 | 326526889 | alter table test_slow modify c2 varchar(152) | stage/sql/altering table | 36628000 |
| 326526744 | 326528280 | alter table test_slow modify c2 varchar(152) | stage/sql/end | 43824000 |
| 326526744 | 326528281 | alter table test_slow modify c2 varchar(152) | stage/sql/query end | 112557000 |
| 326526744 | 326528299 | alter table test_slow modify c2 varchar(152) | stage/sql/closing tables | 27707000 |
| 326526744 | 326528305 | alter table test_slow modify c2 varchar(152) | stage/sql/freeing items | 201614000 |
| 326526744 | 326528308 | alter table test_slow modify c2 varchar(152) | stage/sql/cleaning up | 3584000 |
+-----------+-----------+----------------------------------------------+----------------------------------------------------+---------------+
从结果可以看到，出现了多次stage/sql/Waiting for table metadata lock阶段，并且间隔1s，说明每隔1s钟会重试判断。找一个该阶段的event_id,通过nesting_event_id关联，确定到底在等待哪个wait事件。
SELECT
event_id,
event_name,
source,
timer_wait,
object_name,
index_name,
operation,
nesting_event_id
FROM events_waits_history_long
WHERE nesting_event_id = 326526850;
+-----------+---------------------------------------------------+------------------+--------------+-------------+------------+------------+------------------+
| event_id | event_name | source | timer_wait | object_name | index_name | operation | nesting_event_id |
+-----------+---------------------------------------------------+------------------+--------------+-------------+------------+------------+------------------+
| 326526851 | wait/synch/cond/sql/MDL_context::COND_wait_status | mdl.cc:1327 | 562417991328 | NULL | NULL | timed_wait | 326526850 |
| 326526852 | wait/synch/mutex/mysys/my_thread_var::mutex | sql_class.h:3481 | 733248 | NULL | NULL | lock | 326526850 |
+-----------+---------------------------------------------------+------------------+--------------+-------------+------------+------------+------------------+
通过结果可以知道，产生阻塞的是条件变量MDL_context::COND_wait_status，并且显示了代码的位置。
```