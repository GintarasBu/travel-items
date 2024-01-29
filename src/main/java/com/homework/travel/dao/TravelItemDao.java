package com.homework.travel.dao;

import java.util.List;

import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.model.TravelItem;

public interface TravelItemDao extends BasicDao<TravelItem> {
	
	List<TravelItem> list(SeasonType season, long distance);

	List<TravelItem> list();
	
	TravelItem findById(Long id);
	
	void deleteById(Long id);

}
