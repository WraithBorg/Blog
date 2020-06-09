### 开放端口
```
//  关闭与开启防火墙
systemctl stop firewalld.service
systemctl start firewalld.service
//  查看防火墙是否开启的状态，以及开放端口的情况
systemctl status firewalld.service
sudo firewall-cmd --list-all
//  接下来通过以下命令开放http 80 端口：
sudo firewall-cmd --add-service=http --permanent
sudo firewall-cmd --add-port=80/tcp --permanent
//  命令末尾的--permanent表示用久有效，不加这句的话重启后刚才开放的端口就又失效了。
//  然后重启防火墙：
sudo firewall-cmd --reload
//  再次查看端口的开放情况：
sudo firewall-cmd --list-all
```
