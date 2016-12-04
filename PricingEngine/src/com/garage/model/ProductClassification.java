package com.garage.model;

public class ProductClassification {

	private final boolean supplyHigh;
	private final boolean demandHigh;
	
	public ProductClassification(boolean supplyHigh, boolean demandHigh) {
		this.demandHigh = demandHigh;
		this.supplyHigh = supplyHigh;
	}

	public boolean isSupplyHigh() {
		return supplyHigh;
	}

	public boolean isDemandHigh() {
		return demandHigh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (demandHigh ? 1231 : 1237);
		result = prime * result + (supplyHigh ? 1231 : 1237);
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
		ProductClassification other = (ProductClassification) obj;
		if (demandHigh != other.demandHigh)
			return false;
		if (supplyHigh != other.supplyHigh)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ProductClassification [supplyHigh=" + supplyHigh + ", demandHigh=" + demandHigh + "]";
	}

}
