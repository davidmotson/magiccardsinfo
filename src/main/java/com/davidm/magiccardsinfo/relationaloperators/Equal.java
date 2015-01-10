package com.davidm.magiccardsinfo.relationaloperators;

public class Equal implements RelationalOperator {
	
	private final int number;
	
	public Equal(int number){
		this.number = number;
	}
	
	public String toString(){
		return "=" + number;
	}

}
