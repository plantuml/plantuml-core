package net.sourceforge.plantuml.creole.atom;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sprite.Sprite;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

public class AtomSprite extends AbstractAtom implements Atom {

	private final Sprite sprite;
	private final double scale;
	private final Url url;
	private final HColor color;

	public AtomSprite(HColor newColor, double scale, FontConfiguration fontConfiguration, Sprite sprite, Url url) {
		this.scale = scale;
		this.sprite = sprite;
		this.url = url;
		this.color = newColor == null ? fontConfiguration.getColor() : newColor;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return sprite.asTextBlock(color, scale).calculateDimension(stringBounder);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

	public void drawU(UGraphic ug) {
		if (url != null)
			ug.startUrl(url);

		sprite.asTextBlock(color, scale).drawU(ug);
		if (url != null)
			ug.closeUrl();

	}

}
