


### -o
```
-o参数将服务器的回应保存成文件，等同于wget命令。
$ curl -o example.html https://www.example.com
上面命令将www.example.com保存成example.html
```
### -O
```
-O参数将服务器回应保存成文件，并将 URL 的最后部分当作文件名。
$ curl -O https://www.example.com/foo/bar.html
上面命令将服务器回应保存成文件，文件名为bar.html。
```
### demo
```
curl -O http://dl.zeroturnaround.com/jrebel-stable-nosetup.zip
unzip jrebel-stable-nosetup.zip
```
