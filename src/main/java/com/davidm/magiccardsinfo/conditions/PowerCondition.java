package com.davidm.magiccardsinfo.conditions;

import com.davidm.magiccardsinfo.relationaloperators.RelationalOperator;

public class PowerCondition implements Condition {

	private final RelationalOperator op;

	PowerCondition(RelationalOperator op) {
		this.op = op;
	}

	public String toString() {
		return "pow" + op;
	}

}
