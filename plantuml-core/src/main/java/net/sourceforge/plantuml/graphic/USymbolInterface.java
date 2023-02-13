package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.CircleInterface2;

public class USymbolInterface extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.interface_;
	}

	@Override
	protected TextBlock getDrawing(SymbolContext symbolContext) {
		return new CircleInterface2(symbolContext.getBackColor(), symbolContext.getForeColor(),
				symbolContext.isShadowing() ? 4.0 : 0.0);
	}

}
