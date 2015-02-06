package com.davidm.magiccardsinfo.enums;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public enum Color {
	WHITE('W'), BLUE('U'), BLACK('B'), RED('R'), GREEN('G'), MULTICOLOR('M'), COLORLESS('C');
	
	private char colorChar;
	
	Color(char colorChar){
		this.colorChar = colorChar;
	}
	
	public char getColorChar(){
		return colorChar;
	}

	public String toChar() {
		return String.valueOf(colorChar);
	}
	
	public static Set<Color> parseColor(String in){
		ArrayList<Color> colors = new ArrayList<Color>(3);
		for(Color a : values()){
			if(in.contains(String.valueOf(a.getColorChar()))){
				colors.add(a);
			}
		}
		return colors.stream().collect(Collectors.toSet());
	}
}