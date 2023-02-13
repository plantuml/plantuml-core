package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.Hideable;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public interface IEntityImage extends Hideable, TextBlockBackcolored {

	public static final int CORNER = 25;
	public static final int MARGIN = 5;
	public static final int MARGIN_LINE = 5;

	public ShapeType getShapeType();

	public Margins getShield(StringBounder stringBounder);

	public double getOverscanX(StringBounder stringBounder);

}
