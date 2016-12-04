package com.garage.main;

import java.util.List;

import com.garage.cache.ProductCache;
import com.garage.cache.ProductCompetativePriceCache;
import com.garage.evaluator.PriceEvaluator;
import com.garage.factory.CompetitorPriceFactory;
import com.garage.factory.ProductFactory;
import com.garage.model.CompetitorPrice;
import com.garage.model.Product;

public class PriceEngineMain {

	public static void main(String[] args) {
		//Input 1
		/*
		ProductFactory.createProduct("flashdrive", "H", "H");
		ProductFactory.createProduct("ssd", "L", "H");
		
		
		CompetitorPriceFactory.createCompetitorPrice("X", "flashdrive", 1.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "flashdrive", 0.9);
		
		CompetitorPriceFactory.createCompetitorPrice("Z", "flashdrive", 1.1);
		CompetitorPriceFactory.createCompetitorPrice("X", "ssd", 10.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "ssd", 12.5);
		
		*/
		//Input 2
		
		ProductFactory.createProduct("mp3player", "H", "H");
		ProductFactory.createProduct("ssd", "L", "L");
		
		
		CompetitorPriceFactory.createCompetitorPrice("X", "mp3player", 60.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "mp3player", 20.0);
		//CompetitorPriceFactory.createCompetitorPrice("Z", "mp3player", 45.0);
		CompetitorPriceFactory.createCompetitorPrice("Z", "mp3player", 50.0);
		
		
		CompetitorPriceFactory.createCompetitorPrice("W", "ssd", 11.0);
		CompetitorPriceFactory.createCompetitorPrice("X", "ssd", 12.0);
		//CompetitorPriceFactory.createCompetitorPrice("V", "ssd", 10.0);
		CompetitorPriceFactory.createCompetitorPrice("Y", "ssd", 11.0);
		CompetitorPriceFactory.createCompetitorPrice("Z", "ssd", 12.0);
		
		for(Product product : ProductCache.getProducts()) {
			System.out.println("Product = "+product);
			
			List<CompetitorPrice> competitorPrices =  ProductCompetativePriceCache.getProduct(product.getName());
			if(competitorPrices != null) {
				System.out.println("Competitor Price List = ");
				for(CompetitorPrice cPrice : ProductCompetativePriceCache.getProduct(product.getName())) {
					System.out.println(cPrice.getPrice());
				}
				PriceEvaluator priceEvaluator = new PriceEvaluator(product, competitorPrices);
				System.out.println("Best Product Price = "+priceEvaluator.evaluate());
			} else {
				System.out.println("No Price available");
			}
		}
		
	}
}
