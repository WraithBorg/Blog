## 数据库备份
1. 使用 MySQLdump命令备份
2. 直接复制整个数据库目录
3. 使用MySQLhotcopy工具快速备份目录

### 数据库迁移
```
1. MySQL 本机复制备份数据库
mysqldump zzc -uroot -padmin --add-drop-table | mysql zzz -u root -padmin
2. MySQL 数据库迁移
mysqldump zzz -uroot -padmin --databases | mysql xxx -u root -padmin
mysqldump zzz -uroot -padmin --add-drop-table | mysql xxx -u root -padmin
mysqldump zzz -uroot -padmin --databases --add-drop-table | mysql xxx -u root -padmin
mysqldump zzc -uroot -padmin --add-drop-table | mysql -h 192.168.1.100 xxx -u root -pass
mysqldump --default-character-set=GBK zzc -uroot -padmin --opt --triggers | mysql zzz -u root -padmin
mysqldump zzz -uroot -padmin --master-data --single-transaction --databases | mysql xxx -u root -padmin
3. 生成数据库备份文件
mysqldump -u root -p zzz > d:/zzz.sql
```
```
仅复制数据结构
mysqldump zzz -uroot -padmin --opt -d --triggers | mysql xxx -u root -padmin
仅复制数据
mysqldump zzz -uroot -padmin -t | mysql xxx -u root -padmin
复制数据和结构 SQL 332 MB SQL  命令行导入01:07:49  sql文件导入 01:42:40
mysqldump --default-character-set=GBK zzc -uroot -padmin --opt --triggers | mysql xxx -u root -padmin
命令行导入01:07:49
sql文件导入 01:42:40
```
#### java 调用cmd 执行 mysqldump 迁移数据库
```
public class Test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("cmd.exe /c mysqldump z0211 -uroot -padmin --opt --triggers | mysql z0211test -uroot -padmin");
        try {
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                System.out.println("数据库备份成功");
            }
            InputStreamReader ir = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
            input.close();
            System.out.println("返回信息结束");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库备份异常");
        }
        System.out.println("执行完成");
    }
}
```
### 错误信息
MySql 数据库导入"Unknown command '\n'." -- 编码不对   Mysql -u root -p --default-character-set=utf8 database <backpath