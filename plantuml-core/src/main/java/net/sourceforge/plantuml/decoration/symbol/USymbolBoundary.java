package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.Boundary;

class USymbolBoundary extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.boundary;
	}

	@Override
	protected TextBlock getDrawing(Fashion symbolContext) {
		return new Boundary(symbolContext.withDeltaShadow(symbolContext.isShadowing() ? 4.0 : 0.0));
	}
}
