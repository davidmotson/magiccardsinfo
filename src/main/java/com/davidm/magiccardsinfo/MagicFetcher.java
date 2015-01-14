package com.davidm.magiccardsinfo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.davidm.magiccardsinfo.conditions.Condition;

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

}
