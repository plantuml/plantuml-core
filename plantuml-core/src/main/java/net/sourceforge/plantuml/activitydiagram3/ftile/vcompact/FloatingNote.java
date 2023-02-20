package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.CreoleMode;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.creole.Sheet;
import net.sourceforge.plantuml.klimt.creole.SheetBlock1;
import net.sourceforge.plantuml.klimt.creole.SheetBlock2;
import net.sourceforge.plantuml.klimt.creole.Stencil;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.image.Opale;
import net.sourceforge.plantuml.utils.Direction;

public class FloatingNote extends AbstractTextBlock implements Stencil, TextBlock {

	private final Opale opale;

	private FloatingNote(Display note, ISkinParam skinParam, SName sname, boolean withLink) {

		final Style style = StyleSignatureBasic.of(SName.root, SName.element, sname, SName.note)
				.getMergedStyle(skinParam.getCurrentStyleBuilder());
		final LineBreakStrategy wrapWidth = style.wrapWidth();
		final FontConfiguration fc = FontConfiguration.create(skinParam, style);
		final HColor noteBackgroundColor = style.value(PName.BackGroundColor).asColor(skinParam.getIHtmlColorSet());
		final HColor borderColor = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		final UStroke stroke = style.getStroke();
		final double shadowing = style.value(PName.Shadowing).asDouble();

		final Sheet sheet = skinParam
				.sheet(fc, skinParam.getDefaultTextAlignment(HorizontalAlignment.LEFT), CreoleMode.FULL)
				.createSheet(note);
		final SheetBlock2 sheetBlock2 = new SheetBlock2(new SheetBlock1(sheet, wrapWidth, skinParam.getPadding()), this,
				stroke);
		this.opale = new Opale(shadowing, borderColor, noteBackgroundColor, sheetBlock2, withLink, stroke);

	}

	public static FloatingNote create(Display note, ISkinParam skinParam, SName sname) {
		return new FloatingNote(note, skinParam, sname, false);
	}

	public static FloatingNote createOpale(Display note, ISkinParam skinParam, SName sname) {
		return new FloatingNote(note, skinParam, sname, true);
	}

	public void setOpale(Direction strategy, XPoint2D pp1, XPoint2D pp2) {
		opale.setOpale(strategy, pp1, pp2);

	}

	public void drawU(UGraphic ug) {
		opale.drawU(ug);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return opale.calculateDimension(stringBounder);
	}

	public double getStartingX(StringBounder stringBounder, double y) {
		return -opale.getMarginX1();
	}

	public double getEndingX(StringBounder stringBounder, double y) {
		return opale.calculateDimension(stringBounder).getWidth() - opale.getMarginX1();
	}

}
