package com.garage.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.garage.model.CompetitorPrice;

public class ProductCompetativePriceCache {

private static final Map<String, List<CompetitorPrice>> cache = new HashMap<>();

	private ProductCompetativePriceCache() {}

	public static synchronized void add(String key, CompetitorPrice value) {
		if(key == null || value == null) return;
		
		List<CompetitorPrice> list;
		if(cache.containsKey(key)) {
			list = cache.get(key);
		} else {
			list = new ArrayList<>();
			cache.put(key, list);
		}
		list.add(value);
	}
	
	public static boolean isProductPricePresent(String key) {
		return key == null ? false : cache.containsKey(key);
	}
	
	public static List<CompetitorPrice> getProduct(String key) {
		return key == null ? null : cache.get(key);
	}

}
