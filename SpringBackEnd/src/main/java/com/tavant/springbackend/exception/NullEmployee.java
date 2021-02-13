package com.tavant.springbackend.exception;

public class NullEmployee extends Exception {

	public NullEmployee(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}

	@Override
	public String toString() {
		return super.toString()+this.getMessage();
	}
	
	
}
