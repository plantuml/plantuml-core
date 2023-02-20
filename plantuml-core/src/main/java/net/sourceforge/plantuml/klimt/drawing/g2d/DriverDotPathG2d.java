package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.Graphics2D;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.geom.EnsureVisible;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public class DriverDotPathG2d implements UDriver<DotPath, Graphics2D> {

	private final EnsureVisible visible;

	public DriverDotPathG2d(EnsureVisible visible) {
		this.visible = visible;
	}

	public void draw(DotPath shape, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		DriverLineG2d.manageStroke(param, g2d);

		if (param.getColor().isTransparent() == false) {
			g2d.setColor(param.getColor().toColor(mapper));
			shape.draw(g2d, x, y);
			shape.manageEnsureVisible(x, y, visible);
		}
	}
}
