package com.cts.camel;

import java.util.List;

public class ResultHandler {

	public void displayResult(List list) {
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
	}
}
