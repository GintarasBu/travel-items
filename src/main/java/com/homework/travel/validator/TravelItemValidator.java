package com.homework.travel.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import com.homework.travel.dao.TravelItemDao;
import com.homework.travel.model.TravelItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TravelItemValidator extends BaseValidator<TravelItem> {

	@Value("${travelItem.title.max.length:100}")
	private int titleLength;
	
	@Autowired
	private TravelItemDao travelItemDao;
	
	public TravelItemValidator() {
		super(log, "travelItem");
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return TravelItem.class.isAssignableFrom(clazz);
	}

	@Override
	protected void internalValidate(TravelItem item, Errors errors) {
		if(!required(item, errors, "errors.travel_item.null")) {
			return;
		}
		shouldBeNull(item.getId(), "id", errors, "errors.travel_item.id.possible_overide");
		validateFields(item, errors);
	}
	
	public void validateUpdate(TravelItem item, Errors errors) {
		if(!required(item, errors, "errors.travel_item.null")) {
			return;
		}
		entityRequired(item.getId(), errors);
		validateFields(item, errors);
	}
	
	public void validateDelete(Long id, Errors errors) {
		entityRequired(id, errors);
	}
	
	private void validateFields(TravelItem item, Errors errors){
		if(required(item.getDistance(), "distance", errors, "errors.travel_item.distance.null")) {
			requiredPositive(item.getDistance(), "distance", errors, "errors.travel_item.distance.negative");
		}
		if(required(item.getQuantity(), "quantity", errors, "errors.travel_item.quantity.null")) {
			requiredPositive(item.getQuantity(), "quantity", errors, "errors.travel_item.quantity.negative");
		}
		required(item.getSeason(), "season", errors, "errors.travel_item.season.null");
		
		if(required(item.getTitle(), "title", errors, "errors.travel_item.title.null")) {
			shortenThan(item.getTitle(), titleLength, "title", errors, "errors.travel_item.title.max_length.exceeded");
		}
		if(required(item.getWeight(), "weight", errors, "errors.travel_item.weight.null")) {
			requiredPositive(item.getWeight(), "weight", errors, "errors.travel_item.weight.negative");
		}
	}
	
	private void entityRequired(Long id, Errors errors) {
		if (id != null) {
			TravelItem travelItemEntity = travelItemDao.findById(id);
			if(travelItemEntity == null) {
				log.debug("Validation failed: {} with provided id: {} not exist in DB.", dataType, id);
				errors.reject("errors.travel_item.not_exist", new Object[] {id}, null);
			}
		} else {
			errors.reject("errors.travel_item.id.required");
		}
	}

}
