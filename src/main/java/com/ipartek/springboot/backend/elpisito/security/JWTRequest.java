package com.ipartek.springboot.backend.apirest.securiy;

import lombok.Data;

@Data
public class JWTRequest {
	
	//Esta clase se encarga de recoger en formato Java los datos que 
	//llegan desde el request de autorización
	
	
	private String username;//en realidad es el email
	private String password;
	
	
	

}
