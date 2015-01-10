package com.davidm.magiccardsinfo.relationaloperators;

public class GreaterThan implements RelationalOperator {
	
	private final int number;
	
	public GreaterThan(int number){
		this.number = number;
	}
	
	public String toString(){
		return ">" + number;
	}

}
