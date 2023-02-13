package net.sourceforge.plantuml.creole.atom;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class AtomSpace extends AbstractAtom implements Atom {

	private final double width;

	public static Atom create(double width) {
		return new AtomSpace(width);
	}

	private AtomSpace(double width) {
		this.width = width;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(width, 1);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

	public void drawU(UGraphic ug) {
	}

}
