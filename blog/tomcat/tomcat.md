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
