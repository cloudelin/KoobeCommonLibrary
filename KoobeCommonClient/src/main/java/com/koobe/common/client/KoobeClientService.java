package com.koobe.common.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.koobe.common.model.message.GeneralRequest;

/**
 * Koobe client service for sending request to service layer
 * @author cloude
 * @since 2014-1-20
 */
public interface KoobeClientService {
	
	/**
	 * Get worker status client service, for invoking the rest service of worker
	 * @return
	 */
	public WorkerStatusClientService getWorkerStatusClientService();

	/**
	 * Send queue message to specific queue, and request for running a task
	 * @param request
	 * @param sqsUrl
	 * @throws JsonProcessingException
	 */
	public String sendQueueMessageAndRunTask(GeneralRequest request, String sqsUrl) throws JsonProcessingException;
	
	
}
