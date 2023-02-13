package net.sourceforge.plantuml.skin;

import java.util.Objects;

public class ArrowDressing {

	private final ArrowHead head;
	private final ArrowPart part;
	// private final ArrowDecoration decoration;

	public String name() {
		return toString();
	}

	@Override
	public String toString() {
		return head.name();
	}

	private ArrowDressing(ArrowHead head, ArrowPart part) {
		this.head = Objects.requireNonNull(head);
		this.part = Objects.requireNonNull(part);
	}

	public static ArrowDressing create() {
		return new ArrowDressing(ArrowHead.NONE, ArrowPart.FULL);
	}

	public ArrowDressing withHead(ArrowHead head) {
		return new ArrowDressing(head, part);
	}

	public ArrowDressing withPart(ArrowPart part) {
		return new ArrowDressing(head, part);
	}

	public ArrowHead getHead() {
		return head;
	}

	public ArrowPart getPart() {
		return part;
	}

}
