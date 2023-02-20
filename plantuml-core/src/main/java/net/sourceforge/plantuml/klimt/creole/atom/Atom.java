package net.sourceforge.plantuml.klimt.creole.atom;

import java.util.List;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.creole.Neutron;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public interface Atom extends UShape {

	public XDimension2D calculateDimension(StringBounder stringBounder);

	public double getStartingAltitude(StringBounder stringBounder);

	public void drawU(UGraphic ug);

	public List<Neutron> getNeutrons();

}
