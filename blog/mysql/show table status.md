# SHOW TABLE STATUS
> 查询表状态
[Reference Document](https://dev.mysql.com/doc/refman/5.7/en/show-table-status.html)
```
mysql> SHOW TABLE STATUS IN test WHERE NAME = 'syslog' \G
*************************** 1. row ***************************
           Name: syslog                 表名
         Engine: InnoDB                 表的存储引擎
        Version: 10                     .frm 文件版本号
     Row_format: Dynamic                该行的存储格式 （Fixed,Dynamic,Compressed,Redundant,Compact）
           Rows: 2297906                行数,MyISAM引擎存储确切的计数值，对于InnoDB引擎，该值是近似值，用SELECT COUNT(*)获得准确的计数
 Avg_row_length: 303                    平均行长度
    Data_length: 697286656              MyISAM引擎中指的是数据文件的长度，对于InnoD引擎，指的是聚簇索引分配的大致内存，bytes为单位
Max_data_length: 0                      Unused for InnoDB
   Index_length: 0
      Data_free: 3145728                已分配但未使用的字节数,数据碎片
 Auto_increment: NULL                   下一个AUTO_INCREMENT值。
    Create_time: 2019-08-19 14:48:02        
    Update_time: NULL                   上次更新数据文件时间，对于某些存储引擎，此值为NULL
     Check_time: NULL                   上次检查时间
      Collation: utf8_general_ci        表默认排序规则，排序规则名称以字符集名称开头
       Checksum: NULL                   Unused
 Create_options:    
        Comment: 系统日志
```