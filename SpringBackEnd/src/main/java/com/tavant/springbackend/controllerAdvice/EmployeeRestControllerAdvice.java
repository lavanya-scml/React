package com.tavant.springbackend.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tavant.springbackend.errorresponse.ErrorResponse;
import com.tavant.springbackend.exception.EmployeeNotFound;

@ControllerAdvice
public class EmployeeRestControllerAdvice {
	@ExceptionHandler(EmployeeNotFound.class)
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFound e,WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT_REQUEST",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		
	}

}
