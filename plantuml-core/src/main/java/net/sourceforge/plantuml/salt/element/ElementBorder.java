package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.utils.MathUtils;

public class ElementBorder extends AbstractElement {

	private Element north = new ElementEmpty();
	private Element south = new ElementEmpty();
	private Element east = new ElementEmpty();
	private Element west = new ElementEmpty();
	private Element center = new ElementEmpty();

	public final void setNorth(Element north) {
		this.north = north;
	}

	public final void setSouth(Element south) {
		this.south = south;
	}

	public final void setEast(Element east) {
		this.east = east;
	}

	public final void setWest(Element west) {
		this.west = west;
	}

	public final void setCenter(Element center) {
		this.center = center;
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dimNorth = north.getPreferredDimension(stringBounder, 0, 0);
		final XDimension2D dimSouth = south.getPreferredDimension(stringBounder, 0, 0);
		final XDimension2D dimEast = east.getPreferredDimension(stringBounder, 0, 0);
		final XDimension2D dimWest = west.getPreferredDimension(stringBounder, 0, 0);
		final XPoint2D pA = new XPoint2D(dimWest.getWidth(), dimNorth.getHeight());
		final XPoint2D pB = new XPoint2D(dimToUse.getWidth() - dimEast.getWidth(), dimNorth.getHeight());
		final XPoint2D pC = new XPoint2D(dimWest.getWidth(), dimToUse.getHeight() - dimSouth.getHeight());
		// final XPoint2D pD = new XPoint2D(dimToUse.getWidth() - dimEast.getWidth(), dimToUse.getHeight()
		// - dimSouth.getHeight());

		north.drawU(ug, zIndex, dimToUse);
		south.drawU(ug.apply(UTranslate.dy(pC.getY())), zIndex, dimToUse);
		west.drawU(ug.apply(UTranslate.dy(pA.getY())), zIndex, dimToUse);
		east.drawU(ug.apply(new UTranslate(pB.getX(), pB.getY())), zIndex, dimToUse);
		center.drawU(ug.apply(new UTranslate(pA.getX(), pA.getY())), zIndex, dimToUse);
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		final XDimension2D dimNorth = north.getPreferredDimension(stringBounder, x, y);
		final XDimension2D dimSouth = south.getPreferredDimension(stringBounder, x, y);
		final XDimension2D dimEast = east.getPreferredDimension(stringBounder, x, y);
		final XDimension2D dimWest = west.getPreferredDimension(stringBounder, x, y);
		final XDimension2D dimCenter = center.getPreferredDimension(stringBounder, x, y);
		final double width = MathUtils.max(dimNorth.getWidth(), dimSouth.getWidth(),
				dimWest.getWidth() + dimCenter.getWidth() + dimEast.getWidth());
		final double height = dimNorth.getHeight()
				+ MathUtils.max(dimWest.getHeight(), dimCenter.getHeight(), dimEast.getHeight()) + dimSouth.getHeight();
		return new XDimension2D(width, height);
	}

}
