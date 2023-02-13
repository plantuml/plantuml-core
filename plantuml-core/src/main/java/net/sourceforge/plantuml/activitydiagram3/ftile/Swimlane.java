package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Set;

import net.sourceforge.plantuml.SpecificBackcolorable;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.geom.MinMax;

public class Swimlane implements SpecificBackcolorable, Comparable<Swimlane> {

	private final String name;
	private final int order;
	private Display display;

	private UTranslate translate = new UTranslate();
	private double actualWidth;

	public Swimlane(String name, int order) {
		this.name = name;
		this.display = Display.getWithNewlines(name);
		this.order = order;

	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display label) {
		this.display = label;
	}

	public final UTranslate getTranslate() {
		return translate;
	}

	public final void setTranslate(UTranslate translate) {
		this.translate = translate;
	}

	public final void setWidth(double actualWidth) {
		this.actualWidth = actualWidth;
	}

	public Colors getColors() {
		return colors;
	}

	public void setSpecificColorTOBEREMOVED(ColorType type, HColor color) {
		if (color != null) {
			this.colors = colors.add(type, color);
		}
	}

	private Colors colors = Colors.empty();

	public final double getActualWidth() {
		return actualWidth;
	}

	public void setColors(Colors colors) {
		this.colors = colors;
	}

	private MinMax minMax;

	public void setMinMax(MinMax minMax) {
		this.minMax = minMax;

	}

	public MinMax getMinMax() {
		return minMax;
	}

	@Override
	public int compareTo(Swimlane other) {
		return Integer.compare(this.order, other.order);
	}

	public boolean isSmallerThanAllOthers(Set<Swimlane> others) {
		if (others.size() == 1 && others.contains(this))
			return false;
		for (Swimlane sw : others)
			if (sw.compareTo(this) < 0)
				return false;
		return true;
	}
}
