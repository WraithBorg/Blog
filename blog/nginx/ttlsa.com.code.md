# Nginx pdf code
https://www.cnblogs.com/root0/p/10873867.html


## 编译安装LuaJIT
```
wget  http://luajit.org/download/LuaJIT-2.0.4.tar.gz
tar xf LuaJIT-2.0.4.tar.gz
cd LuaJIT-2.0.4
make PREFIX=/usr/local/luajit
make install PREFIX=/usr/local/luajit
```
## 下载扩展模块
```
cd /usr/local/src/
wget https://github.com/simpl/ngx_devel_kit/archive/v0.3.0.tar.gz
tar -xf v0.3.0.tar.gz

wget https://github.com/openresty/lua-nginx-module/archive/v0.10.8.tar.gz
tar xf v0.10.8.tar.gz
```

## 编译安装nginx
```
yum groupinstall -y "Development Tools"
yum install -y libxml2-devel curl-devel pcre-devel openssl-devel siege traceroute vim openssl
cd /usr/local/src
wget http://nginx.org/download/nginx-1.10.3.tar.gz
export LUAJIT_LIB=/usr/local/luajit/lib
export LUAJIT_INC=/usr/local/luajit/include/luajit-2.0
tar xf nginx-1.10.3.tar.gz && cd nginx-1.10.3
```
```nginx
./configure \
--prefix=/usr/local/nginx-1.10.3 \
--with-http_ssl_module \
--with-http_stub_status_module \
--add-module=/usr/local/src/ngx_devel_kit-0.3.0 \
--add-module=/usr/local/src/lua-nginx-module-0.10.8 \
--add-module=/usr/local/mynginxlibrary/echo-nginx-module-0.61 \
```
```
make && make install
mkdir /usr/local/nginx-1.10.3/conf/vhost
ln -s /usr/local/nginx-1.10.3/sbin/nginx   /bin/nginx

```
### 若报错
```
nginx: error while loading shared libraries: libluajit-5.1.so.2: cannot open shared object file: No such file or directory
```
```
ln -s /usr/local/luajit/lib/libluajit-5.1.so.2 /lib64/
```

### 测试第一个lua脚本
1.server中添加
```
 location /lua {
        default_type 'text/html';
        content_by_lua_file conf/lua/test.lua; 　　 # 相对于nginx安装目录
  }
```
2.
```
cd /usr/local/nginx-1.10.3/conf
mkdir lua && cd lua 

vim test.lua
输入ngx.say("hello world");

nginx -t 
nginx

[root@localhost lua]# curl 'b.ttlsa.com/lua'
hello world
```

## 安装echo
```
wget https://github.com/openresty/echo-nginx-module/archive/v0.61.tar.gz
tar zxvf v0.61.tar.gz
```
然后添加add moudle 重新编译 make && make install
