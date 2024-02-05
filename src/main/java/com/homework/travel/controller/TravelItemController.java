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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.homework.travel.data.Item;
import com.homework.travel.data.TravelItemView;
import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.exception.InvalidDataException;
import com.homework.travel.model.TravelItem;
import com.homework.travel.service.TravelItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = "/api/travel-items", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody Item item, BindingResult result){
		log.debug("create travelItem: {}", item);
		TravelItem ti = travelService.create(item, result);
		if(result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		return ti.getId();
	}
	
	@PutMapping()
	public ResponseEntity<TravelItem> update(@RequestBody TravelItem travelItem, BindingResult result) {
		log.debug("update travelItem: {}", travelItem);
		TravelItem ti = null;
		HttpStatus httpStatus;
		if(travelService.get(travelItem.getId()) != null) {
			 ti = travelService.update(travelItem, result);
			 httpStatus = HttpStatus.OK;
		} else {
			 ti = travelService.create(travelItem, result);
			 httpStatus = HttpStatus.CREATED;
		}
		
		if(result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		return new ResponseEntity<TravelItem>(ti, httpStatus);
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
