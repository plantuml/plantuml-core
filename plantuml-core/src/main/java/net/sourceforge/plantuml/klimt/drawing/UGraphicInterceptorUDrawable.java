package net.sourceforge.plantuml.klimt.drawing;

import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public class UGraphicInterceptorUDrawable extends UGraphicDelegator {

	public UGraphicInterceptorUDrawable(UGraphic ug) {
		super(ug);
	}

	public void draw(UShape shape) {
		if (shape instanceof UDrawable) {
			final UDrawable drawable = (UDrawable) shape;
			drawable.drawU(this);
		} else {
			getUg().draw(shape);
		}

	}

	public UGraphic apply(UChange change) {
		return new UGraphicInterceptorUDrawable(getUg().apply(change));
	}

}
