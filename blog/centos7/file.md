#  查看文件格式 cat -A 
```
[root@ bin]# cat -A catalina-jrebel.sh 
#!/bin/bash^M$
export REBEL_HOME="/usr/local/jrebel"^M$
export JAVA_OPTS="\"-agentpath:$REBEL_HOME/lib/libjrebel64.so\" -Drebel.remoting_plugin=true $JAVA_OPTS"^M$
`dirname $0`/startup.sh $@^M$

# 注
dos格式的文件行尾为^M$，unix格式的文件行尾为$。
```

### 查看文件格式 od
```
od -t x1 filename 如果看到输出内容中存在0d 0a的字符，那么文件是dos格式，如果只有0a，则是unix格式
```

### 查看文件格式 vi
```
vi filename打开文件，执行 : set ff，如果文件为dos格式在显示为fileformat=dos，如果是unxi则显示为fileformat=unix。
```

###  文件格式 dos格式 -> unix格式
```
  解决方法：
（1）使用linux命令dos2unix filename，直接把文件转换为unix格式
（2）使用sed命令sed -i "s/\r//" filename  或者 sed -i "s/^M//" filename直接替换结尾符为unix格式
（3）vi filename打开文件，执行 : set ff=unix 设置文件为unix，然后执行:wq，保存成unix格式。
``` 

### 文件格式
> .sh脚本在windows系统下编写，因此可能存在不可见的字符。从上面的错误提示来分析，原因应该是脚本文件是DOS格式的，即每一行的行尾都是以\r\n来标识，其ASCII码分别是0x0D，0x0A。

解决方案
要解决这个问题，其实方法也很简单，只需要把文件格式改为UNIX就行了。具体步骤如下：
+ 1.使用vim deploy.sh命令进入编辑模式。
+ 2.快捷键shift+:进入命令模式，然后使用命令set ff查看文件格式，如果输出fileformat=dos说明文件格式是DOS的。
+ 3.使用命令set ff=unix将文件格式由DOS改为UNIX，再次set ff，检查是否修改成功。
+ 4.再次执行.sh脚本，如果能执行成功，说明问题已经解决。

