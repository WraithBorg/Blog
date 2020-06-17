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
