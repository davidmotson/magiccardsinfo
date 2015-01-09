# magiccardsinfo
A DSL for searching and parser of magiccards.info

The goal is to be able to query with java code like this

``` java
query( type(CREATURE, LEGENDARY).and(color(GREEN, RED, MULTICOLOR).exclusive()).or(name("Black Lotus"))); 
```
this should return all green-red legendary creatures, and also black lotus
