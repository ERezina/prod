package org.hc.first;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer {
	private static final String PATH_TO_WEBAPP = "webapp";
	
	private Server server;
	private static int port;
	private WebAppContext webappContext;
	
	WebServer(int aPort){
		port = aPort;
	}
	
	public void start() throws Exception{
		server = new Server(port);
		server.setHandler(createHandlers());
		server.setStopAtShutdown(true);
		
		System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");
		
		File jstlTaglibsFile = new File("lib/jstl-1.2.jar");
		File springFormTaglibsFile = new File("lib/spring-webmvc-3.2.6.RELEASE.jar");
		
		List<URL> urlTaglibList = new ArrayList<URL>();
		if (jstlTaglibsFile.exists())
			urlTaglibList.add(jstlTaglibsFile.toURI().toURL());
		if (springFormTaglibsFile.exists())
			urlTaglibList.add(springFormTaglibsFile.toURI().toURL());
		
		if(!urlTaglibList.isEmpty()){
			ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
			URLClassLoader newClassLoader = new URLClassLoader(urlTaglibList.toArray(new URL[0]),currentClassLoader);
			Thread.currentThread().setContextClassLoader(newClassLoader);
		}
		server.start();
		
	}
	
	public void join() throws InterruptedException{
		server.join();
	}
	
	public void stop() throws Exception{
		server.stop();
	}
	
	private HandlerCollection createHandlers() {
		webappContext = new WebAppContext();
		webappContext.setContextPath("/");
		webappContext.setWar(PATH_TO_WEBAPP);
		
		HandlerCollection hendlers = new HandlerCollection();
		hendlers.setHandlers(new Handler[]{webappContext});
		return hendlers;
	}
}
