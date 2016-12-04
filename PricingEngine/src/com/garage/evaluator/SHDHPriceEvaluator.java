package com.garage.evaluator;

/*
 * If Supply is High and Demand is High, Product is sold at same price as chosen price.
 * */
public class SHDHPriceEvaluator implements Evaluator<Double, Double>{
	
	public SHDHPriceEvaluator() {
		
	}
	
	@Override
	public Double evaluate(Double price) {
		return price;
	}
	
}
