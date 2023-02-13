package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileMargedWest extends FtileDecorate {

	private final double margin;

	public FtileMargedWest(Ftile tile, double margin) {
		super(tile);
		this.margin = margin;
	}

	public void drawU(UGraphic ug) {
		super.drawU(ug.apply(UTranslate.dx(margin)));
	}

	@Override
	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		FtileGeometry result = super.calculateDimension(stringBounder);
		result = result.incLeft(margin);
		return result;
	}

}
