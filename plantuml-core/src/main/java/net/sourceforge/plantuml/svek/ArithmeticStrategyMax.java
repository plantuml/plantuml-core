package net.sourceforge.plantuml.svek;

public class ArithmeticStrategyMax implements ArithmeticStrategy {

	private double max;

	public void eat(double v) {
		if (v > max) {
			max = v;
		}
	}

	public double getResult() {
		return max;
	}

}
