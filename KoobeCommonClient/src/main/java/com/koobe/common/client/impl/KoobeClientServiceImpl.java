package com.koobe.common.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.koobe.common.client.KoobeClientService;
import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.model.message.GeneralRequest;
import com.koobe.common.queue.KoobeQueueService;

/**
 * Implementation of Koobe client service
 * @author cloude
 * @since 2014-1-20
 */
@Service
public class KoobeClientServiceImpl extends GeneralKoobeService implements KoobeClientService {

	@Autowired
	private KoobeQueueService queueService;

	public void setQueueService(KoobeQueueService queueService) {
		this.queueService = queueService;
	}

	@Override
	public void sendQueueMessageAndRunTask(GeneralRequest request, String sqsUrl) throws JsonProcessingException {
		queueService.sendMessage(sqsUrl, request.toJson());
	}
}
