/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.queue;

import java.util.List;

import com.amazonaws.services.sqs.model.Message;

/**
 * 
 * @author cloude
 * @since 2014-2-5
 */
public interface KoobeQueueService {
	
	/**
	 * Send queue message to default queue url
	 * @param msg
	 * @return message id
	 */
	public String sendMessage(String msg);
	
	/**
	 * Send queue message to specific queue url
	 * @param queueUrl
	 * @param msg
	 * @return message id
	 */
	public String sendMessage(String queueUrl, String msg);
	
	/**
	 * Receive a queue message from default queue url
	 * @return sqs message
	 */
	public Message receiveMessage();
	
	/**
	 * Receive a queue message from specific queue url
	 * @param queueUrl
	 * @return sqs message
	 */
	public Message receiveMessage(String queueUrl);
	
	/**
	 * Receive messages from default queue url
	 * @return list of sqs message
	 */
	public List<Message> receiveMessages();
	
	/**
	 * Receive messages from specific queue url
	 * @param queueUrl
	 * @return list of sqs message
	 */
	public List<Message> receiveMessages(String queueUrl);
	
}
