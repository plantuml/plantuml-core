package net.sourceforge.plantuml.klimt.compress;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;

public interface UShapeIgnorableForCompression extends UShape {

	public boolean isIgnoreForCompressionOn(CompressionMode mode);

	public void drawWhenCompressed(UGraphic ug, CompressionMode mode);

}
