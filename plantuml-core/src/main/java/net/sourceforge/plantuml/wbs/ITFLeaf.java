// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.wbs;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileBoxOld;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.CreoleMode;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.AbstractCommonUGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.mindmap.IdeaShape;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;

class ITFLeaf extends AbstractTextBlock implements ITF {

	private final TextBlock box;
	private final WElement idea;

	public ITFLeaf(WElement idea, ISkinParam skinParam) {
		final IdeaShape shape = idea.getShape();
		final Style style = idea.getStyle();
		final Display label = idea.getLabel();
		this.idea = idea;
		if (shape == IdeaShape.BOX) {
			this.box = FtileBoxOld.createWbs(style, skinParam, label);
		} else {
			final TextBlock text = label.create0(style.getFontConfiguration(skinParam.getIHtmlColorSet()),
					style.getHorizontalAlignment(), skinParam, style.wrapWidth(), CreoleMode.FULL, null, null);
			this.box = TextBlockUtils.withMargin(text, 0, 3, 1, 1);
		}
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return box.calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug) {
		if (ug instanceof AbstractCommonUGraphic) {
			final UTranslate translate = ((AbstractCommonUGraphic) ug).getTranslate();
			idea.setGeometry(translate, calculateDimension(ug.getStringBounder()));
		}
		box.drawU(ug);
	}

	public XPoint2D getT1(StringBounder stringBounder) {
		final XDimension2D dim = calculateDimension(stringBounder);
		return new XPoint2D(dim.getWidth() / 2, 0);
	}

	public XPoint2D getT2(StringBounder stringBounder) {
		final XDimension2D dim = calculateDimension(stringBounder);
		return new XPoint2D(dim.getWidth() / 2, dim.getHeight());
	}

	public XPoint2D getF1(StringBounder stringBounder) {
		final XDimension2D dim = calculateDimension(stringBounder);
		return new XPoint2D(0, dim.getHeight() / 2);
	}

	public XPoint2D getF2(StringBounder stringBounder) {
		final XDimension2D dim = calculateDimension(stringBounder);
		return new XPoint2D(dim.getWidth(), dim.getHeight() / 2);
	}

}
