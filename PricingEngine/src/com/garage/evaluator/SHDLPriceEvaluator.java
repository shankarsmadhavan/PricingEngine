package com.garage.evaluator;

/*
 * If Supply is High and Demand is Low, Product is sold at 5 % less than chosen price.
 * */
public class SHDLPriceEvaluator implements Evaluator<Double, Double>{

	public SHDLPriceEvaluator() {
	}
	
	@Override
	public Double evaluate(Double price) {
		return price - (((price * 5)/100));
	}
	
}
