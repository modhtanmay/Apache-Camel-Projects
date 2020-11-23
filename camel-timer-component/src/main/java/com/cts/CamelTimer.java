package com.cts;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.util.jndi.JndiContext;

public class CamelTimer {

	public static void main(String[] args) throws Exception {

//		JndiContext jndiContext = new JndiContext();
//		jndiContext.bind("service", new MyService());

		SimpleRegistry registry = new SimpleRegistry();
		registry.put("service", new MyService());

		CamelContext camel = new DefaultCamelContext(registry);

		camel.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("timer:myTimer?period=1000") // here from("a:b") a is component , b is endPoint
						.setBody(simple("Hello from Camel at ${header.firedTime}")).to("stream:out")
						.to("bean:service?method=display");
//						.bean(new MyService(),"say Hello");   // another way for using bean
			}
		});

		camel.start();

		Thread.sleep(5000);

		camel.stop();
	}
}
