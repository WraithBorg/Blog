### Shiro 功能和组件
```
Authentication 认证
    验证用户身份
Authorization 授权
    验证用户权限或角色
Session management 会话管理
Cryptography 加密
Web Support 与web集成，缓存
Caching 支持缓存
Concurrency 支持并发
    shiro 支持多线程应用并发验证，如一个线程开启另一个线程，能把权限自动传播过去
Testing 支持测试
Run As 允许用户利用另一个用户的身份进行访问（如果允许的话）
Remember Me 记住登陆用户，以后无需再次登陆
SessionDAO 数据访问对象
CacheManager   缓存控制器，管理用户角色和权限的缓存

设计好角色或权限后，只需要通过响应接口注入shiro即可
```
### Summary
```
Subject 主体，可以是与任何应用交互的“用户”，shiro对外API核心，Subject的所有交互都会委托给SecurityManager，Subject是门面，SecurityManager是实际执行者
SecurityManager 安全管理器,shiro的核心，与所有安全相关的操作都会与SecurityManager交互，且SecurityManager管理所有Subject，Security负责与其他组件进行交互
Realm  域，安全数据源，Shiro 从Realm获取安全数据（如用户，角色，权限），如果SecurityManager要验证身份，则需要从Realm获取相应的用户进行比较以确认用户身份是否合法，
            也需要从Realm得到用户相应的角色/权限 进行验证用户是否能进行操作。
应用通过Subject进行认证和授权，Subject又委托给SecurityManager，SecurityManager通过注入Realm实现对用户权限的判断
Realm 由开发人员自己进行注入
```
### 优点
可扩展，易插拔（容易插入用户自定义实现）

#### TIPS
CRUD
create retrieve(读取查询） update delete