package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.skin.AbstractComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.ArrowConfiguration;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseNewpage extends AbstractComponent {

	private final HColor foregroundColor;

	public ComponentRoseNewpage(Style style, HColor foregroundColor) {
		super(style);
		this.foregroundColor = foregroundColor;
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final XDimension2D dimensionToUse = area.getDimensionToUse();
		ug = ArrowConfiguration.stroke(ug, 2, 2, 1).apply(foregroundColor);
		ug.draw(ULine.hline(dimensionToUse.getWidth()));
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return 1;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

}
