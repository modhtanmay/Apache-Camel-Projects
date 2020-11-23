package com.cts.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class CamelDatabase {

	public static void main(String[] args) throws Exception {
		
		
		MysqlDataSource datasource = new MysqlDataSource();
		
		datasource.setURL("jdbc:mysql://localhost:3306/cameldb");
		datasource.setUser("root");
		datasource.setPassword("modhtanu");
		
		SimpleRegistry registry = new SimpleRegistry();
		registry.bind("datasource",MysqlDataSource.class,datasource);
		
		CamelContext camel = new DefaultCamelContext(registry);
		
		camel.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
				from("direct:start")
				.to("jdbc:datasource")
				.bean(new ResultHandler(),"displayResult");
				
			}
		});
		
		camel.start();
		
		ProducerTemplate produTemplate = camel.createProducerTemplate();
		produTemplate.sendBody("direct:start","select * from customers");
		
	}
}
