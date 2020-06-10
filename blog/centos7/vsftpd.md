# FTP
[root@test vsftpd]# yum -y install vsftpd 安装
[root@test vsftpd]# systemctl start vsftpd.service 启动
[root@test vsftpd]# getsebool -a | grep ftp 查看状态
[root@test vsftpd]# setsebool -P allow_ftpd_full_access on
[root@test vsftpd]# setsebool -P tftp_home_dir on
