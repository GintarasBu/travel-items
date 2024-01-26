package com.homework.travel.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

	@ExceptionHandler({InvalidDataException.class})
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		InvalidDataException ide = (InvalidDataException) e;
		List<FieldError> fieldErrors = ide.getErrors().getFieldErrors();
		List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();
		
		for(FieldError fieldError: fieldErrors) {
			FieldErrorResource fer = new FieldErrorResource();
			fer.setField(fieldError.getField());
			fer.setResource(fieldError.getObjectName());
			fer.setCode(fieldError.getCode());
			fer.setMessage(messageSourceAccessor.getMessage(fieldError.getCode(), fieldError.getArguments()));
			fieldErrorResources.add(fer);
		}
		
		List<ObjectError> globalErrorList = ide.getErrors().getGlobalErrors();
		StringBuilder sb = new StringBuilder();
		for(ObjectError globalError: globalErrorList) {
			if(StringUtils.hasLength(globalError.getCode())) {
				if(sb.length() > 0) {
					sb.append("\n");
				}
				sb.append(messageSourceAccessor.getMessage(globalError.getCode(), globalError.getArguments()));
			} else {
				sb.append(globalError.getDefaultMessage());
			}
		}
		ErrorResource error = new ErrorResource(HttpStatus.UNPROCESSABLE_ENTITY.value(), ide.getMessage());
		error.setFieldErrors(fieldErrorResources);
		if(StringUtils.hasLength(error.getMessage())) {
			error.setMessage(error.getMessage() + "\n" + sb.toString());
		} else {
			error.setMessage(sb.toString());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
}
