package net.sourceforge.plantuml.utils;

public interface CharInspector {
	char peek(int ahead);

	void jump();
}
