# JDBC

主要知识点

- JDBC使用步骤
- 数据库查询方法
- 数据库写入方法
- SQL注入攻击的应对
- 连接池的使用
- Apache Commons DBUtils

`mysql --version`

`mysql  Ver 8.0.12 for macos10.13 on x86_64 (MySQL Community Server - GPL)`



什么是JDBC

- Java数据库连接-Java DataBase Connectivity
- JDBC可让Java通过程序操作关系型数据库
- JDBC基于驱动程序实现与数据库的连接与操作



什么是驱动程序

JDBC驱动程序：JDBC是一个标准，由数据厂商（mysql、oracle等）进行具体实现

JDBC优点

- 统一的API，提供一致的开发过程
- 易于学习，容易上手，代码结构稳定
- 功能强大，执行效率高，可处理海量数据



### JDBC开发流程

1. 加载并注册JDBC驱动
2. 创建数据库连接
3. 创建Statement对象
4. 遍历查询结果
5. 关闭连接释放资源





数据库与连接字符串

| 数据库     | JDBC驱动类                                   | 连接字符串                                                   |
| ---------- | -------------------------------------------- | ------------------------------------------------------------ |
| MySQL5     | com.mysql.jdbc.Driver                        | jdbc:mysql://主机ip:端口/数据库名?参数列表                   |
| MySQL8     | com.mysql.cj.jdbc.Driver                     | jdbc:mysql://主机ip:端口/数据库名?参数列表                   |
| Oracle     | Oracle.jdbc.driver.OracleDriver              | jdbc:oracle:thin:@主机ip:端口:数据库名?参数列表              |
| SQL Server | com.mircosoft.sqlserver.jdbc.SQLServerDriver | Jdbc:mircosoft:sqlserver:主机ip:端口;databasename=数据库名?参数列表 |

主机ip与端口是可选设置，默认值为127.0.0.1与3306



MySQL连接字符串常用参数

| 参数名                  | 建议参数值                | 说明                         |
| ----------------------- | ------------------------- | ---------------------------- |
| useSSL                  | true（生产）false（开发） | 是否禁用ssl                  |
| useUnicode              | true                      | 启用unicode编码传输数据      |
| CharacterEncoding       | UTF-8                     | 使用UTF-8编码传输数据        |
| serverTimezone          | Asia/Shanghai             | 使用东8时区时间，UTC + 8     |
| allowPublicKeyRetrieval | true                      | 允许从客户端获取公钥加密传输 |





JDBC事务

- 自动提交事务模式
- 手动提交事务模式

























