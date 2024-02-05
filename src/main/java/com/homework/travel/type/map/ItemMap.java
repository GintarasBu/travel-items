package com.homework.travel.type.map;

import org.modelmapper.PropertyMap;

import com.homework.travel.data.Item;
import com.homework.travel.model.TravelItem;

public class ItemMap extends PropertyMap<Item, TravelItem> {

	@Override
	protected void configure() {
		
		map().setTitle(source.getTitle());
		map().setWeight(source.getWeight());
		map().setQuantity(source.getQuantity());
		map().setDistance(source.getDistance());
		map().setSeason(source.getSeason());
	}

}
