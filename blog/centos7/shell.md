# shell

### export
设置或显示环境变量
export -p 列出环境变量
export -p | grep PATH

### bash
sh ,bash 和 ./ 可以执行shell脚本

### source
重新执行刚修改的文档，通常用 “.” 来代替
source filename 
. filename
source /etc/profile 使新设置的环境变量生效

###source filename 与 sh filename 及./filename执行脚本的区别
+ 当shell脚本具有可执行权限时，用sh filename与./filename执行脚本是没有区别得。./filename是因为当前目录没有在PATH中，所有”.”是用来表示当前目录的。
+  sh filename 重新建立一个子shell，在子shell中执行脚本里面的语句，该子shell继承父shell的环境变量，但子shell新建的、改变的变量不会被带回父shell。
+ source filename：这个命令其实只是简单地读取脚本里面的语句依次在当前shell里面执行，没有建立新的子shell。那么脚本里面所有新建、改变变量的语句都会保存在当前shell里面。

#### 标准输入输出
##### shell
```
0 表示标准输入
1 表示标准输出
2 表示标准错误输出
> 默认为标准输出重定向
2>&1 把标准错误输出 重定向到 标准输出
&>file 把标准输出和标准错误输出重定向到文件file中

```
#### linux 三种标准输入输出
```
STDIN 标准输入输出
STDOUT 标准输出
STDERR 标准错误
```

#### 创建脚本
```
[root@test games]# vim a.sh

[root@test games]# cat a.sh
#!/bin/bash
# author:zxu
demoFunc(){
 echo "this is test"
}
echo "start..."
demoFunc
echo "end..."

[root@test games]# sh a.sh
start...
this is test
end...
```

#### 创建带返回值的脚本
```
[root@test games]# vim a.sh

[root@test games]# cat a.sh
#!/bin/bash
# author:zxu
demoFunc(){
 echo "this is test"
}
funWithReturn(){
 echo "输入第一个数字"
 read aNum
 echo "输入第二个人数字"
 read bNum
 echo "两个数字分别为 $aNum 和 $bNum"
 return $(($aNum+$bNum))
}
echo "start..."
funWithReturn
echo "两个数字之和为 $? !"
echo "end..."

[root@test games]# sh a.sh
start...
输入第一个数字
3
输入第二个人数字
4
两个数字分别为 3 和 4
两个数字之和为 7 !
end...

可以通过$!获取上一条命令的返回结果
```
