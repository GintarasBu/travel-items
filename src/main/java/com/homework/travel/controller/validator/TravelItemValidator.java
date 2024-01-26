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
		if (item == null) {
			errors.reject("errors.travel_item.null");
			return;
		}
		
		if (item.getDistance() == null) {
			errors.rejectValue("distance", "errors.travel_item.distance.null");
		} else if (item.getDistance() < 0) {
			errors.rejectValue("distance", "errors.travel_item.distance.negative");
		}
		
		if (item.getQuantity() == null) {
			errors.rejectValue("quantity", "errors.travel_item.quantity.null");
		} else if (item.getQuantity() < 0) {
			errors.rejectValue("quantity", "errors.travel_item.quantity.negative");
		}

		if (item.getSeason() == null) {
			errors.rejectValue("season", "errors.travel_item.season.null");
		}
		
		if (item.getTitle() == null) {
			errors.rejectValue("title", "errors.travel_item.title.null");
		}
		
		if (item.getWeight() == null) {
			errors.rejectValue("weight", "errors.travel_item.weight.null");
		} else if (-1 == item.getWeight().compareTo(BigDecimal.ZERO)) {
			errors.rejectValue("weight", "errors.travel_item.weight.negative");
		}
		
		if (item.getId() != null) {
			errors.rejectValue("id", "errors.travel_item.id.possible_overide", new Object[] { item.getId()}, null );
		}
	}

}
