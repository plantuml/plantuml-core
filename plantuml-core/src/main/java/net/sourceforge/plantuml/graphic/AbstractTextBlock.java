package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;

public abstract class AbstractTextBlock implements TextBlock {

	@Override
	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		throw new UnsupportedOperationException("member=" + member + " " + getClass().toString());
	}

	@Override
	public MinMax getMinMax(StringBounder stringBounder) {
		throw new UnsupportedOperationException(getClass().toString());
	}
}
