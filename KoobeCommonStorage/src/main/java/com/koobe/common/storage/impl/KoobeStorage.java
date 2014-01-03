package com.koobe.common.storage.impl;

import java.io.File;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kyle
 * Date: 2013/11/19
 * Time: 下午 5:26
 * To change this template use File | Settings | File Templates.
 */
public interface KoobeStorage {
    public boolean isAccessible();
    public void putFile(String path, InputStream inputStream);
    public void putFile(String path, File file);
    public File getFile(String path);
    public InputStream getFileInputStream(String path);
    public void removeFile(String path);
    public boolean hasFile(String path);
}
