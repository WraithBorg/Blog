# 哀木涕
## 数据库备份
1. 使用 MySQLdump命令备份
2. 直接复制整个数据库目录
3. 使用MySQLhotcopy工具快速备份目录

### 数据库迁移
```
1. MySQL 本机复制备份数据库
mysqldump zzc -uroot -padmin --add-drop-table | mysql zzz -u root -padmin
2. MySQL 数据库迁移
mysqldump zzz -uroot -padmin --databases | mysql xxx -u root -padmin
mysqldump zzz -uroot -padmin --add-drop-table | mysql xxx -u root -padmin
mysqldump zzz -uroot -padmin --databases --add-drop-table | mysql xxx -u root -padmin
mysqldump zzc -uroot -padmin --add-drop-table | mysql -h 192.168.1.100 xxx -u root -pass
mysqldump --default-character-set=GBK zzc -uroot -padmin --opt --triggers | mysql zzz -u root -padmin
mysqldump zzz -uroot -padmin --master-data --single-transaction --databases | mysql xxx -u root -padmin
3. 生成数据库备份文件
mysqldump -u root -p zzz > d:/zzz.sql
mysqldump -hlocalhost -uroot -padmin jisuding >"c:/jisuding.sql"
```
```
仅复制数据结构
mysqldump zzz -uroot -padmin --opt -d --triggers | mysql xxx -u root -padmin
仅复制数据
mysqldump zzz -uroot -padmin -t | mysql xxx -u root -padmin
复制数据和结构 SQL 332 MB SQL  命令行导入01:07:49  sql文件导入 01:42:40
mysqldump --default-character-set=GBK zzc -uroot -padmin --opt --triggers | mysql xxx -u root -padmin
命令行导入01:07:49
sql文件导入 01:42:40
```
#### java 调用cmd 执行 mysqldump 迁移数据库
```
public class Test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("cmd.exe /c mysqldump z0211 -uroot -padmin --opt --triggers | mysql z0211test -uroot -padmin");
        try {
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                System.out.println("数据库备份成功");
            }
            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
            input.close();
            System.out.println("返回信息结束");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库备份异常");
        }
        System.out.println("执行完成");
    }
}
```
### 错误信息
MySql 数据库导入"Unknown command '\n'." -- 编码不对   Mysql -u root -p --default-character-set=utf8 database <backpath

### 大数据转储
两种方案：
```
    1. 代码实时转，select + insert + delete，一条一条的数据转过去，这边再删，存在两个问题：
        1. 开发得额外开发转储的代码
        2. 开发不好控制事务大小，事务太大容易导致生产主从延迟和性能的波动    事务太小，容易增加生产高事务的峰值
    2. 分区表定时转储方式，使用分区表置换的方式，无限类似于直接copy底层的ibd文件的方式，
        需要前期设计好分区表，用是range time的方式，每个月的数据一个分区，一个月转一次
        其他的方式，都可以不考虑
        现在生产环境，一年至少转50T数据，都是用第二种方案，非常6
        至于冷热数据怎么划分，数据的生命周期，需要在项目设计前期就考虑到，一条数据怎么多久才算业务完结状态，这个是业务需要考虑到的
        如果真的存在那种业务状态未完成但又一起转储到历史库去了的数据
        就需要开发自己捞回 但是几亿行说不定都没有一行，概率非常小
        dump的方式，太落后也太慢了，而且很容易造成大事务

        分区表exchange的方式转成单表后，就可以找个时间做表空间传输
        原库上在低峰期做drop table即可
        如果你们有自动化的话，可以将这种任务设置为上面你说的按钮的方式，想什么时候搞就什么时候搞，
        降低误操作的几率，也降低这种任务带来的对性能的冲击性，，，
```

#### 表空间传输例子 ,bd 文件恢复
----例：需要恢复的表名为xxx,在oms库下，从备份文件中物理拷贝过来 xxx.ibd文件
```
mysql> create table xxx;
mysql> alter table xxx discard tablespace;
shell> cp /backup/xxx.ibd /opt/mysql3306/data/oms/xxx.ibd
shell> chown mysql.mysql xxx.ibd&&chmod644 xxx.ibd
mysql> alter table xxx import tablespace;  ----时间长短受ibd文件大小限制
```
#### 表空间传输实例
```
-- 模拟数据
oldDB; -- 源库
employees --源表(mysql官方测试表 约30w条数据)

newDB; -- 目标库
目标库创建 相同结构的employees表

mysql> alter table employees discard tablespace; -- 删除目标库表空间
mysql> flush tables oldDB.employees for export;  -- 在/var/lib/mysql/oldDB/ 目录下生成cfg文件

将 .ibd 文件和 .cfg metadata 文件从oldDB拷贝到newDB上:
[root@vultr employees]# cp employees.{cfg,ibd} ../newDB/   （本机的话，另开窗口，防止丢失cfg文件）
远程主机为：scp employees.{cfg,ibd} 新主机地址:/var/lib/mysql/newDB

源库上，使用 UNLOCAL TABLES 释放 FLUSH TABLES ... FOR EXPORT 命令获取的锁
mysql> UNLOCK TABLES;

目标库上，导入从源库上复制过来的表空间：
mysql> alter table employees import tablespace;

报错
ERROR 1812 (HY000): Tablespace is missing for table `oldDB`.`employees`.
原因 文件权限
对应文件的owner和group为root，实际上应该均为mysql，修改之后，导入正常
查看文件权限
[root@vultr oldDB]# ll employees.*
-rw-r----- 1 root  root       670 Apr 24 06:14 employees.cfg
-rw-r----- 1 mysql mysql     8768 Apr 24 06:04 employees.frm
-rw-r----- 1 root  root  30408704 Apr 24 06:14 employees.ibd
解决 赋予mysql文件权限
[root@vultr oldDB]# chown mysql:mysql employees.*

再次执行 mysql> alter table employees import tablespace; 成功
```

#### tips
```
mysql ibd文件存储地址 C:\ProgramData\MySQL\MySQL Server 5.5\data
SHOW VARIABLES LIKE '%dir%'; -- 查看mysql路径

```

#### 依次备份表的形式备份数据库
```
create table newDB.user like oldDB.user;
insert into newDB.user select * from oldDB.user;
```

#### 官方文档
https://dev.mysql.com/doc/refman/5.7/en/backup-methods.html

#### mysql 全局变量
show global variables;


## Mysql备份策略：
+ **完整备份**： 就是指对某一个时间点上的所有数据或应用进行的一个完整拷贝，对数据量大的，备份时间较长，当然数据在恢复的时候快。
+ **增量备份**： 备份自上一次备份（包括完整备份，差异备份，增量备份）之后所有变化的数据进行备份。恢复的时候只需要一次完整的备份加上完整备份后的多个增量备份进行恢复即可。
+ **差异备份**： 备份自上一次完整备份之后所有变化的数据，恢复的时候仅需要最新一次完整备份加上差异备份即可。
		
## 备份方式：
+ 1、使用mysqldump进行逻辑备份
+ 2、使用LVM快照备份：
    快照备份就是把当时的场景保存为一个不变的状态，然后对这个不变的状态进行备份。但然，在规划mysql数据库时最好将数据和日志分开放到lvm分区中。使用LVM快照备份，需要将数据放在lvm分区。
+ 3、Xtrabackup备份：
     XtraBackup基于InnoDB的crash-recovery功能。它会复制innodb 的data file，由于不锁表，复制出来的数据是不一致的，
     在恢复的时候使用crash-recovery，使得数据恢复一致。InnoDB维护了一个redo log，又称为transaction log，事务日志，它包含了innodb数据的所有改动情况。
     
