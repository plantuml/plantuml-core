package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.CircleInterface2;

public class USymbolInterface extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.interface_;
	}

	@Override
	protected TextBlock getDrawing(Fashion symbolContext) {
		return new CircleInterface2(symbolContext.getBackColor(), symbolContext.getForeColor(),
				symbolContext.isShadowing() ? 4.0 : 0.0);
	}

}
