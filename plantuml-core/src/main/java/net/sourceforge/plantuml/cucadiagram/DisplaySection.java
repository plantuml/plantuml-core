package net.sourceforge.plantuml.cucadiagram;

import java.util.EnumMap;
import java.util.Map;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.Style;

public class DisplaySection {

	private final Map<HorizontalAlignment, Display> map = new EnumMap<HorizontalAlignment, Display>(
			HorizontalAlignment.class);

	private DisplaySection() {
	}

	public DisplaySection withPage(int page, int lastpage) {
		final DisplaySection result = new DisplaySection();
		for (Map.Entry<HorizontalAlignment, Display> ent : this.map.entrySet())
			result.map.put(ent.getKey(), ent.getValue().withPage(page, lastpage));

		return result;
	}

	public Display getDisplay() {
		if (map.size() == 0)
			return null;

		return map.values().iterator().next();
	}

	public static DisplaySection none() {
		return new DisplaySection();
	}

	public final HorizontalAlignment getHorizontalAlignment() {
		if (map.size() == 0)
			return HorizontalAlignment.CENTER;

		return map.keySet().iterator().next();
	}

	public boolean isNull() {
		if (map.size() == 0)
			return true;

		final Display display = map.values().iterator().next();
		return Display.isNull(display);
	}

	public TextBlock createRibbon(FontConfiguration fontConfiguration, ISkinSimple spriteContainer, Style style) {
		if (map.size() == 0)
			return null;

		final Display display = map.values().iterator().next();
		if (Display.isNull(display) || display.size() == 0)
			return null;

		if (style != null)
			return style.createTextBlockBordered(display, spriteContainer.getIHtmlColorSet(), spriteContainer, null);

		return display.create(fontConfiguration, getHorizontalAlignment(), spriteContainer);
	}

	public void putDisplay(Display display, HorizontalAlignment horizontalAlignment) {
		this.map.put(horizontalAlignment, display);

	}

}
