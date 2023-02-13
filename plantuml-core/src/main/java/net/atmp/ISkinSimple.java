package net.atmp;

import java.util.Map;

import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.SheetBuilder;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.sprite.SpriteContainer;

public interface ISkinSimple extends SpriteContainer {

	public String getValue(String key);

	public Map<String, String> values();

	public double getPadding();

	public String getMonospacedFamily();

	public int getTabSize();

	public HColorSet getIHtmlColorSet();

	public int getDpi();

	public void copyAllFrom(Map<String, String> other);

	public SheetBuilder sheet(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			CreoleMode creoleMode);

	public SheetBuilder sheet(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			CreoleMode creoleMode, FontConfiguration stereo);

}
