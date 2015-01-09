package com.davidm.magiccardsinfo.conditions;

import java.util.stream.Stream;

import com.davidm.magiccardsinfo.enums.Color;
import com.davidm.magiccardsinfo.enums.Rarity;

public class ConditionDSL {
	
	public static Condition rarity(Rarity... rarities){
		return new RarityCondition(rarities);
	}
	
	public static Condition color(Color... colors){
		return new ColorCondition(colors);
	}
	
	public static Condition or(Condition... conditions){
		return new OrCondition(conditions);
	}
	
	public static Condition and(Condition... conditions){
		return new AndCondition(conditions);
	}
	
	public static Condition name(String... names){
		return and((Condition[]) Stream.of(names).map(NameCondition::new).toArray());
	}
	
	public static Condition rules(String... rules){
		return and((Condition[]) Stream.of(rules).map(RulesCondition::new).toArray());
	}

}
