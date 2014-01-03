/*
 * Copyright 2013 Koobe Co., Ltd.
 */
package com.koobe.common.cache;

import com.koobe.common.cache.impl.JMemcachedServer;
import com.koobe.common.cache.impl.SpyMemcachedClient;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.GeneralKoobeService;
import java.net.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Koobe Cache Service
 *
 * - AWS ElastiCache - Built-in JMemcached
 *
 * @author lyhcode
 */
@Service
public class KoobeCacheService extends GeneralKoobeService {

    private static final Logger log = LoggerFactory.getLogger(KoobeCacheService.class);
    
    public enum CacheType {
        MEMCACHED
    }
    
    private final static CacheType DEFAULT_CACHE_TYPE = CacheType.MEMCACHED;

    @Autowired
    private KoobeApplication application;

    /**
     * Get default cache client (aws elasticache, settings from sysenv)
     *
     * @return cache client
     */
    public CacheClient getClient() {
        return new SpyMemcachedClient(application.getConfig().getAwsElastiCacheSocketAddress());
    }
    
    public CacheClient getClient(InetSocketAddress address) {
        return new SpyMemcachedClient(address);
    }

    /**
     * Get default cache server (jmemcached)
     *
     * @return cache server
     */
    public CacheServer getServer() {
        return new JMemcachedServer(application.getConfig().getAwsElastiCacheSocketAddress());
    }

    public CacheServer getServer(InetSocketAddress address) {
        return new JMemcachedServer(address);
    }
}
