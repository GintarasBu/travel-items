package com.homework.travel.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import com.homework.travel.dao.TravelItemDao;
import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.model.TravelItem;
import com.homework.travel.pojo.ItemView;
import com.homework.travel.pojo.TravelItemView;
import com.homework.travel.service.TravelItemService;
import com.homework.travel.validator.TravelItemValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TravelItemServiceImpl implements TravelItemService {
	
	@Autowired
	private TravelItemDao travelItemDao;
	
	@Autowired
	private TravelItemValidator travelItemValidator; 
	
	@Override
	public TravelItemView getItems(SeasonType season, Long distance) {
		try {
			List<TravelItem> items = travelItemDao.list(season, distance);
			if (!CollectionUtils.isEmpty(items)) {
				TravelItemView travelItemView = new TravelItemView();

				Function<TravelItem, BigDecimal> itemWeight = item -> item.getWeight()
						.multiply(BigDecimal.valueOf(item.getQuantity()));

				List<ItemView> aggregatedItems = items.stream()
						.collect(Collectors.groupingBy(TravelItem::getTitle, Collectors.toList()))
						.entrySet().stream()
						.map(entry -> new ItemView(entry.getKey(),
								entry.getValue().stream().map(m -> m.getQuantity()).reduce(0L, Long::sum),
								entry.getValue().stream().map(itemWeight).reduce(BigDecimal.ZERO, BigDecimal::add)))
						.collect(Collectors.toList());

				Long itemsCount = aggregatedItems.stream().map(m -> m.getQuantity()).reduce(0L, Long::sum);

				travelItemView.setTravelItemList(aggregatedItems);
				travelItemView.setIntemsCount(itemsCount.intValue());
				BigDecimal totalWeight = items.stream().map(i -> i.getWeight()).reduce(BigDecimal.ZERO, BigDecimal::add);
				travelItemView.setTotalWeight(totalWeight);
				return travelItemView;
			}
		} catch (Throwable e) {
			log.error("getItems.", e);
		}

		return null;
	}

	@Override
	public TravelItem create(TravelItem travelItem, BindingResult result) {
		try {
			travelItemValidator.validate(travelItem, result);
			if(!result.hasErrors()) {
				travelItemDao.create(travelItem);
			}
		} catch(Throwable e) {
			log.error("Error on creating travelItem", e);
		}
		return travelItem;
	}

	@Override
	public TravelItem update(TravelItem travelItem, BindingResult result) {
		try {
			travelItemValidator.validateUpdate(travelItem, result);
			if(!result.hasErrors()) {
				travelItem = travelItemDao.update(travelItem);
			}
		} catch (Throwable e) {
			log.error("Error on updating travelItem", e);
		}
		
		return travelItem;
	}

	@Override
	public void delete(Long id, BindingResult result) {
		try {
			travelItemValidator.validateDelete(id, result);
			if(!result.hasErrors()) {
				travelItemDao.deleteById(id);
			}
		} catch(Throwable e) {
			log.error("Error on deleting travelItem with id: {}", id, e);
		}
		
	}

}
