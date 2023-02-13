package net.sourceforge.plantuml.style;

import net.sourceforge.plantuml.cucadiagram.Stereostyles;
import net.sourceforge.plantuml.cucadiagram.Stereotype;

public interface StyleSignature {

	public Style getMergedStyle(StyleBuilder styleBuilder);

	public StyleSignature withTOBECHANGED(Stereotype stereotype);

	public StyleSignature with(Stereostyles stereostyles);

}
