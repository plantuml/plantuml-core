package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.awt.geom.XLine2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.DotPath;

public interface Racorder {
	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante);
	public DotPath getRacordOut(XRectangle2D rect, XLine2D tangeante);
}
