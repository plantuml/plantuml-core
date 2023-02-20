package net.sourceforge.plantuml.wbs;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileBoxOld;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.CreoleMode;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.mindmap.IdeaShape;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

abstract class WBSTextBlock extends AbstractTextBlock {

	protected final ISkinParam skinParam;
	private final StyleBuilder styleBuilder;
	private final int level;

	public WBSTextBlock(ISkinParam skinParam, StyleBuilder styleBuilder, int level) {
		this.skinParam = skinParam;
		this.styleBuilder = styleBuilder;
		this.level = level;
	}

	final protected void drawLine(UGraphic ug, XPoint2D p1, XPoint2D p2) {
		final ULine line = new ULine(p1, p2);
		getStyleUsed().applyStrokeAndLineColor(ug.apply(new UTranslate(p1)), skinParam.getIHtmlColorSet()).draw(line);
	}

	private Style getStyleUsed() {
		return getDefaultStyleDefinitionArrow().getMergedStyle(styleBuilder);
	}

	final protected void drawLine(UGraphic ug, double x1, double y1, double x2, double y2) {
		drawLine(ug, new XPoint2D(Math.min(x1, x2), y1), new XPoint2D(Math.max(x1, x2), y2));
	}

	final public StyleSignatureBasic getDefaultStyleDefinitionArrow() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.wbsDiagram, SName.arrow).add(SName.depth(level));
	}

	final protected TextBlock buildMain(WElement idea) {
		final Display label = idea.getLabel();
		final Style style = idea.getStyle();
		if (idea.getShape() == IdeaShape.BOX)
			return FtileBoxOld.createWbs(style, idea.withBackColor(skinParam), label);

		final TextBlock text = label.create0(style.getFontConfiguration(skinParam.getIHtmlColorSet()),
				style.getHorizontalAlignment(), skinParam, style.wrapWidth(), CreoleMode.FULL, null, null);
		return TextBlockUtils.withMargin(text, 0, 3, 1, 1);
	}

}
