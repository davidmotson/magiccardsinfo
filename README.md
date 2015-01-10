# magiccardsinfo
A DSL for searching and parser of magiccards.info

The goal is to be able to query with java code like this

``` java
		query(  type(CREATURE, LEGENDARY).and(
				color(GREEN, BLUE, BLACK, MULTICOLOR).exclusive()))
				.stream().map(SearchResult::getCardName)
				.forEach(System.out::println); 
```

currently results in the following list 

Damia, Sage of Stone
The Mimeoplasm
Sidisi, Brood Tyrant
Vorosh, the Hunter

