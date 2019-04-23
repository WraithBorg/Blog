# Rename database
## innodb更换数据库名字

#### 方式一
>* 适合小型数据库
```
create database new;
C:\Users\admin>mysqldump --default-character-set=GBK oldDB -uroot -padmin --opt --triggers | mysql newDB -uroot -padmin;
drop database old;
```

#### 方式二
>* 适合数据量较大的数据库
```
mysql> create database newDB;
mysql> rename table oldDB.user to newDB.user;
mysql> use newDB;
mysql> show tables;
+---------------+
| Tables_in_new |
+---------------+
| user          |
+---------------+
// 使用脚本 批量重命名表
[root@vultr ~]# mysql -uroot -pThanos oldDB -sNe 'show tables' | while read table;do mysql -uroot -pThanos -sNe "RENAME TABLE oldDB.$table TO newDB.$table";done
// 说明
>* -s是标志，silent mode因此shell的输出较少。
>* -N 阻止从结果中输出列名。
>* -e表示-e应该执行该标志后面的语句然后退出shell。这意味着语句'show tables'并"RENAME TABLE catalog.$table TO library.$table"根据需要作为普通的SQL语句执行
```