package com.homework.travel;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.homework.travel.type.map.ItemMap;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	ModelMapper modelMapper() {
	     ModelMapper mapper = new ModelMapper();
	     mapper
	     .getConfiguration()
	     .setFieldMatchingEnabled(false)
	     .setMatchingStrategy(MatchingStrategies.STRICT)
	     .setFieldMatchingEnabled(false)
	     .setImplicitMappingEnabled(false)
	     .setPropertyCondition(Conditions.isNotNull());
	
	     mapper.addMappings(new ItemMap());
	     return mapper;
	}
}
