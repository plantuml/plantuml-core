// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.abel;

import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public enum NoteLinkStrategy {
	NORMAL, HALF_PRINTED_FULL, HALF_NOT_PRINTED;

	public XDimension2D computeDimension(double width, double height) {
		if (this == HALF_PRINTED_FULL)
			return new XDimension2D(width / 2, height);

		if (this == HALF_NOT_PRINTED)
			return new XDimension2D(0, 0);

		return new XDimension2D(width, height);
	}
}
