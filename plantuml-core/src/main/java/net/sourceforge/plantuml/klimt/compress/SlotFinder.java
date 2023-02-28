package net.sourceforge.plantuml.klimt.compress;

import static net.sourceforge.plantuml.utils.ObjectUtils.instanceOfAny;

import net.sourceforge.plantuml.klimt.UBackground;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.TextLimitFinder;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicNo;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.UEmpty;
import net.sourceforge.plantuml.klimt.shape.UPolygon;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.klimt.shape.UText;

public class SlotFinder extends UGraphicNo {

	@Override
	public UGraphic apply(UChange change) {
		if (!instanceOfAny(change, UBackground.class, HColor.class, UStroke.class, UTranslate.class))
			throw new UnsupportedOperationException(change.getClass().toString());
		final UTranslate tmp = change instanceof UTranslate ? this.getTranslate().compose((UTranslate) change)
				: this.getTranslate();
		SlotFinder result = new SlotFinder(this.getStringBounder(), tmp, this.slot, this.mode);
		return result;
	}

	private final SlotSet slot;
	private final CompressionMode mode;

	public static SlotFinder create(CompressionMode mode, StringBounder stringBounder) {
		return new SlotFinder(stringBounder, new UTranslate(), new SlotSet(), mode);
	}

	private SlotFinder(StringBounder stringBounder, UTranslate translate, SlotSet slot, CompressionMode mode) {
		super(stringBounder, translate);
		this.slot = slot;
		this.mode = mode;
	}

	public void draw(UShape sh) {
		final double x = getTranslate().getDx();
		final double y = getTranslate().getDy();
		if (sh instanceof UShapeIgnorableForCompression) {
			final UShapeIgnorableForCompression shape = (UShapeIgnorableForCompression) sh;
			if (shape.isIgnoreForCompressionOn(mode)) {
				shape.drawWhenCompressed(this, mode);
				return;
			}

		}
		if (sh instanceof URectangle) {
			drawRectangle(x, y, (URectangle) sh);
		} else if (sh instanceof UPath) {
			drawPath(x, y, (UPath) sh);
		} else if (sh instanceof UPolygon) {
			drawPolygon(x, y, (UPolygon) sh);
		} else if (sh instanceof UEllipse) {
			drawEllipse(x, y, (UEllipse) sh);
		} else if (sh instanceof UText) {
			final UText text = (UText) sh;
			drawText(x, y, text);
		} else if (sh instanceof UEmpty) {
			drawEmpty(x, y, (UEmpty) sh);
		}
	}

	private void drawPath(double x, double y, UPath shape) {
		if (mode == CompressionMode.ON_X)
			slot.addSlot(x + shape.getMinX(), x + shape.getMaxX());
		else
			slot.addSlot(y + shape.getMinY(), y + shape.getMaxY());

	}

	private void drawEmpty(double x, double y, UEmpty shape) {
		if (mode == CompressionMode.ON_X)
			slot.addSlot(x, x + shape.getWidth());
		else
			slot.addSlot(y, y + shape.getHeight());

	}

	private void drawText(double x, double y, UText shape) {
		final TextLimitFinder finder = TextLimitFinder.create(getStringBounder(), false);
		finder.apply(new UTranslate(x, y)).draw(shape);
		if (mode == CompressionMode.ON_X)
			slot.addSlot(finder.getMinX(), finder.getMaxX());
		else
			slot.addSlot(finder.getMinY(), finder.getMaxY());

	}

	private void drawEllipse(double x, double y, UEllipse shape) {
		if (mode == CompressionMode.ON_X)
			slot.addSlot(x, x + shape.getWidth());
		else
			slot.addSlot(y, y + shape.getHeight());

	}

	private void drawPolygon(double x, double y, UPolygon shape) {
		if (mode == shape.getCompressionMode())
			return;

		if (mode == CompressionMode.ON_X)
			slot.addSlot(x + shape.getMinX(), x + shape.getMaxX());
		else
			slot.addSlot(y + shape.getMinY(), y + shape.getMaxY());

	}

	private void drawRectangle(double x, double y, URectangle shape) {
		if (mode == CompressionMode.ON_X)
			slot.addSlot(x, x + shape.getWidth());
		else
			slot.addSlot(y, y + shape.getHeight());

	}

	public ColorMapper getColorMapper() {
		return ColorMapper.IDENTITY;
	}

	public SlotSet getSlotSet() {
		return slot;
	}

}