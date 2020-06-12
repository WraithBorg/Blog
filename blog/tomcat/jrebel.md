# idea安装jrebel
`idea-file-setting-plugin-jrebel插件`

### 激活
`https://github.com/ilanyu/ReverseProxy/releases/tag/v1.4`
利用docker部署反向代理破解服务器
```
docker pull ilanyu/golang-reverseproxy
docker run -d -p 8008:8888 ilanyu/golang-reverseproxy
```
+ setting-jrebel-激活地址 填入 http://192.168.13.147:8008/36b058ba-c3dd-421a-bcfd-d9fdcd29fafb
+ 破解完成
