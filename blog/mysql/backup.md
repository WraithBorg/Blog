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

### 转储
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








