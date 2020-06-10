# 远程部署Tomcat
### 相关端口
除了8080以外，还有jdwp端口，rmi等你需要的端口都要打开，
### Tomcat远程部署
+ centos服务器开启vsftpd
+ centos服务器关闭防火墙
+ centos服务器开放端口

+ web程序修改pom文件,改变war包名字
```
<build>
		<finalName>cldpoint</finalName>
</build>
```

+ 被远程Tomcat修改catalina.sh
```
export CATALINA_OPTS="-Dcom.sun.management.jmxremote  -Dcom.sun.management.jmxremote.port=1095 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false  -Djava.rmi.server.hostname=192.168.13.147 -agentlib:jdwp=transport=dt_socket,address=8085,suspend=n,server=y"
export JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote= -Dcom.sun.management.jmxremote.port=1095 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
```

+ 新建自定义脚本zx.sh方便部署
```
tomcatPath="/usr/local/soft/tomcat85"
sh $tomcatPath/bin/shutdown.sh
rm -rf $tomcatPath/webapps/*
rm -rf $tomcatPath/logs/*
pkill java
sh $tomcatPath/bin/startup.sh
tail -f $tomcatPath/logs/catalina.out                                    
```
