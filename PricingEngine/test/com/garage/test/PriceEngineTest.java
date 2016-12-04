package com.garage.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.garage.cache.ProductCache;
import com.garage.cache.ProductClassificationCache;
import com.garage.cache.ProductCompetativePriceCache;
import com.garage.evaluator.PriceEvaluator;
import com.garage.factory.CompetitorPriceFactory;
import com.garage.factory.ProductFactory;
import com.garage.model.CompetitorPrice;
import com.garage.model.Product;

public class PriceEngineTest {

	@BeforeClass
	public static void populateData() {
		ProductFactory.createProduct("flashdrive", "H", "H");
		ProductFactory.createProduct("ssd", "H", "L");
		ProductFactory.createProduct("mp3Player", "L", "H");
		//ProductFactory.createProduct("mp3Player", "H", "H");
		ProductFactory.createProduct("sock", "L", "L");
		
		CompetitorPriceFactory.createCompetitorPrice("X", "flashdrive", 1.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "flashdrive", 0.9);
		
		CompetitorPriceFactory.createCompetitorPrice("Z", "flashdrive", 1.1);
		CompetitorPriceFactory.createCompetitorPrice("X", "ssd", 10.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "ssd", 12.5);
		
		CompetitorPriceFactory.createCompetitorPrice("Z", "mp3player", 60);
		CompetitorPriceFactory.createCompetitorPrice("X", "mp3player", 20);
		CompetitorPriceFactory.createCompetitorPrice("Y", "mp3player", 50);

	}
	
	@Test
	public void checkProductCache() {
		Assert.assertEquals(true, ProductCache.isProductPresent("flashdrive"));
		Assert.assertEquals(true, ProductCache.isProductPresent("mp3Player"));
		Assert.assertEquals(true, ProductCache.isProductPresent("ssd"));
		Assert.assertEquals(true, ProductCache.isProductPresent("sock"));
	}
	
	@Test
	public void checkClassificationCache() {
		Assert.assertEquals(true, ProductClassificationCache.isProductClassificationPresent("HH"));
		Assert.assertEquals(true, ProductClassificationCache.isProductClassificationPresent("HL"));
		Assert.assertEquals(true, ProductClassificationCache.isProductClassificationPresent("LH"));
		Assert.assertEquals(true, ProductClassificationCache.isProductClassificationPresent("LL"));
	}
	
	@Test
	public void checkCompetitorProductPriceCache() {
		Assert.assertEquals(true, ProductCompetativePriceCache.isProductPricePresent("flashdrive"));
		Assert.assertEquals(true, ProductCompetativePriceCache.isProductPricePresent("ssd"));
		Assert.assertEquals(false, ProductCompetativePriceCache.isProductPricePresent("sock"));
	}
	
	
	private Double evaluatePriceEngine(String productName) {
		Product product = ProductCache.getProduct(productName);
		System.out.println("Product = "+product);
		
		Double ret = null;
		if(product != null) {
			List<CompetitorPrice> competitorPrices =  ProductCompetativePriceCache.getProduct(product.getName());
			if(competitorPrices !=null) {
				System.out.println("Competitor Price List = ");
				for(CompetitorPrice cPrice : ProductCompetativePriceCache.getProduct(product.getName())) {
					System.out.println(cPrice.getPrice());
				}
				PriceEvaluator priceEvaluator = new PriceEvaluator(product, competitorPrices);
				ret = priceEvaluator.evaluate();
				System.out.println("Best Product Price = "+ret);
			} else {
				System.out.println("No Price available");
			}
		}
		return ret;
	}
	
	@Test
	public void evaluatePriceEngine() {
		Map<String, Double> map = new HashMap<>();
		map.put("flashdrive", 0.9);
		map.put("ssd", 10.5);
		map.put("sock", null);
		
		map.put(null, null);
		
		
		for(Map.Entry<String, Double> entry : map.entrySet()) {
			Assert.assertEquals(entry.getValue(), evaluatePriceEngine(entry.getKey()));
		}
	}
}