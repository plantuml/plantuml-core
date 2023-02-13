package net.sourceforge.plantuml.activitydiagram3;

import java.util.Objects;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.Rainbow;

public class LinkRendering {

	private final Rainbow rainbow;
	private final Display display;

	public static LinkRendering create(Rainbow rainbow) {
		return new LinkRendering(rainbow, Display.NULL);
	}

	public static LinkRendering none() {
		return LinkRendering.create(Rainbow.none());
	}

	private LinkRendering(Rainbow rainbow, Display display) {
		this.rainbow = Objects.requireNonNull(rainbow);
		this.display = display;
	}

	public LinkRendering withRainbow(Rainbow rainbow) {
		return new LinkRendering(rainbow, display);
	}

	public LinkRendering withDisplay(Display display) {
		return new LinkRendering(rainbow, display);
	}

	public Display getDisplay() {
		return display;
	}

	public Rainbow getRainbow() {
		return rainbow;
	}

	public Rainbow getRainbow(Rainbow defaultColor) {
		if (rainbow.size() == 0) {
			return defaultColor;
		}
		return rainbow;
	}

	public boolean isNone() {
		return Display.isNull(display) && rainbow.size() == 0;
	}

	@Override
	public String toString() {
		return super.toString() + " " + display + " " + rainbow;
	}

}
