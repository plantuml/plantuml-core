package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public interface Racorder {
	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante);

	public DotPath getRacordOut(XRectangle2D rect, XLine2D tangeante);
}
