package org.pigarchetype.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.FileUtils;

/**
 *
 * @goal pig-init
 */
public class InitMojo extends AbstractMojo
{
    private static final String PROPERTIES_FILE_NAME = "initial.properties";
    /**
     * @parameter expression="${project.basedir}"
     */
    private File basedir;


    public void execute() throws MojoExecutionException, MojoFailureException
    {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(basedir, PROPERTIES_FILE_NAME)));

            File destScript = new File(basedir, "scripts/scriptToTest.pig");
            File destInput = new File(basedir, "scripts/input.data");
            File destOutput = new File(basedir, "scripts/expected-output");
            FileUtils.copyFile(new File(properties.getProperty("script")), destScript);
            FileUtils.copyFile(new File(properties.getProperty("input")), destInput);
            FileUtils.copyDirectory(new File(properties.getProperty("output")), destOutput);
        } catch (IOException ex) {
            throw new MojoExecutionException("initialisation failed", ex);
        }
    }
}