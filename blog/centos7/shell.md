# shell

### export
设置或显示环境变量
export -p 列出环境变量
export -p | grep PATH

### bash
sh ,bash 和 ./ 可以执行shell脚本

### source
重新执行刚修改的文档，通常用 “.” 来代替
source filename 
. filename
source /etc/profile 使新设置的环境变量生效

###source filename 与 sh filename 及./filename执行脚本的区别
+ 当shell脚本具有可执行权限时，用sh filename与./filename执行脚本是没有区别得。./filename是因为当前目录没有在PATH中，所有”.”是用来表示当前目录的。
+  sh filename 重新建立一个子shell，在子shell中执行脚本里面的语句，该子shell继承父shell的环境变量，但子shell新建的、改变的变量不会被带回父shell。
+ source filename：这个命令其实只是简单地读取脚本里面的语句依次在当前shell里面执行，没有建立新的子shell。那么脚本里面所有新建、改变变量的语句都会保存在当前shell里面。
