package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public interface IEntityImageBlock {

	XDimension2D getDimension(StringBounder stringBounder);

	void drawU(UGraphic ug, double xTheoricalPosition, double yTheoricalPosition, double marginWidth,
			double marginHeight);
}
