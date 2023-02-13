package net.sourceforge.plantuml.sprite;

import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.HColor;

public interface Sprite {

	public TextBlock asTextBlock(final HColor color, double scale);

}
