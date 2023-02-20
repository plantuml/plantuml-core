package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileHeightFixedMarged extends AbstractFtile {

	private final Ftile tile;
	private final double ymargin1;
	private final double ymargin2;

	public FtileHeightFixedMarged(double ymargin1, Ftile tile, double ymargin2) {
		super(tile.skinParam());
		this.tile = tile;
		this.ymargin1 = ymargin1;
		this.ymargin2 = ymargin2;
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
		final FtileGeometry dim = tile.calculateDimension(stringBounder);
		return dim.translate(getTranslate(stringBounder)).fixedHeight(ymargin1 + dim.getHeight() + ymargin2);
	}

	private UTranslate getTranslate(StringBounder stringBounder) {
		return UTranslate.dy(ymargin1);
	}

	public void drawU(UGraphic ug) {
		ug.apply(getTranslate(ug.getStringBounder())).draw(tile);
	}

}
