package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.decoration.symbol.USymbol;
import net.sourceforge.plantuml.decoration.symbol.USymbols;
import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public enum ActorStyle {

	STICKMAN, STICKMAN_BUSINESS, AWESOME, HOLLOW;

	public USymbol toUSymbol() {
		if (this == STICKMAN)
			return USymbols.ACTOR_STICKMAN;
		else if (this == AWESOME)
			return USymbols.ACTOR_AWESOME;
		else if (this == HOLLOW)
			return USymbols.ACTOR_HOLLOW;

		throw new IllegalStateException();
	}

	public TextBlock getTextBlock(Fashion symbolContext) {
		if (this == STICKMAN)
			return new ActorStickMan(symbolContext, false);
		else if (this == STICKMAN_BUSINESS)
			return new ActorStickMan(symbolContext, true);
		else if (this == AWESOME)
			return new ActorAwesome(symbolContext);
		else if (this == HOLLOW)
			return new ActorHollow(symbolContext);

		throw new IllegalStateException();
	}

}
