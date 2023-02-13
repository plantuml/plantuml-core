package net.sourceforge.plantuml.activitydiagram3.ftile;

public class FtileGeometryMerger {

	private final FtileGeometry result;

	public FtileGeometryMerger(FtileGeometry geo1, FtileGeometry geo2) {
		final double left = Math.max(geo1.getLeft(), geo2.getLeft());
		final double dx1 = left - geo1.getLeft();
		final double dx2 = left - geo2.getLeft();
		final double width = Math.max(geo1.getWidth() + dx1, geo2.getWidth() + dx2);
		final double height = geo1.getHeight() + geo2.getHeight();

		if (geo2.hasPointOut()) {
			result = new FtileGeometry(width, height, left, geo1.getInY(), geo2.getOutY() + geo1.getHeight());
		} else {
			result = new FtileGeometry(width, height, left, geo1.getInY());
		}
	}

	public final FtileGeometry getResult() {
		return result;
	}
}
