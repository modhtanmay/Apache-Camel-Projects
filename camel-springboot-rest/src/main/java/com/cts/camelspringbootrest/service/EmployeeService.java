package com.cts.camelspringbootrest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.cts.camelspringbootrest.model.Employee;

@Service
public class EmployeeService extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public SqlComponent getSqlComponent() {
		SqlComponent sql = new SqlComponent();
		sql.setDataSource(dataSource);
		return sql;
	}

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		// insert route
		from("direct:insert").log("Message is being processed....").setHeader("message", body())
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						Employee employee = exchange.getIn().getBody(Employee.class);

						Map<String, Object> empMap = new HashMap<>();
						empMap.put("id", employee.getId());
						empMap.put("name", employee.getName());

						exchange.getIn().setBody(empMap);
					}
				}).to("sql:INSERT INTO employee values(:#id,:#name)");

		// Select route
		from("direct:select").to("sql:select * from employee").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) exchange.getIn().getBody();
				List<Employee> employees = new ArrayList<Employee>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Employee employee = new Employee();
					employee.setId(data.get("id"));
					employee.setName(data.get("name"));
					employees.add(employee);
				}
				exchange.getIn().setBody(employees);
			}
		});
		
		// select by id
		from("direct:selectById").to("sql:select * from employee where id = "+":#id").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				Employee emp = exchange.getIn().getBody(Employee.class);
				
				exchange.getIn().setBody(emp);
				
			}
		});
	}

}
