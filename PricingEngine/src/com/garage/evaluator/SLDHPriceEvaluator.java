package com.garage.evaluator;

/*
 * If Supply is Low and Demand is High, Product is sold at 5 % more than chosen price.
 * */

public class SLDHPriceEvaluator implements Evaluator<Double, Double>{

	public SLDHPriceEvaluator() {
	}
	
	@Override
	public Double evaluate(Double price) {
		return price + (((price * 5)/100));
	}
	
}
