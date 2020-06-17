# 远程debug remote debug 设置远程调试配置

## 方法一
#### windows系统：
```
在catalina.bat里：
SET CATALINA_OPTS=-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=#{调试端口}
```
#### Linux系统：
```
在catalina.sh里：
CATALINA_OPTS="-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=#{调试端口}"
```

## 方法二
#### windows系统：
```
在tomcat目录下的bin目录中新建debug.bat,编辑内容如下
set JPDA_ADDRESS=#{调试端口}
set JPAD_TRANSPORT=dt_socket
SET CATALINA_OPTS=-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=#{调试端口}
startup
双击debug.bat
```

#### Linux系统：
```
startup.sh 中的最后一行 exec "$PRGDIR"/"$EXEXUTABLE" start "$@"中的start修改成
jpda start ，默认的调试端口是8000 ，可以在catalina.sh 文件中设置JPDA_APPDESS=#{调试端口}
使用startup.sh 或者catalina.sh jpda start 启动tomcat
```
