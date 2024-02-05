package com.homework.travel.enumeration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.homework.travel.enumeration.SeasonType;

@Component
public class SeasonTypeConverter implements Converter<String, SeasonType> {

	@Override
	public SeasonType convert(String source) {
		return SeasonType.fromString(source);
	}

}
