# magiccardsinfo
A DSL for searching and parser of magiccards.info

The goal is to be able to query with java code like this

``` java
query( or(type(CREATURE, LEGENDARY), color(GREEN, RED, MULTICOLOR).exclusive(), name("Black Lotus"))); 
```
