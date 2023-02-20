package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.SName;

public abstract class USymbol {

	abstract public SName getSName();

	public abstract TextBlock asSmall(TextBlock name, TextBlock label, TextBlock stereotype, Fashion symbolContext,
			HorizontalAlignment stereoAlignment);

	public abstract TextBlock asBig(TextBlock label, HorizontalAlignment labelAlignment, TextBlock stereotype,
			double width, double height, Fashion symbolContext, HorizontalAlignment stereoAlignment);

	static class Margin {
		private final double x1;
		private final double x2;
		private final double y1;
		private final double y2;

		Margin(double x1, double x2, double y1, double y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}

		double getWidth() {
			return x1 + x2;
		}

		double getHeight() {
			return y1 + y2;
		}

		public XDimension2D addDimension(XDimension2D dim) {
			return new XDimension2D(dim.getWidth() + x1 + x2, dim.getHeight() + y1 + y2);
		}

		public double getX1() {
			return x1;
		}

		public double getY1() {
			return y1;
		}
	}

	public int suppHeightBecauseOfShape() {
		return 0;
	}

	public int suppWidthBecauseOfShape() {
		return 0;
	}

}
