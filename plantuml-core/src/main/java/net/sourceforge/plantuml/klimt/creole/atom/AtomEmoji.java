package net.sourceforge.plantuml.klimt.creole.atom;

import net.sourceforge.plantuml.emoji.Emoji;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class AtomEmoji extends AbstractAtom implements Atom {

	private static final double MAGIC = 24.0;
	private final Emoji emoji;
	private final double factor;
	private final HColor color;

	public AtomEmoji(Emoji emoji, double scale, double size2D, HColor color) {
		this.emoji = emoji;
		this.factor = scale * size2D / MAGIC;
		this.color = color;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final double size = 36 * factor;
		return new XDimension2D(size, size);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return -3 * factor;
	}

	public void drawU(UGraphic ug) {
		emoji.drawU(ug, this.factor, this.color);
	}

}
