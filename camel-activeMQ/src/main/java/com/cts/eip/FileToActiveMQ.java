package com.cts.eip;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToActiveMQ {

	public static void main(String[] args) throws Exception {
		
		CamelContext camel = new DefaultCamelContext();
		
		ConnectionFactory factory = new ActiveMQConnectionFactory();
		
		camel.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(factory));
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("file:inbox?noop=true")				
					.to("activemq:queue:my_queue");
			}
		});
		
		while(true) {
			camel.start();
		}
	}
}
