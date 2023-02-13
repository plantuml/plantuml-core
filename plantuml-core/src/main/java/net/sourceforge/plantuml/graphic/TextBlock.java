package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;

public interface TextBlock extends UDrawable, UShape {

	public XDimension2D calculateDimension(StringBounder stringBounder);

	public MinMax getMinMax(StringBounder stringBounder);

	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy);

}
