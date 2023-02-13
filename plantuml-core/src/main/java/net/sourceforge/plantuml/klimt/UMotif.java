package net.sourceforge.plantuml.klimt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.awt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class UMotif {

	private final List<XPoint2D> points = new ArrayList<>();

	public UMotif(int... data) {
		assert data.length % 2 == 0;
		for (int i = 0; i < data.length; i += 2) {
			points.add(new XPoint2D(data[i], data[i + 1]));
		}
	}

	public UMotif(String s) {
		XPoint2D last = new XPoint2D();
		for (int i = 0; i < s.length(); i++) {
			final XPoint2D read = convertPoint(s.charAt(i));
			last = last.move(read);
			points.add(last);
		}
	}

	public double getLength() {
		return points.get(0).distance(points.get(points.size() - 1));
	}

	List<XPoint2D> getPoints() {
		return Collections.unmodifiableList(points);
	}

	public DotPath getRectangle(double width, double height) {
		final double len = getLength();
		final int nb1 = (int) (width / len);
		DotPath h1 = drawHorizontal(0, 0, nb1);
		final int nb2 = (int) (height / len);
		final DotPath v1 = drawVertical(h1.getEndPoint().getX(), h1.getEndPoint().getY(), nb2);
		h1 = h1.addAfter(v1);
		return h1;
	}

	public static XPoint2D convertPoint(char c) {
		final int v = convertFromChar(c);
		final int x = v % 7;
		final int y = v / 7;
		return new XPoint2D(x - 3, y - 3);
	}

	public static int convertFromChar(char c) {
		if (c >= 'A' && c <= 'Z') {
			return c - 'A';
		}
		if (c >= 'a' && c <= 'w') {
			return c - 'a' + 26;
		}
		throw new IllegalArgumentException();
	}

	public void drawHorizontal(UGraphic ug, double x, double y, int nb) {
		final DotPath path = drawHorizontal(x, y, nb);
		ug.draw(path);
	}

	public void drawVertical(UGraphic ug, double x, double y, int nb) {
		final DotPath path = drawVertical(x, y, nb);
		ug.draw(path);
	}

	DotPath drawHorizontal(double x, double y, int nb) {
		DotPath path = new DotPath();
		for (int i = 0; i < nb; i++) {
			path = addHorizontal(x, y, path);
			x = path.getEndPoint().getX();
			y = path.getEndPoint().getY();
		}
		return path;
	}

	DotPath drawVertical(double x, double y, int nb) {
		DotPath path = new DotPath();
		for (int i = 0; i < nb; i++) {
			path = addVertical(x, y, path);
			x = path.getEndPoint().getX();
			y = path.getEndPoint().getY();
		}
		return path;
	}

	private DotPath addHorizontal(double x, double y, DotPath path) {
		double lastx = 0;
		double lasty = 0;
		for (XPoint2D p : points) {
			final double x1 = lastx + x;
			final double y1 = lasty + y;
			final double x2 = p.getX() + x;
			final double y2 = p.getY() + y;
			path = path.addAfter(new XCubicCurve2D(x1, y1, x1, y1, x2, y2, x2, y2));
			lastx = p.getX();
			lasty = p.getY();
		}
		return path;
	}

	private DotPath addVertical(double x, double y, DotPath path) {
		double lastx = 0;
		double lasty = 0;
		for (XPoint2D p : points) {
			final double x1 = lastx + x;
			final double y1 = lasty + y;
			final double x2 = p.getY() + x;
			final double y2 = p.getX() + y;
			path = path.addAfter(new XCubicCurve2D(x1, y1, x1, y1, x2, y2, x2, y2));
			lastx = p.getY();
			lasty = p.getX();
		}
		return path;
	}

}
