package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.sprite.SpriteContainer;

public class DisplayUtils {

	private static Display breakLines(Display texts, FontConfiguration fontConfiguration,
			SpriteContainer spriteContainer, double maxWidth) {
		if (texts.size() != 1) {
			return texts;
		}
		final CharSequence s = texts.get(0);
		Display result = Display.empty();
		final int nb = s.length() / 2;
		result = result.add(s.subSequence(0, nb));
		result = result.add(s.subSequence(nb + 1, s.length()));
		return result;
	}

}
