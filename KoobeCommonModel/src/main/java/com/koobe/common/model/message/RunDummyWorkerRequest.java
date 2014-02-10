package com.koobe.common.model.message;

/**
 * 
 * @author cloude
 * @since 2014-2-10
 */
public class RunDummyWorkerRequest extends GeneralRequest {

	public static final String ACTION = "RunDummyWorker";
	
	public RunDummyWorkerRequest() {
		super(ACTION);
	}

}
