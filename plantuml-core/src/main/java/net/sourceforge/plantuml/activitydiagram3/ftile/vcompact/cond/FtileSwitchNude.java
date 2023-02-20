package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class FtileSwitchNude extends FtileDimensionMemoize {

	protected double xSeparation = 20;

	protected final List<Ftile> tiles;
	private final Swimlane in;

	public FtileSwitchNude(List<Ftile> tiles, Swimlane in) {
		super(tiles.get(0).skinParam());
		this.tiles = tiles;
		this.in = in;
	}

	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.unmodifiableCollection(tiles);
	}

	public Set<Swimlane> getSwimlanes() {
		final Set<Swimlane> result = new HashSet<>();
		if (getSwimlaneIn() != null)
			result.add(getSwimlaneIn());

		for (Ftile tile : tiles)
			result.addAll(tile.getSwimlanes());

		return Collections.unmodifiableSet(result);
	}

	public Swimlane getSwimlaneIn() {
		return in;
	}

	public Swimlane getSwimlaneOut() {
		return getSwimlaneIn();
	}

	@Override
	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder) {
		if (tiles.contains(child))
			return getTranslateNude(child, stringBounder);

		throw new UnsupportedOperationException();
	}

	protected UTranslate getTranslateNude(Ftile tile, StringBounder stringBounder) {
		double x1 = 0;
		for (Ftile candidate : tiles) {
			final FtileGeometry dim1 = candidate.calculateDimension(stringBounder);
			if (candidate == tile)
				return UTranslate.dx(x1);

			x1 += dim1.getWidth() + xSeparation;
		}
		throw new IllegalArgumentException();
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		for (Ftile tile : tiles)
			ug.apply(getTranslateNude(tile, stringBounder)).draw(tile);

	}

	@Override
	final protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		final FtileGeometry dimTotal = calculateDimensionInternal(stringBounder);
		for (Ftile tile : tiles)
			if (tile.calculateDimension(stringBounder).hasPointOut())
				return dimTotal;

		return dimTotal.withoutPointOut();
	}

	@Override
	protected FtileGeometry calculateDimensionInternalSlow(StringBounder stringBounder) {
		XDimension2D result = new XDimension2D(0, 0);
		for (Ftile couple : tiles)
			result = result.mergeLR(couple.calculateDimension(stringBounder));

		result = result.delta(xSeparation * (tiles.size() - 1), 100);

		return new FtileGeometry(result, result.getWidth() / 2, 0);
	}

}
