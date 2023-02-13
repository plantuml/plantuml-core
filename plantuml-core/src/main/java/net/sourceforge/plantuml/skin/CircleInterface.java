package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class CircleInterface implements UDrawable {

	private final float thickness;
	private final double headDiam;
	private final HColor backgroundColor;
	private final HColor foregroundColor;

	public CircleInterface(HColor backgroundColor, HColor foregroundColor, double headDiam, float thickness) {
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
		this.headDiam = headDiam;
		this.thickness = thickness;
	}

	public void drawU(UGraphic ug) {
		final UEllipse head = new UEllipse(headDiam, headDiam);
		
		ug.apply(new UStroke(thickness)).apply(backgroundColor.bg())
		.apply(foregroundColor).apply(new UTranslate(thickness, thickness)).draw(head);
	}

	public double getPreferredWidth(StringBounder stringBounder) {
		return headDiam + 2 * thickness;
	}

	public double getPreferredHeight(StringBounder stringBounder) {
		return headDiam + 2 * thickness;
	}

}
