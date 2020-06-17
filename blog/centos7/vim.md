# VIM 命令

### 安装
yum -y install vim*

### 1. 保存和退出

```
:q!       # 不保存文件，强制退出vim，若退出报错，直接用这个命令即可。
:q       # 不保存文件，退出vim。
:wq       # 保存文件，退出vim，w(write), q(quit)。
:wq!       # 保存文件，并强制退出vim。
:w        # 保存文件，但不退出vim。
:w filename       # 将文件另存为filename。
:wq! filename       # 将文件另存为filename，并强制退出vim。
:r filename       # 打开另外一个已经存在的文件filename。
:e filename       # 新建名为filename的文件。
:f filename       # 把当前文件改名为filename文件。
:nw filename       # 将第n行内容保存到文件filename中，n代表数字。
:n1,n2w filename       # 将第n1行开始到n2行结束的内容保存到文件filename中，n1，n2代表数字。
:1,.w filename       # 将第一行开始到光标当前位置的所有内容保存到文件filename中。
:.,$w filename       # 将从光标开始位置到文件末尾的所有内容保存到文件filename中。
:/str/w filename       # 将包含有str的行写到文件filename中，str代表字符。
:/str1/,/str2/w filename       # 将包含有str1开始到str2结束的内容写入文件filename中，str1和str2代表字符。
:e!       # 放弃所有修改，从上次保存文件开始再编辑命令历史
ZZ:       # 保存退出
ZQ       # 不保存退出
```

### 2. 删除命令

```
:d        # 删除当前行。
:nd        # 删除从当前行开始的n行，n代表数字
:n1,n2 d        # 删除n1行开始到n2行结束的所有内容
dG       # 删除从当前行开始到文件末尾的所有内容
:.,$d        # 删除从当前行开始到文件末尾的所有内容
:/str1/,/str2/d        # 删除从str1到str2之间的所有内容，str1、str2代表字符。
```

### 3. 复制、移动、跳转

```
:n1,n2 co n3        # 将n1和n2行之间的所有内容复制到n3行后面
:n1,n2 m n3        # 将n1和n2行之间的所有内容移动到n3行后面
:n        # 跳转到第n行
```

### 4. 替换、查找字符

```
#替换
:s/str1/str2/        # 将光标所在行第一个字符str1替换为str2
:s/str1/str2/g        # 将光标所在行所有的字符str1替换为str2
:n1,n2s/str1/str2/g        # 将n1行到n2行中所有的字符str1替换为str2
:%/str1/str2/g        # 将文件中所有的字符str1替换为str2
:.,$s/str1/str2/g        # 将光标当前位置开始到文件结束的所有字符str1替换为str2
#查找
/关键字       # 先按[/]键，在输入想查找的字符，如果第一次查找的不是想要的，可以一直按[n]键会往后查找下一个关键字，按[N]反向查找。
?关键字       # 同/关键字
```

### 5. 撤销和重复
```
u        # 撤销上一个操作，按多次[n]可以多次执行撤销
U        # 取消所有操作:
.        # 再执行一次刚刚完成的操作，相当于恢复

### 6. 其他命令
:!command        # 运行shell命令，command代表命令
:set number        # 在文件中的每一行前面列出行号
:set nonumber        # 取消在文件中的每一行前面列出行号
Ctrl +g       # 列出光标所在行行号
:set readonly        # 设置文件为只读模式
```

### Basic
```
按ESC键 跳到命令模式，然后：
:w 保存文件但不退出vi
:w file 将修改另外保存到file中，不退出vi
:w! 强制保存，不推出vi
:wq 保存文件并退出vi
:wq! 强制保存文件，并退出vi
q: 不保存文件，退出vi
:q! 不保存文件，强制退出vi
:e! 放弃所有修改，从上次保存文件开始再编辑
```

### 输入命令 ：
: set ff 
