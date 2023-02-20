package net.sourceforge.plantuml.style;

import net.sourceforge.plantuml.stereo.Stereostyles;
import net.sourceforge.plantuml.stereo.Stereotype;

public interface StyleSignature {

	public Style getMergedStyle(StyleBuilder styleBuilder);

	public StyleSignature withTOBECHANGED(Stereotype stereotype);

	public StyleSignature with(Stereostyles stereostyles);

}
