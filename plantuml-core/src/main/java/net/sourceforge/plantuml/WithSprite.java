package net.sourceforge.plantuml;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.sprite.Sprite;

public interface WithSprite extends Diagram {

	public void addSprite(String name, Sprite sprite);
}
