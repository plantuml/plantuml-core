package net.sourceforge.plantuml.klimt.drawing;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;

public class UGraphicFilter extends UGraphicDelegator {

	public UGraphic apply(UChange translate) {
		throw new UnsupportedOperationException();
	}

	private final Collection<Class<? extends UShape>> toprint;

	public UGraphicFilter(UGraphic ug, Class<? extends UShape>... toprint) {
		super(ug);
		this.toprint = Arrays.asList(toprint);
	}

	public void draw(UShape shape) {
		if (toprint.contains(shape.getClass())) {
			getUg().draw(shape);
		}
	}

}
