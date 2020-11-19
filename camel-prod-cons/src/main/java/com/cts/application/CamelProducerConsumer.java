package com.cts.application;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelProducerConsumer {

	/*
	 * A Producer will produce some data and it will send to direct endpoint. From
	 * direct endpoint, seda component with consume autmatically and From seda
	 * component , our consumer will consume the data finally.
	 * 
	 * Direct is Synchronous and Seda is Asynchronous
	 */	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		CamelContext camel = new DefaultCamelContext();
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
				from("direct:start").to("seda:end");
				
			}
		});
		
		camel.start();
		
		ProducerTemplate producer = camel.createProducerTemplate();
		
		producer.sendBody("direct:start", "Hello from Apache Camel using Direct Component");
		
		//Consumer Component witll consume from seda component.
		ConsumerTemplate consumer = camel.createConsumerTemplate();
		
		String receivedMessage = consumer.receiveBody("seda:end", String.class);
		
		System.out.println(receivedMessage);
		
		
	}

}
