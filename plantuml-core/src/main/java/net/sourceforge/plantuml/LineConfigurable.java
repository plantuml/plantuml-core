package net.sourceforge.plantuml;

import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;

public interface LineConfigurable {

	public Colors getColors();
	
	public void setSpecificColorTOBEREMOVED(ColorType type, HColor color);



}
