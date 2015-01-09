package com.davidm.magiccardsinfo.conditions;

import com.davidm.magiccardsinfo.enums.Type;

public class TypeCondition implements Condition {
	
	private final Type type;
	
	TypeCondition(Type type){
		this.type = type;
	}
	
	public String toString(){
		return "t:" + type;
	}

}
