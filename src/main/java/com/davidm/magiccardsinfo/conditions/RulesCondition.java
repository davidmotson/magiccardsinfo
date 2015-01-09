package com.davidm.magiccardsinfo.conditions;

public class RulesCondition implements Condition {
	
	private final String rule;
	
	RulesCondition(String rule){
		this.rule = rule;
	}
	
	public String toString(){
		return " \"" + rule + "\" ";
	}

}
