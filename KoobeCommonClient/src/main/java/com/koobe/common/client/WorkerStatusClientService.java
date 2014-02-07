package com.koobe.common.client;

import com.koobe.common.model.response.TaskStatusResponse;

/**
 * 
 * @author cloude
 * @since 2014-2-7
 */
public interface WorkerStatusClientService {

	public TaskStatusResponse queryStatus(String workerIp, String id);
}
