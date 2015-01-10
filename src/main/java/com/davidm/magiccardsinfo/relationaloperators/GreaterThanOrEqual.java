package com.davidm.magiccardsinfo.relationaloperators;

public class GreaterThanOrEqual implements RelationalOperator {
	
	private final int number;
	
	public GreaterThanOrEqual(int number){
		this.number = number;
	}
	
	public String toString(){
		return ">=" + number;
	}
}
