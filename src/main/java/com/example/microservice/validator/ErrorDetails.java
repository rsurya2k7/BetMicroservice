package com.example.microservice.validator;

public class ErrorDetails {
	String message;
	
	public ErrorDetails(){
		
	}
	
	public ErrorDetails(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
}
