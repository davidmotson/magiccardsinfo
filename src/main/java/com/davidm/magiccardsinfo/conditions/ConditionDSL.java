package com.davidm.magiccardsinfo.conditions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.davidm.magiccardsinfo.AsyncCardService;
import com.davidm.magiccardsinfo.SearchResult;
import com.davidm.magiccardsinfo.enums.Color;
import com.davidm.magiccardsinfo.enums.Rarity;
import com.davidm.magiccardsinfo.enums.Type;
import com.davidm.magiccardsinfo.relationaloperators.Equal;
import com.davidm.magiccardsinfo.relationaloperators.GreaterThan;
import com.davidm.magiccardsinfo.relationaloperators.GreaterThanOrEqual;
import com.davidm.magiccardsinfo.relationaloperators.LessThan;
import com.davidm.magiccardsinfo.relationaloperators.LessThanOrEqual;
import com.davidm.magiccardsinfo.relationaloperators.RelationalOperator;

public class ConditionDSL {

	public static Condition rarity(Rarity... rarities) {
		return new RarityCondition(rarities);
	}

	public static ColorCondition color(Color... colors) {
		return new ColorCondition(colors);
	}

	public static Condition or(Condition... conditions) {
		return new OrCondition(conditions);
	}

	public static Condition and(Condition... conditions) {
		return new AndCondition(conditions);
	}

	public static Condition not(Condition condition) {
		return new NotCondition(condition);
	}

	public static Condition name(String... names) {
		return and((Condition[]) Stream.of(names).map(NameCondition::new)
				.toArray(x -> new Condition[x]));
	}

	public static Condition rules(String... rules) {
		return and((Condition[]) Stream.of(rules).map(RulesCondition::new)
				.toArray(x -> new Condition[x]));
	}

	public static Condition type(Type... types) {
		return and((Condition[]) Stream.of(types).map(TypeCondition::new)
				.toArray(x -> new Condition[x]));
	}

	public static RelationalOperator equal(int num) {
		return new Equal(num);
	}

	public static RelationalOperator eq(int num) {
		return new Equal(num);
	}

	public static RelationalOperator greaterThan(int num) {
		return new GreaterThan(num);
	}

	public static RelationalOperator gt(int num) {
		return new GreaterThan(num);
	}

	public static RelationalOperator greaterThanOrEqualTo(int num) {
		return new GreaterThanOrEqual(num);
	}

	public static RelationalOperator ge(int num) {
		return new GreaterThanOrEqual(num);
	}

	public static RelationalOperator lessThan(int num) {
		return new LessThan(num);
	}

	public static RelationalOperator lt(int num) {
		return new LessThan(num);
	}

	public static RelationalOperator lessThanOrEqualTo(int num) {
		return new LessThanOrEqual(num);
	}

	public static RelationalOperator le(int num) {
		return new LessThanOrEqual(num);
	}

	public static Condition power(RelationalOperator op) {
		return new PowerCondition(op);
	}

	public static Condition toughness(RelationalOperator op) {
		return new ToughnessCondition(op);
	}

	public static Condition cmc(RelationalOperator op) {
		return new CMCCondition(op);
	}

	public static Condition convertedManaCost(RelationalOperator op) {
		return new CMCCondition(op);
	}

	public static List<SearchResult> search(Condition condition) {
		return AsyncCardService.search(condition);
	}
	
	public static Future<List<SearchResult>> searchAsync(Condition condition){
		return AsyncCardService.searchAsync(condition);
	}

}
