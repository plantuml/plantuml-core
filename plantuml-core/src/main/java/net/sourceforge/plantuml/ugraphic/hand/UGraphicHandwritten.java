package net.sourceforge.plantuml.ugraphic.hand;

import java.util.Random;

import net.sourceforge.plantuml.graphic.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.DotPath;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UPolygon;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.svg.UGraphicSvg;

public class UGraphicHandwritten extends UGraphicDelegator implements UGraphic {

	private final Random rnd = new Random(424242L);

	public UGraphicHandwritten(UGraphic ug) {
		super(ug);
		if (ug instanceof UGraphicSvg) {
			((UGraphicSvg) ug).enlargeClip();
		}
	}

	public void draw(UShape shape) {
		// http://www.ufonts.com/fonts/felt-tip-roman.html
		// http://webdesignledger.com/freebies/20-amazing-free-handwritten-fonts-for-your-designs
		if (shape instanceof ULine) {
			drawHand((ULine) shape);
		} else if (shape instanceof URectangle) {
			drawHand((URectangle) shape);
		} else if (shape instanceof UPolygon) {
			drawHand((UPolygon) shape);
		} else if (shape instanceof UEllipse) {
			drawHand((UEllipse) shape);
		} else if (shape instanceof DotPath) {
			drawHand((DotPath) shape);
		} else if (shape instanceof UPath) {
			drawHand((UPath) shape);
		} else {
			getUg().draw(shape);
		}
	}

	private void drawHand(UPath shape) {
		final UPathHand uline = new UPathHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	private void drawHand(DotPath shape) {
		final UDotPathHand uline = new UDotPathHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	private void drawHand(UPolygon shape) {
		final UPolygonHand hand = new UPolygonHand(shape, rnd);
		getUg().draw(hand.getHanddrawn());
	}

	private void drawHand(URectangle shape) {
		final URectangleHand hand = new URectangleHand(shape, rnd);
		getUg().draw(hand.getHanddrawn());
	}

	private void drawHand(ULine shape) {
		final ULineHand uline = new ULineHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	private void drawHand(UEllipse shape) {
		final UEllipseHand uline = new UEllipseHand(shape, rnd);
		getUg().draw(uline.getHanddrawn());
	}

	public UGraphic apply(UChange change) {
		return new UGraphicHandwritten(getUg().apply(change));
	}

}
