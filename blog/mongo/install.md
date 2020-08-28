# Mongo
## INTALL
```
docker pull mongo
docker run --name mongoDB -p 27017:27017  -d mongo --auth
docker exec -it mongoDB mongo admin

// 创建用户
db.createUser({ user:'admin',pwd:'123456',roles:[ { role:'userAdminAnyDatabase', db: 'admin'}]});
// 用户登陆
db.auth('zxu', '4862')
```

## OPERATION
```
// 创建数据库 sprmongo
use sprmongo
db
show dbs
db.sprmongo.insert({"name":"孙wei"})
show dbs
```
