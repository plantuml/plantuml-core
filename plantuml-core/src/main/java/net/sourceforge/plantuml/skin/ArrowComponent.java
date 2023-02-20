package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public interface ArrowComponent extends Component {

	XPoint2D getStartPoint(StringBounder stringBounder, XDimension2D dimensionToUse);

	XPoint2D getEndPoint(StringBounder stringBounder, XDimension2D dimensionToUse);

	double getPaddingY();

	public double getYPoint(StringBounder stringBounder);

	public double getPosArrow(StringBounder stringBounder);

}
