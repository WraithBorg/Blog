
#### 字段设计
```
1. 不为空的一律加 not null，varchar类型都不为空，详细见第五条
2. 标记字段能用tinyint就用tinyint 别用varchar
3. int（tinyint，smallint）型字段加 unsigned
4. 能用smallint 就别用 int，能用tinyint就别用smallint
5. 备注等字段虽然业务上可以为空，但也要设置为not null，并且加上 DEFAULT ''
6. varchar 长度够用就好别太大
7. COMMENT 必须添加，表的COMMENT也要加上
8. 秉着最少占用存储的原则
```

####    优化数据大小  Optimizing Data Size
* Table Columns
    尽可能使用最有效（最小）的数据类型
    Use the most efficient (smallest) data types possible. MySQL has many specialized types that save disk space and memory. For example, use the smaller integer types if possible to get smaller tables. MEDIUMINT is often a better choice than INT because a MEDIUMINT column uses 25% less space.
    尽可能声明not null, 它通过能够更好的使用索引并且减少测试是否为null的开销，除非必要，应该避免每列的值允许为空
    Declare columns to be NOT NULL if possible. It makes SQL operations faster, by enabling better use of indexes and eliminating overhead for testing whether each value is NULL. You also save some storage space, one bit per column. If you really need NULL values in your tables, use them. Just avoid the default setting that allows NULL values in every column.
Row Format
    一些字段可以使用紧凑格式
    InnoDB tables are created using the DYNAMIC row format by default

Indexes
    表的主索引应尽可能短 对于InnoDB表，主键列在每个辅助索引条目中都是重复的，因此如果您有许多辅助索引，则短主键可以节省大量空间。
    仅创建提高查询性能所需的索引。索引适用于检索，但会降低插入和更新操作的速度，
    较短的索引更快，不仅因为它们需要更少的磁盘空间，而且因为它们还会在索引缓存中为您提供更多的命中，从而减少磁盘搜索次数

Joins
    保持列名简单
Normalization
    尽量保持所有数据不冗余 根据数据库理论第三范式
    如果速度比磁盘空间更重要，并且保留多个数据副本的维护成本