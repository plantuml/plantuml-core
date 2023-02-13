package net.sourceforge.plantuml.graphic;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TextBlockHorizontal extends AbstractTextBlock implements TextBlock {

	private final List<TextBlock> blocks = new ArrayList<>();
	private final VerticalAlignment alignment;

	TextBlockHorizontal(TextBlock b1, TextBlock b2, VerticalAlignment alignment) {
		this.blocks.add(b1);
		this.blocks.add(b2);
		this.alignment = alignment;
	}

	public TextBlockHorizontal(List<TextBlock> all, VerticalAlignment alignment) {
		if (all.size() < 2) {
			throw new IllegalArgumentException();
		}
		this.blocks.addAll(all);
		this.alignment = alignment;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		XDimension2D dim = blocks.get(0).calculateDimension(stringBounder);
		for (int i = 1; i < blocks.size(); i++) {
			dim = dim.mergeLR(blocks.get(i).calculateDimension(stringBounder));
		}
		return dim;
	}

	public void drawU(UGraphic ug) {
		double x = 0;
		final XDimension2D dimtotal = calculateDimension(ug.getStringBounder());
		for (TextBlock block : blocks) {
			final XDimension2D dimb = block.calculateDimension(ug.getStringBounder());
			if (alignment == VerticalAlignment.CENTER) {
				final double dy = (dimtotal.getHeight() - dimb.getHeight()) / 2;
				block.drawU(ug.apply(new UTranslate(x, dy)));
			} else {
				block.drawU(ug.apply(UTranslate.dx(x)));
			}
			x += dimb.getWidth();
		}
	}

}
