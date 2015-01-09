package com.davidm.magiccardsinfo.conditions;

import java.util.Arrays;
import java.util.List;

import com.davidm.magiccardsinfo.enums.Rarity;

public class RarityCondition implements Condition {
	
	private final List<Rarity> rarities;
	
	RarityCondition(Rarity... rarities){
		this.rarities = Arrays.asList(rarities);
	}
	
	public String toString(){
		if(rarities.isEmpty()){
			return "";
		}
		return "( " + rarities.stream().map(x -> "r:" + x).reduce((x,y) -> x + " OR " + y).get() + " )";
	}

}
