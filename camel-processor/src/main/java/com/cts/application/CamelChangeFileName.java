package com.cts.application;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelChangeFileName {

	public static void main(String[] args) throws Exception {

		CamelContext camel = new DefaultCamelContext();

		camel.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("file:inbox").process(new FileProcessor()).to("file:outbox");
			}
		});

		while (true) {
			camel.start();
		}
	}
}
