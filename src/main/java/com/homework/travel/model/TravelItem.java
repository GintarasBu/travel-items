package com.homework.travel.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.homework.travel.enumeration.SeasonType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "TRAVEL_ITEM")
public class TravelItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5582590460746254227L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", length = 100 , nullable = false)
	private String title;
	
	@Column(precision = 5, scale = 2, nullable = false)
	private BigDecimal weight;
	
	@Column(nullable = false)
	private Long quantity;
	
	@Column(nullable = false)
	private Long distance;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SeasonType season;
	
}
