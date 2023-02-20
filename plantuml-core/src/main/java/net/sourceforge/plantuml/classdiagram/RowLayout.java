package net.sourceforge.plantuml.classdiagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public class RowLayout implements UDrawable {

	private final List<TextBlock> all = new ArrayList<>();

	public void addLeaf(TextBlock entityImageClass) {
		this.all.add(entityImageClass);
	}

	public double getHeight(StringBounder stringBounder) {
		double y = 0;
		for (TextBlock leaf : all) {
			y = Math.max(y, leaf.calculateDimension(stringBounder).getHeight());
		}
		return y;
	}

	public void drawU(UGraphic ug) {
		double x = 0;
		for (TextBlock leaf : all) {
			leaf.drawU(ug.apply(UTranslate.dx(x)));
			x += leaf.calculateDimension(ug.getStringBounder()).getWidth() + 20;
		}

	}

}
