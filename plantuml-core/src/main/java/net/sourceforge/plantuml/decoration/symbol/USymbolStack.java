package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicStencil;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.SName;

class USymbolStack extends USymbol {

	@Override
	public SName getSName() {
		return SName.stack;
	}

	private void drawQueue(UGraphic ug, double width, double height, double shadowing, double roundCorner) {
		final double border = 15;

		final URectangle rect = new URectangle(width - 2 * border, height).rounded(roundCorner);
		ug.apply(HColors.none()).apply(UTranslate.dx(border)).draw(rect);

		final UPath path = new UPath();
		if (roundCorner == 0) {
			path.moveTo(0, 0);
			path.lineTo(border, 0);
			path.lineTo(border, height);
			path.lineTo(width - border, height);
			path.lineTo(width - border, 0);
			path.lineTo(width, 0);
		} else {
			path.moveTo(0, 0);
			path.lineTo(border - roundCorner / 2, 0);
			path.arcTo(new XPoint2D(border, roundCorner / 2), roundCorner / 2, 0, 1);
			path.lineTo(border, height - roundCorner / 2);
			path.arcTo(new XPoint2D(border + roundCorner / 2, height), roundCorner / 2, 0, 0);
			path.lineTo(width - border - roundCorner / 2, height);
			path.arcTo(new XPoint2D(width - border, height - roundCorner / 2), roundCorner / 2, 0, 0);
			path.lineTo(width - border, roundCorner / 2);
			path.arcTo(new XPoint2D(width - border + roundCorner / 2, 0), roundCorner / 2, 0, 1);
			path.lineTo(width, 0);
		}
		path.setDeltaShadow(shadowing);
		ug.apply(HColors.none().bg()).draw(path);
	}

	private Margin getMargin() {
		return new Margin(25, 25, 10, 10);
	}

	@Override
	public TextBlock asSmall(TextBlock name, final TextBlock label, final TextBlock stereotype,
			final Fashion symbolContext, final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				ug = UGraphicStencil.create(ug, dim);
				ug = symbolContext.apply(ug);
				drawQueue(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
						symbolContext.getRoundCorner());
				final Margin margin = getMargin();
				final TextBlock tb = TextBlockUtils.mergeTB(stereotype, label, HorizontalAlignment.CENTER);
				tb.drawU(ug.apply(new UTranslate(margin.getX1(), margin.getY1())));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				final XDimension2D dimLabel = label.calculateDimension(stringBounder);
				final XDimension2D dimStereo = stereotype.calculateDimension(stringBounder);
				return getMargin().addDimension(dimStereo.mergeTB(dimLabel));
			}
		};
	}

	@Override
	public TextBlock asBig(final TextBlock title, HorizontalAlignment labelAlignment, final TextBlock stereotype,
			final double width, final double height, final Fashion symbolContext,
			final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				ug = symbolContext.apply(ug);
				drawQueue(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
						symbolContext.getRoundCorner());
				final XDimension2D dimStereo = stereotype.calculateDimension(ug.getStringBounder());
				final double posStereo = (width - dimStereo.getWidth()) / 2;
				stereotype.drawU(ug.apply(new UTranslate(posStereo, 13)));
				final XDimension2D dimTitle = title.calculateDimension(ug.getStringBounder());
				final double posTitle = (width - dimTitle.getWidth()) / 2;
				title.drawU(ug.apply(new UTranslate(posTitle, 13 + dimStereo.getHeight())));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return new XDimension2D(width, height);
			}
		};
	}

}