package com.davidm.magiccardsinfo.conditions;

import java.util.Arrays;
import java.util.List;

public class OrCondition implements Condition{
	
	
	private final List<Condition> conditions;
	
	OrCondition(Condition... conditions){
		this.conditions = Arrays.asList(conditions);
	}
	
	public String toString(){
		if(conditions.isEmpty() || conditions.stream().allMatch(x -> x.toString().isEmpty())){
			return "";
		}
		return "( " + conditions.stream().map(Condition::toString).filter(x -> !x.isEmpty()).reduce("", (x,y) -> x + " OR " + y) + " )";
	}

}
