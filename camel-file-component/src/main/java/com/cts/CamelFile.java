package com.cts;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelFile {
	
	public static void main(String[] args) throws Exception {
		
		CamelContext camel = new DefaultCamelContext();
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
				// JAVA DSL
				from("file:inbox?noop=true") // dont delete file from inbox
					.to("file:outbox");
				
			}
		});
		
		while(true) {
			camel.start();
		}
	}
}
