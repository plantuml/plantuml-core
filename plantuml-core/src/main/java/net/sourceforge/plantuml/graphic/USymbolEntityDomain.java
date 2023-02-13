package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.EntityDomain;

class USymbolEntityDomain extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.entity;
	}

	@Override
	protected TextBlock getDrawing(final SymbolContext symbolContext) {
		return new EntityDomain(symbolContext.withDeltaShadow(symbolContext.isShadowing() ? 4.0 : 0.0));
	}
}
