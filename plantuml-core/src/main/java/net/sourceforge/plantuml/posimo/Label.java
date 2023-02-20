package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.Positionable;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class Label implements Positionable {

	private double width;
	private double height;
	private double x;
	private double y;

	public Label(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public final void setCenterX(double center) {
		this.x = center - width / 2;
	}

	public final void setCenterY(double center) {
		this.y = center - height / 2;
	}

	public XPoint2D getPosition() {
		return new XPoint2D(x, y);
	}

	public XDimension2D getSize() {
		return new XDimension2D(width, height);
	}

	public final void setWidth(double width) {
		this.width = width;
	}

	public final void setHeight(double height) {
		this.height = height;
	}

	public final void setX(double x) {
		this.x = x;
	}

	public final void setY(double y) {
		this.y = y;
	}

	public void moveSvek(double deltaX, double deltaY) {
		throw new UnsupportedOperationException();
	}

}
