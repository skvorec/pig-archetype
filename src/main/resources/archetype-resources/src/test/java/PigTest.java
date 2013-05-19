#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package};

import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PigTest {

    private static final File DEST_SCRIPT = new File("scripts/scriptToTest.pig");
    private static final File DEST_DATA = new File("scripts/input.data");
    private static final File ACTUAL_OUTPUT_DIR = new File("target/pig-output");
    

    private Map<String, String> getPredefinedParams() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("data", DEST_DATA.getAbsolutePath().replaceAll("\\\\", "/"));
        result.put("output", ACTUAL_OUTPUT_DIR.getAbsolutePath().replaceAll("\\\\", "/"));
        return result;
    }

    //Please delete this init method after the first run
    @BeforeClass
    public void firstRunInit() throws IOException {
        FileUtils.copyFile(new File(${script}), DEST_SCRIPT);
        FileUtils.copyFile(new File(${input}), DEST_DATA);
    }

    @Test
    public void pigTest() throws ExecException, IOException {

        PigServer pigServer = new PigServer(ExecType.LOCAL);
        final Map<String, String> predefinedParams = getPredefinedParams();
        pigServer.setBatchOn();
        pigServer.debugOn();
        pigServer.registerScript(DEST_SCRIPT.getAbsolutePath(), predefinedParams);
        pigServer.executeBatch();
        pigServer.shutdown();

        PigAssert.assertOutputEquals(null, new File("target/pig-output"));
    }
}
