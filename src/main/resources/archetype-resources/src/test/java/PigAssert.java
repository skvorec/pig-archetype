#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package};

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import org.testng.Assert;

public class PigAssert
{
    private static Comparator<File> fileNamesComparator()
    {
        return new Comparator<File>()
        {
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }


    public static void assertFileContentEquals(File etalonFile, File actualFile)
    {
        BufferedReader etalonBr = null;
        BufferedReader actualBr = null;
        try {
            etalonBr = new BufferedReader(new FileReader(etalonFile));
            actualBr = new BufferedReader(new FileReader(actualFile));
            int lineNumber = 0;
            String etalonLine;
            String actualLine;
            while ((etalonLine = etalonBr.readLine()) != null) {
                lineNumber++;
                actualLine = actualBr.readLine();
                if (actualLine == null) {
                    throw new AssertionError("Files " + etalonFile.getName() + " are not equals, etalon file has more raws!");
                }
                if (!etalonLine.equals(actualLine)) {
                    throw new AssertionError("Files " + etalonFile.getName() + " differs at line " + lineNumber);
                }
            }
            actualLine = actualBr.readLine();
            if (actualLine != null) {
                throw new AssertionError("Files " + etalonFile.getName() + " are not equals, actual file has more raws!");
            }

        } catch (IOException ex) {
            throw new AssertionError(ex.getMessage());
        } finally {
            try {
                etalonBr.close();
                actualBr.close();
            } catch (IOException ex) {
                throw new AssertionError(ex.getMessage());
            }
        }

    }


    public static void assertOutputEquals(File etalonDir, File actualDir)
    {
        File[] actualChildren = actualDir.listFiles();
        File[] etalonChildren = etalonDir.listFiles();


        Assert.assertEquals(actualChildren.length, etalonChildren.length,
                "Etalon and actual dirs has different ammount of files");

        final Comparator<File> fileNamesComparator = fileNamesComparator();
        Arrays.sort(etalonChildren, fileNamesComparator);
        Arrays.sort(actualChildren, fileNamesComparator);

        for (int i = 0; i < etalonChildren.length; i++) {
            assertFileContentEquals(etalonChildren[i], actualChildren[i]);
        }
    }
}

