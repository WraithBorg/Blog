#### file
ls -R 
ls -F
file {文件名}
cat -n {文件名}
cat -b {文件名}
head -n {行数} {文件名}
tail -n {行数} {文件名}
sort -n {文件名}

#### grep
grep -c {搜索内容} {文件名}
grep -n {搜索内容} {文件名}
grep -v {搜索内容} {文件名}

#### zip
gzip {文件名}
gunzip {文件名}
tar -cvf {文件名} {文件名} {文件名} {文件名}
tar -tf  {文件名}
tar -zxvf  {文件名}
file {文件名}
tar -xvf {文件名}

##### alias
alias -p
alias {别名}='{命令名}'

#### shell
-rw-r--r--. 1 root root     3 9月  11 10:39 a.sh
rwx三种权限范围
chmod -x {脚本名}
chmod +x {脚本名}
sh a.sh
./ a.sh 需要执行权限

#### yum
yum list installed
yum intall {软件包名}
yum update {软件包名}
yum remove {软件包名}

#### 源码安装步骤
tar -zxvf {安装包.gz}
cd {解压目录}
./configure
make 
make install

#### vim
:set nu
:u

#### other
df -m
netstat -ntulp | grep 8090
cat /etc/hosts

