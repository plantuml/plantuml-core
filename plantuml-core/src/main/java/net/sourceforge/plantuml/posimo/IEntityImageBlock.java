package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface IEntityImageBlock {

	XDimension2D getDimension(StringBounder stringBounder);

	void drawU(UGraphic ug, double xTheoricalPosition, double yTheoricalPosition, double marginWidth,
			double marginHeight);
}
