package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class WrappedElement implements Element {

	private final Element wrapped;

	public WrappedElement(Element element) {
		this.wrapped = element;
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		return wrapped.getPreferredDimension(stringBounder, 0, 0);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		wrapped.drawU(ug, zIndex, dimToUse);
	}

}
