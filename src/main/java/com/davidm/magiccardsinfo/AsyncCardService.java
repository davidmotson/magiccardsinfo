package com.davidm.magiccardsinfo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.davidm.magiccardsinfo.conditions.Condition;

public class AsyncCardService {

	private static volatile ExecutorService executor;

	private static ExecutorService getExecutorService() {
		if (executor == null) {
			synchronized (AsyncCardService.class) {
				if (executor == null) {
					executor = new ForkJoinPool();
				}
			}
		}
		return executor;
	}

	public static <T> Future<T> submit(Callable<T> task) {
		return getExecutorService().submit(task);
	}

	public static void setExecutorService(ExecutorService service) {
		executor = service;
	}

	public static List<SearchResult> search(Condition condition) {
		try {
			Document resultPage = Jsoup.connect(
					"http://magiccards.info/query?q=" + condition
							+ "&v=olist&s=cname").get();
			return Stream
					.concat(resultPage.getElementsByClass("even").stream(),
							resultPage.getElementsByClass("odd").stream())
					.map(row -> {
						Element result = row.getElementsByTag("a").get(0);
						return new SearchResult(result.text(),
								"http://magiccards.info" + result.attr("href"));
					}).collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException("Lost connection when fetching results", e);
		}
	}

	public static Future<List<SearchResult>> searchAsync(Condition condition) {
		return submit(() -> search(condition));
	}

}
