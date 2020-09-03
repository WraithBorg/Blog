# Cookie
## Cookie属性
#### name
cookie名字
#### value
cookie值
#### maxAge
cookie失效时间,单位为秒,  
如果为正数则cookie是maxAge秒后失效,cookie会持久化到对应文件中  
如果为负数,则cookie为临时cookie,关闭浏览器即失效,浏览器也不会以任何形式保存该cookie,cookie会保存在浏览器内存中  
如果为零,表示删除该cookie,  
默认为-1
#### secure
该cookie是否仅被使用安全协议传输。安全协议包括HTTPS,SSL等,在网络上传输数据之前先将数据加密。默认为false  
#### path
cookie的使用路径,如果设置为`/xxxWeb/`,则只有contextPath为`/xxxWeb`的程序可以访问该cookie,  
如果设置为`/`,则本域名下contextPath都可以访问该cookie  
注意最后一个字符必须为`/`  
#### domain
可以访问该cookie的域名,如果设置为`.sso.com`,则所有以`sso.com`结尾的域名都可以访问该cookie,注意第一个字符必须为`.`
#### comment
该cookie的用处说明,浏览器显示cookie信息的时候显示该说明  
#### version
cookie使用的版本号,0表示遵循Netscape的Cookie规范，1表示遵循W3C的RFC 2109规范  

## Cookie
#### 修改和删除
如果要删除cookie,则新建一个同名cookie来覆盖原来的cookie并将maxAge设为0,并添加到response中即可  
如果要修改cookie,则新建一个同名cookie来覆盖原来的cookie,并添加到response中即可  
注意：修改删除cookie时,新建的cookie除value,maxAge之外所有的属性(包括name,path,domain等),都要与原cookie完全一样,  
否则浏览器会视为两个不同的cookie,导致修改删除失败  
#### 读取cookie
从客户端读取cookie时,包括maxAge在内的其他属性时不可读的,也不会被提交  
浏览器提交cookie时,只会提交name和value属性  
#### cookie域名
yibacookie是不可跨域名的,  
#### cookie的安全性
使用HTTP协议的数据不经过任何加密就直接在网络上传播，有被截获的可能。使用HTTP协议传输很机密的内容是一种隐患  
如果不希望Cookie在HTTP等非安全协议中传输，可以设置Cookie的secure属性为true  
浏览器只会在HTTPS和SSL等安全协议中传输此类Cookie。设置secure属性为true的代码是cookie.setSecure(true);  
secure属性并不能对Cookie内容加密，因而不能保证绝对的安全性。如果需要高安全性，需要在程序中对Cookie内容加密、解密，以防泄密。   

