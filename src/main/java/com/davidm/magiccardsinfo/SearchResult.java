package com.davidm.magiccardsinfo;

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

}
