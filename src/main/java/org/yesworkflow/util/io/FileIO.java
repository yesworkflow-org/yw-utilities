package org.yesworkflow.util.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class FileIO {

    public static final String EOL = System.getProperty("line.separator");
    
    public static String readEOLNormalizedTextFileOnClasspath(String path) throws IOException {
        InputStream s = FileIO.class.getClassLoader().getResourceAsStream(path);
        InputStreamReader r = new InputStreamReader(s);
        return readEOLNormalizedTextStream(r);
    }
    
    public static String localizeLineEndings(String original) throws IOException {
        String corrected = original.replaceAll("\r", "");
        return EOL.equals("\n") ? corrected : corrected.replaceAll("\n", EOL);
    }
    
    // Replaces backslashes in path with forward slashes.
    // Note that backslashes occur in Windows paths but Java can use forward slashes on Windows.
    public static String normalizePathSeparator(String path) {        
        return path.replace('\\', '/');
    }
    
    // reads a file from the filesystem, replacing stored EOL with local EOL sequence
    public static String readEOLNormalizedTextFile(String path) throws IOException {
        InputStream reader =  new FileInputStream(path);
        return readEOLNormalizedTextStream(new InputStreamReader(reader));
    } 

    // reads a file from input stream line by line, replacing stored EOL with local EOL sequence
    public static String readEOLNormalizedTextStream(InputStreamReader fileReader) throws IOException {
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) stringBuilder.append(line).append(EOL);
        return stringBuilder.toString();
    }
}
