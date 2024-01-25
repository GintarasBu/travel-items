package com.homework.travel.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
	private String field;
	private String code;
	private String resource;
	private String message;
}
