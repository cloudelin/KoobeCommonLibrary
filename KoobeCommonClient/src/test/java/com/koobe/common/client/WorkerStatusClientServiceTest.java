package com.koobe.common.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.koobe.common.core.KoobeApplication;

/**
 * 
 * @author cloude
 * @since 2014-2-7
 */
public class WorkerStatusClientServiceTest {
	
	WorkerStatusClientService workerStatusClientService;

	@Before
	public void setUp() throws Exception {
		workerStatusClientService = (WorkerStatusClientService) KoobeApplication.getInstance().getService(WorkerStatusClientService.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryStatus() {
		
		workerStatusClientService.queryStatus("127.0.0.1:8080/KoobeQueueService", "myid" + System.currentTimeMillis());
	}
}
