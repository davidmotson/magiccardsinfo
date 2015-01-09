package com.davidm.magiccardsinfo.conditions;

import java.util.Arrays;
import java.util.List;

public class AndCondition implements Condition{
	
	private final List<Condition> conditions;
	
	AndCondition(Condition... conditions){
		this.conditions = Arrays.asList(conditions);
	}
	
	public String toString(){
		String output = conditions.stream().map(Condition::toString).filter(x -> !x.isEmpty()).reduce((x,y) -> x + " AND " + y).orElse("");
		if(output.isEmpty()){
			return output;
		}
		return "( " + output + " )";
	}

}
