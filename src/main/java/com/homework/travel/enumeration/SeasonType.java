package com.homework.travel.enumeration;

public enum SeasonType {

	SUMMER("summer"),
	WINTER("winter");
	
	private final String text;
	
	SeasonType(final String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}
	
	public static SeasonType fromString(String text) {
		for(SeasonType st: SeasonType.values()) {
			if(st.text.equalsIgnoreCase(text)) {
				return st;
			}
		}
		return null;
	}
}
