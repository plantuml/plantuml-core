package net.sourceforge.plantuml.wbs;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;

interface ITF extends TextBlock {

	public XPoint2D getT1(StringBounder stringBounder);

	public XPoint2D getT2(StringBounder stringBounder);

	public XPoint2D getF1(StringBounder stringBounder);

	public XPoint2D getF2(StringBounder stringBounder);

}
