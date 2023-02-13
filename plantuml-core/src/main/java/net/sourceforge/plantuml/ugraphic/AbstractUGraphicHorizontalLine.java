package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.graphic.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UTranslate;

public abstract class AbstractUGraphicHorizontalLine extends UGraphicDelegator {

	private UTranslate translate = new UTranslate();

	public UGraphic apply(UChange change) {
		final AbstractUGraphicHorizontalLine result;
		if (change instanceof UTranslate) {
			result = copy(getUg());
			result.translate = this.translate.compose((UTranslate) change);
		} else if (change instanceof UClip) {
			final UClip clip = ((UClip) change).translate(translate);
			result = copy(getUg().apply(clip));
			result.translate = this.translate;
		} else {
			result = copy(getUg().apply(change));
			result.translate = this.translate;
		}
		return result;
	}

	protected abstract AbstractUGraphicHorizontalLine copy(UGraphic ug);

	protected AbstractUGraphicHorizontalLine(UGraphic ug) {
		super(ug);
	}

	protected abstract void drawHline(UGraphic ug, UHorizontalLine line, UTranslate translate);

	public void draw(UShape shape) {
		if (shape instanceof UHorizontalLine) {
			drawHline(getUg(), (UHorizontalLine) shape, UTranslate.dy(translate.getDy()));
		} else {
			getUg().apply(translate).draw(shape);
		}
	}

}
