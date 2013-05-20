package org.pigarchetype;

import java.io.File;
import org.testng.annotations.Test;

/**
 *
 */
public class PigAssertTest
{
    @Test
    public void assertFileContentEquals()
    {
        File inputFile = new File("test-data/input.txt");
        File copiedFile = new File("test-data/input_copy.txt");
        PigAssert.assertFileContentEquals(copiedFile, inputFile);
    }


    @Test(expectedExceptions = {AssertionError.class})
    public void assertFileContentEquals_FilesModified()
    {
        File inputFile = new File("test-data/input.txt");
        File copiedFile = new File("test-data/input_modify.txt");
        PigAssert.assertFileContentEquals(copiedFile, inputFile);
    }


    @Test(expectedExceptions = {AssertionError.class})
    public void assertFileContentEquals_ExpectedTrimmed()
    {
        File inputFile = new File("test-data/input.txt");
        File copiedFile = new File("test-data/input_trim.txt");
        PigAssert.assertFileContentEquals(copiedFile, inputFile);
    }


    @Test(expectedExceptions = {AssertionError.class})
    public void assertFileContentEquals_ActualTrimmed()
    {
        File inputFile = new File("test-data/input.txt");
        File copiedFile = new File("test-data/input_trim.txt");
        PigAssert.assertFileContentEquals(inputFile, copiedFile);
    }
    
    
    @Test
    public void assertOutputEquals(){
        File etalonDir = new File("test-data/expected");
        File actualDir = new File("test-data/actual");
        PigAssert.assertOutputEquals(etalonDir, actualDir);
    }
}
