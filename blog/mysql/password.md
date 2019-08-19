mysql root密码修改

找到mysql安装目录下的my.ini，找到里面的[mysqld] 然后在下面加上skip_grant_tables(启动MySQL服务的时候跳过权限表认证)
安装mysql的时候出现这个问题，在增加用户的时候

The MySQL server is running with the –skip-grant-tables option so it cannot execute this statement

flush privileges 一下
mysql> flush privileges;