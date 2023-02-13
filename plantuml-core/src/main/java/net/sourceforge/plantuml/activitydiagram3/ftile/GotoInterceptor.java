package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.UGraphicInterceptorGoto;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class GotoInterceptor extends AbstractTextBlock implements TextBlock {

	private final TextBlock swinlanes;

	public GotoInterceptor(TextBlock swinlanes) {
		this.swinlanes = swinlanes;
	}

	public void drawU(UGraphic ug) {
		new UGraphicInterceptorGoto(ug).draw(swinlanes);

	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return swinlanes.calculateDimension(stringBounder);
	}

}
