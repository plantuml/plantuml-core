package net.sourceforge.plantuml.klimt;

import net.sourceforge.plantuml.klimt.comp.CompressionMode;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface UShapeIgnorableForCompression extends UShape {

	public boolean isIgnoreForCompressionOn(CompressionMode mode);

	public void drawWhenCompressed(UGraphic ug, CompressionMode mode);

}
