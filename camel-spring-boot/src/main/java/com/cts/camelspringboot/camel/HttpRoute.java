package com.cts.camelspringboot.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class HttpRoute extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		JacksonDataFormat addressDataFormat = new JacksonDataFormat(AddressList.class);
		addressDataFormat.setPrettyPrint(true);
		
		from("direct:http")
		.log(LoggingLevel.INFO,this.log, "This will be second message")
		.to("https://fakerapi.it/api/v1/addresses?_quantity=1")
		.unmarshal(addressDataFormat)
		.log(LoggingLevel.INFO,this.log,"Deserialized JSON: ${body}")
		.marshal(addressDataFormat)
		.log(LoggingLevel.INFO,this.log,"Serialized JSON : ${body}");
	}
}
