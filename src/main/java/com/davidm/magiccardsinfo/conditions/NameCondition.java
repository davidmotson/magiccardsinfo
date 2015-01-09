package com.davidm.magiccardsinfo.conditions;

public class NameCondition implements Condition {
	
	private final String name;
	
	NameCondition(String name){
		this.name = name;
	}
	
	public String toString(){
		return " \"" + name + "\" ";
	}

}
