package org.hc.first;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.eclipse.jetty.util.Loader;
import org.hc.jp.Constants;

public class Main {
	private WebServer server;
	private Properties properties;
	
	public static void main(String[] args) throws Exception{
		initializeJettyLogger();
		new Main().start();
		
		
		}
	
	public Main(){
		properties = new Properties();
	}
	public void start() throws Exception{
		// TODO Auto-generated method stub
		loadProperties();
		server = new WebServer(getWebserverPort());
		server.start();
		server.join();
		
	}
	
	private int getWebserverPort() {
		String val = properties.getProperty(Constants.PROPERTY_KEY_SERVER_PORT, String.valueOf(Constants.DEFAULT_SERVER_PORT));
	//	String val = properties.getProperty(Constants.PROPERTY_KEY_SERVER_PORT, String.valueOf(Constants.DEFAULT_SERVER_PORT));
		
		try{
			return Integer.parseInt(val);
				}
		catch(NumberFormatException e){
			return Constants.DEFAULT_SERVER_PORT;
		}
	}

	private void loadProperties() throws Exception {
		File propFile = getPropertyFile();
		FileInputStream fis = new FileInputStream(propFile);
		properties.load(fis);
		fis.close();
	}

	private File getPropertyFile() throws Exception {
		String propertyFilePath = Constants.PROPERTY_FILE_PATH;
		File propFile = new File(propertyFilePath);
		if(!propFile.exists()){
			propertyFilePath = propertyFilePath.replaceAll("\\\\", "/");
			propFile = new File(propertyFilePath);
		}
		if(!propFile.exists()){
			throw new Exception("No Property file [" + propFile.getAbsolutePath()+"]");
		}
		return    propFile;
		
	}
	
	/**
	 * Этот метод нужен, т.к. без него jetty не подхватывает Slf4J
	 * @throws Exception 
	 */
	private static void initializeJettyLogger() throws Exception		
	{	
		//initialize class and static block into class
		org.eclipse.jetty.util.log.Log.setLog(null);
		
		//load log_class
		Class<?> log_class = Loader.loadClass(org.eclipse.jetty.util.log.Log.class, org.eclipse.jetty.util.log.Log.__logClass);
		org.eclipse.jetty.util.log.Logger LOG = (org.eclipse.jetty.util.log.Logger)log_class.newInstance();
		LOG.debug("Logging to {} via {}", LOG, log_class.getName());
		org.eclipse.jetty.util.log.Log.setLog(LOG);
	}
	
	
}
