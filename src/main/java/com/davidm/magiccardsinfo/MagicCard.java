package com.davidm.magiccardsinfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.davidm.magiccardsinfo.enums.Color;
import com.davidm.magiccardsinfo.enums.Rarity;
import com.davidm.magiccardsinfo.enums.Type;

public class MagicCard {
	private final String name;
	private final List<String> manaCost;
	private final Set<Color> colors;
	private final Set<Type> types;
	private final Rarity rarity;
	private final String text;
	private final String flavorText;
	private final Integer power;
	private final Integer toughness;
	private final String imageUrl;
	private final Price price;
	
	public MagicCard(String name, List<String> manaCost, Set<Color> colors,
			Set<Type> types, Rarity rarity, String text, String flavorText,
			Integer power, Integer toughness, String imageUrl, Price price) {
		this.name = name;
		this.manaCost = manaCost;
		this.colors = colors;
		this.types = types;
		this.rarity = rarity;
		this.text = text;
		this.flavorText = flavorText;
		this.power = power;
		this.toughness = toughness;
		this.imageUrl = imageUrl;
		this.price = price;
	}
	
	public String getName(){
		return name;
	}
	
	public List<String> getManaCost(){
		return manaCost;
	}
	
	public Set<Color> getColors(){
		return colors;
	}
	
	public Set<Type> getTypes(){
		return types;
	}
	
	public Rarity getRarity(){
		return rarity;
	}
	
	public String getText(){
		return text;
	}
	
	public String getFlavorText(){
		return flavorText;
	}
	
	public Optional<Integer> getPower(){
		return Optional.ofNullable(power);
	}
	
	public Optional<Integer> getToughness(){
		return Optional.ofNullable(toughness);
	}
	
	public String getImageUrl(){
		return imageUrl;
	}
	
	public Optional<Price> getPrice(){
		return Optional.ofNullable(price);
	}

	@Override
	public String toString() {
		return "MagicCard [name=" + name + ", manaCost=" + manaCost
				+ ", colors=" + colors + ", types=" + types + ", rarity="
				+ rarity + ", text=" + text + ", flavorText=" + flavorText
				+ ", power=" + getPower() + ", toughness=" + getToughness()
				+ ", imageUrl=" + imageUrl + ", price=" + getPrice() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colors == null) ? 0 : colors.hashCode());
		result = prime * result
				+ ((flavorText == null) ? 0 : flavorText.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result
				+ ((manaCost == null) ? 0 : manaCost.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((toughness == null) ? 0 : toughness.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagicCard other = (MagicCard) obj;
		if (colors == null) {
			if (other.colors != null)
				return false;
		} else if (!colors.equals(other.colors))
			return false;
		if (flavorText == null) {
			if (other.flavorText != null)
				return false;
		} else if (!flavorText.equals(other.flavorText))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (manaCost == null) {
			if (other.manaCost != null)
				return false;
		} else if (!manaCost.equals(other.manaCost))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rarity != other.rarity)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (toughness == null) {
			if (other.toughness != null)
				return false;
		} else if (!toughness.equals(other.toughness))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}
	

}
