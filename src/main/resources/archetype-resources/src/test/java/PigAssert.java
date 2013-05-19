#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package};

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

public class PigAssert {

    private static Comparator<File> fileNamesComparator() {
        return new Comparator<File>() {
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    public static void assertOutputEquals(File etalonDir, File actualDir) {
        File[] actualChildren = actualDir.listFiles();
        File[] etalonChildren = etalonDir.listFiles();


        Assert.assertEquals(actualChildren.length, etalonChildren.length,
                "Etalon and actual dirs has different ammount of files");

        final Comparator<File> fileNamesComparator = fileNamesComparator();
        Arrays.sort(etalonChildren, fileNamesComparator);
        Arrays.sort(actualChildren, fileNamesComparator);

        try {
            for (int i = 0; i < etalonChildren.length; i++) {
                File actualChild = actualChildren[i];
                File etalonChild = etalonChildren[i];
                Assert.assertEquals(FileUtils.readFileToString(actualChild), FileUtils.readFileToString(etalonChild));
            }
        } catch (IOException ex) {
            
        }
    }
}
