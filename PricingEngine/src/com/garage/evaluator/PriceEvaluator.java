package com.garage.evaluator;

import java.util.Collection;
import java.util.HashSet;

import com.garage.model.CompetitorPrice;
import com.garage.model.Product;

public class PriceEvaluator {

	private final Product product;
	private final Collection<Double> prices;
	
	public PriceEvaluator(Product product, Collection<CompetitorPrice> collection) {
		this.product = product;

		this.prices = new HashSet<>();
	
		for(CompetitorPrice price: collection) {
			prices.add(price.getPrice());
		}
	}
	
	public Double evaluate() {

		Double price = null;
		
		if (!prices.isEmpty()) {
			
			Double sum = 0.0;
				
			for (Double cPrice : prices) {
				sum = sum + cPrice;
			}
			
			Double avg = sum / prices.size();
			
			Double avgHalf = avg/2;
			Double avgPlusHalf = avg + avgHalf;
			
			boolean validPrice = false;
			
			Evaluator<Double, Double> eval = getEvaluator(product);
			
			if(eval != null) {
				do {
					Double minPrice = findMinPrice();
					if(minPrice == null) break;
					
					price = eval.evaluate(minPrice);
					validPrice = isPriceValid(minPrice, price, avg, avgHalf, avgPlusHalf);
					
					//System.out.println("Price = "+price +" is Valid "+validPrice);
					
				} while(!validPrice && prices.size() > 0);
				if(!validPrice) price = null;
			}
			
		}
		
		return price;
	}
	
	private Evaluator<Double, Double> getEvaluator(Product product) {

		Evaluator<Double, Double> eval = null;

		if(product.getProductClassification().isDemandHigh() 
				&& product.getProductClassification().isSupplyHigh()) {
			eval = new SHDHPriceEvaluator();
		} else if(!product.getProductClassification().isDemandHigh() 
				&& product.getProductClassification().isSupplyHigh()) {
			eval = new SHDLPriceEvaluator();
		} else if(product.getProductClassification().isDemandHigh() 
				&& !product.getProductClassification().isSupplyHigh()) {
			eval = new SLDHPriceEvaluator();
		} else if(!product.getProductClassification().isDemandHigh() 
				&& !product.getProductClassification().isSupplyHigh()) {
			eval = new SLDLPriceEvaluator();
		} else {
			System.out.println("No Price Evaluator Found");
		}
		
		return eval;
	}

	//Find Minimum price
	public Double findMinPrice() {		
		if (!prices.isEmpty()) {
			Double min = null;
			for (Double cPrice : prices) {
				
				if(cPrice < 0) continue;
			
				if(min == null){ 
					min = cPrice;
				}
				
				if(cPrice < min) min = cPrice;
				
			}
			return min;
		}
		return null;
	}
	
	/*
	 * Prices less than 50% of average price are treated as promotion and not considered. 
	 * Prices more than 50% of average price are treated as data errors and not considered.
	 */
	public boolean isPriceValid(Double cPrice, Double price, Double avg, Double avgHalf, Double avgPlusHalf) {
		boolean priceValid = false;
		
		if (price != null) {
			
			System.out.println("Avg = "+avg+ " avgHalf = "+avgHalf+" avgPlusHalf = "+avgPlusHalf+ " Competitor Price = "+cPrice+ " Evaluated Price = "+price);
				
			if(price < avgHalf) {
				System.out.println("Cannot Use Promotion Price");
			} else if(price > avgPlusHalf) {
				System.out.println("Data Error");
			} else {
				priceValid = true;
			}
			
		}
		
		if(!priceValid && prices.size() > 0) {
			prices.remove(cPrice);
		} 
				
		return priceValid;
	}
}
