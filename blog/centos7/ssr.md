### ShadowSocks
```
//1
yum -y install wget
//2
wget --no-check-certificate https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocksR.sh
//3
chmod +x shadowsocksR.sh
//4
./shadowsocksR.sh 2>&1 | tee shadowsocksR.log
```