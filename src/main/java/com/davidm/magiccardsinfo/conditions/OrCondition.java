package com.davidm.magiccardsinfo.conditions;

import java.util.Arrays;
import java.util.List;

public class OrCondition implements Condition{
	
	
	private final List<Condition> conditions;
	
	OrCondition(Condition... conditions){
		this.conditions = Arrays.asList(conditions);
	}
	
	public String toString(){
		return conditions.stream().map(Condition::toString)
				.filter(x -> !x.isEmpty()).reduce((x, y) -> x + " OR " + y)
				.map(x -> "( " + x + " )").orElse("");
	}

}
