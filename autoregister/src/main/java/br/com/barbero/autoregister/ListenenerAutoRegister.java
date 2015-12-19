package br.com.barbero.autoregister;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.jar.Manifest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ListenenerAutoRegister
 *
 */
public class ListenenerAutoRegister implements ServletContextListener {


	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		  try {
			InetAddress ip = InetAddress.getLocalHost();
			
			System.out.println("Current IP address : " + ip.getHostAddress());
			
			Enumeration<URL> resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
					while (resources.hasMoreElements()) {
					    try {
					      Manifest manifest = new Manifest(resources.nextElement().openStream());
					      
					      java.util.Map<String, java.util.jar.Attributes> entries = manifest.getEntries();
					      java.util.Iterator<String> it = entries.keySet().iterator();
					      while (it.hasNext()){
					          String key = it.next();
					          printAttributes(entries.get(key));
					          System.out.println();
					      }
					      
					    } catch (IOException E) {
					      // handle
					    }
					}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private  void printAttributes(java.util.jar.Attributes attributes){
	    java.util.Iterator it = attributes.keySet().iterator();
	    while (it.hasNext()){
	        java.util.jar.Attributes.Name key = (java.util.jar.Attributes.Name) it.next();
	        Object value = attributes.get(key);
	        System.out.println(key + ":  " + value);
	    }
	}
	
}
