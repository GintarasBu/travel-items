package com.homework.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.exception.InvalidDataException;
import com.homework.travel.model.TravelItem;
import com.homework.travel.pojo.TravelItemView;
import com.homework.travel.service.TravelItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/travel-items")
@RestController
public class TravelItemController {
	
	@Autowired
	private TravelItemService travelService;

	@GetMapping(value = "/{season}/{distance}")
	public TravelItemView getItems(@PathVariable("season") SeasonType seasonType, @PathVariable Long distance){
		log.debug("getItems. season: {}, distance: {}", seasonType, distance);
		TravelItemView travelItemView = travelService.getItems(seasonType, distance);
		return travelItemView;
	}
	
	@PostMapping()
	public Long create(@RequestBody TravelItem travelItem, BindingResult result){
		log.debug("create travelItem: {}", travelItem);
		TravelItem ti = travelService.create(travelItem, result);
		if(result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		return ti.getId();
	}
	
	@PutMapping()
	public TravelItem update(@RequestBody TravelItem travelItem, BindingResult result) {
		log.debug("update travelItem: {}", travelItem);
		TravelItem ti = travelService.update(travelItem, result);
		if(result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		return ti;
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		log.debug("delete travelItem id: {}", id);
		BindingResult result = new BeanPropertyBindingResult(new TravelItem(), "id");
		travelService.delete(id, result);
		if(result.hasErrors()) {
			throw new InvalidDataException(result);
		}
	}
}
