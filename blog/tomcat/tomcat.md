#### tips
修改Tomcat命令窗口名字，tomcat_home\bin\catalina.bat  ———> if "%TITLE%" == "" set TITLE=极速订
查看jdk版本: tomcat/bin 目录 Ctrl + 右键进入命令行，输入version
同一个浏览器访问同一个服务器下的两个tomcat服务，会产生session覆盖的问题。

#### 启动关闭tomcat
启动
cd /usr/tomcat/tomcat8/bin && sh startup.sh

停用
cd /usr/tomcat/tomcat8/bin && sh shutdown.sh


#### 查看tomcat运行状态
[root@test webapps]# ps -ef | grep tomcat

#### 查看tomcat实时日志
[root@test /]# cd /usr/local/soft/tomcat85/logs
[root@test logs]# tail -f catalina.out
[root@test logs]# tail /usr/local/soft/tomcat85/logs/catalina.out


export CATALINA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=192.168.13.147"

sh catalina.sh run &

## 修改tomcat进程名字

### window平台：
打开tomcat_home\bin\setclasspath.bat文件，找到set _RUNJAVA=”%JRE_HOME%\bin\java”这一行。
将该行注释掉 ,然后在该行下面添加如下两行并进行保存：
```
copy "%JAVA_HOME%\bin\java.exe" "%JAVA_HOME%\bin\my_java.exe"
set _RUNJAVA="%JAVA_HOME%\bin\my_java"
```
注意：my_java为你所想看到的进程名字（即在任务管理器中的映像名称),其实就是拷贝一份java.exe文件,并调用拷贝的java.exe
重启tomcat(用startup.bat启动)后在在任务管理器中看见的进程名就不再是java.exe,而是my_java.exe了。

### linux/unix平台：
打开tomcat_home\bin\setclasspath.sh文件,找到
```
_RUNJAVA="$JRE_HOME"/bin/java
```
这一行,
并注释掉,然后在该行下面添加如两行并进行保存：
```
cp "$JAVA_HOME/bin/java" "$JAVA_HOME/bin/saleEdi_java"
_RUNJAVA="$JRE_HOME/bin/saleEdi_java"
```
重启tomcat,在终端输入ps -ef|grep my_java 进行查看
 
