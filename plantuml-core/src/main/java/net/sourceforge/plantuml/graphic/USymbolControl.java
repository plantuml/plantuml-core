package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.Control;

class USymbolControl extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.control;
	}

	@Override
	protected TextBlock getDrawing(final SymbolContext symbolContext) {
		return new Control(symbolContext.withDeltaShadow(symbolContext.isShadowing() ? 4.0 : 0.0));
	}

}
