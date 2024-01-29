package com.homework.travel.service;

import org.springframework.validation.BindingResult;

import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.model.TravelItem;
import com.homework.travel.pojo.TravelItemView;

public interface TravelItemService {
	
	TravelItemView getItems(SeasonType season, Long distance);

	TravelItem create(TravelItem travelItem, BindingResult result);
	
	TravelItem update(TravelItem travelItem, BindingResult result);

	void delete(Long id, BindingResult result);
}
