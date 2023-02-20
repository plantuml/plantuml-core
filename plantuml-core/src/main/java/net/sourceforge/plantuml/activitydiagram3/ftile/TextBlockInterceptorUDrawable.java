package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.HashMap;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public class TextBlockInterceptorUDrawable extends AbstractTextBlock implements TextBlock {

	private final TextBlock textBlock;

	public TextBlockInterceptorUDrawable(TextBlock textBlock) {
		this.textBlock = textBlock;
	}

	public void drawU(UGraphic ug) {
		new UGraphicInterceptorUDrawable2(ug, emptyHashMap()).draw(textBlock);
		ug.flushUg();
	}

	private HashMap<String, UTranslate> emptyHashMap() {
		return new HashMap<>();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		throw new UnsupportedOperationException();
	}

}
