package com.davidm.magiccardsinfo.enums;

public enum Rarity {
	
	MYTHIC("mythic"), RARE("rare"), UNCOMMON("uncommon"), LAND("land"), COMMON("common"), SPECIAL("special");
	
	private String name;
	
	Rarity(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
}
