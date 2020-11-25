package com.cts.camelspringbootjdbc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.camelspringbootjdbc.model.Address;

@Service
public class AddressService extends RouteBuilder{

	@Autowired
	DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		// direct:select
		from("direct:select")
		.setBody(constant("select * from address"))                         
		.to("jdbc:dataSource")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				ArrayList<Map<String,String>> data = (ArrayList<Map<String, String>>) exchange.getIn().getBody();
				List<Address> addresses = new ArrayList<Address>();
				System.out.println(addresses);
				for(Map<String,String> record : data) {
					Address address = new Address();
					address.setStreet(record.get("street"));
					address.setStreetName(record.get("streetName"));
					address.setBuildingNumber(record.get("buildingNumber"));
					address.setCity(record.get("city"));
					address.setZipcode(record.get("zipcode"));
					address.setCountry(record.get("country"));
					address.setCountryCode(record.get("countryCode"));
					
					addresses.add(address);
				}
				
				exchange.getIn().setBody(addresses);
			}
		});
		
		
		// direct:insert
		from("direct:insert")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				Address address = exchange.getIn().getBody(Address.class);
				String sql = "INSERT INTO address values('" + address.getStreet()+"','"+address.getStreetName()+"','"+address.getBuildingNumber()+"','"+address.getCity()+"','"+address.getZipcode()+"','"+address.getCountry()+"','"+address.getCountryCode()+"')";
				exchange.getIn().setBody(sql);
			}
		}).to("jdbc:dataSource");
		
		
		// direct:update
		from("direct:update")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				Address address = exchange.getIn().getBody(Address.class);
				String sql = "UPDATE address set street = '"+address.getStreet()+"' where countryCode = '"+address.getCountryCode()+"'";
				exchange.getIn().setBody(sql);
			}
		}).to("jdbc:dataSource");
		

		
		// direct:delete
		from("direct:delete")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				Address address = exchange.getIn().getBody(Address.class);
				String countryCode = "PK";
//				String sql = "DELETE from address where countryCode = '"+countryCode+"'";
				String sql = "DELETE from address where countryCode = '"+address.getCountryCode()+"'";
				exchange.getIn().setBody(sql);
			}
		});
		
		
	}

}
