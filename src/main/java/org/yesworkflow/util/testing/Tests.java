package org.yesworkflow.util.testing;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;

public abstract class Tests {
    
    public static final String EOL = System.getProperty("line.separator");

    protected OutputStream stdoutBuffer;
    protected OutputStream stderrBuffer;
    
    protected PrintStream stdoutStream;
    protected PrintStream stderrStream;
    
    protected static Path getTestDirectory(String testName) throws IOException {
        Path testDirectoryPath = new File("target/tests/" + testName).toPath();
        Files.createDirectories(testDirectoryPath);
        return testDirectoryPath;
    }
    

    @Before
    public void setUp() throws Exception {
        
        stdoutBuffer = new ByteArrayOutputStream();
        stdoutStream = new PrintStream(stdoutBuffer);
    
        stderrBuffer = new ByteArrayOutputStream();
        stderrStream = new PrintStream(stderrBuffer);
    }
    

}
