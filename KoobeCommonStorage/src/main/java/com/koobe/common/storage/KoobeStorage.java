package com.koobe.common.storage;

import java.io.File;
import java.io.InputStream;

/**
 *
 */
public interface KoobeStorage {
    public boolean isAccessible();
    public void putFile(String path, InputStream inputStream);
    public void putFile(String path, File file);
    public void putFiles(String path, File directory);
    public File getFile(String path);
    public InputStream getFileInputStream(String path);
    public void removeFile(String path);
    public boolean hasFile(String path);
    public boolean objectExists(String path);
}
