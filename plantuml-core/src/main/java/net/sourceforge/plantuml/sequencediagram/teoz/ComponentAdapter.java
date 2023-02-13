package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.SimpleContext2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ComponentAdapter extends AbstractTextBlock implements TextBlock {

	private final Component component;

	public ComponentAdapter(Component component) {
		this.component = component;
	}

	public void drawU(UGraphic ug) {
		if (component == null) {
			return;
		}
		component.drawU(ug, new Area(calculateDimension(ug.getStringBounder())), new SimpleContext2D(false));

	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		if (component == null) {
			return new XDimension2D(0, 0);
		}
		final double width = component.getPreferredWidth(stringBounder);
		final double height = component.getPreferredHeight(stringBounder);
		return new XDimension2D(width, height);
	}

}
