/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.data.service.DataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author lyhcode
 */
@Service
public class KoobeDataService extends GeneralKoobeService {

    private static final Logger log = LoggerFactory.getLogger(KoobeDataService.class);

    @Autowired
    KoobeApplication application;
    
    public DataService getDataService(Class<?> dataServiceClass) {
        Object bean = application.getContext().getBean(dataServiceClass);
        if (bean != null && bean instanceof DataService) {
            return (DataService)bean;
        }
        log.error("{} not found.", dataServiceClass);
        return null;
    }
    
    public DataService getDataService(String dataServiceName) {
        Object bean = application.getContext().getBean(dataServiceName);
        if (bean != null && bean instanceof DataService) {
            return (DataService)bean;
        }
        log.error("{} not found.", dataServiceName);
        return null;
    }
    
    public Repository getRepository(Class<?> repositoryClass) {
        Object bean = application.getContext().getBean(repositoryClass);
        if (bean != null && bean instanceof Repository) {
            return (Repository)bean;
        }
        log.error("{} not found.", repositoryClass);
        return null;
    }
        
    public Repository getRepository(String repositoryName) {
        Object bean = application.getContext().getBean(repositoryName);
        if (bean != null && bean instanceof Repository) {
            return (Repository)bean;
        }
        log.error("{} not found.", repositoryName);
        return null;
    }


}
