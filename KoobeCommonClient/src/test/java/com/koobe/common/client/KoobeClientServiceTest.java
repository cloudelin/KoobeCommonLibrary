package com.koobe.common.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.model.message.ExtractEpubRequest;

/**
 * 
 * @author cloude
 * @since 2014-1-20
 */
public class KoobeClientServiceTest {
	
	KoobeClientService clientService;

	@Before
	public void setUp() throws Exception {
		clientService = (KoobeClientService) KoobeApplication.getInstance().getService(KoobeClientService.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReuqestForExtractEpubFile() throws JsonProcessingException {
		
		assertNotNull(clientService);
		
		ExtractEpubRequest request = new ExtractEpubRequest("koobe-cloude-test", "koobe-cloude-test", 
				"dickens-oliver-twist.epub", "KoobeClientServiceTest-" + System.currentTimeMillis());
		
//		clientService.sendQueueMessageAndRunTask(request, "https://sqs.ap-southeast-1.amazonaws.com/417280986345/KGL-Service-Queue-Test");
		String messageId = clientService.sendQueueMessageAndRunTask(request, "https://sqs.ap-southeast-1.amazonaws.com/417280986345/awseb-e-gurm7xd7ag-stack-AWSEBWorkerQueue-19RGZXIFQ4P93");
		System.out.println(messageId);
	}
}
