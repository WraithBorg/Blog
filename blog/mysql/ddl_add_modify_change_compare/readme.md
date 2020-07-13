# 为什么ALTER TABLE 的时候 执行 add column速度比modify column和 change column慢很多
# 数据文件定义
db.opt  存储当前数据库的默认字符集和字符校验规则
*.frm文件 存储表结构相关的信息
*.ibd 存储表数据等信息

# 结论
modify column和 change column不修改ibd文件，
只有add column会修改ibd文件，
而且 frm文件比较小，idb文件随着数据的增加而变大

> 不过仅限于mysql5.6/mysql5.7 的InnoDB引擎的数据库，不适用于mysql8
