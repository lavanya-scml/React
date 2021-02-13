package com.tavant.springbackend.exception;

public class EmployeeNotFound extends Exception {

	public EmployeeNotFound(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.toString()+this.getMessage();
	}

	
}
