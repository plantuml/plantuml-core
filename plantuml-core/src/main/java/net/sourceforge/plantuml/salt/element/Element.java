package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public interface Element {

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y);

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse);

}
