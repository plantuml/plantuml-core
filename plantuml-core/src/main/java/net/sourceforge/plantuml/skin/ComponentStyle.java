package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.decoration.symbol.USymbol;
import net.sourceforge.plantuml.decoration.symbol.USymbols;

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