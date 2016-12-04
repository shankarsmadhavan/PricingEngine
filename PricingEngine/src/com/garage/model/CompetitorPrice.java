package com.garage.model;

public class CompetitorPrice {

	private final String name;
	private final Product product;
	private final Double price;
	
	
	public CompetitorPrice(String name, Product product, Double price) {
		this.name = name;
		this.product = product;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Product getProduct() {
		return product;
	}

	public Double getPrice() {
		return price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		CompetitorPrice other = (CompetitorPrice) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompetitorPrice [name=" + name + ", product=" + product + ", price=" + price + "]";
	}
	
}
