package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.skin.AbstractComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseDestroy extends AbstractComponent {

	private final HColor foregroundColor;

	public ComponentRoseDestroy(Style style, HColor foregroundColor, ISkinSimple spriteContainer) {
		super(style);
		if (style != null)
			this.foregroundColor = style.value(PName.LineColor).asColor(spriteContainer.getIHtmlColorSet());
		else
			this.foregroundColor = foregroundColor;
	}

	private final int crossSize = 9;

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		ug = ug.apply(new UStroke(2)).apply(foregroundColor);

		ug.draw(new ULine(2 * crossSize, 2 * crossSize));
		ug.apply(UTranslate.dy(2 * crossSize)).draw(new ULine(2 * crossSize, -2 * crossSize));
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return crossSize * 2;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return crossSize * 2;
	}

}
