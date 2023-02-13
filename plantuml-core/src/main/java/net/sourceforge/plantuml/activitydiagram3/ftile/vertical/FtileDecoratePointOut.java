package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileDecoratePointOut extends FtileDecorate {

	final private double dx;
	final private double dy;

	public FtileDecoratePointOut(final Ftile ftile, final double dx, double dy) {
		super(ftile);
		this.dx = dx;
		if (dx != 0) {
			throw new IllegalArgumentException();
		}
		this.dy = dy;
	}

	@Override
	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		final FtileGeometry geo = super.calculateDimension(stringBounder);
		return new FtileGeometry(geo.getWidth(), geo.getHeight(), geo.getLeft(), geo.getInY(), geo.getOutY() + dy);
	}

}
