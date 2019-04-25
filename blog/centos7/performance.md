# 性能检测

#### mpstat 实时系统监控
mpstat 实时系统监控（Multiprocessor Statistics）
```
参数	释义	从/proc/stat获得数据
CPU	处理器ID
%usr	在internal时间段里，用户态的CPU时间（%），不包含 nice值为负进程	usr/total*100
%nice	在internal时间段里，nice值为负进程的CPU时间（%）	nice/total*100
%sys	在internal时间段里，核心时间（%）	system/total*100
%iowait	在internal时间段里，硬盘IO等待时间（%）	iowait/total*100
%irq	在internal时间段里，硬中断时间（%）	irq/total*100
%soft	在internal时间段里，软中断时间（%）	softirq/total*100
%steal	显示虚拟机管理器在服务另一个虚拟处理器时虚拟CPU处在非自愿等待下花费时间的百分比	steal/total*100
%guest	显示运行虚拟处理器时CPU花费时间的百分比	guest/total*100
%gnice		gnice/total*100
%idle	在internal时间段里，CPU除去等待磁盘IO操作外的因为任何原因而空闲的时间闲置时间（%）	idle/total*100
```
#### vmstat 虚拟内存统计
Virtual Meomory Statistics（虚拟内存统计） 对操作系统的虚拟内存、进程、CPU活动进行监控
```
Procs（进程）
r:	运行队列中进程数量，这个值也可以判断是否需要增加CPU。（长期大于1）
b	等待IO的进程数量。
Memory（内存）
swpd	使用虚拟内存大小，如果swpd的值不为0，但是SI，SO的值长期为0，这种情况不会影响系统性能。
free	空闲物理内存大小。
buff	用作缓冲的内存大小。
cache	用作缓存的内存大小，如果cache的值大的时候，说明cache处的文件数多，如果频繁访问到的文件都能被cache处，那么磁盘的读IO bi会非常小。
Swap
si	每秒从交换区写到内存的大小，由磁盘调入内存。
so	每秒写入交换区的内存大小，由内存调入磁盘。
IO
bi	每秒读取的块数
bo	每秒写入的块数
system（系统）
in	每秒中断数，包括时钟中断。
cs	每秒上下文切换数。
CPU（以百分比表示）
us	用户进程执行时间百分比(user time) us的值比较高时，说明用户进程消耗的CPU时间多，但是如果长期超50%的使用，那么我们就该考虑优化程序算法或者进行加速。
sy:	内核系统进程执行时间百分比(system time) sy的值高时，说明系统内核消耗的CPU资源多，这并不是良性表现，我们应该检查原因。
wa	IO等待时间百分比 wa的值高时，说明IO等待比较严重，这可能由于磁盘大量作随机访问造成，也有可能磁盘出现瓶颈（块操作）。
id	空闲时间百分比
```

#### sar 系统活动情况报告
System Activity Reporter系统活动情况报告
包括：文件的读写情况、系统调用的使用情况、磁盘I/O、CPU效率、内存使用状况、进程活动及IPC有关的活动等
```
CPU：all 表示统计信息为所有 CPU 的平均值。
%user：显示在用户级别(application)运行使用 CPU 总时间的百分比。
%nice：显示在用户级别，用于nice操作，所占用 CPU 总时间的百分比。
%system：在核心级别(kernel)运行所使用 CPU 总时间的百分比。
%iowait：显示用于等待I/O操作占用 CPU 总时间的百分比。
%steal：管理程序(hypervisor)为另一个虚拟进程提供服务而等待虚拟 CPU 的百分比。
%idle：显示 CPU 空闲时间占用 CPU 总时间的百分比。
```
#### iostat 输入/输出统计
I/O statistics（输入/输出统计）
```
%user：CPU处在用户模式下的时间百分比。
%nice：CPU处在带NICE值的用户模式下的时间百分比。
%system：CPU处在系统模式下的时间百分比。
%iowait：CPU等待输入输出完成时间的百分比。
%steal：管理程序维护另一个虚拟处理器时，虚拟CPU的无意识等待时间百分比。
%idle：CPU空闲时间百分比。
注
如果%iowait的值过高，表示硬盘存在I/O瓶颈
如果%idle值高，表示CPU较空闲
如果%idle值高但系统响应慢时，可能是CPU等待分配内存，应加大内存容量。
如果%idle值持续低于10，表明CPU处理能力相对较低，系统中最需要解决的资源是CPU。
```
```
tps：该设备每秒的传输次数
kB_read/s：每秒从设备（drive expressed）读取的数据量；
kB_wrtn/s：每秒向设备（drive expressed）写入的数据量；
kB_read：  读取的总数据量；
kB_wrtn：写入的总数量数据量；
```