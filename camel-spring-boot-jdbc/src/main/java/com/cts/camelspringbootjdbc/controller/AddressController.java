package com.cts.camelspringbootjdbc.controller;

import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.camelspringbootjdbc.model.Address;
import com.cts.camelspringbootjdbc.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	ProducerTemplate producerTemplate;
	
	@GetMapping("/addresses")
	public List<Address> getAllAddresses(){
		List<Address> addresses = producerTemplate.requestBody("direct:select",null,List.class);
		return addresses;
	}

	@PostMapping("/addresses")
	public String insertAddress(@RequestBody Address addr) {
		producerTemplate.requestBody("direct:insert",addr,List.class);
		return "Address Inserted";
	}
	
	@PutMapping("/addresses")
	public String updateAddress(@RequestBody Address addr) {
		producerTemplate.requestBody("direct:update",addr,List.class);
		return "Address Updated";
	}
	
	@DeleteMapping("/addresses/{countryCode}")
	public String deleteAddress(@PathVariable String countryCode,@RequestBody Address addr) {
		producerTemplate.requestBody("direct:delete",addr,List.class);
		return "Address Deleted";
	}
}
