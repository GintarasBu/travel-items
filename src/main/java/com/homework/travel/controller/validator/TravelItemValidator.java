package com.homework.travel.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.homework.travel.model.TravelItem;

@Component
public class TravelItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TravelItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TravelItem item = (TravelItem) target;
		if(item == null) {
			errors.reject("errors.travel_item.null");
		} else if(item.getDistance() == null) {
			errors.reject("errors.travel_item.distance.null");
		} else if(item.getDistance() < 0) {
			errors.reject("errors.travel_item.distance.negative");
		} else if(item.getQuantity() == null) {
			errors.reject("errors.travel_item.quantity.null");
		} else if(item.getQuantity() < 0) {
			errors.reject("errors.travel_item.quantity.negative");
		} else if(item.getSeason() == null) {
			errors.reject("errors.travel_item.season.null");
		} else if(item.getTitle() == null) {
			errors.reject("errors.travel_item.title.null");
		} else if(item.getWeight() == null) {
			errors.reject("errors.travel_item.weight.null");
		} else if(-1 == item.getWeight().compareTo(BigDecimal.ZERO))  {
			errors.reject("errors.travel_item.weight.negative");
		} else if(item.getId() != null) {
			errors.reject("errors.travel_item.id.possible_overide", new String[] {Long.toString(item.getId())}, null);
		}
		
	}

}
