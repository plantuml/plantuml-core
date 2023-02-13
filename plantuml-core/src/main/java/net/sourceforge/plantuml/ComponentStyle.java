package net.sourceforge.plantuml;

import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.graphic.USymbols;

public enum ComponentStyle {

	UML1, UML2, RECTANGLE;

	public USymbol toUSymbol() {
		switch (this) {
		case UML1:
			return USymbols.COMPONENT1;
		case UML2:
			return USymbols.COMPONENT2;
		case RECTANGLE:
			return USymbols.COMPONENT_RECTANGLE;
		}
		throw new AssertionError();
	}

}
