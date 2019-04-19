### SQL
```
SHOW engines; -- 显示系统支持的存储引擎
SHOW table status FROM test_db WHERE name = 'test'  \G  -- 显示某表使用的存储引擎
SHOW CREATE TABLE test; -- 可显示表使用的存储引擎
ALTER table e engine=myisam; -- 修改表存储引擎
```