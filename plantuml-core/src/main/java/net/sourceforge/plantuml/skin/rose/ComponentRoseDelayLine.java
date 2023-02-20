package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.UAntiAliasing;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.skin.AbstractComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.ArrowConfiguration;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseDelayLine extends AbstractComponent {

	private final HColor color;

	public ComponentRoseDelayLine(Style style, HColor color) {
		super(style);
		this.color = color;
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final XDimension2D dimensionToUse = area.getDimensionToUse();
		ug = ArrowConfiguration.stroke(ug, 1, 4, 1).apply(color);
		final int x = (int) (dimensionToUse.getWidth() / 2);
		ug.apply(UAntiAliasing.ANTI_ALIASING_OFF).apply(UTranslate.dx(x)).draw(ULine.vline(dimensionToUse.getHeight()));
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
