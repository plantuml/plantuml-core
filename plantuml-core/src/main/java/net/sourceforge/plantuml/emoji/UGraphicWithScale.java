package net.sourceforge.plantuml.emoji;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;

public class UGraphicWithScale {

	final private UGraphic ug;
	final private AffineTransform at;
	final private double angle;
	final private double scale;

	public UGraphicWithScale(UGraphic ug, double scale) {
		this(ug, AffineTransform.getScaleInstance(scale, scale), 0, scale);
	}

	private UGraphicWithScale(UGraphic ug, AffineTransform at, double angle, double scale) {
		this.ug = ug;
		this.at = at;
		this.angle = angle;
		this.scale = scale;
	}

	public UGraphic getUg() {
		return ug;
	}

	public UGraphicWithScale apply(UChange change) {
		return new UGraphicWithScale(ug.apply(change), at, angle, scale);
	}

	public UGraphicWithScale applyScale(double changex, double changey) {
		if (changex != changey)
			throw new IllegalArgumentException();
		final AffineTransform copy = new AffineTransform(at);
		copy.scale(changex, changey);
		return new UGraphicWithScale(ug, copy, angle, 1 * changex);
	}

	public void draw(UShape shape) {
		ug.draw(shape);
	}

	public UGraphicWithScale applyRotate(double delta_angle, double x, double y) {
		final AffineTransform copy = new AffineTransform(at);
		copy.rotate(delta_angle * Math.PI / 180, x, y);
		return new UGraphicWithScale(ug, copy, this.angle + delta_angle, this.scale);
	}

	public UGraphicWithScale applyTranslate(double x, double y) {
		final AffineTransform copy = new AffineTransform(at);
		copy.translate(x, y);
		return new UGraphicWithScale(ug, copy, angle, this.scale);
	}

	public AffineTransform getAffineTransform() {
		return at;
	}

	public UGraphicWithScale applyMatrix(double v1, double v2, double v3, double v4, double v5, double v6) {
		final AffineTransform copy = new AffineTransform(at);
		copy.concatenate(new AffineTransform(new double[] { v1, v2, v3, v4, v5, v6 }));
		return new UGraphicWithScale(ug, copy, angle, this.scale);
	}

	public final double getAngle() {
		return angle;
	}

	public double getScale() {
		return scale;
	}

}
