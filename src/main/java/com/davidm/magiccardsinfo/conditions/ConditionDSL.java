package com.davidm.magiccardsinfo.conditions;

import java.util.stream.Stream;

import com.davidm.magiccardsinfo.enums.Color;
import com.davidm.magiccardsinfo.enums.Rarity;
import com.davidm.magiccardsinfo.enums.Type;

public class ConditionDSL {
	
	public static Condition rarity(Rarity... rarities){
		return new RarityCondition(rarities);
	}
	
	public static ColorCondition color(Color... colors){
		return new ColorCondition(colors);
	}
	
	public static Condition or(Condition... conditions){
		return new OrCondition(conditions);
	}
	
	public static Condition and(Condition... conditions){
		return new AndCondition(conditions);
	}
	
	public static Condition not(Condition condition){
		return new NotCondition(condition);
	}
	
	public static Condition name(String... names){
		return and((Condition[]) Stream.of(names).map(NameCondition::new).toArray(x -> new Condition[x]));
	}
	
	public static Condition rules(String... rules){
		return and((Condition[]) Stream.of(rules).map(RulesCondition::new).toArray(x -> new Condition[x]));
	}
	
	public static Condition type(Type... types){
		return and((Condition[]) Stream.of(types).map(TypeCondition::new).toArray(x -> new Condition[x]));
	}

}
