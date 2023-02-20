package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.skin.AbstractComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseLine extends AbstractComponent {

	private final HColor color;
	private final boolean continueLine;
	private final UStroke stroke;

	public ComponentRoseLine(Style style, boolean continueLine, HColorSet set) {
		super(style);
		this.color = style.value(PName.LineColor).asColor(set);
		this.stroke = style.getStroke();
		this.continueLine = continueLine;
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final XDimension2D dimensionToUse = area.getDimensionToUse();
		ug = ug.apply(color);
		ug = ug.apply(stroke);
//		if (continueLine)
//			ug = ug.apply(new UStroke());

		final int x = (int) (dimensionToUse.getWidth() / 2);
		ug.apply(UTranslate.dx(x)).draw(ULine.vline(dimensionToUse.getHeight()));
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return 20;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return 1;
	}

}
