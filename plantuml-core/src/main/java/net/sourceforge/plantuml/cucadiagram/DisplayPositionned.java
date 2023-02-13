package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.api.ApiWarning;

/**
 * 
 * There is a typo in this class name.
 * 
 * You should use directly DisplayPositioned and not this interface which is
 * here for legacy code. This file will be removed, so use DisplayPositioned
 * instead.
 * 
 */
@Deprecated
@ApiWarning(willBeRemoved = "use DisplayPositioned instead")
public class DisplayPositionned {

	public Display getDisplay() {
		return null;
	}

	public boolean isNull() {
		return false;
	}

}
