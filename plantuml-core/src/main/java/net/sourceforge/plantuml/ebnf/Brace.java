package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.ULine;

public class Brace implements UDrawable {

	private final double width;
	private final double height;

	public Brace(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void drawU(UGraphic ug) {
		ug = ug.apply(new UStroke(0.5));

		final double cinq = 5;
		CornerCurved.createNW(cinq).drawU(ug);
		CornerCurved.createSE(cinq).drawU(ug.apply(new UTranslate(width / 2, 0)));
		CornerCurved.createSW(cinq).drawU(ug.apply(new UTranslate(width / 2, 0)));
		CornerCurved.createNE(cinq).drawU(ug.apply(new UTranslate(width, 0)));

		ug.apply(new UTranslate(cinq, 0)).draw(new ULine(width / 2 - 2 * cinq, 0));
		ug.apply(new UTranslate(cinq + width / 2, 0)).draw(new ULine(width / 2 - 2 * cinq, 0));
//		ug.apply(new UTranslate(width / 2, -height)).draw(new ULine(width / 2, height));

	}

}
