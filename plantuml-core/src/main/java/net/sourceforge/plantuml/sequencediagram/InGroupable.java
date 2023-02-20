package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.klimt.font.StringBounder;

public interface InGroupable {

	public double getMinX(StringBounder stringBounder);

	public double getMaxX(StringBounder stringBounder);

	public String toString(StringBounder stringBounder);

}
