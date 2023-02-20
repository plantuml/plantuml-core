package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileMargedRight extends AbstractFtile {

	private final Ftile tile;
	private final double maxX;

	public FtileMargedRight(Ftile tile, double maxX) {
		super(tile.skinParam());
		this.tile = tile;
		this.maxX = maxX;
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
		if (orig.getWidth() > maxX) {
			throw new IllegalStateException();
		}
		return new FtileGeometry(maxX, orig.getHeight(), orig.getLeft(), orig.getInY(), orig.getOutY());
	}

	public void drawU(UGraphic ug) {
		ug.draw(tile);
	}

	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.singleton(tile);
	}

	@Override
	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder) {
		if (child == tile)
			return new UTranslate();
		return super.getTranslateFor(child, stringBounder);
	}

}
