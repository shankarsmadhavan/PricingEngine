package com.garage.cache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.garage.model.ProductClassification;

public class ProductClassificationCache {

	private static final ConcurrentHashMap<String, ProductClassification> cache = new ConcurrentHashMap<>();
	
	private ProductClassificationCache() {}
	
	public static void add(String key, ProductClassification value) {
		if(key == null || value == null) return;
		cache.putIfAbsent(key, value);
	}
	
	public static boolean isProductClassificationPresent(String key) {
		return key == null ? false : cache.containsKey(key);
	}
	
	public static ProductClassification getProductClassification(String key) {
		return key == null ? null : cache.get(key);
	}
	
	public static Collection<ProductClassification> getProductClassifications() {
		return cache.values();
	}
}
