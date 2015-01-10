package com.davidm.magiccardsinfo.conditions;

import com.davidm.magiccardsinfo.relationaloperators.RelationalOperator;

public class ToughnessCondition implements Condition{
	
	private final RelationalOperator op;

	ToughnessCondition(RelationalOperator op) {
		this.op = op;
	}

	public String toString() {
		return "tou" + op;
	}

}
