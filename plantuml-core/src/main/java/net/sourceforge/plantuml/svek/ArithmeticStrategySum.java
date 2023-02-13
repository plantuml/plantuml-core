package net.sourceforge.plantuml.svek;

public class ArithmeticStrategySum implements ArithmeticStrategy {

	private double sum;

	public void eat(double v) {
		sum += v;
	}

	public double getResult() {
		return sum;
	}

}
