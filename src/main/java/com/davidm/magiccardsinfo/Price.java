package com.davidm.magiccardsinfo;

import java.util.Optional;

public class Price {
	private final Integer highCents;
	private final Integer midCents;
	private final Integer lowCents;
	
	public Price(Integer low,Integer mid, Integer high){
		lowCents = low;
		midCents = mid;
		highCents = high;
	}
	
	public Optional<Integer> getLowCents(){
		return Optional.ofNullable(lowCents);
	}
	
	public Optional<Integer> getMidCents(){
		return Optional.ofNullable(midCents);
	}
	
	public Optional<Integer> getHighCents(){
		return Optional.ofNullable(highCents);
	}
	
	public Optional<String> getLowDollars(){
		if(lowCents == null){
			return Optional.empty();
		}
		return Optional.of("$" + (lowCents / 100) + "." + (lowCents % 100));
	}
	
	public Optional<String> getMidDollars(){
		if(midCents == null){
			return Optional.empty();
		}
		return Optional.of("$" + (midCents / 100) + "." + (midCents % 100));
	}
	
	public Optional<String> getHighDollars(){
		if(highCents == null){
			return Optional.empty();
		}
		return Optional.of("$" + (highCents / 100) + "." + (highCents % 100));
	}
	
	
	@Override
	public String toString(){
		return "{low: " + getLowDollars().orElse("NA") + " mid: "
				+ getMidDollars().orElse("NA") + " high: "
				+ getHighDollars().orElse("NA") + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((highCents == null) ? 0 : highCents.hashCode());
		result = prime * result
				+ ((lowCents == null) ? 0 : lowCents.hashCode());
		result = prime * result
				+ ((midCents == null) ? 0 : midCents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (highCents == null) {
			if (other.highCents != null)
				return false;
		} else if (!highCents.equals(other.highCents))
			return false;
		if (lowCents == null) {
			if (other.lowCents != null)
				return false;
		} else if (!lowCents.equals(other.lowCents))
			return false;
		if (midCents == null) {
			if (other.midCents != null)
				return false;
		} else if (!midCents.equals(other.midCents))
			return false;
		return true;
	}

}
