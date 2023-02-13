package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.cucadiagram.dot.Neighborhood;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.InnerStrategy;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EntityImageProtected extends AbstractTextBlock implements IEntityImage, Untranslated, WithPorts {

	private final IEntityImage orig;
	private final double border;
	private final Bibliotekon bibliotekon;
	private final Neighborhood neighborhood;

	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		final XRectangle2D result = orig.getInnerPosition(member, stringBounder, strategy);
		return new XRectangle2D(result.getMinX() + border, result.getMinY() + border, result.getWidth(),
				result.getHeight());
	}

	public EntityImageProtected(IEntityImage orig, double border, Neighborhood neighborhood, Bibliotekon bibliotekon) {
		this.orig = orig;
		this.border = border;
		this.bibliotekon = bibliotekon;
		this.neighborhood = neighborhood;
	}

	public boolean isHidden() {
		return orig.isHidden();
	}

	public HColor getBackcolor() {
		return orig.getBackcolor();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return orig.calculateDimension(stringBounder).delta(2 * border);
	}

	public void drawU(UGraphic ug) {
		orig.drawU(ug.apply(new UTranslate(border, border)));
	}

	public void drawUntranslated(UGraphic ug, double minX, double minY) {
		final XDimension2D dim = orig.calculateDimension(ug.getStringBounder());
		neighborhood.drawU(ug, minX + border, minY + border, bibliotekon, dim);
	}

	public ShapeType getShapeType() {
		return orig.getShapeType();
	}

	public Margins getShield(StringBounder stringBounder) {
		return orig.getShield(stringBounder);
	}

	public double getOverscanX(StringBounder stringBounder) {
		return orig.getOverscanX(stringBounder);
	}

	@Override
	public Ports getPorts(StringBounder stringBounder) {
		return ((WithPorts) orig).getPorts(stringBounder);
	}

}
