package com.combanc.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;


public class IOUtils {

    private static Logger log = Logger.getLogger(IOUtils.class);
    
    public static byte[] toByteArray(InputStream input) throws IOException {
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
    
    public static int copy(InputStream input, OutputStream output) 
            throws IOException {
        
        byte[] buffer = new byte[1024];
        long count = 0L;
        int n = 0;
        while(-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return (int)count;
    }
    public static String toString(InputStream is, String characterEncoding) 
            throws UnsupportedEncodingException, IOException {
        
        return new String(toByteArray(is), characterEncoding);
    }
    
    /**
     * @param reader
     * @return
     * @throws IOException
     */
    public static String toString(Reader reader) throws IOException {
        
        if (!(reader instanceof BufferedReader))
            reader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        
        char[] data = new char[64];
        int len;
        while (true) {
            if ((len = reader.read(data)) == -1)
                break;
            sb.append(data, 0, len);
        }
        return sb.toString();
    }
    
}
