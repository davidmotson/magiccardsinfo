package com.davidm.magiccardsinfo;

import java.io.IOException;

public class SearchResult {
	private final String cardName;
	private final String cardUrl;
	
	public SearchResult(String cardName, String cardUrl){
		this.cardName = cardName;
		this.cardUrl = cardUrl;
	}
	
	public String getCardName(){
		return cardName;
	}
	
	public String getCardUrl(){
		return cardUrl;
	}
	
	public MagicCard getCard(){
		try {
			return MagicFetcher.MagicCardBuilder.build(cardUrl, false);
		} catch (IOException e) {
			throw new RuntimeException("Error while fetching: " + cardName, e);

		}
	}
	
	public MagicCard getCardWithPrice(){
		try {
			return MagicFetcher.MagicCardBuilder.build(cardUrl, true);
		} catch (IOException e) {
			throw new RuntimeException("Error while fetching: " + cardName, e);
		}
	}

}
