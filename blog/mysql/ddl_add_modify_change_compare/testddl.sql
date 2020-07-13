DROP DATABASE IF EXISTS testddl;
CREATE DATABASE testddl;
USE testddl;
CREATE TABLE user(
                     id VARCHAR(32) NOT NULL ,
                     nickname VARCHAR(50) NOT NULL,
                     telephone VARCHAR(20) NOT NULL,
                     age tinyint(1) NOT NULL,
                     loginpwd VARCHAR(20) NOT NULL,
                     PRIMARY KEY (`id`) USING BTREE
) ENGINE INNODB;


INSERT INTO `testddl`.`user`(`id`, `nickname`, `telephone`, `age`, `loginpwd`) VALUES ('u02', '李四', '110', 1, '0000');
INSERT INTO `testddl`.`user`(`id`, `nickname`, `telephone`, `age`, `loginpwd`) VALUES ('u03', '王五', '110', 1, '0000');
INSERT INTO `testddl`.`user`(`id`, `nickname`, `telephone`, `age`, `loginpwd`) VALUES ('u04', '薛六', '110', 1, '0000');

SELECT @@datadir FROM dual; -- C:\ProgramData\MySQL\MySQL Server 8.0\Data\

-- ALTER TABLE testddl.user MODIFY COLUMN age TINYINT(2) NOT NULL COMMENT '年龄';
-- ALTER TABLE testddl.user CHANGE telephone phone VARCHAR(30) NOT NULL;
-- ALTER TABLE testddl.user ADD COLUMN headimg VARCHAR(50) NOT NULL DEFAULT '';


SHOW CREATE TABLE user;
