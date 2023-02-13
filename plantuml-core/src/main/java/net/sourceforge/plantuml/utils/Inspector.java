package net.sourceforge.plantuml.utils;

public interface Inspector<O> {
	O peek(int ahead);

	void jump();
}
