package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Set;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileKilled extends AbstractFtile {

	private final Ftile tile;

	public FtileKilled(Ftile tileToKill) {
		super(tileToKill.skinParam());
		this.tile = tileToKill;
	}

	@Override
	public Collection<Ftile> getMyChildren() {
		return tile.getMyChildren();
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
		final FtileGeometry geo = tile.calculateDimension(stringBounder);
		return new FtileGeometry(tile.calculateDimension(stringBounder), geo.getLeft(), geo.getInY());
	}

	public void drawU(UGraphic ug) {
		ug.draw(tile);
	}

}
