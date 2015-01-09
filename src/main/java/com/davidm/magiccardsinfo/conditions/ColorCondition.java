package com.davidm.magiccardsinfo.conditions;

import java.util.Arrays;
import java.util.List;


import com.davidm.magiccardsinfo.enums.Color;

public class ColorCondition implements Condition {
	private final List<Color> colors;
	private boolean exclusive;
	
	ColorCondition(Color... colors){
		this.colors = Arrays.asList(colors);
		exclusive = false;
	}
	
	public ColorCondition exclusive(){
		exclusive = true;
		return this;
	}
	
	public String toString(){
		if (colors.isEmpty()){
			return "";
		}
		return "(c" + (exclusive ? "!" : ":") + colors.stream().map(Color::toChar).reduce("", String::concat) + ")";
	}
}
