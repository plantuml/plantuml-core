package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.skin.ActorStyle;
import net.sourceforge.plantuml.style.SName;

class USymbolActor extends USymbolSimpleAbstract {

	private final ActorStyle actorStyle;

	public USymbolActor(ActorStyle actorStyle) {
		this.actorStyle = actorStyle;

	}

	@Override
	public SName getSName() {
		return SName.actor;
	}

	@Override
	protected TextBlock getDrawing(SymbolContext symbolContext) {
		// final double deltaShadow = symbolContext.isShadowing() ? 4.0 : 0.0;
		// final SymbolContext tmp =
		// symbolContext.withDeltaShadow(deltaShadow).withStroke(new UStroke(2));
		return actorStyle.getTextBlock(symbolContext);
	}

}
