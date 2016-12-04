package com.garage.factory;

import com.garage.cache.ProductCache;
import com.garage.cache.ProductClassificationCache;
import com.garage.model.Product;
import com.garage.model.ProductClassification;

public class ProductFactory {

	public static Product createProduct(String productName, String supplyHigh, String demandHigh ) {
		
		if(!ProductCache.isProductPresent(productName)) {
			ProductClassification productClassification;
			if(!ProductClassificationCache.isProductClassificationPresent(demandHigh+supplyHigh)) {
				productClassification = createClassification(demandHigh, supplyHigh);
				ProductClassificationCache.add(demandHigh+supplyHigh, productClassification);
			} else {
				productClassification = ProductClassificationCache.getProductClassification(demandHigh+supplyHigh);
			}
			ProductCache.add(productName, new Product(productName, productClassification));
		} else {
			
		}
		
		return ProductCache.getProduct(productName);
	}
	
	private static ProductClassification createClassification(String supplyHigh, String demandHigh) {		
		return new ProductClassification("H".equalsIgnoreCase(supplyHigh), "H".equalsIgnoreCase(demandHigh));
	}
}
