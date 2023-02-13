package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.baraye.Bag;

final public class Together implements Bag {

	private final Together parent;

	public Together(Together parent) {
		this.parent = parent;
	}

	public final Together getParent() {
		return parent;
	}

}
