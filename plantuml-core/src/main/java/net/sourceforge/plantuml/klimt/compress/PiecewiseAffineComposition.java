package net.sourceforge.plantuml.klimt.compress;

public class PiecewiseAffineComposition implements PiecewiseAffineTransform {

	private final PiecewiseAffineComposition first;
	private final PiecewiseAffineComposition second;

	public PiecewiseAffineComposition(PiecewiseAffineComposition first, PiecewiseAffineComposition second) {
		this.first = first;
		this.second = second;
	}

	public double transform(double v) {
		return second.transform(first.transform(v));
	}

}