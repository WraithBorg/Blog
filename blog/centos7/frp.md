# FRP 内网穿透
##### 背景描述
frp是一个可用于内网穿透的高性能的反向代理应用，支持tcp,udp,http,https协议

[TOC]
 
##### 需要准备
公网域名，公网服务器  此处演示用Centos7 64位系统
## 服务端 Centos7.0 64
```
[安装 frps]# wget --no-check-certificate https://raw.githubusercontent.com/clangcn/onekey-install-shell/master/frps/install-frps.sh -O ./install-frps.sh
[获取文件修改权限]# chmod 700 ./install-frps.sh
[安装frp]# ./install-frps.sh install
[启动frp服务]# frps start

开放相应端口号
如果报错则 yum install firewalld 或者 systemctl start firewalld开启防火墙 或者切换root用户 sudo su
[root@vultr ~]# systemctl status firewalld.service
[root@vultr ~]# sudo firewall-cmd --list-all
[root@vultr ~]# sudo firewall-cmd --add-service=http --permanent
[root@vultr ~]# sudo firewall-cmd --add-port=80/tcp --permanent
[root@vultr ~]# sudo firewall-cmd --add-port=5443/tcp --permanent
[root@vultr ~]# sudo firewall-cmd --add-port=6443/tcp --permanent
[root@vultr ~]# sudo firewall-cmd --reload
[查询frp版本 方便下载对应windows客户端]# frps --version

其他命令
停止服务：frps stop       重启服务：frps restart      运行状态：frps status
参数：    frps config       版本：frps version
卸载：   ./install-frps.sh uninstall
更新：   ./install-frps.sh update
安装：   ./install-frps.sh install
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
[RDP]
type = tcp
local_ip = 127.0.0.1
local_port = 3388 本地端口
remote_port = 3389 远程访问用端口
```
##### Windows 客户端启动frp
```
cmd命令行 运行
c:\frp\frpc.exe -c c:\frp\frpc.ini
浏览器访问
http://a.thelonegunmen.club
```
#####  待办工作 时间有限先不弄了
1.设置开机启动和后台运行

#### 问题分析
1. 远程桌面连接显示您的凭据不工作
```
运行窗口中输入命令gpedit.msc
打开“本地组策略编辑器”窗口，在窗口左侧边栏依次点击“计算机配置/管理模板/系统”
在Windows10组件菜单下找到“凭据分配”菜单项，点击后在右侧窗口中找到“允许分配保存的凭据用于仅NTLM服务器身份验证”一项
右键点击上面的设置项，在弹出菜单中选择“编辑”菜单项。
这时会打开该项的编辑窗口，在窗口中选中“已启用”一项，点击选项窗口中的“显示”按钮，在弹出的显示内容窗口中输入termsrv/*，最后点击确定按钮。

如果上面的设置还没有解决问题的话，接下来继续下面的操作。同样打开本地组策略编辑器窗口，在窗口中依次点击“计算机配置/Windows设置/安全设置/安全选项”菜单项。
在右侧窗口中选中“网络访问：本地帐户的共享和安全模型”设置项。
右键点击上面的设置项，然后在弹出菜单中选择“属性”菜单项
在打开的编辑窗口中选择“经典：对本地用户进行身份验证，不改变其本来身份”菜单项，最后点击确定按钮。再次连接远程桌面，就不再弹出无法连接的提示了。
```

#### tips
```
如果启动失败，大都是端口占用
```