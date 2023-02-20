package net.sourceforge.plantuml.svek.image;

import java.util.EnumMap;
import java.util.Map;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.UGroupType;
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

public class EntityImageBranch extends AbstractEntityImage {

	final private static int SIZE = 12;

	public EntityImageBranch(Entity entity, ISkinParam skinParam) {
		super(entity, skinParam);
	}

	public StyleSignatureBasic getDefaultStyleDefinition() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activity, SName.diamond);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE * 2, SIZE * 2);
	}

	final public void drawU(UGraphic ug) {
		final UPolygon diams = new UPolygon();
		diams.addPoint(SIZE, 0);
		diams.addPoint(SIZE * 2, SIZE);
		diams.addPoint(SIZE, SIZE * 2);
		diams.addPoint(0, SIZE);
		diams.addPoint(SIZE, 0);

		final Style style = getDefaultStyleDefinition().getMergedStyle(getSkinParam().getCurrentStyleBuilder());
		final HColor border = style.value(PName.LineColor).asColor(getSkinParam().getIHtmlColorSet());
		final HColor back = style.value(PName.BackGroundColor).asColor(getSkinParam().getIHtmlColorSet());
		final UStroke stroke = style.getStroke();
		final double shadowing = style.value(PName.Shadowing).asDouble();

		diams.setDeltaShadow(shadowing);
		final Map<UGroupType, String> typeIDent = new EnumMap<>(UGroupType.class);
		typeIDent.put(UGroupType.CLASS, "elem " + getEntity().getName() + " selected");
		typeIDent.put(UGroupType.ID, "elem_" + getEntity().getName());
		ug.startGroup(typeIDent);
		ug.apply(border).apply(back.bg()).apply(stroke).draw(diams);
		ug.closeGroup();
	}

	public ShapeType getShapeType() {
		return ShapeType.DIAMOND;
	}

}
