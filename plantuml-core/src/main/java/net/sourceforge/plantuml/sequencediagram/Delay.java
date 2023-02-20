package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.style.WithStyle;

public class Delay extends AbstractEvent implements Event, WithStyle {

	private final Display text;

	final private Style style;

	public StyleSignatureBasic getStyleSignature() {
		return ComponentType.DELAY_TEXT.getStyleSignature();
	}

	public Style[] getUsedStyles() {
		return new Style[] { style };
	}

	public Delay(Display text, StyleBuilder styleBuilder) {
		this.text = text;
		this.style = getStyleSignature().getMergedStyle(styleBuilder);
	}

	public final Display getText() {
		return text;
	}

	public boolean dealWith(Participant someone) {
		return false;
	}
}
