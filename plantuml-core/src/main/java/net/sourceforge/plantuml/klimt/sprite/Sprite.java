package net.sourceforge.plantuml.klimt.sprite;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public interface Sprite {

	public TextBlock asTextBlock(final HColor color, double scale);

}
