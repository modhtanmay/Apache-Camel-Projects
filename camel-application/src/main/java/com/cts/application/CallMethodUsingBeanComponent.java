package com.cts.application;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CallMethodUsingBeanComponent {

	public static void main(String[] args) throws Exception {
		
		
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("MyService", new MyService());
		CamelContext camel = new DefaultCamelContext(jndiContext);
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start").to("bean:MyService?method=display");
			}
		});
		
		camel.start();
		
		ProducerTemplate producer = camel.createProducerTemplate();
		producer.sendBody("direct:start", "Hello From Producer Bean Component");
	}
}
