package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.command.Position;
import net.sourceforge.plantuml.graphic.color.Colors;

public class CucaNote {

	private final Display display;
	private final Position position;
	private final Colors colors;
	private final NoteLinkStrategy strategy;

	private CucaNote(Display display, Position position, Colors colors, NoteLinkStrategy strategy) {
		this.display = display;
		this.position = position;
		this.colors = colors;
		this.strategy = strategy;
	}

	public static CucaNote build(Display display, Position position, Colors colors) {
		return new CucaNote(display, position, colors, NoteLinkStrategy.NORMAL);
	}

	public CucaNote withStrategy(NoteLinkStrategy strategy) {
		return new CucaNote(display, position, colors, strategy);
	}

	public final Display getDisplay() {
		return display;
	}

	public final NoteLinkStrategy getStrategy() {
		return strategy;
	}

	public final Colors getColors() {
		return colors;
	}

	public final Position getPosition() {
		return position;
	}

}
