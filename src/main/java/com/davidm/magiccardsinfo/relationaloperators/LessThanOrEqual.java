package com.davidm.magiccardsinfo.relationaloperators;

public class LessThanOrEqual implements RelationalOperator {
	
	private final int number;
	
	public LessThanOrEqual(int number){
		this.number = number;
	}
	
	public String toString(){
		return "<=" + number;
	}
}
