package net.sourceforge.plantuml.klimt.shape;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.AbstractShadowable;
import net.sourceforge.plantuml.klimt.compress.CompressionMode;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class UPolygon extends AbstractShadowable {

	private final List<XPoint2D> all = new ArrayList<XPoint2D>();
	private final String name;

	private MinMax minmax = MinMax.getEmpty(false);

	public UPolygon() {
		this((String) null);
	}

	public UPolygon(List<XPoint2D> points) {
		this((String) null);
		all.addAll(points);
		for (XPoint2D pt : all)
			manageMinMax(pt.getX(), pt.getY());

	}

	public UPolygon(String name) {
		this.name = name;
	}

	public XPoint2D getPoint(int idx) {
		return all.get(idx);
	}

	public XPoint2D checkMiddleContactForSpecificTriangle(XPoint2D center) {
		for (int i = 0; i < all.size() - 1; i++) {
			final XPoint2D pt1 = all.get(i);
			final XPoint2D pt2 = all.get(i + 1);
			final XPoint2D middle = new XPoint2D((pt1.getX() + pt2.getX()) / 2, (pt1.getY() + pt2.getY()) / 2);
			final double delta = middle.distance(center);
			if (delta < 1)
				return all.get((i + all.size() - 1) % all.size());

		}
		return null;
	}

	public void addPoint(double x, double y) {
		all.add(new XPoint2D(x, y));
		manageMinMax(x, y);
	}

	public void addPoint(XPoint2D point) {
		addPoint(point.getX(), point.getY());
	}

	private void manageMinMax(double x, double y) {
		minmax = minmax.addPoint(x, y);
	}

	public List<XPoint2D> getPoints() {
		return all;
	}

	public UPolygon translate(double dx, double dy) {
		final UPolygon result = new UPolygon();
		for (XPoint2D pt : all)
			result.addPoint(pt.x + dx, pt.y + dy);

		return result;
	}

	public void rotate(double theta) {
		if (theta == 0)
			return;
		affine(AffineTransform.getRotateInstance(theta));
	}

	public void affine(AffineTransform rotate) {
		for (int i = 0; i < all.size(); i++)
			all.set(i, all.get(i).transform(rotate));

	}

	@Override
	public String toString() {
		if (name != null)
			return name;

		return super.toString() + " " + all;
	}

	public double getHeight() {
		return minmax.getHeight();
	}

	public double getWidth() {
		return minmax.getWidth();
	}

	public double getMinX() {
		return minmax.getMinX();
	}

	public double getMinY() {
		return minmax.getMinY();
	}

	public double getMaxX() {
		return minmax.getMaxX();

	}

	public double getMaxY() {
		return minmax.getMaxY();
	}

	public MinMax getMinMax() {
		return minmax;
	}

	public double[] getPointArray(double x, double y) {
		final double points[] = new double[getPoints().size() * 2];
		int i = 0;

		for (XPoint2D pt : getPoints()) {
			points[i++] = pt.getX() + x;
			points[i++] = pt.getY() + y;
		}
		return points;
	}

	private CompressionMode compressionMode;

	public final CompressionMode getCompressionMode() {
		return compressionMode;
	}

	public final void setCompressionMode(CompressionMode compressionMode) {
		this.compressionMode = compressionMode;
	}

}