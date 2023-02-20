package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.AbstractShadowable;
import net.sourceforge.plantuml.klimt.UShapeSized;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class UEllipse extends AbstractShadowable implements UShapeSized {

	private final double width;
	private final double height;
	private final double start;
	private final double extend;

	public UEllipse(double width, double height) {
		this(width, height, 0, 0);
	}

	public UEllipse(double width, double height, double start, double extend) {
		this.width = width;
		this.height = height;
		this.start = start;
		this.extend = extend;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public final double getStart() {
		return start;
	}

	public final double getExtend() {
		return extend;
	}

	public XDimension2D getDimension() {
		return new XDimension2D(width, height);
	}

	public UEllipse bigger(double more) {
		final UEllipse result = new UEllipse(width + more, height + more);
		result.setDeltaShadow(getDeltaShadow());
		return result;
	}

	public UEllipse scale(double factor) {
		final UEllipse result = new UEllipse(width * factor, height * factor);
		result.setDeltaShadow(getDeltaShadow());
		return result;
	}

	public double getStartingX(double y) {
		y = y / height * 2;
		final double x = 1 - Math.sqrt(1 - (y - 1) * (y - 1));
		return x * width / 2;
	}

	public double getEndingX(double y) {
		y = y / height * 2;
		final double x = 1 + Math.sqrt(1 - (y - 1) * (y - 1));
		return x * width / 2;
	}

	public XPoint2D getPointAtAngle(double alpha) {
		final double x = width / 2 + width / 2 * Math.cos(alpha);
		final double y = height / 2 + height / 2 * Math.sin(alpha);
		return new XPoint2D(x, y);
	}

}
