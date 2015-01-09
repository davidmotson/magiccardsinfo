package com.davidm.magiccardsinfo.enums;

public enum Type {
	ARTIFACT, CREATURE, ENCHANTMENT, LAND, INSTANT, SORCERY, PLANESWALKER, EQUIPMENT, FORTIFICATION, BASIC_LAND, LEGENDARY, AURA, SHRINE, WORLD_ENCHANTMENT, SNOW, ARCANE, TRIBAL;
	
	public String toString(){
		switch(this){
		case ARTIFACT:
			return "artifact";
		case CREATURE:
			return "creature";
		case ENCHANTMENT:
			return "enchantment";
		case LAND:
			return "land";
		case INSTANT:
			return "instant";
		case SORCERY:
			return "sorcery";
		case PLANESWALKER:
			return "planeswalker";
		case EQUIPMENT:
			return "equipment";
		case FORTIFICATION:
			return "fortification";
		case BASIC_LAND:
			return "\"basic land\"";
		case LEGENDARY:
			return "legendary";
		case AURA:
			return "aura";
		case SHRINE:
			return "shrine";
		case WORLD_ENCHANTMENT:
			return "\"world enchantment\"";
		case SNOW:
			return "snow";
		case ARCANE:
			return "arcane";
		case TRIBAL:
			return "tribal";
		}
		throw new RuntimeException("Unknown Card Type");
	}
}
