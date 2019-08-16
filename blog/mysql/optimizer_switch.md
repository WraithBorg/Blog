## MYSQL 优化器功能开关
```
https://dev.mysql.com/doc/refman/5.7/en/switchable-optimizations.html

SELECT @@optimizer_switch;

index_merge=on,index_merge_union=on,
index_merge_sort_union=on,index_merge_intersection=on,
engine_condition_pushdown=on,
index_condition_pushdown=on,mrr=on,mrr_cost_based=on,
block_nested_loop=on,
batched_key_access=off,materialization=on,semijoin=on,
loosescan=on,firstmatch=on,
subquery_materialization_cost_based=on,
use_index_extensions=on

```