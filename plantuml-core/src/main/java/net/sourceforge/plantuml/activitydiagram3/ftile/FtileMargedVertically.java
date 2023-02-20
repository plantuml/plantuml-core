package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileMargedVertically extends FtileDecorate {

	private final double margin1;
	private final double margin2;

	public FtileMargedVertically(Ftile tile, double margin1, double margin2) {
		super(tile);
		this.margin1 = margin1;
		this.margin2 = margin2;
	}

	public void drawU(UGraphic ug) {
		if (margin1 > 0) {
			ug = ug.apply(UTranslate.dy(margin1));
		}
		ug.draw(getFtileDelegated());
	}

	private FtileGeometry cached;

	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		if (cached == null) {
			this.cached = calculateDimensionSlow(stringBounder);
		}
		return this.cached;
	}

	private FtileGeometry calculateDimensionSlow(StringBounder stringBounder) {
		return super.calculateDimension(stringBounder).incVertically(margin1, margin2);
	}

}
