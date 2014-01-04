/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.cache;

/**
 * Cache Client Interface
 *
 * @author lyhcode
 */
public interface CacheClient {
    public boolean isAvailable();
    public Object read(String key);
    public void write(String key, Object value);
    public void write(String key, Object value, int ttl);
    public void touch(String key, int ttl);
    public void remove(String key);
}
