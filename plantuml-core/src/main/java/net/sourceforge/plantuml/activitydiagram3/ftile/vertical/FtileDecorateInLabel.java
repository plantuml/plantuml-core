package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class FtileDecorateInLabel extends FtileDecorate {

	final private double xl;
	final private double yl;

	public FtileDecorateInLabel(Ftile ftile, XDimension2D dim) {
		this(ftile, dim.getWidth(), dim.getHeight());
	}

	private FtileDecorateInLabel(final Ftile ftile, double xl, double yl) {
		super(ftile);
		this.xl = xl;
		this.yl = yl;
	}

	@Override
	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		FtileGeometry result = super.calculateDimension(stringBounder);
		result = result.addTop(yl);
		final double missing = xl - result.getRight();
		if (missing > 0)
			result = result.incRight(missing);

		return result;
	}

	@Override
	public void drawU(UGraphic ug) {
		super.drawU(ug.apply(UTranslate.dy(yl)));
	}

}
