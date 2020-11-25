### Docker 安装sqlserver
```
sudo docker pull microsoft/mssql-server-linux:2017-latest

sudo docker run -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=Asdfgh123' \
   -p 1433:1433 --name sql-server \
   -d microsoft/mssql-server-linux:2017-latest
   
sudo docker ps -a

sudo docker exec -it sql-server /opt/mssql-tools/bin/sqlcmd \
-S localhost -U SA -P 'Asdfgh123' \
-Q 'ALTER LOGIN SA WITH PASSWORD="qazWSX123"'

sudo docker exec -it sql-server "bash"

/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'qazWSX123'

CREATE DATABASE TestDB
SELECT Name from sys.Databases
GO

docker stop sql-server
docker rm sql-server
```
