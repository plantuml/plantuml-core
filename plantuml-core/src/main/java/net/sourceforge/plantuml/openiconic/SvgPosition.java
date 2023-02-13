package net.sourceforge.plantuml.openiconic;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class SvgPosition {

	final private SvgCommandNumber x;
	final private SvgCommandNumber y;

	public SvgPosition() {
		this(new SvgCommandNumber("0"), new SvgCommandNumber("0"));
	}

	public SvgPosition(SvgCommandNumber x, SvgCommandNumber y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return x.toSvg() + "," + y.toSvg();
	}

	public SvgPosition(double x, double y) {
		this.x = new SvgCommandNumber(x);
		this.y = new SvgCommandNumber(y);
	}

	public SvgCommandNumber getX() {
		return x;
	}

	public SvgCommandNumber getY() {
		return y;
	}

	public double getXDouble() {
		return x.getDouble();
	}

	public double getYDouble() {
		return y.getDouble();
	}

	public SvgPosition add(SvgPosition other) {
		return new SvgPosition(x.add(other.x), y.add(other.y));
	}

	public SvgPosition getMirror(SvgPosition tobeMirrored) {
		final double centerX = getXDouble();
		final double centerY = getYDouble();
		// x1+x2 = 2*xc
		final double x = 2 * centerX - tobeMirrored.getXDouble();
		final double y = 2 * centerY - tobeMirrored.getYDouble();
		return new SvgPosition(x, y);
	}

	public XPoint2D affine(AffineTransform at) {
		final XPoint2D tmp = new XPoint2D(getXDouble(), getYDouble()).transform(at);
		return tmp;
	}
}
