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
		ProductFactory.createProduct("ssd", "L", "L");
		ProductFactory.createProduct("mp3Player", "H", "L");
		ProductFactory.createProduct("sock", "L", "H");
		ProductFactory.createProduct("jacket", "L", "H");
		ProductFactory.createProduct("xbox", "H", "H");
		
		CompetitorPriceFactory.createCompetitorPrice("X", "flashdrive", 1.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "flashdrive", 0.9);
		
		CompetitorPriceFactory.createCompetitorPrice("Z", "flashdrive", 1.1);
		CompetitorPriceFactory.createCompetitorPrice("X", "ssd", 10.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "ssd", 12.5);
		CompetitorPriceFactory.createCompetitorPrice("Z", "ssd", 11.0);
		
		
		CompetitorPriceFactory.createCompetitorPrice("Z", "mp3Player", 60);
		CompetitorPriceFactory.createCompetitorPrice("X", "mp3Player", 20);
		CompetitorPriceFactory.createCompetitorPrice("Y", "mp3Player", 50);
		
		CompetitorPriceFactory.createCompetitorPrice("P", "jacket", 500);
		CompetitorPriceFactory.createCompetitorPrice("Q", "jacket", 150);
		CompetitorPriceFactory.createCompetitorPrice("R", "jacket", 350);

		CompetitorPriceFactory.createCompetitorPrice("P", "xbox", 900);
		CompetitorPriceFactory.createCompetitorPrice("Q", "xbox", 100);
		CompetitorPriceFactory.createCompetitorPrice("R", "xbox", 50);
	}
	
	@Test
	public void checkProductCache() {
		Assert.assertEquals(true, ProductCache.isProductPresent("flashdrive"));
		Assert.assertEquals(true, ProductCache.isProductPresent("mp3Player"));
		Assert.assertEquals(true, ProductCache.isProductPresent("ssd"));
		Assert.assertEquals(true, ProductCache.isProductPresent("sock"));
		Assert.assertEquals(true, ProductCache.isProductPresent("jacket"));
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
		Assert.assertEquals(true, ProductCompetativePriceCache.isProductPricePresent("jacket"));
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
		map.put("ssd", 11.0);
		map.put("sock", null);
		map.put("mp3Player", 47.5);
		map.put("jacket", 367.5);
		map.put(null, null);
		map.put("xbox", null);
		
		for(Map.Entry<String, Double> entry : map.entrySet()) {
			Assert.assertEquals(entry.getValue(), evaluatePriceEngine(entry.getKey()));
		}
	}
	
}