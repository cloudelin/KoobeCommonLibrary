package com.koobe.common.queue.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.config.KoobeConfig;
import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.queue.KoobeQueueService;

/**
 * 
 * @author cloude
 * @since 2014-2-5
 */
@Service
public class KoobeQueueServiceImpl extends GeneralKoobeService implements KoobeQueueService {
	
	private static final Logger log = LoggerFactory.getLogger(KoobeQueueService.class);

    private String queueUrl;
    private AmazonSQS sqsClient;

    @Autowired
    public KoobeQueueServiceImpl(KoobeApplication application) {

        KoobeConfig config = application.getConfig();

        AWSCredentials credentials = new BasicAWSCredentials(config.getAwsAccessKeyID(), config.getAwsSecretKey());

        this.sqsClient = new AmazonSQSClient(credentials);
        
        Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
        this.sqsClient.setRegion(region);
        log.info("SQS Region: " + region);

        this.queueUrl = config.getDefaultSqsQueueUrl();
    }
    
    protected List<Message> receive(String queueUrl, boolean onlyOne) {
    	
    	ReceiveMessageRequest req  = new ReceiveMessageRequest(queueUrl);
    	
    	if (onlyOne) {
    		req.setMaxNumberOfMessages(1);
    	}
    	
    	ReceiveMessageResult res = sqsClient.receiveMessage(req);
    	
    	for (Message msg: res.getMessages()) {
    		DeleteMessageRequest dreq = new DeleteMessageRequest(queueUrl, msg.getReceiptHandle());
    		sqsClient.deleteMessage(dreq);
    	}
    	
    	return res.getMessages();
	}

    
    @Override
    public String sendMessage(String msg) {
        return sendMessage(this.queueUrl, msg);
    }

    @Override
    public String sendMessage(String queueUrl, String msg) {
        SendMessageResult result;
        result = sqsClient.sendMessage(new SendMessageRequest(queueUrl, msg));
        return result.getMessageId();
    }
    
    @Override
	public Message receiveMessage() {
    	return receiveMessage(this.queueUrl);
	}


	@Override
	public Message receiveMessage(String queueUrl) {
		
		Message message = null;
    	
    	List<Message> messages = receive(queueUrl, true);
    	if (messages.size() != 0) {
    		message = messages.get(0);
    	}
    	
		return message;
	}

	@Override
	public List<Message> receiveMessages() {
		return receive(this.queueUrl, false);
	}

	@Override
	public List<Message> receiveMessages(String queueUrl) {
        return receive(queueUrl, false);
	}
}
