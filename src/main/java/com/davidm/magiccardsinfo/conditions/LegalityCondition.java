package com.davidm.magiccardsinfo.conditions;

import com.davidm.magiccardsinfo.enums.Format;

public class LegalityCondition implements Condition {
	
	private final Format format;
	
	LegalityCondition(Format format){
		this.format = format;
	}
	
	
	public String toString(){
		return "( f:" + format + " )";
	}

}
