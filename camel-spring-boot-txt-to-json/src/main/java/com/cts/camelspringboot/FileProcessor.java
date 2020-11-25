package com.cts.camelspringboot;

import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String message = exchange.getIn().getBody(String.class);
		
		
		StringTokenizer str = new StringTokenizer(message, ",");
		
		String eid = str.nextToken();
		String ename = str.nextToken();
		String esal = str.nextToken();
		
		String outMessage = "{eid:"+eid+",ename:"+ename+",esal:"+esal+"}";
		
		exchange.getIn().setBody(outMessage);
	}

}
