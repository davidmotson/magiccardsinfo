package com.davidm.magiccardsinfo.conditions;

import java.util.Arrays;
import java.util.List;

import com.davidm.magiccardsinfo.enums.Color;

public class ColorIdCondition implements Condition {
	private final List<Color> colors;
	
	ColorIdCondition(Color... colors){
		this.colors = Arrays.asList(colors);
	}
	
	
	public String toString(){
		if (colors.isEmpty()){
			return "";
		}
		return "(ci:" + colors.stream().map(Color::toChar).reduce("", String::concat) + ")";
	}
}
