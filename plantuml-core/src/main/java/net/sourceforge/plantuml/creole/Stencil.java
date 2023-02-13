package net.sourceforge.plantuml.creole;

import net.sourceforge.plantuml.klimt.font.StringBounder;

public interface Stencil {

	public double getStartingX(StringBounder stringBounder, double y);

	public double getEndingX(StringBounder stringBounder, double y);

}
