package com.homework.travel.pojo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TravelItemView {
	
	private int intemsCount;
	private BigDecimal totalWeight;
	private List<ItemView> travelItemList;
}
