package com.homework.travel.data;

import java.math.BigDecimal;

import com.homework.travel.enumeration.SeasonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private String title;
	private BigDecimal weight;
	private Long quantity;
	private Long distance;
	private SeasonType season;
}
