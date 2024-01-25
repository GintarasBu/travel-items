package com.homework.travel.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {

	private int code;
	private String message;
	private List<FieldErrorResource> fieldErrors;
	
	public ErrorResource(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
