// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.abel.Entity;
import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.Rankdir;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;

public class EntityImageSynchroBar extends AbstractEntityImage {

	// private final SName styleName;

	public EntityImageSynchroBar(Entity entity, ISkinParam skinParam, SName styleName) {
		super(entity, skinParam);
		// this.styleName = styleName;
	}

	private StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activityBar);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		if (getSkinParam().getRankdir() == Rankdir.LEFT_TO_RIGHT)
			return new XDimension2D(8, 80);

		return new XDimension2D(80, 8);
	}

	final public void drawU(UGraphic ug) {
		final XDimension2D dim = calculateDimension(ug.getStringBounder());
		final Shadowable rect = URectangle.build(dim.getWidth(), dim.getHeight());

		final Style style = getStyleSignature().withTOBECHANGED(getEntity().getStereotype())
				.getMergedStyle(getSkinParam().getCurrentStyleBuilder());
		final HColor color = style.value(PName.BackGroundColor).asColor(getSkinParam().getIHtmlColorSet());
		final double shadowing = style.value(PName.Shadowing).asDouble();

		rect.setDeltaShadow(shadowing);
		ug.apply(HColors.none()).apply(color.bg()).draw(rect);
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

}
