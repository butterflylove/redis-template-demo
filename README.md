## RedisTemplate的使用姿势
1. zset的使用，实现排行榜功能
2. 自定义validation
3. OptionalInt使用
4. reduce的两种使用姿势
5. method reference的四种姿势
6. springboot命令行传参的两种方式

```
java -DcommandLine.testEnv=system -jar target/boot-test-0.0.1-SNAPSHOT.jar   # 系统变量的方式

java -jar target/boot-test-0.0.1-SNAPSHOT.jar --commandLine.testEnv=springcmd   # 命令行参数
```