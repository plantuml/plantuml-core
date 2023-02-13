package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.skin.SimpleContext2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.svek.IEntityImage;
import net.sourceforge.plantuml.svek.Margins;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EntityImageNoteLink extends AbstractTextBlock implements IEntityImage {

	private final Component comp;

	public EntityImageNoteLink(Display note, Colors colors, ISkinParam skinParam, StyleBuilder styleBuilder) {
		final Rose skin = new Rose();
		comp = skin.createComponentNote(
				new Style[] { ComponentType.NOTE.getStyleSignature().getMergedStyle(styleBuilder) }, ComponentType.NOTE,
				skinParam, note, colors);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final double height = comp.getPreferredHeight(stringBounder);
		final double width = comp.getPreferredWidth(stringBounder);
		return new XDimension2D(width, height);
	}

	public void drawU(UGraphic ug) {
		comp.drawU(ug, new Area(calculateDimension(ug.getStringBounder())), new SimpleContext2D(false));
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

	public HColor getBackcolor() {
		return null;
	}

	public Margins getShield(StringBounder stringBounder) {
		return Margins.NONE;
	}

	public boolean isHidden() {
		return false;
	}

	public double getOverscanX(StringBounder stringBounder) {
		return 0;
	}

}
