package com.garage.model;

public class Product {
	
	private final String name;
	private final ProductClassification productClassification;
	
	public Product(String name, ProductClassification productClassification) {
		this.name = name;
		this.productClassification = productClassification;
	}

	public String getName() {
		return name;
	}

	public ProductClassification getProductClassification() {
		return productClassification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productClassification == null) ? 0 : productClassification.hashCode());
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
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productClassification == null) {
			if (other.productClassification != null)
				return false;
		} else if (!productClassification.equals(other.productClassification))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", productClassification=" + productClassification + "]";
	}

}
