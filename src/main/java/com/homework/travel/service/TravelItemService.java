package com.homework.travel.service;

import org.springframework.validation.BindingResult;

import com.homework.travel.data.Item;
import com.homework.travel.data.TravelItemView;
import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.model.TravelItem;

public interface TravelItemService {
	
	TravelItem get(Long id);
	
	TravelItemView getItems(SeasonType season, Long distance);

	TravelItem create(Item travelItem, BindingResult result);
	
	TravelItem create(TravelItem travelItem, BindingResult result);
	
	TravelItem update(TravelItem travelItem, BindingResult result);

	void delete(Long id, BindingResult result);

}
