package net.sourceforge.plantuml.klimt.creole.atom;

import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class AtomVerticalTexts extends AbstractAtom implements Atom {
	private final List<Atom> all;

	public AtomVerticalTexts(List<Atom> texts) {
		this.all = texts;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		double width = 0;
		double height = 0;
		for (Atom text : all) {
			final XDimension2D dim = text.calculateDimension(stringBounder);
			width = Math.max(width, dim.getWidth());
			height += dim.getHeight();
		}
		return new XDimension2D(width, height);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return all.get(0).getStartingAltitude(stringBounder);
	}

	public void drawU(UGraphic ug) {
		double y = 0;
		for (Atom text : all) {
			final XDimension2D dim = text.calculateDimension(ug.getStringBounder());
			text.drawU(ug.apply(UTranslate.dy(y)));
			y += dim.getHeight();
		}
	}

}
