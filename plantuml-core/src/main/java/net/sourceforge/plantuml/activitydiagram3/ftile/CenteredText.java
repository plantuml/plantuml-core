package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UShape;

public class CenteredText implements UShape {

	private final TextBlock text;
	private final double totalWidth;

	public CenteredText(TextBlock text, double totalWidth) {
		this.text = text;
		this.totalWidth = totalWidth;
	}

	public TextBlock getText() {
		return text;
	}

	public double getTotalWidth() {
		return totalWidth;
	}

}
