package com.davidm.magiccardsinfo;

public class Price {
	private Integer highCents;
	private Integer midCents;
	private Integer lowCents;
	
	public Price(Integer low,Integer mid, Integer high){
		lowCents = low;
		midCents = mid;
		highCents = high;
	}
	
	@Override
	public String toString(){
		return "{low: " + lowCents + " mid: " + midCents + " high: "
				+ highCents + "}";
	}

}
