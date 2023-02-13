package net.sourceforge.plantuml;

import net.sourceforge.plantuml.graphic.color.Colors;

public interface SpecificBackcolorable {

	public Colors getColors();

	// public void setSpecificColorTOBEREMOVED(ColorType type, HtmlColor color);
	
	public void setColors(Colors colors);

}
