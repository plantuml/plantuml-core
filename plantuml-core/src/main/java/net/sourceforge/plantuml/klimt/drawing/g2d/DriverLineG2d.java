package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.ULine;

public class DriverLineG2d extends DriverShadowedG2d implements UDriver<ULine, Graphics2D> {

	private final double dpiFactor;

	public DriverLineG2d(double dpiFactor) {
		this.dpiFactor = dpiFactor;
	}

	public void draw(ULine shape, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		final Shape line = new Line2D.Double(x, y, x + shape.getDX(), y + shape.getDY());
		manageStroke(param, g2d);
		// Shadow
		if (shape.getDeltaShadow() != 0) {
			drawShadow(g2d, line, shape.getDeltaShadow(), dpiFactor);
		}
		final HColor color = param.getColor();
		DriverRectangleG2d.drawBorder(param, color, mapper, shape, line, g2d, x, y);
//		g2d.setColor(mapper.getMappedColor(color));
//		g2d.draw(line);
	}

	static void manageStroke(UParam param, Graphics2D g2d) {
		manageStroke(param.getStroke(), g2d);
	}

	static void manageStroke(UStroke stroke, Graphics2D g2d) {
		final float thickness = (float) stroke.getThickness();
		if (stroke.getDashVisible() == 0) {
			g2d.setStroke(new BasicStroke(thickness));
		} else {
			final float dash1 = (float) stroke.getDashVisible();
			final float dash2 = (float) stroke.getDashSpace();
			final float[] style = { dash1, dash2 };
			g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, style, 0));
		}
	}
}
