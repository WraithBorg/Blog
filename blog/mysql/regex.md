# MYSQL 正则提取数字/字符
```
use jisuding;
DELIMITER $$
 
DROP FUNCTION IF EXISTS `Num_char_extract`$$
 
CREATE FUNCTION `Num_char_extract`(Varstring VARCHAR(100)CHARSET utf8, flag INT) RETURNS VARCHAR(50) CHARSET utf8
BEGIN
	DECLARE len INT DEFAULT 0;
	DECLARE Tmp VARCHAR(100) DEFAULT '';
	SET len=CHAR_LENGTH(Varstring);
	IF flag = 0 
	THEN
		WHILE len > 0 DO
		IF MID(Varstring,len,1)REGEXP'[0-9]' THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
		END IF;
		SET len = len - 1;
		END WHILE;
	ELSEIF flag=1
	THEN
		WHILE len > 0 DO
		IF (MID(Varstring,len,1)REGEXP '[a-zA-Z]') 
		THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
		END IF;
		SET len = len - 1;
		END WHILE;
	ELSEIF flag=2
	THEN
		WHILE len > 0 DO
		IF ( (MID(Varstring,len,1)REGEXP'[0-9]')
		OR (MID(Varstring,len,1)REGEXP '[a-zA-Z]') ) 
		THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
		END IF;
		SET len = len - 1;
		END WHILE;
	ELSEIF flag=3
	THEN
		WHILE len > 0 DO
		IF NOT (MID(Varstring,len,1)REGEXP '^[u0391-uFFE5]')
		THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
		END IF;
		SET len = len - 1;
		END WHILE;
	ELSE 
		SET Tmp = 'Error: The second paramter should be in (0,1,2,3)';
		RETURN Tmp;
	END IF;
	RETURN REVERSE(Tmp);
    END$$
 
DELIMITER ;
select Num_char_extract('888二哥来个大保健',0);
```
```$xslt
mysql> select Num_char_extract('888二哥来个大保健',0);
+-----------------------------------------+
| Num_char_extract('888二哥来个大保健',0) |
+-----------------------------------------+
| 888                                     |
+-----------------------------------------+

第二个参数为0：代表提取数字
第二个参数为1：代表提取字母
第二个参数为2：代表提取数字+字母
第二个参数为3：代表提取汉字
第二个参数为其他数字：打印错误提示
```