package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicStencil;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.SName;

class USymbolComponent2 extends USymbol {

	@Override
	public SName getSName() {
		return SName.component;
	}

	private void drawComponent2(UGraphic ug, double widthTotal, double heightTotal, double shadowing,
			double roundCorner) {

		final URectangle form = new URectangle(widthTotal, heightTotal).rounded(roundCorner);
		form.setDeltaShadow(shadowing);

		final UShape small = new URectangle(15, 10);
		final UShape tiny = new URectangle(4, 2);

		ug.draw(form);

		// UML 2 Component Notation
		ug.apply(new UTranslate(widthTotal - 20, 5)).draw(small);
		ug.apply(new UTranslate(widthTotal - 22, 7)).draw(tiny);
		ug.apply(new UTranslate(widthTotal - 22, 11)).draw(tiny);

	}

	private Margin getMargin() {
		return new Margin(10 + 5, 20 + 5, 15 + 5, 5 + 5);
	}

	@Override
	public TextBlock asSmall(TextBlock name, final TextBlock label, final TextBlock stereotype,
			final Fashion symbolContext, final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				ug = UGraphicStencil.create(ug, dim);
				ug = symbolContext.apply(ug);
				drawComponent2(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
						symbolContext.getRoundCorner());
				final Margin margin = getMargin();

				final TextBlock tb = TextBlockUtils.mergeTB(stereotype, label, HorizontalAlignment.CENTER);
				tb.drawU(ug.apply(new UTranslate(margin.getX1(), margin.getY1())));
				// label.drawU(ug.apply(new UTranslate(margin.getX1(), margin.getY1())));
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
				drawComponent2(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
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
