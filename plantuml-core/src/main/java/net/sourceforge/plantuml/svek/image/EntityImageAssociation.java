package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UPolygon;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;

public class EntityImageAssociation extends AbstractEntityImage {

	final private static int SIZE = 12;
	private final SName sname;

	public EntityImageAssociation(Entity entity, ISkinParam skinParam, SName sname) {
		super(entity, skinParam);
		this.sname = sname;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE * 2, SIZE * 2);
	}

	private Style getStyle() {
		return getStyleSignature().getMergedStyle(getSkinParam().getCurrentStyleBuilder());
	}

	private StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, sname, SName.diamond);
	}

	final public void drawU(UGraphic ug) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(SIZE, 0);
		diams.addPoint(SIZE * 2, SIZE);
		diams.addPoint(SIZE, SIZE * 2);
		diams.addPoint(0, SIZE);
		diams.addPoint(SIZE, 0);

		final Style style = getStyle();
		final HColor borderColor = style.value(PName.LineColor).asColor(getSkinParam().getIHtmlColorSet());
		final HColor backgroundColor = style.value(PName.BackGroundColor).asColor(getSkinParam().getIHtmlColorSet());
		final double shadow = style.value(PName.Shadowing).asDouble();
		final UStroke stroke = style.getStroke();

		diams.setDeltaShadow(shadow);
		ug.apply(borderColor).apply(backgroundColor.bg()).apply(stroke).draw(diams);
	}

	public ShapeType getShapeType() {
		return ShapeType.DIAMOND;
	}

}
