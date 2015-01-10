package com.davidm.magiccardsinfo.relationaloperators;

public class LessThan implements RelationalOperator {
	
	private final int number;
	
	public LessThan(int number){
		this.number = number;
	}
	
	public String toString(){
		return "<" + number;
	}
}
