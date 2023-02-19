package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileMarged extends AbstractFtile {

	private final Ftile tile;
	private final double margin1;
	private final double margin2;

	public FtileMarged(Ftile tile, double margin1, double margin2) {
		super(tile.skinParam());
		this.tile = tile;
		this.margin1 = margin1;
		this.margin2 = margin2;
	}

	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.singleton(tile);
		// return tile.getMyChildren();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return tile.getInLinkRendering();
	}

	@Override
	public LinkRendering getOutLinkRendering() {
		return tile.getOutLinkRendering();
	}

	public Set<Swimlane> getSwimlanes() {
		return tile.getSwimlanes();
	}

	public Swimlane getSwimlaneIn() {
		return tile.getSwimlaneIn();
	}

	public Swimlane getSwimlaneOut() {
		return tile.getSwimlaneOut();
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		final FtileGeometry orig = tile.calculateDimension(stringBounder);
		return new FtileGeometry(orig.getWidth() + margin1 + margin2, orig.getHeight(), orig.getLeft() + margin1,
				orig.getInY(), orig.getOutY());
	}

	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder) {
		if (child == tile)
			return getTranslate();

		UTranslate result = tile.getTranslateFor(child, stringBounder);
		result = result.compose(getTranslate());
		return result;
	}

	private UTranslate getTranslate() {
		return UTranslate.dx(margin1);
	}

	public void drawU(UGraphic ug) {
		ug.apply(getTranslate()).draw(tile);
	}

}
