package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.Boundary;

class USymbolBoundary extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.boundary;
	}

	@Override
	protected TextBlock getDrawing(SymbolContext symbolContext) {
		return new Boundary(symbolContext.withDeltaShadow(symbolContext.isShadowing() ? 4.0 : 0.0));
	}
}
