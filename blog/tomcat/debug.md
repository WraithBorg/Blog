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

### JAVA远程调试
```
JVM 激活远程调试的启动参数有 JPDA_OPTS, CATALINA_OPTS 和 JAVA_OPTS。
其中 JAVA_OPTS 是通常不建议使用的，
 因为基于 JAVA_OPTS 的参数设定会暴露给所有的 JVM 应用，
 而 CATALINA_OPTS 定义的设定值限制在Tomcat 内。
```

#### 远程调试 使用JPDA_OPTS
Linux setenv.sh
```
export JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"
```
Windows setenv.bat
```
set JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"
```

+ 指定运行的被调试应用和调试者之间的通信协议，(ie: transport=dt_socket)
+ 远程被调试应用开通的端口，(ie: address=1043)， 可定义其他端口，比如9999
+ server=y 表示这个 JVM 即将被调试
+ suspend=n 用来告知 JVM 立即执行，不要等待未来将要附着上/连上（attached）的调试者。如果设成 y, 则应用将暂停不运行，直到有调试者连接上
+  suspend=y的一个比较适用的场景是，当debug一个会阻止应用成功启动的问题时， 通过suspend=y可以确保调试者连上来之后再启动应用，否则应用已经启动报错了再调试也没意义了。*
+ 当然上面的设置也可以直接放到 catalina.sh （catalina.bat ）内，但是有个 setevn.* 额外的配置文件一直是最佳选择， tomcat会自动读取

#### 远程调试 使用 JAVA_OPTS / CATALINA_OPTS
 
`如果是在 Windows 系统把 Tomcat 作为系统服务来运行的，直接打开 Apache Tomcat 的属性对话框，在Java Tab也添加启动参数：`
```
-agentlib:jdwp=transport=dt_socket,
address=1043,server=y,suspend=n

请确保每一条配置都是新的行，参数选项之间没有空格
```
`但如果Tomcat没有作为 Windows 系统服务， 启用方法与前面类似，在 setenv.bat 文件中写入`
```
set CATALINA_OPTS="-agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"
```
`如果运行在Linux上， setenv.sh 中写入`
```
export CATALINA_OPTS="-agentlib:jdw`p=transport=dt_socket,address=1043,server=y,suspend=n"

按照普通的方式启动Tomcat即可；
 ./startup.sh 或者 ./catalina.sh start
```

#### 使用JPDA启动

最后一种启用远程调试的方式是用 JPDA 切换， 用如下的启动命令将使用默认值自动启用远程调试，
```
catalina jpda start
```
该命令默认使用的设置是 -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n































