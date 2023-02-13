package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface Element {

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y);

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse);

}
