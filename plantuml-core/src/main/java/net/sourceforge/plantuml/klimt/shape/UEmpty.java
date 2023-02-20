package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class UEmpty implements UShape {

	private final double width;
	private final double height;

	public UEmpty(double width, double height) {
		if (width == 0) {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
	}

	public UEmpty(XDimension2D dim) {
		this(dim.getWidth(), dim.getHeight());
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
