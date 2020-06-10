###  !$ 代表命令最后一个参数
```
[root@test download]# mv b/A.txt a/aa/aaa
mv: 无法获取"b/A.txt" 的文件状态(stat): 没有那个文件或目录
[root@test download]# mv b/B.txt !$
mv b/B.txt a/aa/aaa
```

###  如果上一条命令执行顺序有问题，则通过”!:n“修复
```
[root@test download]# tar -cvf ggiitt git-2.26.2.tar.gz 
git-2.26.2.tar.gz
[root@test download]# !:0 !:1 !:3 !:2
tar -cvf git-2.26.2.tar.gz ggiitt

```
### 获取上条命令的参数并重新执行，亦可用!:1-2或者 !:3-9 来获取参数子集
```
[root@test download]# zip -cvf gg.tar ggiitt
	zip warning: gg.tar not found or empty
[root@test download]# tar !:1-$
tar -cvf gg.tar ggiitt
```

#### 获取第N条命令的第N个参数
```
[root@test download]# echo !278
echo mv b/A.txt a/aa/aaa
mv b/A.txt a/aa/aaa

[root@test download]# echo !278:$
echo a/aa/aaa
a/aa/aaa

[root@test download]# echo !278:0
echo mv
mv

[root@test download]# echo !278:1
echo b/A.txt
b/A.txt

[root@test download]# echo !278:2
echo a/aa/aaa
a/aa/aaa

[root@test download]# echo !278:1-2
echo b/A.txt a/aa/aaa
b/A.txt a/aa/aaa

```
```
[root@test download]# echo !308:2
echo a/aa/aaa
a/aa/aaa
[root@test download]# mv gg.tar !308:2
mv gg.tar a/aa/aaa
[root@test download]# ll !308:2
```

#### 获取第N个命令的第N个参数（包含文件的路径）的路径
```
[root@test download]# echo !249
echo tail /usr/local/soft/tomcat85/conf/server.xml
tail /usr/local/soft/tomcat85/conf/server.xml

[root@test download]# echo !249$:h
echo /usr/local/soft/tomcat85/conf
/usr/local/soft/tomcat85/conf

[root@test download]# echo !249:1:h 第249条命令的第一个参数的路径
echo /usr/local/soft/tomcat85/conf


```

#### 获取当前行第一个元素 ‘!#:1’
```
[root@test download]# mv a/aa/aaa/gg.tar !#:1.bak
mv a/aa/aaa/gg.tar a/aa/aaa/gg.tar.bak
```


```
[root@test download]# echo !!
echo mv a/aa/aaa/gg.tar a/aa/aaa/gg.tar.bak

```
```
将362行命令的cat替换成cd
[root@test logs]# !362:gs/cat /cd /

```
