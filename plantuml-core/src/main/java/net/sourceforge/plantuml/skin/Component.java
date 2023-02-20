package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.style.WithStyle;

public interface Component extends WithStyle {

	XDimension2D getPreferredDimension(StringBounder stringBounder);

	double getPreferredWidth(StringBounder stringBounder);

	double getPreferredHeight(StringBounder stringBounder);

	void drawU(UGraphic ug, Area area, Context2D context);

}
