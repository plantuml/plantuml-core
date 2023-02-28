package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.Graphics2D;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.UPixel;

public class DriverPixelG2d implements UDriver<UPixel, Graphics2D> {

	public void draw(UPixel pixel, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		g2d.setColor(param.getColor().toColor(mapper));
		g2d.fillRect((int) x, (int) y, 1, 1);
	}

}