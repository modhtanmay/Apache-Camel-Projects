package com.cts.camelspringboot;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("file:H:/c-source?noop=true")
			.process(new FileProcessor())
			.to("file:H:/c-destination?fileName=emp.json");
	}

	
}
