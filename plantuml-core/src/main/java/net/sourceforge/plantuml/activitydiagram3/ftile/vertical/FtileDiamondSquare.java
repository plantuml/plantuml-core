package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Hexagon;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileDiamondSquare extends FtileDiamondWIP {

	public FtileDiamondSquare(TextBlock label, ISkinParam skinParam, HColor backColor, HColor borderColor,
			Swimlane swimlane) {
		this(label, skinParam, backColor, borderColor, swimlane, TextBlockUtils.empty(0, 0), TextBlockUtils.empty(0, 0),
				TextBlockUtils.empty(0, 0), TextBlockUtils.empty(0, 0));
	}

	public FtileDiamondSquare withNorth(TextBlock north) {
		return new FtileDiamondSquare(label, skinParam(), backColor, borderColor, swimlane, north, west, east, south);
	}

	public FtileDiamondSquare withWest(TextBlock west) {
		return new FtileDiamondSquare(label, skinParam(), backColor, borderColor, swimlane, north, west, east, south);
	}

	public FtileDiamondSquare withEast(TextBlock east) {
		return new FtileDiamondSquare(label, skinParam(), backColor, borderColor, swimlane, north, west, east, south);
	}

	public FtileDiamondSquare withSouth(TextBlock south) {
		return new FtileDiamondSquare(label, skinParam(), backColor, borderColor, swimlane, north, west, east, south);
	}

	public Ftile withWestAndEast(TextBlock tb1, TextBlock tb2) {
		return withWest(tb1).withEast(tb2);
	}

	private FtileDiamondSquare(TextBlock label, ISkinParam skinParam, HColor backColor, HColor borderColor,
			Swimlane swimlane, TextBlock north, TextBlock west, TextBlock east, TextBlock south) {
		super(label, skinParam, backColor, borderColor, swimlane, north, south, east, west);
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dimLabel = label.calculateDimension(stringBounder);
		final XDimension2D dimTotal = calculateDimensionInternal(stringBounder);
		ug = ug.apply(borderColor).apply(getThickness(getStyle())).apply(backColor.bg());

		ug.draw(Hexagon.asPolygonSquare(shadowing, dimTotal.getWidth(), dimTotal.getHeight()));

		// Fix why north and south are the same
		north.drawU(ug.apply(new UTranslate(4 + dimTotal.getWidth() / 2, dimTotal.getHeight())));
		south.drawU(ug.apply(new UTranslate(4 + dimTotal.getWidth() / 2, dimTotal.getHeight())));

		final double lx = (dimTotal.getWidth() - dimLabel.getWidth()) / 2;
		final double ly = (dimTotal.getHeight() - dimLabel.getHeight()) / 2;
		label.drawU(ug.apply(new UTranslate(lx, ly)));

		final XDimension2D dimWeat = west.calculateDimension(stringBounder);
		west.drawU(ug.apply(new UTranslate(-dimWeat.getWidth(), -dimWeat.getHeight() + Hexagon.hexagonHalfSize)));

		final XDimension2D dimEast = east.calculateDimension(stringBounder);
		east.drawU(ug.apply(new UTranslate(dimTotal.getWidth(), -dimEast.getHeight() + Hexagon.hexagonHalfSize)));

	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		final XDimension2D dim = calculateDimensionInternal(stringBounder);
		return new FtileGeometry(dim, dim.getWidth() / 2, 0, dim.getHeight());
	}

	private XDimension2D calculateDimensionInternal(StringBounder stringBounder) {
		final XDimension2D dimLabel = label.calculateDimension(stringBounder);
		if (dimLabel.getWidth() == 0 || dimLabel.getHeight() == 0)
			return new XDimension2D(Hexagon.hexagonHalfSize * 2, Hexagon.hexagonHalfSize * 2);

		XDimension2D result = dimLabel;
		result = result.delta(Hexagon.hexagonHalfSize * 2, Hexagon.hexagonHalfSize * 2);
		return result;
	}

}
