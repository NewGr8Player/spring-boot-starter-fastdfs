package com.xavier.springbootstarterfastdfs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

@Getter
@AllArgsConstructor
public class FastDfsManager {

	private TrackerClient trackerClient;

	private TrackerServer trackerServer;

	private StorageServer storageServer;

	private StorageClient1 storageClient1;

}

