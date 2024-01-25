package com.homework.travel.pojo;

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
	
	public static ItemView sum(ItemView i1, ItemView i2) {
		return new ItemView(i1.getTitle(), 
				i1.getQuantity() + i2.getQuantity() , 
				i1.getTotalWeight().add(i2.getTotalWeight()));
	}
}
