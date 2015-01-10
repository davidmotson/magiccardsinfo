package com.davidm.magiccardsinfo.enums;

public enum RelationalOperators {
	EQUAL, LESS_THAN, GREATER_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN_OR_EQUAL;
	
	public String apply(int in){
		return this.toString() + in;
	}
	
	public String toString(){
		switch(this){
		case EQUAL:
			return "=";
		case LESS_THAN:
			return "<";
		case GREATER_THAN:
			return ">";
		case LESS_THAN_OR_EQUAL:
			return "<=";
		case GREATER_THAN_OR_EQUAL:
			return ">=";
		}
		throw new RuntimeException("Unknown Relational Operator");
	}
}
