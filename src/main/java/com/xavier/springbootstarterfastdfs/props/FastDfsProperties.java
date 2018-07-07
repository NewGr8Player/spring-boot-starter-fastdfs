package com.xavier.springbootstarterfastdfs.props;

import com.xavier.springbootstarterfastdfs.constant.FastDfsConfigKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * FastDfs配置项Bean
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.fastdfs")
public class FastDfsProperties {

	private Properties properties;/* properties实例 */

	private String trackerServers;/* spring.fastdfs.tracker_servers tracker地址 */

	private Integer connectTimeoutInSeconds;/* spring.fastdfs.connect_timeout_in_seconds 连接超时时间(秒) */

	private Integer networkTimeoutInSeconds; /* spring.fastdfs.network_timeout_in_seconds 工作超时时间(秒) */

	private String charset;/* spring.fastdfs.charset 字符集 */

	private Boolean httpAntiStealToken;/* spring.fastdfs.http_anti_steal_token 是否开启防盗链 */

	private String httpSecretKey;/* spring.fastdfs.http_secret_key 防盗链秘钥 */

	private Integer httpTrackerHttpPort;/* spring.fastdfs.http_tracker_http_port tracker监听端口(Nginx中配置)*/

	private Boolean initConnOnLoad = true;/* spring.fastdfs.init_conn_on_load 初始化时即加载 */

	@PostConstruct
	private void initProperties() {
		if (properties != null) {
			properties.clear();
		} else {
			properties = new Properties();
		}

		setFastDfsProperty(FastDfsConfigKey.KEY_TRACKER_SERVERS, trackerServers);
		setFastDfsProperty(FastDfsConfigKey.KEY_CONN_TIMEOUT_IN_SECONDS, connectTimeoutInSeconds);
		setFastDfsProperty(FastDfsConfigKey.KEY_NETWORK_TIMEOUT_IN_SECONDS, networkTimeoutInSeconds);
		setFastDfsProperty(FastDfsConfigKey.KEY_CHARSET, charset);
		setFastDfsProperty(FastDfsConfigKey.KEY_HTTP_ANTI_STEAL_TOKEN, httpAntiStealToken);
		setFastDfsProperty(FastDfsConfigKey.KEY_HTTP_SECRET_KEY, httpSecretKey);
		setFastDfsProperty(FastDfsConfigKey.KEY_HTTP_TRACKER_HTTP_PORT, httpTrackerHttpPort);
	}

	private void setFastDfsProperty(String key, Object value) {
		if (value == null) {
			return;
		}
		properties.setProperty(key, value.toString());
	}

}
