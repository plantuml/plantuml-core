package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.SimpleContext2D;

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
