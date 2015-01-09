package com.davidm.magiccardsinfo.enums;

public enum Rarity {
	// (r:mythic or r:rare or r:uncommon or r:land or r:common or r:special)
	
	MYTHIC, RARE, UNCOMMON, LAND, COMMON, SPECIAL;
	
	public String toString(){
		switch(this){
		case MYTHIC:
			return "mythic";
		case RARE:
			return "rare";
		case UNCOMMON:
			return "uncommon";
		case LAND:
			return "land";
		case COMMON:
			return "common";
		case SPECIAL:
			return "special";
		}
		throw new RuntimeException("Unknown Rarity");
	}
	
}
