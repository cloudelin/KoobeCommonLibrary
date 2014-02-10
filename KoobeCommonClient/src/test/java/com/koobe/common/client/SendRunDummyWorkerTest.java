package com.koobe.common.client;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.model.message.RunDummyWorkerRequest;

/**
 * 
 * @author cloude
 * @since 2014-2-10
 */
public class SendRunDummyWorkerTest {

	KoobeClientService clientService;
	
	String queueUrl = "https://sqs.ap-southeast-1.amazonaws.com/417280986345/awseb-e-gurm7xd7ag-stack-AWSEBWorkerQueue-19RGZXIFQ4P93";

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
		
		RunDummyWorkerRequest dummyWorkerRequest = new RunDummyWorkerRequest();
		
		String messageId = clientService.sendQueueMessageAndRunTask(dummyWorkerRequest, queueUrl);
		
		System.out.println(messageId);
		
		assertNotNull(messageId);
	}
}
