package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.png.PngTitler;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TeozLayer extends AbstractTextBlock implements TextBlock {

	private final PngTitler titler;
	private XDimension2D dimension;
	private final FontParam param;

	public TeozLayer(PngTitler titler, StringBounder stringBounder, FontParam param) {
		this.titler = titler;
		this.param = param;

		dimension = new XDimension2D(0, 0);
		if (titler != null && titler.getRibbonBlock() != null) {
			dimension = titler.getRibbonBlock().calculateDimension(stringBounder);
		}
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return dimension;
	}

	public FontParam getParam() {
		return param;
	}

	public void drawU(UGraphic ug) {
		if (titler != null) {
			titler.getRibbonBlock().drawU(ug);
		}
	}

}
