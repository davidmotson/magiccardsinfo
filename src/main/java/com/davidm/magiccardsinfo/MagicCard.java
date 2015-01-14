package com.davidm.magiccardsinfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.davidm.magiccardsinfo.enums.Color;
import com.davidm.magiccardsinfo.enums.Rarity;
import com.davidm.magiccardsinfo.enums.Type;

public class MagicCard {
	private String name;
	private List<String> manaCost;
	private Set<Color> color;
	private Set<Type> type;
	private Rarity rarity;
	private String text;
	private String flavorText;
	private Optional<Integer> power;
	private Optional<Integer> toughness;
	private String imageUrl;
	private int lowPriceCents;
	private int midPriceCents;
	private int highPriceCents;
}
