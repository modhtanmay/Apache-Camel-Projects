package com.cts.application;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String originalFileName = exchange.getIn().getHeader(Exchange.FILE_NAME,String.class);
		System.out.println(originalFileName);
		
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		
		String changeFileName = sdf.format(date) + " " + originalFileName;
		
		System.out.println(changeFileName);
		exchange.getIn().setHeader(Exchange.FILE_NAME, changeFileName);
		
//		String message = exchange.getIn().getBody(String.class);
//		System.out.println(message);
//		exchange.getOut().setBody(message.toUpperCase());

	}

}
