package net.sourceforge.plantuml.svek.image;

import static net.sourceforge.plantuml.utils.ObjectUtils.instanceOfAny;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.UBackground;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicNo;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.UEmpty;
import net.sourceforge.plantuml.klimt.shape.UHorizontalLine;
import net.sourceforge.plantuml.klimt.shape.UImage;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.klimt.shape.UText;

public class Footprint {

	private final StringBounder stringBounder;

	public Footprint(StringBounder stringBounder) {
		this.stringBounder = stringBounder;

	}

	class MyUGraphic extends UGraphicNo {

		private final List<XPoint2D> all;

		public MyUGraphic() {
			super(stringBounder, new UTranslate());
			this.all = new ArrayList<>();
		}

		private MyUGraphic(MyUGraphic other, UChange change) {
			// super(other, change);
			super(other.getStringBounder(),
					change instanceof UTranslate ? other.getTranslate().compose((UTranslate) change)
							: other.getTranslate());
			if (!instanceOfAny(change, UBackground.class, HColor.class, UStroke.class, UTranslate.class))
				throw new UnsupportedOperationException(change.getClass().toString());

			this.all = other.all;
		}

		public UGraphic apply(UChange change) {
			return new MyUGraphic(this, change);
		}

		public void draw(UShape shape) {
			final double x = getTranslate().getDx();
			final double y = getTranslate().getDy();
			if (shape instanceof UText) {
				drawText(x, y, (UText) shape);
			} else if (shape instanceof UHorizontalLine) {
				// Definitively a Horizontal line
//				line.drawTitleInternalForFootprint(this, x, y);
			} else if (shape instanceof ULine) {
				// Probably a Horizontal line
			} else if (shape instanceof UImage) {
				drawImage(x, y, (UImage) shape);
			} else if (shape instanceof UPath) {
				drawPath(x, y, (UPath) shape);
			} else if (shape instanceof URectangle) {
				drawRectangle(x, y, (URectangle) shape);
			} else if (shape instanceof UEllipse) {
				drawEllipse(x, y, (UEllipse) shape);
			} else if (shape instanceof UEmpty) {
				drawEmpty(x, y, (UEmpty) shape);
			} else {
				throw new UnsupportedOperationException(shape.getClass().toString());
			}
		}

		public ColorMapper getColorMapper() {
			return ColorMapper.IDENTITY;
		}

		private void addPoint(double x, double y) {
			all.add(new XPoint2D(x, y));
		}

		private void drawText(double x, double y, UText text) {
			final XDimension2D dim = getStringBounder().calculateDimension(text.getFontConfiguration().getFont(),
					text.getText());
			y -= dim.getHeight() - 1.5;
			addPoint(x, y);
			addPoint(x, y + dim.getHeight());
			addPoint(x + dim.getWidth(), y);
			addPoint(x + dim.getWidth(), y + dim.getHeight());
		}

		private void drawImage(double x, double y, UImage image) {
			addPoint(x, y);
			addPoint(x, y + image.getHeight());
			addPoint(x + image.getWidth(), y);
			addPoint(x + image.getWidth(), y + image.getHeight());
		}

		private void drawPath(double x, double y, UPath path) {
			addPoint(x + path.getMinX(), y + path.getMinY());
			addPoint(x + path.getMaxX(), y + path.getMaxY());
		}

		private void drawRectangle(double x, double y, URectangle rect) {
			addPoint(x, y);
			addPoint(x + rect.getWidth(), y + rect.getHeight());
		}

		private void drawEllipse(double x, double y, UEllipse rect) {
			addPoint(x, y);
			addPoint(x + rect.getWidth(), y + rect.getHeight());
		}

		private void drawEmpty(double x, double y, UEmpty rect) {
			addPoint(x, y);
			addPoint(x + rect.getWidth(), y + rect.getHeight());
		}
	}

	public ContainingEllipse getEllipse(UDrawable drawable, double alpha) {
		final MyUGraphic ug = new MyUGraphic();
		drawable.drawU(ug);
		final List<XPoint2D> all = ug.all;
		final ContainingEllipse circle = new ContainingEllipse(alpha);
		for (XPoint2D pt : all)
			circle.append(pt);

		return circle;
	}

	// public void drawDebug(UGraphic ug, double dx, double dy, TextBlock text) {
	// final MyUGraphic mug = new MyUGraphic();
	// text.drawU(mug, dx, dy);
	// for (Point2D pt : mug.all) {
	// ug.draw(pt.getX(), pt.getY(), new URectangle(1, 1));
	// }
	//
	// }

}
