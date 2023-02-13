package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

public class TextBlockWithUrl implements TextBlock {

	private final TextBlock block;
	private final Url url;

	public static TextBlock withUrl(TextBlock block, Url url) {
		if (url == null) {
			return block;
		}
		return new TextBlockWithUrl(block, url);

	}

	private TextBlockWithUrl(TextBlock block, Url url) {
		this.block = block;
		this.url = url;
	}

	public void drawU(UGraphic ug) {
		ug.startUrl(url);
		block.drawU(ug);
		ug.closeUrl();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return block.calculateDimension(stringBounder);
	}

	public MinMax getMinMax(StringBounder stringBounder) {
		return block.getMinMax(stringBounder);
	}

	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		return block.getInnerPosition(member, stringBounder, strategy);
	}

}
