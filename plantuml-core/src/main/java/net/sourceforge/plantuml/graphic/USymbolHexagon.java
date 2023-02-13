package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class USymbolHexagon extends USymbol {

	@Override
	public SName getSName() {
		return SName.hexagon;
	}

	private final double marginY = 5;

	@Override
	public TextBlock asSmall(TextBlock name, final TextBlock label, final TextBlock stereotype,
			final SymbolContext symbolContext, final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				// ug = UGraphicStencil.create(ug, dim);

				final TextBlock tb = TextBlockUtils.mergeTB(stereotype, label, stereoAlignment);
				final double deltaX = dim.getWidth() / 4;
				tb.drawU(ug.apply(new UTranslate(deltaX, marginY)));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				final XDimension2D dimLabel = label.calculateDimension(stringBounder);
				final XDimension2D dimStereo = stereotype.calculateDimension(stringBounder);
				final XDimension2D full = dimStereo.mergeTB(dimLabel);
				return new XDimension2D(full.getWidth() * 2, full.getHeight() + 2 * marginY);
			}
		};
	}

	private void drawRect(UGraphic ug, double width, double height, double shadowing, double roundCorner,
			double diagonalCorner) {
//		final UShape shape = new URectangle(width, height);
		final UPath shape = new UPath();
		final double dx = width / 8;
		shape.moveTo(0, height / 2);
		shape.lineTo(dx, 0);
		shape.lineTo(width - dx, 0);
		shape.lineTo(width, height / 2);
		shape.lineTo(width - dx, height);
		shape.lineTo(dx, height);
		shape.lineTo(0, height / 2);
		shape.closePath();

		shape.setDeltaShadow(shadowing);

		ug.draw(shape);
	}

	private Margin getMargin() {
		return new Margin(10, 10, 10, 10);
	}

	@Override
	public TextBlock asBig(final TextBlock title, final HorizontalAlignment labelAlignment, final TextBlock stereotype,
			final double width, final double height, final SymbolContext symbolContext,
			final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {
			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				ug = symbolContext.apply(ug);
				drawRect(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
						symbolContext.getRoundCorner(), 0);
				final XDimension2D dimStereo = stereotype.calculateDimension(ug.getStringBounder());
				final double posStereoX;
				final double posStereoY;
				if (stereoAlignment == HorizontalAlignment.RIGHT) {
					posStereoX = width - dimStereo.getWidth() - getMargin().getX1() / 2;
					posStereoY = getMargin().getY1() / 2;
				} else {
					posStereoX = (width - dimStereo.getWidth()) / 2;
					posStereoY = 2;
				}
				stereotype.drawU(ug.apply(new UTranslate(posStereoX, posStereoY)));
				final XDimension2D dimTitle = title.calculateDimension(ug.getStringBounder());
				final double posTitle;
				if (labelAlignment == HorizontalAlignment.LEFT) {
					posTitle = 3;
				} else if (labelAlignment == HorizontalAlignment.RIGHT) {
					posTitle = width - dimTitle.getWidth() - 3;
				} else {
					posTitle = (width - dimTitle.getWidth()) / 2;
				}
				title.drawU(ug.apply(new UTranslate(posTitle, 2 + dimStereo.getHeight())));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return new XDimension2D(width, height);
			}
		};
	}

}
