package com.homework.travel.validator;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseValidator<T> implements Validator {

	protected final Logger log;
	protected final String dataType;
	
	protected abstract void internalValidate(T target, Errors errors);
	
	@Override
	@SuppressWarnings("unchecked")
	public void validate(Object target, Errors errors) {
		internalValidate((T) target, errors);
	}
	
	protected boolean required(String value, String field, Errors errors, String message) {
		if(StringUtils.isEmpty(value)) {
			log.debug("Validation failed: {}.{} is empty.", dataType, field);
			errors.rejectValue(field, message);
			return false;
		}
		return true;
	}
	
	protected boolean required(Long value, String field, Errors errors, String message) {
		if(value == null) {
			log.debug("Validation failed: {}.{} is empty.", dataType, field);
			errors.rejectValue(field, message);
			return false;
		}
		return true;
	}
	
	protected boolean required(Object value, String field, Errors errors, String message) {
		if(value == null) {
			log.debug("Validation failed: {}.{} is empty.", dataType, field);
			errors.rejectValue(field, message);
			return false;
		}
		return true;
	}
	
	protected boolean required(Object value, Errors errors, String message) {
		if(value == null) {
			log.debug("Validation failed: {} is empty.", dataType);
			errors.reject(message);
			return false;
		}
		return true;
	}
	
	protected boolean shouldBeNull(Long value, String field, Errors errors, String message) {
		if(value != null) {
			log.debug("Validation failed: {}.{} is not null.", dataType, field);
			errors.rejectValue(field, message);
			return false;
		}
		return true;
	}
	
	protected boolean requiredPositive(Long value, String field, Errors errors, String message) {
		if(value != null && value <= 0) {
			log.debug("Validation failed: {}.{} is negative or 0.", dataType, field);
			errors.rejectValue(field, message);
			return false;
		}
		return true;
	}
	
	protected boolean requiredPositive(BigDecimal value, String field, Errors errors, String message) {
		if(-1 == value.compareTo(BigDecimal.ZERO) || 0 == value.compareTo(BigDecimal.ZERO)) {
			log.debug("Validation failed: {}.{} is negative or 0.", dataType, field);
			errors.rejectValue(field, message);
			return false;
		}
		return true;
	}
	
	protected boolean shortenThan(String value, int length, String field, Errors errors, String message) {
		if(StringUtils.isNotEmpty(value) && value.length() > length) {
			log.debug("Validation failed: {}.{} is longer than {}", dataType, field, length);
			errors.rejectValue(field, message, new Object[] {length}, null);
			return false;
		}
		return true;
	}
}
