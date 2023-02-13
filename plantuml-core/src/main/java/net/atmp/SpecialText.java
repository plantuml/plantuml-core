package net.atmp;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UShapeIgnorableForCompression;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.comp.CompressionMode;
import net.sourceforge.plantuml.ugraphic.UEmpty;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class SpecialText implements UShapeIgnorableForCompression {

	private final TextBlock title;

	public SpecialText(TextBlock title) {
		this.title = title;
	}

	public boolean isIgnoreForCompressionOn(CompressionMode mode) {
		return true;
	}

	public void drawWhenCompressed(UGraphic ug, CompressionMode mode) {
		final XDimension2D dim = title.calculateDimension(ug.getStringBounder());
		ug.apply(UTranslate.dx(dim.getWidth())).draw(new UEmpty(1, 1));
	}

	public final TextBlock getTitle() {
		return title;
	}

}
