package com.davidm.magiccardsinfo.enums;

public enum Rarity {
	
	MYTHIC("Mythic"), RARE("Rare"), UNCOMMON("Uncommon"), LAND("Land"), COMMON("Common"), SPECIAL("Special");
	
	private String name;
	
	Rarity(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}

	public static Rarity parseRarity(String rarity) {
		for(Rarity r : values()){
			if(rarity.toLowerCase().contains(r.toString().toLowerCase())){
				return r;
			}
		}
		return null;
	}
	
}
