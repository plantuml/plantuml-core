package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Map;

import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.svek.UGraphicForSnake;

public class UGraphicInterceptorUDrawable2 extends UGraphicDelegator {

	private final Map<String, UTranslate> positions;

	public UGraphicInterceptorUDrawable2(UGraphic ug, Map<String, UTranslate> positions) {
		super(ug);
		this.positions = positions;
	}

	public void draw(UShape shape) {
		if (shape instanceof Ftile) {
			final Ftile ftile = (Ftile) shape;
			// System.err.println("ftile=" + ftile);
			ftile.drawU(this);
			if (ftile instanceof FtileLabel) {
				positions.put(((FtileLabel) ftile).getName(), getPosition());
				// System.err.println("ug=" + getUg().getClass());
			}
			if (ftile instanceof FtileGoto) {
				// System.err.println("positions=" + positions);
				drawGoto((FtileGoto) ftile);
			}
		} else if (shape instanceof UDrawable) {
			final UDrawable drawable = (UDrawable) shape;
			drawable.drawU(this);
		} else {
			getUg().draw(shape);
		}

	}

	private UTranslate getPosition() {
		return ((UGraphicForSnake) getUg()).getTranslation();
	}

	private void drawGoto(FtileGoto ftile) {
		final HColor gotoColor = HColors.MY_RED;

		final FtileGeometry geom = ftile.calculateDimension(getStringBounder());
		final XPoint2D pt = geom.getPointIn();
		UGraphic ugGoto = getUg().apply(gotoColor).apply(gotoColor.bg());
		ugGoto = ugGoto.apply(new UTranslate(pt));
		final UTranslate posNow = getPosition();
		final UTranslate dest = positions.get(ftile.getName());
		if (dest == null)
			return;
		final double dx = dest.getDx() - posNow.getDx();
		final double dy = dest.getDy() - posNow.getDy();
		ugGoto.draw(new UEllipse(3, 3));
		ugGoto.apply(new UTranslate(dx, dy)).draw(new UEllipse(3, 3));
		ugGoto.draw(ULine.hline(dx));
		ugGoto.apply(UTranslate.dx(dx)).draw(ULine.vline(dy));
		// ugGoto.draw(new ULine(dx, dy));
	}

	public UGraphic apply(UChange change) {
		return new UGraphicInterceptorUDrawable2(getUg().apply(change), positions);
	}

}
