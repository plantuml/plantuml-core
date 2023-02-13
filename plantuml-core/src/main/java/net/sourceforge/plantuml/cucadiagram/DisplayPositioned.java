package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public class DisplayPositioned extends DisplayPositionned {

	private final Display display;
	private final HorizontalAlignment horizontalAlignment;
	private final VerticalAlignment verticalAlignment;

	private DisplayPositioned(Display display, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment) {
		this.display = display;
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
	}

	public static DisplayPositioned single(Display display, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment) {
		return new DisplayPositioned(display, horizontalAlignment, verticalAlignment);
	}

	public static DisplayPositioned none(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
		return new DisplayPositioned(Display.NULL, horizontalAlignment, verticalAlignment);
	}

	public final Display getDisplay() {
		return display;
	}

	public final HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public final VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public boolean isNull() {
		return Display.isNull(display);
	}

	public boolean hasUrl() {
		return display.hasUrl();
	}

}
