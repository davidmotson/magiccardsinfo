package com.davidm.magiccardsinfo.enums;

public enum Format {
	VINTAGE("vintage"), LEGACY("legacy"), EXTENDED("extended"), 
	STANDARD("standard"), CLASSIC("classic"), COMMANDER("commander"),
	MODERN("modern");

	private String name;

	Format(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

}
