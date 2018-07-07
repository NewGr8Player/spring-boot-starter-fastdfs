package com.xavier.springbootstarterfastdfs.config;

import com.xavier.springbootstarterfastdfs.FastDfsManager;
import com.xavier.springbootstarterfastdfs.props.FastDfsProperties;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * FastDfs配置
 *
 * @author NewGr8Player
 */
@Configuration
@EnableConfigurationProperties(FastDfsProperties.class)
public class FastDfsConfig {

	@Autowired
	private FastDfsProperties fastDfsProperties;

	private boolean connInitialized;/* 是否已经被初始化 */


	/**
	 * 原型模式，避免多线程引用
	 *
	 * @return FastDfsManager
	 * @throws IOException
	 * @throws MyException
	 */
	@Bean
	@Scope(scopeName = "prototype")
	public FastDfsManager fastDfsManager() throws IOException, MyException {
		if (!connInitialized) {
			ClientGlobal.initByProperties(fastDfsProperties.getProperties());
			this.connInitialized = true;
		}

		final TrackerClient trackerClient = new TrackerClient();
		final TrackerServer trackerServer = trackerClient.getConnection();
		final StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
		final StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

		return new FastDfsManager(trackerClient, trackerServer, storageServer, storageClient1);
	}

	/**
	 * 初始化
	 *
	 * @throws IOException
	 * @throws MyException
	 */
	@PostConstruct
	private void initFastDfs() throws IOException, MyException {
		if (fastDfsProperties.getInitConnOnLoad() != null && fastDfsProperties.getInitConnOnLoad()) {
			// Do the init
			ClientGlobal.initByProperties(fastDfsProperties.getProperties());

			// Set the initialized flag
			this.connInitialized = true;
		}
	}
}