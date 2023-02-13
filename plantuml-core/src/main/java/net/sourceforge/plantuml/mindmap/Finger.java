package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public interface Finger extends UDrawable {

	public double getPhalanxThickness(StringBounder stringBounder);

	public double getNailThickness(StringBounder stringBounder);

	public double getFullThickness(StringBounder stringBounder);

	public double getPhalanxElongation(StringBounder stringBounder);

	public double getNailElongation(StringBounder stringBounder);

	public double getFullElongation(StringBounder stringBounder);

	public void doNotDrawFirstPhalanx();

}
