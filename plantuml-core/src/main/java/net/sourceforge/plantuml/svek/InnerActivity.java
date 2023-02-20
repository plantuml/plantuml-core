package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public final class InnerActivity extends AbstractTextBlock implements IEntityImage {

	private final IEntityImage im;
	private final HColor borderColor;
	private final double shadowing;
	private final HColor backColor;

	public InnerActivity(final IEntityImage im, HColor borderColor, HColor backColor, double shadowing) {
		this.im = im;
		this.backColor = backColor;
		this.borderColor = borderColor;
		this.shadowing = shadowing;
	}

	public final static double THICKNESS_BORDER = 1.5;

	public void drawU(UGraphic ug) {
		final XDimension2D total = calculateDimension(ug.getStringBounder());

		ug = ug.apply(backColor.bg()).apply(borderColor).apply(new UStroke(THICKNESS_BORDER));
		final URectangle rect = new URectangle(total.getWidth(), total.getHeight()).rounded(IEntityImage.CORNER);
		rect.setDeltaShadow(shadowing);
		ug.draw(rect);
		ug = ug.apply(new UStroke());
		im.drawU(ug);
	}

	public HColor getBackcolor() {
		return im.getBackcolor();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D img = im.calculateDimension(stringBounder);
		return img;
	}

	public ShapeType getShapeType() {
		return ShapeType.ROUND_RECTANGLE;
	}

	public Margins getShield(StringBounder stringBounder) {
		return Margins.NONE;
	}

	public boolean isHidden() {
		return im.isHidden();
	}

	public double getOverscanX(StringBounder stringBounder) {
		return 0;
	}

}
