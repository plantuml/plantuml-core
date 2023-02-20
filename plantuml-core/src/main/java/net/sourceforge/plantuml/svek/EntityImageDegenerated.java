package net.sourceforge.plantuml.svek;

import net.atmp.InnerStrategy;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.UEmpty;

public class EntityImageDegenerated implements IEntityImage {

	private final IEntityImage orig;
	private final double delta = 7;
	private final HColor backcolor;

	public EntityImageDegenerated(IEntityImage orig, HColor backcolor) {
		this.orig = orig;
		this.backcolor = backcolor;
	}

	public boolean isHidden() {
		return orig.isHidden();
	}

	public HColor getBackcolor() {
		// return orig.getBackcolor();
		return backcolor;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return orig.calculateDimension(stringBounder).delta(delta * 2, delta * 2);
	}

	public MinMax getMinMax(StringBounder stringBounder) {
		return orig.getMinMax(stringBounder);
		// return orig.getMinMax(stringBounder).translate(new UTranslate(delta, delta));
		// return orig.getMinMax(stringBounder).appendToMax(delta, delta);
	}

	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		return orig.getInnerPosition(member, stringBounder, strategy);
	}

	public void drawU(UGraphic ug) {
		orig.drawU(ug.apply(new UTranslate(delta, delta)));

		final XDimension2D dim = calculateDimension(ug.getStringBounder());
		ug.apply(new UTranslate(dim.getWidth() - delta, dim.getHeight() - delta)).draw(new UEmpty(delta, delta));

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

}
