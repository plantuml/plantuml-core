package net.sourceforge.plantuml.ugraphic;

import java.awt.Color;

public class ShadowManager {
	
	// http://www.w3schools.com/svg/svg_feoffset.asp

	private final int c1;
	private final int c2;

	public ShadowManager(int c1, int c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public double[] getShadowDeltaPoints(double deltaShadow, double diff, double[] points) {
		assert points.length % 2 == 0;
		double cx = 0;
		double cy = 0;
		for (int i = 0; i < points.length; i += 2) {
			cx += points[i];
			cy += points[i + 1];
		}
		final int nbPoints = points.length / 2;

		cx = cx / nbPoints;
		cy = cy / nbPoints;

		final double[] result = new double[points.length];
		for (int i = 0; i < result.length; i += 2) {
			final double diffx = points[i] > cx ? -diff : diff;
			final double diffy = points[i + 1] > cy ? -diff : diff;
			result[i] = points[i] + diffx + deltaShadow;
			result[i + 1] = points[i + 1] + diffy + deltaShadow;
		}
		return result;
	}

	public Color getColor(double delta, double total) {
		final int c = (int) (c2 + 1.0 * delta / total * (c1 - c2));
		return new Color(c, c, c);
	}

}
