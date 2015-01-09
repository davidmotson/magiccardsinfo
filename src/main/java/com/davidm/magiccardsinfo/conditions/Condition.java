package com.davidm.magiccardsinfo.conditions;

public interface Condition {
	public String toString();
	
	default Condition or(Condition... conditions){
		Condition[] tempConditions = new Condition[conditions.length + 1];
		for(int i = 0;i < conditions.length; i ++){
			tempConditions[i] = conditions[i];
		}
		tempConditions[conditions.length] = this;
		return new OrCondition(tempConditions);
	}
	
	default Condition and(Condition... conditions){
		Condition[] tempConditions = new Condition[conditions.length + 1];
		for(int i = 0;i < conditions.length; i ++){
			tempConditions[i] = conditions[i];
		}
		tempConditions[conditions.length] = this;
		return new AndCondition(tempConditions);
	}
}
