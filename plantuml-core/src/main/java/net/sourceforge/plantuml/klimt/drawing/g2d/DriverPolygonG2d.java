package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.geom.EnsureVisible;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class DriverPolygonG2d extends DriverShadowedG2d implements UDriver<UPolygon, Graphics2D> {

	private final double dpiFactor;
	private final EnsureVisible visible;

	public DriverPolygonG2d(double dpiFactor, EnsureVisible visible) {
		this.dpiFactor = dpiFactor;
		this.visible = visible;
	}

	public void draw(UPolygon shape, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		g2d.setStroke(new BasicStroke((float) param.getStroke().getThickness()));

		final GeneralPath path = new GeneralPath();

		final HColor back = param.getBackcolor();

		XPoint2D last = null;
		for (XPoint2D pt : shape.getPoints()) {
			final double xp = pt.getX() + x;
			final double yp = pt.getY() + y;
			visible.ensureVisible(xp, yp);
			if (last == null)
				path.moveTo((float) xp, (float) yp);
			else
				path.lineTo((float) xp, (float) yp);

			last = new XPoint2D(xp, yp);
		}

		if (last != null)
			path.closePath();

		if (shape.getDeltaShadow() != 0)
			if (back.isTransparent())
				drawOnlyLineShadowSpecial(g2d, path, shape.getDeltaShadow(), dpiFactor);
			else
				drawShadow(g2d, path, shape.getDeltaShadow(), dpiFactor);

		if (back instanceof HColorGradient) {
			final HColorGradient gr = (HColorGradient) back;
			final char policy = gr.getPolicy();
			final GradientPaint paint;
			if (policy == '|')
				paint = new GradientPaint((float) x, (float) (y + shape.getHeight()) / 2,
						gr.getColor1().toColor(mapper), (float) (x + shape.getWidth()),
						(float) (y + shape.getHeight()) / 2, gr.getColor2().toColor(mapper));
			else if (policy == '\\')
				paint = new GradientPaint((float) x, (float) (y + shape.getHeight()), gr.getColor1().toColor(mapper),
						(float) (x + shape.getWidth()), (float) y, gr.getColor2().toColor(mapper));
			else if (policy == '-')
				paint = new GradientPaint((float) (x + shape.getWidth()) / 2, (float) y, gr.getColor1().toColor(mapper),
						(float) (x + shape.getWidth()) / 2, (float) (y + shape.getHeight()),
						gr.getColor2().toColor(mapper));
			else
				// for /
				paint = new GradientPaint((float) x, (float) y, gr.getColor1().toColor(mapper),
						(float) (x + shape.getWidth()), (float) (y + shape.getHeight()),
						gr.getColor2().toColor(mapper));

			g2d.setPaint(paint);
			g2d.fill(path);
		} else if (back.isTransparent() == false) {
			g2d.setColor(back.toColor(mapper));
			DriverRectangleG2d.managePattern(param, g2d);
			g2d.fill(path);
		}

		if (param.getColor().isTransparent() == false) {
			g2d.setColor(param.getColor().toColor(mapper));
			DriverLineG2d.manageStroke(param, g2d);
			g2d.draw(path);
		}
	}

}
