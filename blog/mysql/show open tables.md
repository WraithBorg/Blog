# SHOW OPEN TABLES
列出TEMPORARY表缓存中当前打开的表
```
SHOW OPEN TABLES where In_use > 0;
Database	Table	In_use(锁请求的数量)	Name_locked(表名是否已锁定)
test	    syslog	1	                    0
```