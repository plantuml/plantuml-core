package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.graphic.SymbolContext;

public class Step {

	private final double value;
	private final boolean destroy;
	private final int indent;
	private final SymbolContext color;

	public Step(double value, boolean destroy, int indent, SymbolContext color) {
		if (indent < 0) {
			throw new IllegalArgumentException();
		}
		this.indent = indent;
		this.color = color;
		this.value = value;
		this.destroy = destroy;
	}

	public double getValue() {
		return value;
	}

	public boolean isDestroy() {
		return destroy;
	}
	
	public int getIndent() {
		return indent;
	}

	public SymbolContext getColors() {
		return color;
	}


}
