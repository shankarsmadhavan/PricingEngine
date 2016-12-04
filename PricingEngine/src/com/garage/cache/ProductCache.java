package com.garage.cache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.garage.model.Product;

public class ProductCache {

	private static final ConcurrentHashMap<String, Product> cache = new ConcurrentHashMap<>();
	
	private ProductCache(){}
	
	public static void add(String key, Product value) {
		if(key == null || value == null) return;
		cache.putIfAbsent(key, value);
	}
	
	public static boolean isProductPresent(String key) {
		return key == null ? false : cache.containsKey(key);
	}
	
	public static Product getProduct(String key) {
		return key == null ? null : cache.get(key);
	}
	
	public static Collection<Product> getProducts() {
		return cache.values();
	}
}
