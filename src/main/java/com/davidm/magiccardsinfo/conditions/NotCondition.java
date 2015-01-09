package com.davidm.magiccardsinfo.conditions;

public class NotCondition implements Condition {
	
	private final Condition condition;
	
	NotCondition(Condition condition){
		this.condition = condition;
	}
	
	public String toString(){
		if (condition.toString().isEmpty()){
			return "";
		}
		return "( NOT " + condition + " )";
	}

}
