package net.sourceforge.plantuml.mindmap;

import java.awt.geom.Line2D;

public class SymetricalTeePositioned {

	private final SymetricalTee tee;
	private double y;

	@Override
	public String toString() {
		return "y=" + y + " " + tee;
	}

	public SymetricalTeePositioned(SymetricalTee tee) {
		this(tee, 0);
	}

	private SymetricalTeePositioned(SymetricalTee tee, double y) {
		this.tee = tee;
		this.y = y;
	}

	public void moveSoThatSegmentA1isOn(double newY) {
		final double current = getSegmentA1().getY1();
		y += newY - current;
	}

	public void moveSoThatSegmentA2isOn(double newY) {
		final double current = getSegmentA2().getY1();
		y += newY - current;
	}

	public void move(double delta) {
		y += delta;
	}

	public Line2D getSegmentA1() {
		return new Line2D.Double(0, y - tee.getThickness1() / 2, tee.getElongation1(), y - tee.getThickness1() / 2);
	}

	public Line2D getSegmentB1() {
		return new Line2D.Double(0, y + tee.getThickness1() / 2, tee.getElongation1(), y + tee.getThickness1() / 2);
	}

	public Line2D getSegmentA2() {
		return new Line2D.Double(tee.getElongation1(), y - tee.getThickness2() / 2,
				tee.getElongation1() + tee.getElongation2(), y - tee.getThickness2() / 2);
	}

	public Line2D getSegmentB2() {
		return new Line2D.Double(tee.getElongation1(), y + tee.getThickness2() / 2,
				tee.getElongation1() + tee.getElongation2(), y + tee.getThickness2() / 2);
	}

	public double getMaxX() {
		return tee.getElongation1() + tee.getElongation2();
	}

	public double getMaxY() {
		return y + Math.max(tee.getThickness1() / 2, tee.getThickness2() / 2);
	}

	public double getMinY() {
		return y - Math.max(tee.getThickness1() / 2, tee.getThickness2() / 2);
	}

	public final double getY() {
		return y;
	}

	public SymetricalTeePositioned getMax(SymetricalTeePositioned other) {
		if (this.tee != other.tee)
			throw new IllegalArgumentException();

		if (other.y > this.y)
			return other;

		return this;
	}

}
