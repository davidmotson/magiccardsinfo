package com.davidm.magiccardsinfo.enums;

public enum Color {
	WHITE, BLUE, BLACK, RED, GREEN, MULTICOLOR, COLORLESS;

	public String toChar() {
		switch (this) {
		case WHITE:
			return "W";
		case BLUE:
			return "U";
		case BLACK:
			return "B";
		case RED:
			return "R";
		case GREEN:
			return "G";
		case COLORLESS:
			return "C";
		case MULTICOLOR:
			return "M";
		}
		throw new RuntimeException("Unknown Color");
	}
}