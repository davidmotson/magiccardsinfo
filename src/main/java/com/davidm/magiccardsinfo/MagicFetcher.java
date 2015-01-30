package com.davidm.magiccardsinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	

	
	
	private static class MagicCardBuilder{
		private static Pattern costTypePattern = Pattern.compile("\\s*([^,]*)(,\\s+(\\S+)(\\s+\\((.*)\\))?+)?+\\s*");
		private static Pattern manaCostPattern = Pattern.compile("W|U|B|R|G|(\\d+)|(\\{.*?\\})");
		private Document doc;
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
		private String priceUrl;
		
		public static void main(String... args) throws IOException{
			MagicCardBuilder temp = new MagicCardBuilder(Jsoup.connect(
					"http://magiccards.info/fvd/en/10.html").get());
			temp.extractName();
			System.out.println(temp.name);
		}
		
		public MagicCardBuilder(Document doc){
			this.doc = doc;
		}
		
		private MagicCardBuilder extractName() {
			name = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > span > a")
					.get(0).text();
			return this;
		}
		
		private MagicCardBuilder extractCost(){
			String textBox = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > p:nth-child(2)").get(0).text();
			Matcher matcher = costTypePattern.matcher(textBox);
			if(!matcher.matches()){
				manaCost = Collections.emptyList();
				return this;
			}
			String cost = matcher.group(3);
			Matcher costMatcher = manaCostPattern.matcher(cost);
			manaCost = new ArrayList<String>();
			while(costMatcher.find()){
				manaCost.add(costMatcher.group());
			}
			return this;
		}
		
		
	}
	
	

}
