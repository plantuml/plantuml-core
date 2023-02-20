package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.geom.EnsureVisible;
import net.sourceforge.plantuml.klimt.shape.UEllipse;

public class DriverEllipseG2d extends DriverShadowedG2d implements UDriver<UEllipse, Graphics2D> {

	private final double dpiFactor;
	private final EnsureVisible visible;

	public DriverEllipseG2d(double dpiFactor, EnsureVisible visible) {
		this.dpiFactor = dpiFactor;
		this.visible = visible;
	}

	public void draw(UEllipse ellipse, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		g2d.setStroke(new BasicStroke((float) param.getStroke().getThickness()));
		visible.ensureVisible(x, y);
		visible.ensureVisible(x + ellipse.getWidth(), y + ellipse.getHeight());
		final HColor color = param.getColor();
		if (ellipse.getStart() == 0 && ellipse.getExtend() == 0) {
			final Shape shape = new Ellipse2D.Double(x, y, ellipse.getWidth(), ellipse.getHeight());

			// Shadow
			if (ellipse.getDeltaShadow() != 0) {
				drawShadow(g2d, shape, ellipse.getDeltaShadow(), dpiFactor);
			}

			final HColor back = param.getBackcolor();
			if (back instanceof HColorGradient) {
				final GradientPaint paint = DriverRectangleG2d.getPaintGradient(x, y, mapper, ellipse.getWidth(),
						ellipse.getHeight(), back);
				g2d.setPaint(paint);
				g2d.fill(shape);
				DriverRectangleG2d.drawBorder(param, color, mapper, ellipse, shape, g2d, x, y);
			} else {
				if (back.isTransparent() == false) {
					g2d.setColor(param.getBackcolor().toColor(mapper));
					DriverRectangleG2d.managePattern(param, g2d);
					g2d.fill(shape);
				}
				if (color.isTransparent() == false && color.equals(param.getBackcolor()) == false)
					DriverRectangleG2d.drawBorder(param, color, mapper, ellipse, shape, g2d, x, y);

			}
		} else {
			final Shape arc = new Arc2D.Double(x, y, ellipse.getWidth(), ellipse.getHeight(), round(ellipse.getStart()),
					round(ellipse.getExtend()), Arc2D.OPEN);
			if (color.isTransparent() == false) {
				g2d.setColor(color.toColor(mapper));
				g2d.draw(arc);
			}
		}
	}

	private static final double ROU = 5.0;

	static double round(double value) {
		return value;
		// final int v = (int) Math.round(value / ROU);
		// return v * ROU;
	}

}
