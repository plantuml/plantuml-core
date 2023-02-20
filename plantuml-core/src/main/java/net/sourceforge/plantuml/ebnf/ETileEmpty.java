package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class ETileEmpty extends ETile {

	@Override
	public void drawU(UGraphic ug) {
	}

	@Override
	public void push(ETile tile) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getWidth(StringBounder stringBounder) {
		return 0;
	}

	@Override
	public double getH1(StringBounder stringBounder) {
		return 0;
	}

	@Override
	public double getH2(StringBounder stringBounder) {
		return 0;
	}

}
