/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.cache.impl;

import com.koobe.common.cache.CacheServer;
import com.thimbleware.jmemcached.CacheImpl;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.MemCacheDaemon;
import com.thimbleware.jmemcached.storage.CacheStorage;
import com.thimbleware.jmemcached.storage.hash.ConcurrentLinkedHashMap;
import java.net.InetSocketAddress;

/**
 *
 * @author lyhcode
 */
public class JMemcachedServer implements CacheServer {

    private final static MemCacheDaemon<LocalCacheElement> daemon = new MemCacheDaemon<LocalCacheElement>();
    private final static int DEFAULT_SIZE = 1024 * 1024 * 1024;
    
    private final InetSocketAddress address;

    public JMemcachedServer(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public void start() {
        CacheStorage<Key, LocalCacheElement> storage;
        storage = ConcurrentLinkedHashMap.create(
                ConcurrentLinkedHashMap.EvictionPolicy.FIFO, 1000, DEFAULT_SIZE);
        daemon.setCache(new CacheImpl(storage));
        daemon.setBinary(false);
        daemon.setAddr(address);
        daemon.setIdleTime(3000);
        daemon.setVerbose(false);
        daemon.start();
    }

    @Override
    public void stop() {
        daemon.stop();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
