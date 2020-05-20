# Git Command

### Git 基本命令
#### 设置用户名和email
$ git config user.name 获取当前git用户名
$ git config --global user.name "Zxu"
$ git config --global user.email "zz@qq.com"

#### 创建版本库
$ mkdir mygitspace && cd mygitspace
$ git init 将目录变成git可管理的仓库
$ ls -a

$ git add readme.txt 将文件添加到仓库
$ git commit -m "this is readme" 将文件提交到仓库
$ git add A.txt B.txt C.txt
$ git commit -m "add 3 files"

$ git status 查看当前仓库状态
$ git diff readme.txt 比较当前文件和仓库内文件的不同

$ git log --pretty=oneline
$ git reset --hard HEAD^ 回退上一个版本，修改head位置，将head指向某个版本
$ git reset --hard HEAD~100  回退上一百个版本
$ git reset --hard 102ac0 回退到某个版本

$ git reflog 查看命令历史
$ git diff HEAD -- readme.txt 查看工作区和版本库里最新版本的区别


$ git checkout --readme.txt 丢弃工作区的修改或删除
一种是readme.txt自修改后还没有被放到暂存区，现在，撤销修改就回到和版本库一模一样的状态；
一种是readme.txt已经添加到暂存区（add）后，又作了修改，现在，撤销修改就回到添加到暂存区后的状态。

$ git reset HEAD readme.txt 将暂存区修改撤销掉，重新放回工作区

#### 不常用命令
$ git blame readme.txt 显示文件每一行最后修改的版本和作者
$ git mergetool
$ git revert --no-commit f77ss1..ae223d 撤销两个指定的提交，用..做分隔符

### 远程仓库
$ ssh-keygen -t rsa -C "8472@qq.com" 创建SSH Key,一直回车,然后可在主目录找到id_rsa和id_rsa.pub文件

$ git remote add origin git@github.com:hahaha/mygitdemo.git 关联远程仓库
$ git push -u origin master 将本地仓库所有master分支的内容推送到远程仓库，
-u参数用于第一次推送的时候，将本地master分支和远程的master分支关联起来
第一次push或clone会有警告
$ git push origin master 以后只用这个命令即可

$ git clone git@github.com:hahaha/mygitdemo.git 克隆远程仓库

$ git checkout -b dev 创建dev分支，-b表示创建并切换
$ git branch dev 创建dev分支
$ git checkout dev 切换到dev分支
$ git branch 列出所有分支

$ git merge dev 将dev分支的内容合并到master分支上
$ git branch -d dev 删除dev分支

$ git switch -c dev 创建并切换到dev分支
$ git switch master 切换到master分支

$ git log --graph --pretty=oneline --abbrev-commit 查看日志

$ git merge --no-ff -m "this is merge test" dev  切回master执行该命令，表示合并dev分支
--no-ff表示禁用fast forward
合并分支git可能会用fast forward模式，这种模式下，删除分支后会丢掉分支信息
如果强制禁用fast forward模式，git就会在merge时生成一个新的commit,这样可以从分支历史上看到分支信息

$ git stash 把当前工作区存起来，git status查看当前工作区会很干净
当工作进行到一半，要紧急修复一个临时bug的时候会用到该命令

$ git stash list查看工作区
$ git stash apply 恢复工作区，但是stash内容不删
$ git stash drop 删除缓存内容
$ git stash pop 恢复缓存内容并删除stash内容

$ git cherry-pick 4saa22 复制一个特定的提交到当前分支

$ git branch -D gittest-feature 强制删除分支
$ git remote 查看远程仓库信息
$ git remote -v 查看远程仓库详细信息

$ git pull 从远程拉取最新内容并合并，git pull = git fetch + get merge
$ git fetch 将远程拉取到本地，然后用户觉得是否合并到本地

$ git rebase 将分叉的提交历史整理成一条直线

$ git tag v1.0 建立一个新标签
$ git tag 查看所有标签
$ git tag v1.0 f43s22 为某次提交建一个标签

$ git show v1.0 查看标签信息
$ git tag -a v0.1 -m "version 0.1 released" 1033ds 创建带有说明的标签

$ git push origin v1.0 推送某个标签到远程
$ git push origin --tags 一次性推送全部标签到远程

$ git tag -d v0.2 删除标签
$ git push origin :refs/tags/v0.2 删除远程标签


$ git remote rm origin 删除已有的github远程库

### 忽略特殊文件
工作区根目录创建.gitignore文件
*.class忽略class文件

$ git add -f App.class 添加被忽略的文件
$ git check-ignore -v App.class 查看该文件忽略规则

### 配置别名
$ git config --global alias.st status 用git st 代替 git status命令

$ git last 查看最近一次提交
