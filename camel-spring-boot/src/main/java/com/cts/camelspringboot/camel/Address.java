package com.cts.camelspringboot.camel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	private String street;
	private String streetName;
	private String buildingNumber;
	private String city;
	private String zipcode;
	private String country;
	private String county_code;
	private float latitude;
	private float longitude;
	

	
//	"street": "66473 Rosendo Vista Suite 201",
//    "streetName": "Rhiannon Plain",
//    "buildingNumber": "8864",
//    "city": "Angiestad",
//    "zipcode": "25291-7641",
//    "country": "Indonesia",
//    "county_code": "LS",
//    "latitude": -64.83009,
//    "longitude": -146.096405
}
