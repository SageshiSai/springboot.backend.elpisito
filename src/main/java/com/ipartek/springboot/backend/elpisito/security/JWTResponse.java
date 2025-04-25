package com.ipartek.springboot.backend.apirest.securiy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {
	
	private String jwt;
	private String message;
	private String messageTitle;

}
