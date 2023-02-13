package net.sourceforge.plantuml.mindmap;

public class SymetricalTee {

	private final double thickness1;
	private final double elongation1;
	private final double thickness2;
	private final double elongation2;

	@Override
	public String toString() {
		return "t1=" + thickness1 + " e1=" + elongation1 + " t2=" + thickness2 + " e2=" + elongation2;
	}

	public SymetricalTee(double thickness1, double elongation1, double thickness2, double elongation2) {
		this.thickness1 = thickness1;
		this.elongation1 = elongation1;
		this.thickness2 = thickness2;
		this.elongation2 = elongation2;
	}

	public double getThickness1() {
		return thickness1;
	}

	public double getElongation1() {
		return elongation1;
	}

	public double getThickness2() {
		return thickness2;
	}

	public double getElongation2() {
		return elongation2;
	}

	public double getFullElongation() {
		return elongation1 + elongation2;
	}

	public double getFullThickness() {
		return Math.max(thickness1, thickness2);
	}

}
