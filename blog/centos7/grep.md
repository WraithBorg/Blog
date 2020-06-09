### 查找指定进程
[root@test conf]# ps -ef | grep tomcat
### 搜索文档内容
[root@test conf]# cat server.xml | grep 8080
[root@test conf]# cat server.xml | grep -n 8080  显示行号
[root@test conf]# grep -n '8080' server.xml
[root@test conf]# grep -n '8080' server.xml server.xml.bak
[root@test conf]# cat logging.properties | grep -n ^java 找出java开头的内容
[root@test conf]# cat logging.properties | grep -n ^[^java] 找出非java开头的内容
[root@test conf]# cat logging.properties | grep -n INFO$ 找出以INFO结尾的内容
[root@test conf]# cat logging.properties | grep -E "java|encoding"  找出包含java和encoding的内容
[root@test conf]# grep '[a-z]\{3\}' *.txt  找出当前目录下所有txt文件中至少包含3个连续小写字母的行
index.txt:aaaaa:
main.txt:aaa
main.txt:bbb
