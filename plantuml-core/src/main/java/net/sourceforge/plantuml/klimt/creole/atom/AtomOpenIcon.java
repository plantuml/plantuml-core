package net.sourceforge.plantuml.klimt.creole.atom;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.openiconic.OpenIcon;
import net.sourceforge.plantuml.url.Url;

public class AtomOpenIcon extends AbstractAtom implements Atom {

	private final OpenIcon openIcon;
	private final double factor;
	private final Url url;
	private final HColor color;

	public AtomOpenIcon(HColor newColor, double scale, OpenIcon openIcon, FontConfiguration fontConfiguration,
			Url url) {
		this.url = url;
		this.openIcon = openIcon;
		this.factor = scale * fontConfiguration.getSize2D() / 12.0;
		this.color = newColor == null ? fontConfiguration.getColor() : newColor;
	}

	private TextBlock asTextBlock() {
		return TextBlockUtils.withMargin(openIcon.asTextBlock(color, factor), 1, 0);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return asTextBlock().calculateDimension(stringBounder);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return -3 * factor;
	}

	public void drawU(UGraphic ug) {
		if (url != null) {
			ug.startUrl(url);
		}
		asTextBlock().drawU(ug);
		if (url != null) {
			ug.closeUrl();
		}
	}

}