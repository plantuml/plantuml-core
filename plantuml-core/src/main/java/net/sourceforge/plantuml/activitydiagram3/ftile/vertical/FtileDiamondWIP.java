package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.style.Styleable;

abstract class FtileDiamondWIP extends AbstractFtile implements Styleable {

	protected final HColor backColor;
	protected final HColor borderColor;
	protected final Swimlane swimlane;

	protected final TextBlock label;

	protected final TextBlock north;
	protected final TextBlock south;
	protected /* final */ TextBlock west;
	protected /* final */ TextBlock east;

	protected final double shadowing;

	public void swapEastWest() {
		final TextBlock tmp = this.west;
		this.west = this.east;
		this.east = tmp;

	}

	final public StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activity, SName.diamond);
	}

	final public Style getStyle() {
		return getStyleSignature().getMergedStyle(skinParam().getCurrentStyleBuilder());
	}

	@Override
	final public Collection<Ftile> getMyChildren() {
		return Collections.emptyList();
	}

	protected FtileDiamondWIP(TextBlock label, ISkinParam skinParam, HColor backColor, HColor borderColor,
			Swimlane swimlane, TextBlock north, TextBlock south, TextBlock east, TextBlock west) {
		super(skinParam);
		Style style = getStyleSignature().getMergedStyle(skinParam.getCurrentStyleBuilder());
		this.borderColor = borderColor;
		this.backColor = backColor;
		this.shadowing = style.value(PName.Shadowing).asDouble();

		this.swimlane = swimlane;

		this.label = label;
		this.north = north;
		this.west = west;
		this.east = east;
		this.south = south;
	}

	final public Set<Swimlane> getSwimlanes() {
		if (swimlane == null)
			return Collections.emptySet();

		return Collections.singleton(swimlane);
	}

	final public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	final public Swimlane getSwimlaneOut() {
		return swimlane;
	}

}
