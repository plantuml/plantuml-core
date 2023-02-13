package net.sourceforge.plantuml.creole;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.creole.atom.AbstractAtom;
import net.sourceforge.plantuml.creole.atom.Atom;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UHorizontalLine;

public class CreoleHorizontalLine extends AbstractAtom implements Atom {

	private final FontConfiguration fontConfiguration;
	private final String line;
	private final char style;
	private final ISkinSimple skinParam;

	private final static double defaultThickness = 1;

	public static CreoleHorizontalLine create(FontConfiguration fontConfiguration, String line, char style,
			ISkinSimple skinParam) {
		return new CreoleHorizontalLine(fontConfiguration, line, style, skinParam);
	}

	private CreoleHorizontalLine(FontConfiguration fontConfiguration, String line, char style, ISkinSimple skinParam) {
		this.fontConfiguration = fontConfiguration;
		this.line = line;
		this.style = style;
		this.skinParam = skinParam;
	}

	private UHorizontalLine getHorizontalLine() {
		if (line.length() == 0) {
			return UHorizontalLine.infinite(defaultThickness, 0, 0, style);
		}
		final TextBlock tb = getTitle();
		return UHorizontalLine.infinite(defaultThickness, 0, 0, tb, style);
	}

	private TextBlock getTitle() {
		if (line.length() == 0) {
			return TextBlockUtils.empty(0, 0);
		}
		final SheetBuilder parser = skinParam.sheet(fontConfiguration, HorizontalAlignment.LEFT, CreoleMode.FULL);
		final Sheet sheet = parser.createSheet(Display.getWithNewlines(line));
		final TextBlock tb = new SheetBlock1(sheet, LineBreakStrategy.NONE, skinParam.getPadding());
		return tb;
	}

	public void drawU(UGraphic ug) {
		// ug = ug.apply(UChangeColor.nnn(fontConfiguration.getColor()));
		final XDimension2D dim = calculateDimension(ug.getStringBounder());
		ug = ug.apply(UTranslate.dy(dim.getHeight() / 2));
		ug.draw(getHorizontalLine());
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		if (line.length() == 0) {
			return new XDimension2D(10, 10);
		}
		final TextBlock tb = getTitle();
		return tb.calculateDimension(stringBounder);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

}
