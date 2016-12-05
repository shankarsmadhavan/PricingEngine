package com.garage.factory;

import com.garage.cache.ProductCache;
import com.garage.cache.ProductClassificationCache;
import com.garage.model.Product;
import com.garage.model.ProductClassification;

public class ProductFactory {

	public static Product createProduct(String productName, String supplyHigh, String demandHigh ) {
		
		if(!ProductCache.isProductPresent(productName)) {
			ProductClassification productClassification;
			if(!ProductClassificationCache.isProductClassificationPresent(supplyHigh+demandHigh)) {
				productClassification = createClassification(supplyHigh, demandHigh);
				ProductClassificationCache.add(supplyHigh+demandHigh, productClassification);
			}
			
			productClassification = ProductClassificationCache.getProductClassification(supplyHigh+demandHigh);
			
			ProductCache.add(productName, new Product(productName, productClassification));
		}
		
		return ProductCache.getProduct(productName);
	}
	
	private static ProductClassification createClassification(String supplyHigh, String demandHigh) {		
		return new ProductClassification("H".equalsIgnoreCase(supplyHigh), "H".equalsIgnoreCase(demandHigh));
	}
}
