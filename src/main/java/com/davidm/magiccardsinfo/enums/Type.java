package com.davidm.magiccardsinfo.enums;

public enum Type {
	ARTIFACT("artifact"),
	CREATURE("creature"),
	ENCHANTMENT("enchantment"),
	LAND("land"), 
	INSTANT("instant"), 
	SORCERY("sorcery"), 
	PLANESWALKER("planeswalker"), 
	EQUIPMENT("equipment"), 
	FORTIFICATION("fortification"), 
	BASIC_LAND("\"basic land\""), 
	LEGENDARY("legendary"), 
	AURA("aura"), 
	SHRINE("shrine"), 
	WORLD_ENCHANTMENT("\"world enchantment\""), 
	SNOW("snow"), 
	ARCANE("arcane"), 
	TRIBAL("tribal");
	
	private String name;
	
	Type(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
