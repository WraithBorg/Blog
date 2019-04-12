# FRP 内网穿透
##### 背景描述
frp是一个可用于内网穿透的高性能的反向代理应用，支持tcp,udp,http,https协议

[TOC]
 
##### 需要准备
公网域名，公网服务器  此处演示用Centos7 64位系统
## 服务端 Centos7.0 64
##### 安装 frps
```
[root@vultr ~]# wget --no-check-certificate https://raw.githubusercontent.com/clangcn/onekey-install-shell/master/frps/install-frps.sh -O ./install-frps.sh
```
##### 获取文件修改权限？我不懂linux 这些命令都是超的
```
[root@vultr ~]# chmod 700 ./install-frps.sh
```
##### 安装frp
```
[root@vultr ~]# ./install-frps.sh install
```
##### 启动frp服务
```
[root@vultr ~]# frps start
```
##### 开放相应端口号
```
[root@vultr ~]# systemctl status firewalld.service
[root@vultr ~]# sudo firewall-cmd --list-all
[root@vultr ~]# sudo firewall-cmd --add-service=http --permanent
[root@vultr ~]# sudo firewall-cmd --add-port=80/tcp --permanent
[root@vultr ~]# sudo firewall-cmd --add-port=5443/tcp --permanent
[root@vultr ~]# sudo firewall-cmd --add-port=6443/tcp --permanent
[root@vultr ~]# sudo firewall-cmd --reload
```
##### 查询frp版本 并下载对应windows客户端
```
[root@vultr ~]# frps --version
```
此处演示用 v0.20.0版本
https://github.com/fatedier/frp/releases
##客户端 Windows
##### windows客户端配置文件 frpc.ini
```
[common]
server_addr = 公网ip
server_port = frp服务器端安装时设置的bind_port,默认5443
token = frp服务器端安装时设置的token
[ssh]
type = tcp
local_ip =  127.0.0.1
local_port = 22
remote_port = 6000
[web01]
type = http
local_ip = 需要穿透内网的设备IP，比如192.168.1.6 或 127.0.0.1
local_port = 80
use_encryption = false
use_compression = true
custom_domains = 替换为你拥有的一个二级域名，比如a.frp.com，将frp.com指向你的服务器IP，域名中的a可以随意替换
locations = /,/pic
host_header_rewrite = example.com
header_X-From-Where = frp
```
##### Windows 客户端启动frp
```
cmd命令行 运行
d:\frp\frpc.exe -c d:\frp\frpc.ini
浏览器访问
http://a.thelonegunmen.club
```
#####  待办工作 时间有限先不弄了
1.设置开机启动和后台运行
2.设置远程桌面