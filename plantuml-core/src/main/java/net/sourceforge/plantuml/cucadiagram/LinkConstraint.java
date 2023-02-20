package net.sourceforge.plantuml.cucadiagram;

import net.atmp.Link;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.style.ISkinParam;

public class LinkConstraint {

	private final Link link1;
	private final Link link2;
	private final Display display;

	private double x1;
	private double y1;
	private double x2;
	private double y2;

	public LinkConstraint(Link link1, Link link2, Display display) {
		this.link1 = link1;
		this.link2 = link2;
		this.display = display;
	}

	public void setPosition(Link link, XPoint2D pt) {
		if (link == link1) {
			x1 = pt.getX();
			y1 = pt.getY();
		} else if (link == link2) {
			x2 = pt.getX();
			y2 = pt.getY();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void drawMe(UGraphic ug, ISkinParam skinParam) {
		if (x1 == 0 && y1 == 0) {
			return;
		}
		if (x2 == 0 && y2 == 0) {
			return;
		}
		ug = ug.apply(HColors.BLACK);
//		ug.apply(new UTranslate(x1, y1)).draw(new URectangle(10, 10));
//		ug.apply(new UTranslate(x2, y2)).draw(new URectangle(10, 10));

		final ULine line = new ULine(x2 - x1, y2 - y1);
		ug = ug.apply(new UStroke(3, 3, 1));
		ug.apply(new UTranslate(x1, y1)).draw(line);

		final TextBlock label = display.create(FontConfiguration.create(skinParam, FontParam.ARROW, null),
				HorizontalAlignment.CENTER, skinParam);
		final XDimension2D dimLabel = label.calculateDimension(ug.getStringBounder());
		final double x = (x1 + x2) / 2 - dimLabel.getWidth() / 2;
		final double y = (y1 + y2) / 2 - dimLabel.getHeight() / 2;
		label.drawU(ug.apply(new UTranslate(x, y)));

	}

}
