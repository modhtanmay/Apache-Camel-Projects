package com.cts.eip;

import java.time.LocalDateTime;
import java.util.Date;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ObjectToActiveMQ {

	public static void main(String[] args) throws Exception {
		
		CamelContext camel = new DefaultCamelContext();
		
		ConnectionFactory factory = new ActiveMQConnectionFactory();
		
		camel.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(factory));
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:abc")				
					.to("activemq:queue:my_queue");
			}
		});
			camel.start();
			
			ProducerTemplate producer = camel.createProducerTemplate();
			producer.sendBody("direct:abc",new String("In ActiveMQ"));
	}
}
