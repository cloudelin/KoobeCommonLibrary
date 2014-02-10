package com.koobe.common.data.repository.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.koobe.common.data.repository.WorkerStatusLogRepository;

/**
 * 
 * @author cloude
 * @since 2014-2-10
 */
@Service
public class RepositoryUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	public static WorkerStatusLogRepository getWorkerStatusLogRepository() {
		return context.getBean(WorkerStatusLogRepository.class);
	}
}
