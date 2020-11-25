package com.cts.camelspringboot.camel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressList {
	
	private String status;
	private int code;
	private int total;
	private List<Address> data;

}
