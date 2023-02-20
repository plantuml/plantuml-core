package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.geom.EnsureVisible;
import net.sourceforge.plantuml.klimt.shape.UImage;

public class DriverImageG2d implements UDriver<UImage, Graphics2D> {

	private final EnsureVisible visible;

	private final double dpiFactor;

	public DriverImageG2d(double dpiFactor, EnsureVisible visible) {
		this.visible = visible;
		this.dpiFactor = dpiFactor;
	}

	public void draw(UImage shape, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		visible.ensureVisible(x, y);
		visible.ensureVisible(x + shape.getWidth(), y + shape.getHeight());
		if (dpiFactor == 1) {
			g2d.drawImage(shape.getImage(1), (int) (x), (int) (y), null);
		} else {
			final AffineTransform back = g2d.getTransform();
			g2d.scale(1 / dpiFactor, 1 / dpiFactor);
			g2d.drawImage(shape.getImage(dpiFactor), (int) (x * dpiFactor), (int) (y * dpiFactor), null);
			g2d.setTransform(back);
		}
	}

}
