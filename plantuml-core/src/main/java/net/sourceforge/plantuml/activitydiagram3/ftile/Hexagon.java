package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.creole.Stencil;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class Hexagon {

	final static public double hexagonHalfSize = 12;

	public static UPolygon asPolygon(double shadowing) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(hexagonHalfSize, 0);
		diams.addPoint(hexagonHalfSize * 2, hexagonHalfSize);
		diams.addPoint(hexagonHalfSize, hexagonHalfSize * 2);
		diams.addPoint(0, hexagonHalfSize);
		diams.addPoint(hexagonHalfSize, 0);

		// if (shadowing) {
		// diams.setDeltaShadow(3);
		// }
		diams.setDeltaShadow(shadowing);

		return diams;
	}

	public static UPolygon asPolygon(double shadowing, double width, double height) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(hexagonHalfSize, 0);
		diams.addPoint(width - hexagonHalfSize, 0);
		diams.addPoint(width, height / 2);
		diams.addPoint(width - hexagonHalfSize, height);
		diams.addPoint(hexagonHalfSize, height);
		diams.addPoint(0, height / 2);
		diams.addPoint(hexagonHalfSize, 0);

		// if (shadowing) {
		// diams.setDeltaShadow(3);
		// }
		diams.setDeltaShadow(shadowing);

		return diams;
	}

	public static Stencil asStencil(final TextBlock tb) {
		return new Stencil() {

			private final double getDeltaX(double height, double y) {
				final double p = y / height * 2;
				if (p <= 1) {
					return hexagonHalfSize * p;
				}
				return hexagonHalfSize * (2 - p);
			}

			public double getStartingX(StringBounder stringBounder, double y) {
				final XDimension2D dim = tb.calculateDimension(stringBounder);
				return -getDeltaX(dim.getHeight(), y);
			}

			public double getEndingX(StringBounder stringBounder, double y) {
				final XDimension2D dim = tb.calculateDimension(stringBounder);
				return dim.getWidth() + getDeltaX(dim.getHeight(), y);
			}
		};
	}

	public static UPolygon asPolygonSquare(double shadowing, double width, double height) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(width / 2, 0);
		diams.addPoint(width, height / 2);
		diams.addPoint(width / 2, height);
		diams.addPoint(0, height / 2);

		diams.setDeltaShadow(shadowing);

		return diams;
	}

}
