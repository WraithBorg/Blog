## Tips
#### basic
```
退出编辑中的sql quit; 或者 'quit;
查看mysql 安装路径
select @@basedir as basePath from dual    
C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqldump.exe
TINYINT 8bit 无符号数最大值 2^8 - 1  ，有符号数最大值2^7 - 1;
含null的比较 用 <=> 不用 =
	SELECT null = 1; -- 返回null
	SELECT null <=> 1; -- 返回0
!= 和 <> 都不能比较null
	SELECT null != null; -- 返回null 
	SELECT null <> null; -- 返回null 
distinct 作用于所有列
```