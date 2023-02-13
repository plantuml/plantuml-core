package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.graphic.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class UGraphicInterceptorGoto extends UGraphicDelegator {

	public UGraphicInterceptorGoto(UGraphic ug) {
		super(ug);
	}

	public void draw(UShape shape) {
		System.err.println("inter=" + shape.getClass());

		if (shape instanceof Ftile) {
			final Ftile foo = (Ftile) shape;
			foo.drawU(this);
		} else {
			getUg().draw(shape);
			System.err.println("Drawing " + shape);
		}

	}

	public UGraphic apply(UChange change) {
		return new UGraphicInterceptorGoto(getUg().apply(change));
	}

}
