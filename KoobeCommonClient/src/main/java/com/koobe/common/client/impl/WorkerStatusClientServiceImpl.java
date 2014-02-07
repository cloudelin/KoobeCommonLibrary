package com.koobe.common.client.impl;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koobe.common.client.WorkerStatusClientService;
import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.model.response.TaskStatusResponse;

/**
 * The client for worker status rest service
 * @author cloude
 * @since 2014-2-7
 */
@Service
public class WorkerStatusClientServiceImpl extends GeneralKoobeService implements WorkerStatusClientService {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private ClientConfig config = new ClientConfig();
	private Client client = ClientBuilder.newClient(config);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public WorkerStatusClientServiceImpl() {
		log.info("{} initialized", getClass().getName());
	}
	
	@Override
	public TaskStatusResponse queryStatus(String workerIp, String id) {
		
		String uri = String.format("http://%s", workerIp, id);
		log.info("Call service endpoint {}", uri);
		
		WebTarget webTarget = client.target(uri);
		
		Invocation.Builder builder = webTarget.path("workerstatus").path(id).request(MediaType.APPLICATION_JSON);
		
		Response response = builder.get();
		
		String json = response.readEntity(String.class);
		
		TaskStatusResponse responseModel = null;
		
		try {
			responseModel = objectMapper.readValue(json, TaskStatusResponse.class);
		} catch (IOException e) {
			log.info("message {} to object failed", json);
			e.printStackTrace();
		}
		log.info("{}", responseModel.getTaskId());
		
		return responseModel;
	}
}
