#### 测试环境
Mysql5.7 (只能是5.7版本)

#### 准备数据
执行`测试数据.sql`文件，会创建数据库`test_20200812`，表`a`，表`b`

#### 执行sql
```
SELECT
        `lh`.`serial`,
        `lh`.`update_time`,
        rd. NAME rd_name
FROM
        `a` `lh`
INNER JOIN `b` `rd` ON `rd`.`serial` = `lh`.`rd_serial`
WHERE
        `lh`.`status` = 1
ORDER BY
        `lh`.`update_time` DESC
LIMIT 0,
 20
```

#### 如果优化查询速度？
执行查询sql后，发现执行时间为9s左右  
希望优化后，查询时间控制在0.05s以内