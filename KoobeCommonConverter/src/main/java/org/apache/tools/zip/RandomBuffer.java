/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.tools.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ThomasTarn
 */
public class RandomBuffer extends ByteArrayInputStream {
    public RandomBuffer(byte[] buf) {
        super(buf);
    }
    
    public RandomBuffer(byte[] buf, int offset, int length) {
        super(buf, offset, length);
    }
    
    public static RandomBuffer getRandomBuffer(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while ((c=is.read()) != -1)
            baos.write(c);
        
        return new RandomBuffer(baos.toByteArray());
    }
    
    public int pos() {
        return this.pos;
    }
    
    public void seek(long n) {
        reset();
        skip(n);
    }
    
    public long length() {
        return this.count;
    }
}
