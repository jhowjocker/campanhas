package br.com.campanhas.app.response;

public class BaseResponse {
	
	public int statusCode;
	public String message;
	
	
	
	public BaseResponse() {
		
	}
	public BaseResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	
	
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		statusCode = statusCode;
	}
		
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
