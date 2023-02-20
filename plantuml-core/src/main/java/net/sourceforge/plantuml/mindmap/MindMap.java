package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.Rankdir;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.NoStyleAvailableException;

public class MindMap implements UDrawable {

	private final Branch regular = new Branch();
	private final Branch reverse = new Branch();

	private final ISkinParam skinParam;

	public MindMap(ISkinParam skinParam) {
		this.skinParam = skinParam;
	}

	private void computeFinger() {
		if (this.reverse.hasFinger() == false && this.regular.hasFinger() == false) {
			if (this.reverse.hasChildren())
				reverse.initFinger(skinParam, false);

			if (this.reverse.hasFinger() == false || this.regular.hasChildren())
				regular.initFinger(skinParam, true);

			if (this.reverse.hasFinger() && this.regular.hasFinger())
				this.reverse.doNotDrawFirstPhalanx();

		}
	}

	XDimension2D calculateDimension(StringBounder stringBounder) {
		this.computeFinger();
		final double y1 = this.regular.getHalfThickness(stringBounder);
		final double y2 = this.reverse.getHalfThickness(stringBounder);
		final double y = Math.max(y1, y2);

		final double width = this.reverse.getX12(stringBounder) + this.regular.getX12(stringBounder);
		final double height = y
				+ Math.max(this.reverse.getHalfThickness(stringBounder), this.regular.getHalfThickness(stringBounder));
		if (skinParam.getRankdir() == Rankdir.TOP_TO_BOTTOM)
			return new XDimension2D(height, width);
		else
			return new XDimension2D(width, height);

	}

	@Override
	public void drawU(UGraphic ug) {
		if (this.reverse.hasRoot() == false && this.regular.hasRoot() == false)
			return;

		this.computeFinger();

		final StringBounder stringBounder = ug.getStringBounder();
		final double y1 = this.regular.getHalfThickness(stringBounder);
		final double y2 = this.reverse.getHalfThickness(stringBounder);
		final double y = Math.max(y1, y2);

		final double x = this.reverse.getX12(stringBounder);
		if (skinParam.getRankdir() == Rankdir.TOP_TO_BOTTOM)
			ug = ug.apply(new UTranslate(y, x));
		else
			ug = ug.apply(new UTranslate(x, y));
		this.regular.drawU(ug);
		this.reverse.drawU(ug);
	}

	private int multiplier = 0;

	CommandExecutionResult addIdeaInternal(String stereotype, HColor backColor, int level, Display label,
			IdeaShape shape, boolean direction) {
		try {
			if (this.reverse.hasRoot() == false && this.regular.hasRoot() == false)
				level = 0;

			if (level == 0) {
				this.regular.initRoot(skinParam.getCurrentStyleBuilder(), backColor, label, shape, stereotype);
				this.reverse.initRoot(skinParam.getCurrentStyleBuilder(), backColor, label, shape, stereotype);
				return CommandExecutionResult.ok();
			}

			if (multiplier == 0)
				multiplier = level;
			assert multiplier > 0;

			if (level % multiplier != 0)
				return CommandExecutionResult.error("Bad indentation");

			level /= multiplier;
			if (direction == false)
				return this.reverse.add(skinParam.getCurrentStyleBuilder(), backColor, level, label, shape, stereotype);

			return this.regular.add(skinParam.getCurrentStyleBuilder(), backColor, level, label, shape, stereotype);
		} catch (NoStyleAvailableException e) {
			// Logme.error(e);
			return CommandExecutionResult.error("General failure: no style available.");
		}
	}

	boolean isFull(int level) {
		return level == 0 && this.regular.hasRoot();
	}

}
