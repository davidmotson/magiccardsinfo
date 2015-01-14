package com.davidm.magiccardsinfo.conditions;

public class SetCondition implements Condition {
	
	private String setName;
	
	SetCondition(String setName){
		this.setName = setName;
	}
	
	public String toString(){
		return "( e:" + setName + "/en )";
	}

}
