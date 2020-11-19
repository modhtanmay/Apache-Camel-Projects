package com.cts.application;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelHelloWorld {
	
	public static void main(String[] args) throws Exception {
		
		// we will use Camel core class i.e. CamelContext
		// It helps to integrate filters,processors,endpoints etc...
		
		CamelContext camel = new DefaultCamelContext();
		
		camel.addRoutes(new MyRouteBuilder());
		
		camel.start();
		
		Thread.sleep(2000);
	}
}
