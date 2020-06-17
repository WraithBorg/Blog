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
