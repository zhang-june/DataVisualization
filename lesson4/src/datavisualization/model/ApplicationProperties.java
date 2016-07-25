package datavisualization.model;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationProperties {
	/** Singleton instance. */
	static ApplicationProperties inst; 
	
	Properties props;
	
	public Properties getProps() {
		return props;
	}

	/** Locked down constructor outside package */   
	ApplicationProperties() {
		props = new Properties();

		try {
			FileInputStream fis = new FileInputStream("application.properties");
			props.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}        

	public static ApplicationProperties instance() {
		if (inst == null) {
			inst = new ApplicationProperties();
	    }
	    return inst;
	}
}
