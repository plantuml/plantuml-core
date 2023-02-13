package net.sourceforge.plantuml.graphic;

import java.util.Objects;

import net.sourceforge.plantuml.StringUtils;

public class SpriteCommand implements HtmlCommand {

	private final String sprite;

	SpriteCommand(String sprite) {
		Objects.requireNonNull(sprite);
		if (sprite.startsWith("<$") == false) {
			throw new IllegalArgumentException();
		}
		if (sprite.endsWith(">") == false) {
			throw new IllegalArgumentException();
		}
		this.sprite = StringUtils.trin(sprite.substring(2, sprite.length() - 1));
	}

	public String getSprite() {
		return sprite;
	}

}
