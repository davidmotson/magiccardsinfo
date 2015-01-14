package com.davidm.magiccardsinfo.enums;

public enum Color {
	WHITE('W'), BLUE('U'), BLACK('B'), RED('R'), GREEN('G'), MULTICOLOR('M'), COLORLESS('C');
	
	private char colorChar;
	
	Color(char colorChar){
		this.colorChar = colorChar;
	}

	public String toChar() {
		return String.valueOf(colorChar);
	}
}