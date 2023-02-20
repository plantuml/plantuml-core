package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.skin.Context2D;

public class UGraphicInterceptorTile extends UGraphicDelegator implements Context2D {

	private final boolean isBackground;

	public UGraphicInterceptorTile(UGraphic ug, boolean isBackground) {
		super(ug);
		this.isBackground = isBackground;
	}

	public void draw(UShape shape) {
		if (shape instanceof Tile) {
			final UDrawable drawable = (UDrawable) shape;
			drawable.drawU(this);
		} else {
			getUg().draw(shape);
		}

	}

	public UGraphic apply(UChange change) {
		return new UGraphicInterceptorTile(getUg().apply(change), isBackground);
	}

	public boolean isBackground() {
		return isBackground;
	}

}
