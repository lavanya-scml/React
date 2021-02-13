package com.tavant.springbackend.exception;

public class NoDataFoundException extends Exception {

	public NoDataFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
		
	}

	@Override
	public String toString() {
		return super.toString()+this.getMessage();
	}
	
	
}
