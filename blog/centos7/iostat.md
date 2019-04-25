# iostat
**查看系统IO状态信息，确定IO性能是否存在瓶颈**
#### INSTALL
```
[root@vultr newDB]# iostat
-bash: iostat: command not found
[root@vultr newDB]# yum install -y sysstat

```
#### HELP
```
[root@vultr newDB]# iostat --help
Usage: iostat [ options ] [ <interval> [ <count> ] ]
options 操作项，，interval 统计时间间隔，，count 总共输出次数
OPTION
    -c 查看cpu状态  iostat -c
    -d 显示设备（磁盘） 使用状态 iostat -d 1 3
        输出解析
        tps：每秒传输次数，一次传输即一次IO请求，多个逻辑请求可能会合并为一次IO请求
        kB_read/s：每秒从设备读取的数据量 kB_read/s：每秒写入设备的数据量 kB_read/s：读取总数据量 kB_read/s：写入的总数据量
    -k 以Kilobytes(KB)为单位显示 iostat -k 1 10
    -m 以MB为单位显示 iostat -m 1 10
    -N 显示磁盘阵列（LVM）信息
    -n 显示NFS使用情况
    -p 报告每块磁盘每片分区的使用情况
    -t 显示终端和cpu信息
    -x 输出更多详细信息
        rrqm/s  每秒这个设备相关的读取请求有多少被Merge
        wrqm/s  每秒这个设备相关的写入请求有多少被Merge
        rsec/s  每秒读取的扇区数
        wsec/s  每秒写入的扇区数
        r/s
        w/s
        await   每一个IO请求的处理的平均时间（单位是毫秒）这里可以理解为IO的响应时间，一般地系统IO响应时间应该低于5ms，如果大于10ms就比较大了
        $util   在统计时间内所有处理IO时间，除以总共统计时间。例如，如果统计间隔1秒，该设备有0.8秒在处理IO，而0.2秒闲置，那么该设备的%util = 0.8/1 = 80%，所以该参数暗示了设备的繁忙程度。一般地，如果该参数是100%表示设备已经接近满负荷运行了（当然如果是多磁盘，即使%util是100%，因为磁盘的并发能力，所以磁盘使用未必就到了瓶颈）。

        %user：cpu处在用户模式下的时间百分比
        %idle: cpu空间时间百分比

        %nice：CPU处在带NICE值的用户模式下的时间百分比
        %system：CPU处在系统模式下的时间百分比
        %iowait：CPU等待输入输出完成时间的百分比
        %steal：管理程序维护另一个虚拟处理器时，虚拟CPU的无意识等待时间百分比

        Device：设备名称
        rrqm/s：每秒合并到设备的读取请求数
        wrqm/s：每秒合并到设备的写请求数
        r/s：每秒向磁盘发起的读操作数
        w/s：每秒向磁盘发起的写操作数
        rkB/s：每秒读K字节数
        wkB/s:每秒写K字节数
        avgrq-sz：平均每次设备I/O操作的数据大小
        avgqu-sz：平均I/O队列长度
        await：平均每次设备I/O操作的等待时间 (毫秒)，一般地，系统I/O响应时间应该低于5ms，如果大于 10ms就比较大了
        r_await：每个读操作平均所需的时间；不仅包括硬盘设备读操作的时间，还包括了在kernel队列中等待的时间
        w_await：每个写操作平均所需的时间；不仅包括硬盘设备写操作的时间，还包括了在kernel队列中等待的时间
        svctm：平均每次设备I/O操作的服务时间 (毫秒)（这个数据不可信！）
        %util：一秒中有百分之多少的时间用于I/O操作，即被IO消耗的CPU百分比，一般地，如果该参数是100%表示设备已经接近满负荷运行了
```

#### 常用命令
```
iostat -d -k 1 10       # 查看TPS和吞吐量信息
iostat -d -x -k 1 10    # 查看设备使用率（%util），响应时间(await)
iostat -c 1 10          # 查看cpu状态
```
```
查看指定磁盘吞吐量和速率
[root@vultr newDB]# iostat -d 1 1
Linux 3.10.0-957.1.3.el7.x86_64 (vultr.guest) 	04/25/2019 	_x86_64_	(1 CPU)
Device:            tps    kB_read/s    kB_wrtn/s    kB_read    kB_wrtn
vda               0.49         0.85         6.24    2996674   21891204
// 平均传输次数0.49 每秒读0.85M 每秒写6.24M
```
```
磁盘性能统计
[root@vultr newDB]# iostat -x -k 1 1
Linux 3.10.0-957.1.3.el7.x86_64 (vultr.guest) 	04/25/2019 	_x86_64_	(1 CPU)
avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           0.21    0.00    0.12    0.01    0.00   99.66
Device:         rrqm/s   wrqm/s     r/s     w/s    rkB/s    wkB/s avgrq-sz avgqu-sz   await r_await w_await  svctm  %util
vda               0.00     0.58    0.03    0.46     0.85     6.24    29.14     0.00    0.86    2.10    0.79   0.13   0.01

```

#### 性能监控指标
```
%iowait：如果该值较高，表示磁盘存在I/O瓶颈
await：一般地，系统I/O响应时间应该低于5ms，如果大于10ms就比较大了
avgqu-sz：如果I/O请求压力持续超出磁盘处理能力，该值将增加。如果单块磁盘的队列长度持续超过2，一般认为该磁盘存在I/O性能问题。需要注意的是，如果该磁盘为磁盘阵列虚拟的逻辑驱动器，需要再将该值除以组成这个逻辑驱动器的实际物理磁盘数目，以获得平均单块硬盘的I/O等待队列长度
%util：一般地，如果该参数是100%表示设备已经接近满负荷运行了
最后，除了关注指标外，我们更需要结合部署的业务进行分析。对于磁盘随机读写频繁的业务，比如图片存取、数据库、邮件服务器等，此类业务吗，tps才是关键点。对于顺序读写频繁的业务，需要传输大块数据的，如视频点播、文件同步，关注的是磁盘的吞吐量。

```