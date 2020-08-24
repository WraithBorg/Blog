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

## 常用技巧

#### 脚本失败立即退出
```
1:
vim test.sh
cat test.sh
    echo 'hello'
sh test.sh
// 输出hello
2:
vim test.sh
cat test.sh
    lp
    echo 'hello'
sh test.sh
// 输出 test.sh: line 1: lp: command not found
      //hello
// 即失败之后仍继续运行
3:
vim test.sh
cat test.sh
    set -e
    lp
    echo 'hello'
sh test.sh
//输出 testsh.sh: line 2: lp: command not found
//即失败之后便停止运行
4:
如果希望失败后仍要继续运行,可做如下改变
set -e
lp || true
echo 'hello'
5:
如果希望某些条件失败后继续执行，某些条件失败后停止执行，则如下
cat test.sh
    set +e
    lp
    set -e
    echo 'hello'
    llpp
    echo 'hello llpp'

```

#### 脚本调试
```
sh -x testsh.sh
    + set +e
    + lp
    testsh.sh: line 2: lp: command not found
    + set -e
    + echo hello
    hello
    + llpp
    testsh.sh: line 5: llpp: command not found
// 前面带+的内容就是命令实际执行的
```
#### 显示未定义变量
`脚本开头添加 set -u`
#### 管道命令一个失败时整个失败
`cat test.sh |grep if | cut -d ';' -f 2` 三条命令一行执行，如果第一条失败了，后续命令依旧执行
如果希望第一条命令失败了就停止执行，则在脚本中添加 set -o pipefail
#### 静态变量设置readonly
```
#!/bin/bash
readonly MY_PATH=/usr/bin
```
#### 设置变量默认值
```
[root@VM-0-16-centos games]# cat testsh.sh
#!/bin/bash
name=${1:-shouqian}
echo "$name"

[root@VM-0-16-centos games]# sh testsh.sh haha
haha
[root@VM-0-16-centos games]# sh testsh.sh
shouqian
```
#### 多条命令执行
假如cmd0失败,如果不希望后面的命令执行,则使用 cmd0 && cmd1 && cmd1
假如cmd0失败,如果希望后面的命令执行,则使用 cmd0;cmd1;cmd1
```
[root@VM-0-16-centos games]# echo '11';echo1 '22';echo '44';
11
-bash: echo1: command not found
44
```
```
[root@VM-0-16-centos games]# echo '11'&&echo1 '22'&&echo '44';
11
-bash: echo1: command not found

```
