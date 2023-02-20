package net.sourceforge.plantuml.klimt.font;

import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public interface StringBounder {

	public XDimension2D calculateDimension(UFont font, String text);

	public double getDescent(UFont font, String text);

	public boolean matchesProperty(String propertyName);

}
