#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package};

import java.io.IOException;
import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;

public class App {
    
    public static void main(String[] args) throws ExecException, IOException {
        PigServer pigServer = new PigServer(ExecType.LOCAL);
    }
}
