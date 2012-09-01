import java.io.BufferedReader;
import java.io.File;

import javax.persistence.ValidationMode;

import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

public class Runner {

	public static void main(String[] args) throws Exception {
		
//        File configFile = new File ("c:\\myapp\\embeddedserver\\domains\\domain1\\config\\domain.xml");
        File war = new File("target/real-jee-web-1.0.0.war");
        GlassFish glassfish = null;
        GlassFishRuntime glassfishRuntime = null; 
        try {
            glassfishRuntime = GlassFishRuntime.bootstrap();

            GlassFishProperties glassfishProperties = new GlassFishProperties();
//            glassfishProperties.setConfigFileURI(configFile.toURI().toASCIIString());
            glassfishProperties.setConfigFileReadOnly(false);
            
            glassfish = glassfishRuntime.newGlassFish(glassfishProperties);
            glassfish.start();
            
            Deployer deployer = glassfish.getDeployer();
            deployer.deploy(war, "--force=true");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Press Enter to stop server");
        // wait for Enter
        new BufferedReader(new java.io.InputStreamReader(System.in)).readLine();
        try {
            glassfish.dispose();
            glassfishRuntime.shutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
