package com.garage.evaluator;

/*
 * If Supply is Low and Demand is Low, Product is sold at 10 % more than chosen price.
 * */
public class SLDLPriceEvaluator implements Evaluator<Double, Double>{

	public SLDLPriceEvaluator() {
	}
	
	@Override
	public Double evaluate(Double price) {
		return price + ((price * 10)/100);
	}
	
}
