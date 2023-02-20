package net.sourceforge.plantuml;

import java.util.Map;

import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.creole.CreoleMode;
import net.sourceforge.plantuml.klimt.creole.Parser;
import net.sourceforge.plantuml.klimt.creole.SheetBuilder;
import net.sourceforge.plantuml.klimt.creole.legacy.CreoleParser;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.sprite.Sprite;
import net.sourceforge.plantuml.klimt.sprite.SpriteContainer;
import net.sourceforge.plantuml.klimt.sprite.SpriteImage;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.text.Guillemet;

public class SpriteContainerEmpty implements SpriteContainer, ISkinSimple {

	@Override
	public Sprite getSprite(String name) {
		return SpriteImage.fromInternal(name);
	}

	@Override
	public String getValue(String key) {
		return null;
	}

	@Override
	public double getPadding() {
		return 0;
	}

	@Override
	public Guillemet guillemet() {
		return Guillemet.DOUBLE_COMPARATOR;
	}

	@Override
	public String getMonospacedFamily() {
		return Parser.MONOSPACED;
	}

	@Override
	public int getTabSize() {
		return 8;
	}

	@Override
	public HColorSet getIHtmlColorSet() {
		return HColorSet.instance();
	}

	@Override
	public int getDpi() {
		return 96;
	}

	@Override
	public void copyAllFrom(Map<String, String> other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, String> values() {
		throw new UnsupportedOperationException();
	}

	public double minClassWidthTOBEREMOVED(Style style) {
		return 0;
	}

	@Override
	public String transformStringForSizeHack(String s) {
		return s;
	}

	@Override
	public SheetBuilder sheet(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			CreoleMode creoleMode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SheetBuilder sheet(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			CreoleMode creoleMode, FontConfiguration stereo) {
		return new CreoleParser(fontConfiguration, horizontalAlignment, this, creoleMode, stereo);
	}

}
