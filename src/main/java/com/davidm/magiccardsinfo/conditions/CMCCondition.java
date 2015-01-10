package com.davidm.magiccardsinfo.conditions;

import com.davidm.magiccardsinfo.relationaloperators.RelationalOperator;

public class CMCCondition implements Condition {
	
	private final RelationalOperator op;

	CMCCondition(RelationalOperator op) {
		this.op = op;
	}

	public String toString() {
		return "cmc" + op;
	}
}
