package com.homework.travel.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemView {
	
	private String title;
	private long quantity;
	private BigDecimal totalWeight;
}
