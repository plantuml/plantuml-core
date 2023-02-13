package net.sourceforge.plantuml.creole.atom;

import java.util.List;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.creole.Neutron;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface Atom extends UShape {

	public XDimension2D calculateDimension(StringBounder stringBounder);

	public double getStartingAltitude(StringBounder stringBounder);

	public void drawU(UGraphic ug);

	public List<Neutron> getNeutrons();

}
