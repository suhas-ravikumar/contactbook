package com.assignment.contactservice.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String errorMessage;
    
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    

}
