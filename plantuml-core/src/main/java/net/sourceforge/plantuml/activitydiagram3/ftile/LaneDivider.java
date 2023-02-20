package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.UEmpty;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class LaneDivider extends AbstractTextBlock {

	private final ISkinParam skinParam;;

	private final double x1;
	private final double x2;
	private final double height;
	private Style style;

	public LaneDivider(ISkinParam skinParam, double x1, double x2, double height) {
		this.skinParam = skinParam;
		this.x1 = x1;
		this.x2 = x2;
		this.height = height;
	}

	public StyleSignatureBasic getDefaultStyleDefinition() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.swimlane);
	}

	private Style getStyle() {
		if (style == null) {
			this.style = getDefaultStyleDefinition().getMergedStyle(skinParam.getCurrentStyleBuilder());
		}
		return style;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(x1 + x2, height);
	}

	public void drawU(UGraphic ug) {
//		final UShape back = new URectangle(x1 + x2, height).ignoreForCompressionOnY();
//		ug.apply(UChangeColor.nnn(HColorUtils.BLUE)).draw(back);
		final UShape back = new UEmpty(x1 + x2, 1);
		ug.draw(back);

		final HColor color = getStyle().value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		final UStroke thickness = getStyle().getStroke();

		ug.apply(UTranslate.dx(x1)).apply(thickness).apply(color).draw(ULine.vline(height));

	}

	public double getWidth() {
		return x1 + x2;
	}

	public final double getX1() {
		return x1;
	}

	public final double getX2() {
		return x2;
	}

}
