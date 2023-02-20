package net.sourceforge.plantuml.klimt.sprite;

import net.sourceforge.plantuml.text.Guillemet;
import net.sourceforge.plantuml.text.SvgCharSizeHack;

public interface SpriteContainer extends SvgCharSizeHack {

	public Sprite getSprite(String name);

	public Guillemet guillemet();

}
