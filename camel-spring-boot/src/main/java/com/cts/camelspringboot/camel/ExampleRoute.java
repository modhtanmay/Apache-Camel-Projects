package com.cts.camelspringboot.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExampleRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer:foo?period=50000")
		.routeId("Main Camel Route")
		.log(LoggingLevel.INFO,this.log,"This will be first message ever")
		.to("direct:http");
	}
}
