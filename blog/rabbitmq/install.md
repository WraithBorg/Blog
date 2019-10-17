# 安装MQ
`https://blog.51cto.com/lookingdream/2110776`

#### Account
```
http://192.168.13.147:15672/#/
XRom XRom123
```

#### rabbitmq常用命令
```
add_user        <UserName> <Password>
delete_user    <UserName>
change_password <UserName> <NewPassword>
list_users
add_vhost    <VHostPath>
delete_vhost <VHostPath>
list_vhostsset_permissions  [-p <VHostPath>] <UserName> <Regexp> <Regexp> <Regexp>
clear_permissions [-p <VHostPath>] <UserName>
list_permissions  [-p <VHostPath>]
list_user_permissions <UserName>
list_queues    [-p <VHostPath>] [<QueueInfoItem> ...]
list_exchanges [-p <VHostPath>] [<ExchangeInfoItem> ...]
list_bindings  [-p <VHostPath>]
list_connections [<ConnectionInfoItem> ...]
```

```
4369 -- erlang发现口
5672 --client端通信口
15672 -- 管理界面ui端口
25672 -- server间内部通信口
```
　　　　