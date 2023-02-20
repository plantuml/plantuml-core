package net.sourceforge.plantuml.klimt.shape;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class TextBlockRaw extends AbstractTextBlock implements TextBlock {

	private List<Line> lines2;

	private final List<String> strings;
	private final FontConfiguration fontConfiguration;

	public TextBlockRaw(List<String> strings, FontConfiguration fontConfiguration) {
		this.strings = strings;
		this.fontConfiguration = fontConfiguration;
	}

	private List<Line> getLines(StringBounder stringBounder) {
		if (lines2 == null) {
			if (stringBounder == null) {
				throw new IllegalStateException();
			}
			this.lines2 = new ArrayList<>();
			for (String s : strings) {
				lines2.add(SingleLine.rawText(s, fontConfiguration));
			}
		}
		return lines2;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return getTextDimension(stringBounder);
	}

	protected final XDimension2D getTextDimension(StringBounder stringBounder) {
		double width = 0;
		double height = 0;
		for (Line line : getLines(stringBounder)) {
			final XDimension2D size2D = line.calculateDimension(stringBounder);
			height += size2D.getHeight();
			width = Math.max(width, size2D.getWidth());
		}
		return new XDimension2D(width, height);
	}

	public void drawU(UGraphic ug) {
		double y = 0;

		for (Line line : getLines(ug.getStringBounder())) {
			line.drawU(ug.apply(UTranslate.dy(y)));
			y += line.calculateDimension(ug.getStringBounder()).getHeight();
		}
	}

}
