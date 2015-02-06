package com.davidm.magiccardsinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.davidm.magiccardsinfo.conditions.Condition;
import com.davidm.magiccardsinfo.enums.Color;
import com.davidm.magiccardsinfo.enums.Rarity;
import com.davidm.magiccardsinfo.enums.Type;

public class MagicFetcher {

	public static List<SearchResult> search(Condition condition) {
		try {
			Document resultPage = Jsoup.connect(
					"http://magiccards.info/query?q=" + condition
							+ "&v=olist&s=cname").get();
			List<SearchResult> results = getResultsFromPage(resultPage);
			int pagesLeft = resultPage
					.select("body > table:nth-child(5) > tbody > tr > td:nth-child(2) > a")
					.stream()
					.mapToInt(
							link -> {
								String href = link.attr("href");
								return Integer.parseInt(href.substring(href.indexOf("&p=") + 3));
							}).max().orElse(1);
			for (int i = 2; i <= pagesLeft; i++) {
				results.addAll(getResultsFromPage(Jsoup.connect(
						"http://magiccards.info/query?q=" + condition
								+ "&v=olist&s=cname&p=" + i).get()));
			}
			return results;
		} catch (IOException e) {
			throw new RuntimeException("Lost connection when fetching results",
					e);
		}
	}

	private static List<SearchResult> getResultsFromPage(Document resultPage) {
		return Stream
				.concat(resultPage.getElementsByClass("even").stream(),
						resultPage.getElementsByClass("odd").stream())
				.map(row -> {
					Element result = row.getElementsByTag("a").get(0);
					return new SearchResult(result.text(),
							"http://magiccards.info" + result.attr("href"));
				}).collect(Collectors.toList());
	}
	

	
	
	static class MagicCardBuilder{
		private static Pattern costTypePattern = Pattern.compile("\\s*([^,]*)(,\\s+(\\S+)(\\s+\\((.*)\\))?+)?+\\s*");
		private static Pattern manaCostPattern = Pattern.compile("W|U|B|R|G|(\\d+)|(\\{.*?\\})");
		private static Pattern pricePattern = Pattern.compile(".*L:\\s*<.*>(\\$[\\d[\\.]]+|NA)<.*M:\\s*<.*>(\\$[\\d[\\.]]+|NA)<.*H:\\s*<.*>(\\$[\\d[\\.]]+|NA)<.*");
		private Document doc;
		private String name;
		private List<String> manaCost;
		private Set<Color> colors;
		private Set<Type> types;
		private Rarity rarity;
		private String text;
		private String flavorText;
		private Integer power;
		private Integer toughness;
		private String imageUrl;
		private String priceUrl;
		private Price price;
		
		public static MagicCard build(String url, boolean getPrice) throws IOException{
			MagicCardBuilder builder = new MagicCardBuilder(Jsoup.connect(url).get());
			builder.extractName();
			builder.extractCost();
			builder.extractColors();
			builder.extractTypes();
			builder.extractRarity();
			builder.extractRarity();
			builder.extractText();
			builder.extractFlavorText();
			builder.extractPowerAndToughness();
			builder.extractImageUrl();
			builder.extractPriceUrl();
			if(getPrice){
				builder.extractPrices();
			}
			return new MagicCard(builder.name, builder.manaCost,
					builder.colors, builder.types, builder.rarity,
					builder.text, builder.flavorText, builder.power,
					builder.toughness, builder.imageUrl, builder.price);
		}
		
		
		private MagicCardBuilder(Document doc){
			this.doc = doc;
		}
		
		private void extractName() {
			name = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > span > a")
					.get(0).text();
		}
		
		private void extractCost(){
			String textBox = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > p:nth-child(2)").first().text();
			Matcher matcher = costTypePattern.matcher(textBox);
			if(!matcher.matches()){
				manaCost = Collections.emptyList();
				return;
			}
			String cost = matcher.group(3);
			if(cost == null){
				manaCost = Collections.emptyList();
				return;
			}
			Matcher costMatcher = manaCostPattern.matcher(cost);
			manaCost = new ArrayList<String>();
			while(costMatcher.find()){
				manaCost.add(costMatcher.group());
			}
		}
		
		private void extractColors(){
			colors = manaCost.stream().map(Color::parseColor)
					.flatMap(Set::stream).filter(x -> x != null)
					.collect(Collectors.toSet());
			if(colors.isEmpty()){
				colors = EnumSet.of(Color.COLORLESS);
			}
		}
		
		private void extractTypes(){
			String textBox = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > p:nth-child(2)").first().text();
			Matcher matcher = costTypePattern.matcher(textBox);
			if(!matcher.matches()){
				types = Collections.emptySet();
				return;
			}
			types = Stream.of(matcher.group(1).split("\\s"))
					.map(Type::parseType).filter(x -> x != null)
					.collect(Collectors.toSet());
		}
		
		private void extractRarity(){
			String text = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(3) small > b").get(1).text();
			String[] split = text.split("\\(");
			String rarity = split[split.length-1];
			this.rarity = Rarity.parseRarity(rarity);
		}
		
		private void extractText(){
			text = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > p.ctext > b")
					.first().html().replace("<br>", "\n");
		}
		
		private void extractFlavorText(){
			flavorText = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > p:nth-child(4) > i")
					.first().html().replace("<br>", "\n");
		}
		
		private void extractPowerAndToughness(){
			String textBox = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > p:nth-child(2)").first().text();
			Matcher matcher = costTypePattern.matcher(textBox);
			if(!matcher.matches()){
				power = null;
				toughness = null;
				return;
			}
			String[] typeAndPower = matcher.group(1).split(" ");
			String[] powerToughness = typeAndPower[typeAndPower.length - 1].split("/");
			if(powerToughness.length < 2){
				power = null;
				toughness = null;
				return;
			}
			try{
				power = Integer.parseInt(powerToughness[0]);
			}catch(NumberFormatException e){
				power = null;
			}
			try{
				toughness = Integer.parseInt(powerToughness[1]);
			}catch(NumberFormatException e){
				toughness = null;
			}
		}
		
		private void extractImageUrl(){
			imageUrl = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(1) > img").first().attr("src");
		}
		
		private void extractPriceUrl(){
			priceUrl = Optional.ofNullable(
							doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(1) > script")
									.first()).map(x -> x.attr("src"))
					.orElse(null);
		}
		
		private void extractPrices(){
			if(priceUrl == null){
				price = null;
				return;
			}
			try{
				String priceString = Jsoup.connect(priceUrl).ignoreContentType(true).execute().body();
				Matcher priceMatcher = pricePattern.matcher(priceString);
				if(!priceMatcher.matches()){
					price = null;
					return;
				}
				Integer lowPrice;
				Integer midPrice;
				Integer highPrice;
				if(priceMatcher.group(1).equals("NA")){
					lowPrice = null;
				}else{
					lowPrice = Integer.parseInt(priceMatcher.group(1).replaceAll("\\$|\\.", ""));
				}
				if(priceMatcher.group(2).equals("NA")){
					midPrice = null;
				}else{
					midPrice = Integer.parseInt(priceMatcher.group(2).replaceAll("\\$|\\.", ""));
				}
				if(priceMatcher.group(3).equals("NA")){
					highPrice = null;
				}else{
					highPrice = Integer.parseInt(priceMatcher.group(3).replaceAll("\\$|\\.", ""));
				}
				price = new Price(lowPrice, midPrice, highPrice);
			}catch(IOException e){
				price = null;
			}
		}
		
		
	}
	
	

}
