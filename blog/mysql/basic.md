#### 数据库三大范式
```
第一范式 每一列都是不可分割的原子数据项
第二范式 实体属性完全依赖于关键字。即不能存在仅依赖关键字一部分的属性，如果存在就应该分理出一个实体
第三范式 任何非主属性不依赖于其他非主属性
```
#### SQL 分类
```
DDL：数据库定义
	关键词：SHOW CREATE ALTER DROP TRUNCATE,  USE database
DML：数据库操作
	关键词: INSERT UPDATE DELETE
DQL: 数据库查询
	关键词: SELECT * FROM {table} WHERE {条件} GROUP BY {分组字段} HAVING {条件} ORDER BY {排序字段}
	执行顺序：FROM > WHERE > GROUP BY > HAVING > ORDER BY > SELECT
DCL: 数据库控制
	create user, grant, revoke, show, drop, 用户, 权限, 事物
```