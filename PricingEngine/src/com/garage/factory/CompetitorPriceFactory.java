package com.garage.factory;

import com.garage.cache.ProductCache;
import com.garage.cache.ProductCompetativePriceCache;
import com.garage.model.CompetitorPrice;

public class CompetitorPriceFactory {

	public static CompetitorPrice createCompetitorPrice(String competitorName, String productName, double price) {
		CompetitorPrice competitorPrice = null;
		
		if(ProductCache.isProductPresent(productName)) {
			competitorPrice = new CompetitorPrice(competitorName, ProductCache.getProduct(productName), price);
			
			ProductCompetativePriceCache.add(productName, competitorPrice);
		}
		
		return competitorPrice;
	}
}
