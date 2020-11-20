package com.cts.application;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CallMethodUsingClassComponent {

	public static void main(String[] args) throws Exception {
		
		CamelContext camel = new DefaultCamelContext();
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				// JAVA DSL LANGUAGE
				
				from("direct:abc").to("class:com.cts.application.MyService?method=display");
			}
		});
		
		camel.start();
		
		ProducerTemplate producer = camel.createProducerTemplate();
		producer.sendBody("direct:abc", "Hello from Producer Class Component");
	}
}
