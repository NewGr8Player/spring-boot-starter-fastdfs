# spring-boot-starter-fastdfs
spring-boot-starter-fastdfs

```xml
<dependency>
    <groupId>com.xavier</groupId>
    <artifactId>spring-boot-starter-fastdfs</artifactId>
    <version>${version}</version>
</dependency>
```

```yaml
spring:
    fastdfs:
        charset: # 字符集
        init_conn_on_load: # 即刻初始化
        tracker_servers: # tracker 地址(多个逗号隔开)
        http_anti_steal_token: # 开启反盗链(Boolean)
        http_secret_key: # 秘钥
        http_tracker_http_port: # Nginx中设置的监听端口(Integer)
        connect_timeout_in_seconds: # 连接超时(秒/Integer)
        network_timeout_in_seconds: # 工作超时(秒/Integer)
```

**注入FastDfsManager即可使用**
```java
    @Autowired
    private FastDfsManager fastDfsManager;
```

TODO
 - []拓展FastDfsManager内容

