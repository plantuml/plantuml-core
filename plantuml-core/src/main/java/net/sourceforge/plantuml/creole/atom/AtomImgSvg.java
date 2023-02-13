package net.sourceforge.plantuml.creole.atom;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.TileImageSvg;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class AtomImgSvg extends AbstractAtom implements Atom {

	private final TileImageSvg tileImageSvg;

	public AtomImgSvg(TileImageSvg tileImageSvg) {
		this.tileImageSvg = tileImageSvg;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return tileImageSvg.calculateDimension(stringBounder);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

	public void drawU(UGraphic ug) {
		tileImageSvg.drawU(ug);
	}

}
