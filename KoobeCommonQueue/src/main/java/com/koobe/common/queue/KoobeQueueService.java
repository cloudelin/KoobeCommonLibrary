/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.queue;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.config.KoobeConfig;
import com.koobe.common.core.service.GeneralKoobeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyhcode
 */
@Service("koobeQueueService")
public class KoobeQueueService extends GeneralKoobeService {

    private static final Logger log = LoggerFactory.getLogger(KoobeQueueService.class);

    private static String queueUrl;
    private static AmazonSQS sqsClient;

    @Autowired
    public KoobeQueueService(KoobeApplication application) {

        KoobeConfig config = application.getConfig();

        AWSCredentials credentials = new BasicAWSCredentials(config.getAwsAccessKeyID(), config.getAwsSecretKey());

        sqsClient = new AmazonSQSClient(credentials);
        
        Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
        sqsClient.setRegion(region);
        log.info("SQS Region: " + region);

        queueUrl = config.getDefaultSqsQueueUrl();
    }

    /**
     * Receive messages from default queue url.
     *
     * @return messages
     */
    public List<String> receiveMessages() {
        return receiveMessages(queueUrl);
    }

    /**
     * Receive message from specify queue url.
     *
     * @param queueUrl
     * @return messages
     */
    public List<String> receiveMessages(String queueUrl) {
    	log.debug("receive messages from queue: " + queueUrl);
    	
        ReceiveMessageResult result;
        result = sqsClient.receiveMessage(new ReceiveMessageRequest(queueUrl));

        List<Message> msgs = result.getMessages();
        List<String> msgs2 = new ArrayList<String>();

        if (!msgs.isEmpty()) {
            for (Message msg : msgs) {
                log.info("Receive message: " + msg.getBody());
                msgs2.add(msg.getBody());

                // delete after retrieved
                sqsClient.deleteMessage(new DeleteMessageRequest(queueUrl,
                        msg.getReceiptHandle()));
            }
        }

        return msgs2;
    }
    
    /**
     * Receive one message from the queue
     * @return
     */
    public String receiveMessage() {
    	return receiveMessage(queueUrl);
    }
    
    /**
     * Receive one message from the queue (specify url)
     * @param queueUrl
     * @return
     */
    public String receiveMessage(String queueUrl) {
    	
    	String return_value = null;
    	
    	ReceiveMessageRequest req  = new ReceiveMessageRequest(queueUrl);
    	req.setMaxNumberOfMessages(1);
    	
    	ReceiveMessageResult res = sqsClient.receiveMessage(req);
    	
    	for (Message msg: res.getMessages()) {
    		return_value = msg.getBody();
    		
    		DeleteMessageRequest dreq = new DeleteMessageRequest(queueUrl, msg.getReceiptHandle());
    		sqsClient.deleteMessage(dreq);
    	}
    	
    	return return_value;
    }
    

    /**
     * @param msg
     * @return message id
     */
    public String sendMessage(String msg) {
        return sendMessage(queueUrl, msg);
    }

    /**
     * @param queueUrl
     * @param msg
     * @return message id
     */
    public String sendMessage(String queueUrl, String msg) {
        SendMessageResult result;
        result = sqsClient.sendMessage(new SendMessageRequest(queueUrl, msg));

        return result.getMessageId();
    }
}
