package net.sourceforge.plantuml.classdiagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public class FullLayout implements UDrawable {

	private final List<RowLayout> all = new ArrayList<>();

	public void addRowLayout(RowLayout rawLayout) {
		this.all.add(rawLayout);
	}

	public void drawU(UGraphic ug) {
		double y = 0;
		for (RowLayout rawLayout : all) {
			rawLayout.drawU(ug.apply(UTranslate.dy(y)));
			y += rawLayout.getHeight(ug.getStringBounder()) + 20;
		}

	}

}
